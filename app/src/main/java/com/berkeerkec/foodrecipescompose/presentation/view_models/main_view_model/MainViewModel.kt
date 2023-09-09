package com.berkeerkec.foodrecipescompose.presentation.view_models.main_view_model

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.berkeerkec.foodrecipescompose.data.local.RecipesEntity
import com.berkeerkec.foodrecipescompose.data.remote.dto.FoodRecipe
import com.berkeerkec.foodrecipescompose.domain.repository.LocalDataSource
import com.berkeerkec.foodrecipescompose.domain.use_case.get_data_use_case.GetDataUseCase
import com.berkeerkec.foodrecipescompose.util.Constant
import com.berkeerkec.foodrecipescompose.util.Constant.Companion.DEFAULT_DIET_TYPE
import com.berkeerkec.foodrecipescompose.util.Constant.Companion.DEFAULT_MEAL_TYPE
import com.berkeerkec.foodrecipescompose.util.Constant.Companion.DEFAULT_RECIPES_NUMBER
import com.berkeerkec.foodrecipescompose.util.Constant.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.berkeerkec.foodrecipescompose.util.Constant.Companion.QUERY_API_KEY
import com.berkeerkec.foodrecipescompose.util.Constant.Companion.QUERY_DIET
import com.berkeerkec.foodrecipescompose.util.Constant.Companion.QUERY_FILL_INGREDIENTS
import com.berkeerkec.foodrecipescompose.util.Constant.Companion.QUERY_NUMBER
import com.berkeerkec.foodrecipescompose.util.Constant.Companion.QUERY_TYPE
import com.berkeerkec.foodrecipescompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val localRepo : LocalDataSource,
    private val application : Application
): AndroidViewModel(application){

    /** Room Database **/
    private val _readRecipe = mutableStateOf<ReadRecipeState>(ReadRecipeState())
    val readRecipe : State<ReadRecipeState> = _readRecipe

    private fun insertRecipes(recipesEntity: RecipesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepo.insertRecipes(recipesEntity)
        }
    }

    private fun readRecipes(){
        localRepo.readDatabase().onEach {

            _readRecipe.value = ReadRecipeState(it)

        }.launchIn(viewModelScope)
    }


    /** Retrofit **/
    private val _state = mutableStateOf<GetRecipeState>(GetRecipeState())
    val state : State<GetRecipeState> = _state

    init {
        getRecipes(applyQueries())
        readRecipes()
    }

    private fun applyQueries() : HashMap<String,String>{
        val queries : HashMap<String,String> = HashMap()

        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = Constant.API_KEY
        queries[QUERY_TYPE] = DEFAULT_MEAL_TYPE
        queries[QUERY_DIET] = DEFAULT_DIET_TYPE
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

    fun getRecipes(queries : Map<String,String>){

        if (hasInternetConnection()){
            getDataUseCase.executeGetRecipe(queries).onEach {

                when(it){
                    is Resource.Success -> {
                        _state.value = GetRecipeState(recipes = it.data)

                        val foodRecipe = _state.value.recipes
                        if (foodRecipe != null){
                            offlineCacheRecipes(foodRecipe)
                        }
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = it.message ?: "Error!")
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                }

            }.launchIn(viewModelScope)
        }else{
            Resource.Error(null, "No Internet Connection!")
        }

    }

    private fun offlineCacheRecipes(foodRecipe: FoodRecipe) {
        val entity = RecipesEntity(foodRecipe)
        insertRecipes(entity)
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}