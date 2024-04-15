package com.selincengiz.news.data.repo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.selincengiz.news.common.Resource
import com.selincengiz.news.domain.repo.AuthRepo
import kotlinx.coroutines.tasks.await

class AuthRepoImpl(private val auth: FirebaseAuth) :AuthRepo{


    override suspend fun firebaseLogin(email: String, password: String): Resource<String> {

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


    override suspend fun firebaseRegister(email: String, password: String, name: String): Resource<String> {

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