package com.csci448.fpmobileapp.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlin.Result // Use Kotlin's Result type

class AuthRepo {
    private val firebaseAuth: FirebaseAuth = Firebase.auth
    private val TAG = "AuthRepository"

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    suspend fun login(email: String, password: String): Result<FirebaseUser> {
        return try {
            Log.d(TAG, "Attempting Firebase login for: $email")
            val credential = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (credential.user != null) {
                Log.i(TAG, "Firebase login successful for: $email")
                Result.success(credential.user!!)
            } else {
                Log.w(TAG, "Firebase login failed: Null user after await for $email")
                Result.failure(Exception("Login failed: Unknown error"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Firebase login exception for $email: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun signup(email: String, password: String): Result<FirebaseUser> {
        return try {
            Log.d(TAG, "Attempting Firebase signup for: $email")
            val credential = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            if (credential.user != null) {
                Log.i(TAG, "Firebase signup successful for: $email, UID: ${credential.user!!.uid}")
                Result.success(credential.user!!)
            } else {
                Log.w(TAG, "Firebase signup failed: Null user after await for $email")
                Result.failure(Exception("Signup failed: Unknown error"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Firebase signup exception for $email: ${e.message}", e)
            Result.failure(e)
        }
    }

    fun logout() {
        Log.d(TAG, "Firebase logout called")
        firebaseAuth.signOut()
    }
}