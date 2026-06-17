package com.rital.warehouse.di

import com.rital.warehouse.data.repository.ProductRepositoryImpl
import com.rital.warehouse.data.repository.UserRepositoryImpl
import com.rital.warehouse.domain.repository.ProductRepository
import com.rital.warehouse.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}