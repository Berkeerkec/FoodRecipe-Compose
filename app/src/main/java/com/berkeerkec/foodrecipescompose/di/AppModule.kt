package com.berkeerkec.foodrecipescompose.di

import com.berkeerkec.foodrecipescompose.data.remote.FoodRecipesApi
import com.berkeerkec.foodrecipescompose.data.repository.RemoteDataSourceImpl
import com.berkeerkec.foodrecipescompose.domain.repository.RemoteDataSource
import com.berkeerkec.foodrecipescompose.util.Constant.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideFoodRecipesApi(okHttpClient: OkHttpClient) : FoodRecipesApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodRecipesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(api : FoodRecipesApi) : RemoteDataSource{
        return RemoteDataSourceImpl(api)
    }
}