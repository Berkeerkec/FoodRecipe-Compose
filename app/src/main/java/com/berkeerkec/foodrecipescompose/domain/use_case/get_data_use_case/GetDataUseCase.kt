package com.berkeerkec.foodrecipescompose.domain.use_case.get_data_use_case

import com.berkeerkec.foodrecipescompose.data.remote.dto.FoodRecipe
import com.berkeerkec.foodrecipescompose.domain.repository.RemoteDataSource
import com.berkeerkec.foodrecipescompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOError
import javax.inject.Inject

class GetDataUseCase @Inject constructor(
    private val repo : RemoteDataSource
) {
    fun executeGetRecipe(queries : Map<String,String>) : Flow<Resource<FoodRecipe>> = flow {
        try {
            emit(Resource.Loading())
            val data = repo.getRecipes(queries)
            emit(Resource.Success(data))

        }catch (e : IOError){
            emit(Resource.Error(null ,e.message ?: "No Internet Connection"))
        }catch (e : HttpException){
            emit(Resource.Error(null, e.message ?: "Error!"))
        }
    }
}