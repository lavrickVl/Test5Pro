package com.test.android.testtask.data

import android.util.Log
import com.test.android.testtask.core.Constants.TAG
import com.test.android.testtask.core.Resource
import com.test.android.testtask.data.local.AppDatabase
import com.test.android.testtask.data.local.entities.user.UserEntity
import com.test.android.testtask.data.remote.dto.user.UserLoginPost
import com.test.android.testtask.domain.model.UserBio
import com.test.android.testtask.domain.model.UserCredentials
import com.test.android.testtask.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val api: TestApi,
): Repository {

    override suspend fun login(userCredentials: UserCredentials): Resource<UserBio> {
        val phone_code_eu = userCredentials.phone_number.substring(0,4)
        val phone_number = userCredentials.phone_number.substring(4)
        Log.d(TAG, "login: $phone_code_eu")
        Log.d(TAG, "login: $phone_number")

        val userLoginPost = UserLoginPost(
            phone_code = phone_code_eu,
            phone_number = phone_number,
            password = userCredentials.password,
        )

        try {
            val response = api.postUserLogin(userLoginPost)


        if (response.isSuccessful){

            val user = response.body() ?: return Resource.Error(response.message())
            Log.d(TAG, "login user: $user")

            val userBio = UserBio(
                name = user.user.name,
                second_name = user.user.second_name,
                phone_code = user.user.phone_code,
                phone_number = user.user.phone_number,
            )

            db.userDao().saveUser(
                UserEntity(
                    0,
                    user.user.api_key,
                    user.user.app_brand_id,
                    user.user.avatar ?: "",
                    user.user.chat_id,
                    user.user.firebase_id,
                    user.user.location_type?: "",
                    user.user.name,
                    user.user.payout_reject_msg ?: "",
                    user.user.payout_status,
                    user.user.phone_code,
                    user.user.phone_number,
                    user.user.place ?: "",
                    user.user.referral ?: "",
                    user.user.referral_bonus,
                    user.user.second_name,
                    user.user.solar_staff,
                    user.user.type_blocked,
                    user.user.user_id,
                    user.user.user_type,
                )
            )

            return Resource.Success(userBio)

        } else {
            return Resource.Error(response.message())
        }

        } catch (ex: Exception){
            return Resource.Error(ex.message.toString())
        }
    }


    override suspend fun getUserBio(): UserBio {
        return db.userDao().getUserBio()
    }



}