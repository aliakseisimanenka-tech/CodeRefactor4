package com.lsimanenka.coderefactor4.data

import com.lsimanenka.coderefactor4.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Binds
    abstract fun bindsUserRepository(impl: UserRepositoryImpl) : UserRepository

}