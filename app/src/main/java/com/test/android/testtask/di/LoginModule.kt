package com.test.android.testtask.di

import com.test.android.testtask.data.RepositoryImpl
import com.test.android.testtask.domain.usecases.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
internal object LoginModule {

    @ViewModelScoped
    @Provides
    fun provideLoginUseCase(
        repository: RepositoryImpl
    ): LoginUseCase {
        return LoginUseCase(repository = repository)
    }

}
