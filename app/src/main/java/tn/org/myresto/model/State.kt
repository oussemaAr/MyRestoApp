package tn.org.myresto.model

sealed class State {
    class Success(val data: List<Restaurant>) : State()
    class Failure(val message: String) : State()
    object Loading : State()
}