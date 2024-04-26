package vn.hoanguyen.compose.onlinestore.data_providers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthService @Inject constructor() {

    suspend fun isLoggedIn() = false // no further - just for test UI

    suspend fun signInWithEmailAndPassword(email: String, password: String): Result<Unit> {
        return try {
            withContext(Dispatchers.IO) {
                delay(2_000L)
                Result.success(Unit)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUpWithEmailAndPassword(email: String, password: String): Result<Unit> {
        return try {
            withContext(Dispatchers.IO) {
                delay(2_000L)
                Result.success(Unit)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logOut(): Result<Unit> {
        return try {
            withContext(Dispatchers.IO) {
                delay(1_000L)
                Result.success(Unit)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}