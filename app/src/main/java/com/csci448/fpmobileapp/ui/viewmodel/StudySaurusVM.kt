package com.csci448.fpmobileapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csci448.fpmobileapp.data.repos.AuthRepo
import com.csci448.fpmobileapp.data.repos.ItemRepo
import com.csci448.fpmobileapp.data.daos.ItemsDao
import com.csci448.fpmobileapp.data.Saurus
import com.csci448.fpmobileapp.data.SaurusPreferenceKeys
import com.csci448.fpmobileapp.data.repos.SaurusSettingsRepo
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.data.ShopItem
import com.csci448.fpmobileapp.data.Task
import com.csci448.fpmobileapp.data.daos.TaskDao
import com.csci448.fpmobileapp.data.UserProfile
import com.csci448.fpmobileapp.data.repos.UserRepo
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * the ViewModel for our app
 *
 * TODO:
 *  the whole thing
 */
class StudySaurusVM(private val mySaurus: Saurus, private val taskDao: TaskDao, private val itemsDao: ItemsDao, private val saurusSettingsRepository: SaurusSettingsRepo, private val authRepository: AuthRepo,
                    private val userRepository: UserRepo
) : ViewModel() {
    private val _currentSaurus = mutableStateOf(mySaurus.copy())
    val currentSaurusState: State<Saurus> = _currentSaurus

    // --- Authentication State ---
    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser.asStateFlow()

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile.asStateFlow()

    private val _authError = MutableStateFlow<String?>(null)
    val authError: StateFlow<String?> = _authError.asStateFlow()

    private val _isLoadingAuth = MutableStateFlow(false)
    val isLoadingAuth: StateFlow<Boolean> = _isLoadingAuth.asStateFlow()

    val navBarColorKey: StateFlow<String> = saurusSettingsRepository.navBarColorKeyFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), SaurusPreferenceKeys.DEFAULT_NAV_BAR_COLOR_KEY)

    val appThemeKey: StateFlow<String> = saurusSettingsRepository.appThemeKeyFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), SaurusPreferenceKeys.DEFAULT_APP_THEME_KEY)

    val selectableNavBarColors = mapOf(
        // Key "Default" is handled by the UI now
        "Blue" to Color.Blue.copy(alpha = 0.7f), // Example colors
        "Green" to Color.Green.copy(alpha = 0.7f),
        "Purple" to Color(0xFFBB86FC).copy(alpha = 0.7f) // Example purple
        // Add other selectable colors here
    )


    // --- Update Settings Functions ---
    fun updateNavBarColor(colorKey: String) {
        // Allow setting "Default" or any key present in the map
        if (colorKey == SaurusPreferenceKeys.DEFAULT_NAV_BAR_COLOR_KEY || selectableNavBarColors.containsKey(colorKey)) {
            viewModelScope.launch {
                saurusSettingsRepository.updateNavBarColorKey(colorKey)
            }
        } else {
            Log.w("VM_SETTINGS", "Attempted to set invalid nav bar color key: $colorKey")
        }
    }

    fun updateAppTheme(themeKey: String) {
        // Add validation if needed ("Light", "Dark", "System")
        viewModelScope.launch {
            saurusSettingsRepository.updateAppThemeKey(themeKey)
        }
    }

    init {
        viewModelScope.launch {
            val existing = itemsDao.getAllItemsOnce()
            if (existing.isEmpty()) {
                Log.d("VM_INIT", "Database empty, inserting initial items.")
                // Add all items from ItemRepo
                itemsDao.insertItem(ItemRepo.topHat)
                itemsDao.insertItem(ItemRepo.crown)
                itemsDao.insertItem(ItemRepo.belt)
                itemsDao.insertItem(ItemRepo.skirt)
                itemsDao.insertItem(ItemRepo.necklace)
                itemsDao.insertItem(ItemRepo.bowtie)
                itemsDao.insertItem(ItemRepo.partyHat)
                // Add any other default items here
            } else {
                Log.d("VM_INIT", "Database already has items.")
            }
        }

        _currentUser.value = authRepository.getCurrentUser()
        _currentUser.value?.uid?.let { loadUserProfile(it) }
        Log.d("VM_INIT", "Initial Check - Current User: ${_currentUser.value?.email}")

        saurusSettingsRepository.saurusPreferencesFlow
            .onEach { preferences ->
                Log.d("VM_SAURUS_LOAD", "DataStore emitted: Hat=${preferences.hatId}, Neck=${preferences.neckwearId}, Belt=${preferences.beltId}")
                // Update the state with the loaded equipped item IDs
                // Keep other Saurus properties (like name, type) from initialSaurus
                _currentSaurus.value = _currentSaurus.value.copy(
                    hat = preferences.hatId,
                    neckWear = preferences.neckwearId,
                    belt = preferences.beltId
                )
            }
            .launchIn(viewModelScope)
    }

    // --- Auth Functions ---
    fun loginUser(email: String, password: String) {
        if (_isLoadingAuth.value) return
        viewModelScope.launch {
            _isLoadingAuth.value = true
            _authError.value = null
            Log.d("VM_AUTH", "Attempting login for $email")
            val result = authRepository.login(email, password) // Call repository
            if (result.isSuccess) {
                val user = result.getOrNull()
                _currentUser.value = user
                user?.uid?.let { loadUserProfile(it) } // Load profile on success
                Log.i("VM_AUTH", "Login SUCCESS for ${user?.email}")
            } else {
                _authError.value = result.exceptionOrNull()?.message ?: "Login failed"
                Log.w("VM_AUTH", "Login FAILED: ${_authError.value}")
                _currentUser.value = null // Ensure user is null on failure
                _userProfile.value = null
            }
            _isLoadingAuth.value = false
        }
    }

    fun signupUser(email: String, username: String, password: String) {
        if (_isLoadingAuth.value) return
        // Basic validation (add more robust validation)
        if (email.isBlank() || username.isBlank() || password.isBlank()) {
            _authError.value = "Email, Username, and Password cannot be empty."
            return
        }
        viewModelScope.launch {
            _isLoadingAuth.value = true
            _authError.value = null
            Log.d("VM_AUTH", "Attempting signup for $email / $username")
            val authResult = authRepository.signup(email, password) // Call repo
            if (authResult.isSuccess) {
                val firebaseUser = authResult.getOrNull()
                if (firebaseUser != null) {
                    // Create profile in Firestore
                    val profileResult = userRepository.createUserProfile(firebaseUser.uid, email, username)
                    if (profileResult.isSuccess) {
                        Log.i("VM_AUTH", "Signup SUCCESS, profile created.")
                        _currentUser.value = firebaseUser
                        loadUserProfile(firebaseUser.uid) // Load the new profile
                    } else {
                        _authError.value = profileResult.exceptionOrNull()?.message ?: "Failed to create profile"
                        Log.w("VM_AUTH", "Signup OK, but Profile FAILED: ${_authError.value}")
                        // Consider deleting the auth user here if profile fails, or provide retry
                        _currentUser.value = null // Don't consider logged in if profile fails
                        _userProfile.value = null
                    }
                } else {
                    _authError.value = "Signup failed: No user returned."
                    Log.w("VM_AUTH", "Signup FAILED: No user returned.")
                }
            } else {
                _authError.value = authResult.exceptionOrNull()?.message ?: "Signup failed"
                Log.w("VM_AUTH", "Signup FAILED: ${_authError.value}")
            }
            _isLoadingAuth.value = false
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            Log.d("VM_AUTH", "Logging out user: ${_currentUser.value?.email}")
            authRepository.logout()
            _currentUser.value = null
            _userProfile.value = null
            _authError.value = null
            Log.i("VM_AUTH", "User logged out.")
            // Decide if you want to reset Saurus state on logout
            // _currentSaurus.value = initialSaurus.copy() // Example reset
        }
    }

    // --- Profile Loading ---
    private fun loadUserProfile(uid: String) {
        viewModelScope.launch {
            Log.d("VM_PROFILE", "Loading profile for UID: $uid")
            val result = userRepository.getUserProfile(uid)
            if(result.isSuccess) {
                _userProfile.value = result.getOrNull()
                Log.d("VM_PROFILE", "Profile loaded: ${_userProfile.value?.username}")
            } else {
                Log.e("VM_PROFILE", "Failed to load profile: ${result.exceptionOrNull()?.message}")
                // Maybe set userProfile to null or keep previous state?
                _userProfile.value = null // Clear profile if loading fails
            }
        }
    }


    fun selectHat(id: Int) {
        // Only update state and save if the value actually changed
        if (_currentSaurus.value.hat != id) {
            _currentSaurus.value = _currentSaurus.value.copy(hat = id)
            viewModelScope.launch {
                saurusSettingsRepository.updateHatId(id) // Save to DataStore
            }
        }
    }

    fun selectNeckwear(id: Int) {
        if (_currentSaurus.value.neckWear != id) {
            _currentSaurus.value = _currentSaurus.value.copy(neckWear = id)
            viewModelScope.launch {
                saurusSettingsRepository.updateNeckwearId(id) // Save to DataStore
            }
        }
    }

    fun selectBelt(id: Int) {
        if (_currentSaurus.value.belt != id) {
            _currentSaurus.value = _currentSaurus.value.copy(belt = id)
            viewModelScope.launch {
                saurusSettingsRepository.updateBeltId(id) // Save to DataStore
            }
        }
    }



    val currentScreen: MutableState<SelectedScreen> = mutableStateOf(SelectedScreen.STARTUP)

    fun setCurrentScreen(screenSelection: SelectedScreen){
        currentScreen.value = screenSelection
    }

    val taskList: StateFlow<List<Task>> = taskDao
        .getAllTasks()
        .map { list -> list.filter { !it.archived } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val totalCoins: StateFlow<Int> = taskList
        .map { tasks -> tasks.filter { it.completed }.sumOf { it.coins } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    val archivedTasks: StateFlow<List<Task>> = taskDao
        .getArchivedTasks()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun archiveCompletedTasks() {
        viewModelScope.launch {
            taskDao.archiveCompletedTasks()
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insertTask(task)
        }
    }

    fun updateTask(newTask: Task) {
        viewModelScope.launch {
            val oldTask = taskDao.getTaskById(newTask.id) // Fetch previous state
            if (oldTask != null) {
                Log.d("VM_TASK", "Updating task: ${newTask.title}, Old completed: ${oldTask.completed}, New completed: ${newTask.completed}")
                // Check if completion state CHANGED
                if (!oldTask.completed && newTask.completed) {
                    // Task was just completed
                    saurusSettingsRepository.increaseCoinsEarned(newTask.coins)
                } else if (oldTask.completed && !newTask.completed) {
                    // Task was just deselected (mistake corrected)
                    saurusSettingsRepository.decreaseCoinsEarned(newTask.coins)
                }
            } else {
                Log.w("VM_TASK", "Could not find old task with id ${newTask.id} before updating.")
                // Decide handling: If oldTask is null, should completing it still grant coins?
                // Maybe only grant if !newTask.completed initially? For simplicity, we'll allow grant:
                if(newTask.completed) {
                    saurusSettingsRepository.increaseCoinsEarned(newTask.coins)
                    Log.w("VM_TASK", "Granted coins for newly completed task with no old record found.")
                }
            }
            // Update the task in the database regardless of coin logic
            taskDao.updateTask(newTask)
            Log.d("VM_TASK", "Task DB update called for ${newTask.title}")
        }
    }


    val allItems: StateFlow<List<ShopItem>> = itemsDao.getAllItems()
        .map { list ->
            // Log the raw list from the DAO
            Log.d("DATA_FLOW_DEBUG", "allItems Flow Emitted (Raw from DAO): ${list.size} items -> ${list.joinToString { it.name + "(id=${it.id}, owned=${it.owned})" }}")
            list // return original list
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // Optionally, you can also define a flow for shop items only:
    val shopItems: StateFlow<List<ShopItem>> = allItems
        .map { list -> list.filter { !it.owned } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun insertShopItem(item: ShopItem) {
        viewModelScope.launch {
            itemsDao.insertItem(item)
        }
    }

    // Owned items flow:
    val ownedItems: StateFlow<List<ShopItem>> = allItems
        .map { list ->
            // Log before filtering for owned
            Log.d("DATA_FLOW_DEBUG", "ownedItems Flow: Filtering ${list.size} items for owned status.")
            list.filter { it.owned }
        }
        .map { ownedList ->
            // Log the result after filtering
            Log.d("DATA_FLOW_DEBUG", "ownedItems Flow Emitted (Filtered): ${ownedList.size} items -> ${ownedList.joinToString { it.name + "(id=${it.id})" }}")
            ownedList
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    //Shop Logic
    private val _coinsSpent = MutableStateFlow(0)

    val availableCoins: StateFlow<Long> = saurusSettingsRepository.totalCoinsEarnedFlow
        .combine(saurusSettingsRepository.totalCoinsSpentFlow) { earned, spent ->
            Log.d("VM_COINS", "Combining Earned ($earned) and Spent ($spent)")
            val difference = earned - spent
            maxOf(0L, difference) // Ensure coins don't go negative
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), 0L) // Start with 0

    fun purchaseItems(itemsToBuy: List<ShopItem>) {
        val totalCost = itemsToBuy.sumOf { it.price }
        // Check available coins using .value (current state)
        if (availableCoins.value < totalCost) {
            Log.w("VM_PURCHASE", "Purchase attempt failed: Not enough coins. Have ${availableCoins.value}, Need $totalCost")
            // TODO: Maybe trigger a UI event/message here?
            return
        }

        viewModelScope.launch {
            Log.d("VM_PURCHASE", "----- Purchase Start -----")
            Log.d("VM_PURCHASE","Attempting purchase, cost: $totalCost, available: ${availableCoins.value}")

            itemsToBuy.forEach { item ->
                val itemToUpdate = item.copy(owned = true)
                Log.d("VM_PURCHASE","Updating item: ${itemToUpdate.name} (id=${itemToUpdate.id}) to owned=true")
                itemsDao.updateItem(itemToUpdate) // Update DB first
            }

            // Update persisted spent coins *after* DB update succeeds
            saurusSettingsRepository.increaseCoinsSpent(totalCost)
            Log.d("VM_PURCHASE", "Purchase complete. Increased Spent Coins by $totalCost.")
            Log.d("VM_PURCHASE", "----- Purchase End -----")
            // Note: availableCoins flow will update automatically when totalCoinsSpentFlow emits new value
        }
    }
}