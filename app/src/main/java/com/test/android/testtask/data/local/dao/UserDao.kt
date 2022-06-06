package com.test.android.testtask.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.android.testtask.data.local.entities.user.UserEntity
import com.test.android.testtask.domain.model.UserBio

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(userEntity: UserEntity)

    @Query("select name,second_name,phone_code,phone_number from UserEntity")
    fun getUserBio(): UserBio
}