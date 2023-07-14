package com.dhxxn.dogterestapp.view.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhxxn.dogterestapp.view.base.BaseUiAction
import com.dhxxn.dogterestapp.view.base.BaseUiState
import com.dhxxn.dogterestapp.view.base.BaseViewModel
import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : BaseViewModel() {

    val state: SearchContract.SearchState
        get() = state()

    private var job: Job? = null

    private fun requestSearchKeyword(keyword: String) {
        if (job != null && job?.isActive == true) return

        job = viewModelScope.launch {
            val response = searchUseCase.requestSearchKeyword(keyword)
            when (response) {
                is NetworkResponse.Success -> {
                    state.searchResult.sendState {
                        response.body.list
                    }
                }

                else -> {
                    Log.d("SearchViewModel", "!!! error: ${response}")
                }
            }
        }
    }

    private fun inputKeyword(keyword: String) {
        state.searchKeyword.sendState { keyword }
    }

    private fun getBreedList() {
        val breedList = arrayListOf<String>(
            "affenpinscher", "african", "airedale", "akita", "appenzeller", "basenji", "beagle", "bluetick", "borzoi", "bouvier", "boxer", "brabancon", "briard", "buhund", "bulldog", "bullterrier", "cattledog", "chihuahua", "chow", "clumber", "cockapoo", "collie", "coonhound", "corgi", "cotondetulear",
            "dachshund", "dalmatian", "dane", "deerhound", "dhole", "dingo", "doberman", "elkhound", "entlebucher", "eskimo",
            "finnish", "frise", "germanshepherd", "greyhound", "groenendael", "havanese", "hound", "husky",
            "keeshond", "kelpie", "komondor", "kuvasz", "labradoodle", "labrador", "leonberg", "lhasa", "malamute", "malinois", "maltese", "mastiff",
            "mexicanhairless", "mix", "mountain", "newfoundland", "otterhound", "ovcharka", "papillon", "pekinese", "pembroke", "pinscher",
            "pitbull", "pointer", "pomeranian", "poodle", "pug", "puggle", "pyrenees", "redbone",
            "retriever", "golden", "ridgeback", "rottweiler", "saluki", "samoyed", "schipperke", "schnauzer", "segugio", "setter", "sharpei", "sheepdog", "shiba", "shihtzu", "spaniel", "spitz",
            "springer", "stbernard", "terrier", "tervuren", "vizsla", "waterdog", "weimaraner", "whippet", "wolfhound")

        val randomNumList = (0..breedList.size).shuffled().take(10)
        val randomBreedList = arrayListOf<String>()
        randomNumList.forEach { _index ->
            randomBreedList.add(breedList[_index])
        }
        state.tagList.sendState { randomBreedList }
    }

    init {
        initialData()
        loadData()
    }

    override fun loadData() {
        getBreedList()
    }

    override fun initialData() {
        state.tagList.sendState { emptyList() }
    }

    override fun handleEvents(action: BaseUiAction) {
        when (action) {
            is SearchContract.SearchAction.ClickBreed -> {
                requestSearchKeyword(action.breed)
            }
        }
    }

    override fun initialState(): BaseUiState {
        return SearchContract.SearchState(
            searchKeyword = mutableDogStateOf(""),
            tagList = mutableDogStateListOf(emptyList()),
            searchResult = mutableDogStateListOf(emptyList())
        )
    }
}