package com.test.android.testtask.di

import android.content.Context
import com.test.android.testtask.core.Constants.BASE_URL
import com.test.android.testtask.data.RepositoryImpl
import com.test.android.testtask.data.TestApi
import com.test.android.testtask.data.local.AppDatabase
import com.test.android.testtask.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext appContext: Context): TestApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL ?: "")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TestApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.invoke(context = appContext)
    }

    @Singleton
    @Provides
    fun provideRepository(
        db: AppDatabase,
        api: TestApi
    ): Repository {
        return RepositoryImpl(
            db = db,
            api = api
        )
    }

}