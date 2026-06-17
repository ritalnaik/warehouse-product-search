package com.rital.warehouse.di

import com.rital.warehouse.core.Constants
import com.rital.warehouse.data.remote.UserApi
import com.rital.warehouse.data.remote.WarehouseApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader(Constants.SUBSCRIPTION_KEY, Constants.SUBSCRIPTION_KEY_VALUE)
                    .build()
                chain.proceed(request)
            }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.HTTP_URL_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideProductApi(
        retrofit: Retrofit
    ): WarehouseApi = retrofit.create(WarehouseApi::class.java)

    @Provides
    @Singleton
    fun provideUserApi(
        retrofit: Retrofit
    ): UserApi = retrofit.create(UserApi::class.java)
}