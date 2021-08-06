package io.realworld.android.data

import io.realworld.api.ConduitClient
import io.realworld.api.models.entities.LoginData
import io.realworld.api.models.entities.SignupData
import io.realworld.api.models.entities.User
import io.realworld.api.models.requests.LoginRequest
import io.realworld.api.models.requests.SignupRequest

object UserRepo {

    val api= ConduitClient.publicApi
    val authAPI = ConduitClient.authApi

    suspend fun login(email: String, password: String)  : User? {

        val response = api.loginUser(LoginRequest(LoginData(email, password)))
        ConduitClient.authToken = response.body()?.user?.token
        return response.body()?.user
    }
    suspend fun signup(username: String, email: String, password: String): User? {
        val response = api.signupUser(SignupRequest(SignupData(
            email, password, username
        )))
        ConduitClient.authToken = response.body()?.user?.token

        return response.body()?.user

    }

    suspend fun getUserProfile() = authAPI.getCurrentUser().body()?.user

}