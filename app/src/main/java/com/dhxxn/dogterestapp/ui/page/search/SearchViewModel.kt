package com.dhxxn.dogterestapp.ui.page.search

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.dhxxn.dogterestapp.ui.base.BaseUiAction
import com.dhxxn.dogterestapp.ui.base.BaseUiState
import com.dhxxn.dogterestapp.ui.base.BaseViewModel
import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

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
        val breedList = arrayListOf<Pair<String, String>>(
            Pair("affenpinscher", "아펜핀셔"),
            Pair("african", "아프리카니스"),
            Pair("airedale", "에어데일 테리어"),
            Pair("akita", "아키타견"),
            Pair("basenji", "바센지"),
            Pair("beagle", "비글"),
            Pair("bluetick", "블루틱 쿤하운드"),
            Pair("borzoi", "보르조이"),
            Pair("bouvier", "부비에 데 플랑드르"),
            Pair("boxer", "박서견"),
            Pair("brabancon", "브라방견"),
            Pair("briard", "브리아드견"),
            Pair("buhund", "부훈드견"),
            Pair("bulldog", "불독"),
            Pair("bullterrier", "불테리어"),
            Pair("cattledog", "캐틀독"),
            Pair("chihuahua", "치와와"),
            Pair("chow", "차우차우"),
            Pair("clumber", "클럼버스파니엘"),
            Pair("cockapoo", "코카푸"),
            Pair("collie", "콜리견"),
            Pair("coonhound", "쿤하운드"),
            Pair("corgi", "웰시코기"),
            Pair("cotondetulear", "코튼드툴레아"),
            Pair("dachshund", "닥스훈트"),
            Pair("dalmatian", "달마시안"),
            Pair("dane", "대너"),
            Pair("deerhound", "디어하운드"),
            Pair("dhole", "들헐"),
            Pair("dingo", "딩고"),
            Pair("doberman", "도베르만"),
            Pair("elkhound", "엘크하운드"),
            Pair("entlebucher", "엔틀부처"),
            Pair("eskimo", "에스키모견"),
            Pair("finnish", "핀란드스피츠"),
            Pair("frise", "비숑프리제"),
            Pair("germanshepherd", "저먼셰퍼드견"),
            Pair("greyhound", "그레이하운드"),
            Pair("groenendael", "푸들"),
            Pair("hound", "하운드"),
            Pair("husky", "허스키"),
            Pair("keeshond", "키스혼드"),
            Pair("kelpie", "켈피"),
            Pair("komondor", "코몬도르"),
            Pair("kuvasz", "쿠바스"),
            Pair("labradoodle", "래브라도들플"),
            Pair("labrador", "래브라도레트리버"),
            Pair("leonberg", "레온베르거"),
            Pair("lhasa", " 라사압소"),
            Pair("malamute", "말라뮤트"),
            Pair("malinois", "말리노아"),
            Pair("maltese", "말티즈"),
            Pair("mastiff", "마스티프"),
            Pair("mexicanhairless", "멕시칸헤어리스"),
            Pair("mix", "혼종견"),
            Pair("mountain", "산악견"),
            Pair("newfoundland", "뉴펀들랜드"),
            Pair("otterhound", "오터하운드"),
            Pair("ovcharka", "오브차르카"),
            Pair("papillon", "파피용"),
            Pair("pekinese", "페키니즈"),
            Pair("pembroke", "펨브록"),
            Pair("pinscher", "핀셔"),
            Pair("pitbull", "피트불"),
            Pair("pointer", "포인터"),
            Pair("pomeranian", "포메라니안"),
            Pair("poodle", "푸들"),
            Pair("pug", "퍼그"),
            Pair("puggle", "퍼글"),
            Pair("pyrenees", "피레네"),
            Pair("redbone", "레드본"),
            Pair("retriever", "리트리버"),
            Pair("golden", "골든 리트리버"),
            Pair("ridgeback", "리지백"),
            Pair("rottweiler", "로트와일러"),
            Pair("saluki", "살루키"),
            Pair("samoyed", "사모예드"),
            Pair("schipperke", "슈퍼커크"),
            Pair("schnauzer", "슈나우저"),
            Pair("segugio", "세구지오 이탈리아노"),
            Pair("setter", "세터"),
            Pair("sharpei", "사페이"),
            Pair("sheepdog", "쉽독"),
            Pair("shiba", "시바견"),
            Pair("shihtzu", "시츄"),
            Pair("spaniel", "스패니얼"),
            Pair("spitz", "스피츠"),
            Pair("springer", "스프링어 스파니엘"),
            Pair("stbernard", "세인트 버나드"),
            Pair("terrier", "테리어"),
            Pair("tervuren", "테뷰렌"),
            Pair("vizsla", "비즐라"),
            Pair("waterdog", "워터독"),
            Pair("weimaraner", "바이마라너"),
            Pair("whippet", "휘핏"),
            Pair("wolfhound", "울프하운드")
        )

        val randomNumList = (0..breedList.size).shuffled().take(7)
        val randomBreedList = arrayListOf<Pair<String, String>>()
        randomNumList.forEach { _index ->
            randomBreedList.add(breedList[_index])
        }
        state.tagList.sendState { randomBreedList }

        val colorList = arrayListOf<Color>()
        for (i in 0..randomBreedList.size) {
            colorList.add(
                Color.Companion
                    .random()
                    .copy(alpha = 0.8f)
            )
        }
        state.colorList.sendState { colorList }

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
            is SearchContract.SearchAction.SearchBreed -> {
                if (state.screenType.value() == SEARCH_SCREEN_TYPE.DEFAULT) {
                    state.screenType.sendState { SEARCH_SCREEN_TYPE.RESULT }
                }
                state.searchKeyword.sendState { action.breed }
                requestSearchKeyword(action.breed)
            }

            is SearchContract.SearchAction.InputKeyword -> {
                inputKeyword(action.keyword)
            }

            is SearchContract.SearchAction.BackToDefault -> {
                state.screenType.sendState { SEARCH_SCREEN_TYPE.DEFAULT }
                state.searchKeyword.sendState { "" }
            }
        }
    }

    override fun initialState(): BaseUiState {
        return SearchContract.SearchState(
            searchKeyword = mutableDogStateOf(""),
            tagList = mutableDogStateListOf(emptyList()),
            searchResult = mutableDogStateListOf(emptyList()),
            colorList = mutableDogStateListOf(emptyList()),
            screenType = mutableDogStateOf(SEARCH_SCREEN_TYPE.DEFAULT)
        )
    }
}