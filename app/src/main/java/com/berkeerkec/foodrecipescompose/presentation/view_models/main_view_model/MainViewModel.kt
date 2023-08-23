package com.berkeerkec.foodrecipescompose.presentation.view_models.main_view_model

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkeerkec.foodrecipescompose.data.remote.dto.FoodRecipe
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
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val application : Application
): AndroidViewModel(application){

    private val _state = mutableStateOf<GetRecipeState>(GetRecipeState())
    val state : State<GetRecipeState> = _state



    init {
        getRecipes(applyQueries())
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