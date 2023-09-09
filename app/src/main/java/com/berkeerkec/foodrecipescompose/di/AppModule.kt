package com.berkeerkec.foodrecipescompose.di

import android.content.Context
import androidx.room.Room
import com.berkeerkec.foodrecipescompose.data.local.RecipesDao
import com.berkeerkec.foodrecipescompose.data.local.RecipesDatabase
import com.berkeerkec.foodrecipescompose.data.remote.FoodRecipesApi
import com.berkeerkec.foodrecipescompose.data.repository.LocalDataSourceImpl
import com.berkeerkec.foodrecipescompose.data.repository.RemoteDataSourceImpl
import com.berkeerkec.foodrecipescompose.domain.repository.LocalDataSource
import com.berkeerkec.foodrecipescompose.domain.repository.RemoteDataSource
import com.berkeerkec.foodrecipescompose.util.Constant.Companion.BASE_URL
import com.berkeerkec.foodrecipescompose.util.Constant.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun provideRecipesDatabase(@ApplicationContext context : Context) = Room.databaseBuilder(
        context,RecipesDatabase::class.java,DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRecipeDao(database : RecipesDatabase) = database.recipesDao()

    @Singleton
    @Provides
    fun provideLocalSource(dao : RecipesDao) : LocalDataSource{
        return LocalDataSourceImpl(dao)
    }
}