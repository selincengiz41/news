package com.selincengiz.news.data.repo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.selincengiz.news.common.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait

class AuthRepo(private val auth: FirebaseAuth) {


    suspend fun firebaseLogin(email: String, password: String): Resource<String> {

        return try {


            val exception = auth.signInWithEmailAndPassword(email, password).run {
                await()
                exception
            }

            if (exception != null) {
                Resource.Error(exception)
            } else {
                Resource.Success("Successfully logined")
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }

    }


    suspend fun firebaseRegister(email: String, password: String, name: String): Resource<String> {

        return try {
            val exception = auth.createUserWithEmailAndPassword(email, password).run {
                await()
                exception
            }

            if (exception != null) {
                Resource.Error(exception)
            } else {
                val profileUpdates = userProfileChangeRequest {
                    displayName = name
                }
                val exception2 = auth.currentUser!!.updateProfile(profileUpdates).run {
                    await()
                    getException()
                }
                if (exception2 != null) {
                    Resource.Error(exception2)
                } else {
                    Resource.Success("Successfully registered")
                }
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }

    }


}