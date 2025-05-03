package com.csci448.fpmobileapp.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlin.Result // Use Kotlin's Result type

class UserRepo {
    private val firestore: FirebaseFirestore = Firebase.firestore
    private val usersCollection = firestore.collection("users")
    private val TAG = "UserRepository"

    suspend fun createUserProfile(uid: String, email: String, username: String): Result<Unit> {
        val userProfile = UserProfile(uid = uid, email = email, username = username)
        return try {
            Log.d(TAG, "Creating Firestore profile for UID: $uid, Username: $username")
            usersCollection.document(uid).set(userProfile).await()
            Log.i(TAG, "Firestore profile created successfully for UID: $uid")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Firestore profile creation failed for UID: $uid: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun getUserProfile(uid: String): Result<UserProfile?> {
        return try {
            Log.d(TAG, "Fetching Firestore profile for UID: $uid")
            val documentSnapshot = usersCollection.document(uid).get().await()
            if (documentSnapshot.exists()) {
                val userProfile = documentSnapshot.toObject(UserProfile::class.java)
                Log.i(TAG, "Firestore profile fetched successfully for UID: $uid. Username: ${userProfile?.username}")
                Result.success(userProfile)
            } else {
                Log.w(TAG, "Firestore profile does not exist for UID: $uid")
                Result.success(null) // User exists in Auth but not Firestore? Or invalid UID passed.
            }
        } catch (e: Exception) {
            Log.e(TAG, "Firestore profile fetch failed for UID: $uid: ${e.message}", e)
            Result.failure(e)
        }
    }
}