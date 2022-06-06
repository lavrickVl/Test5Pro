package com.test.android.testtask.di

import com.test.android.testtask.data.RepositoryImpl
import com.test.android.testtask.domain.usecases.GetUserUseCase
import com.test.android.testtask.domain.usecases.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
internal object ProfileModule {

    @ViewModelScoped
    @Provides
    fun provideGetUserUseCase(
        repository: RepositoryImpl
    ): GetUserUseCase {
        return GetUserUseCase(repository = repository)
    }

}
