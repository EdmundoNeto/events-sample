package com.edmundo.events.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edmundo.domain.model.Event
import com.edmundo.domain.repository.EventListRepository
import com.edmundo.events.utils.State
import kotlinx.coroutines.launch

class EventsViewModel(val repository: EventListRepository) : BaseViewModel() {

    val eventsList: MutableLiveData<List<Event>> = MutableLiveData()
    val noListFoundVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    fun validateData() {
        eventsList.value?.takeIf { it.isEmpty() }?.run {
            noListFoundVisibility.value = getVisibility(true)
        }
    }

    private fun clearEmptyWarning() {
        noListFoundVisibility.value = getVisibility(false)
    }

    fun getData() {
        clearEmptyWarning()

        setState(State.LOADING)

        viewModelScope.launch {
            try {
                eventsList.value = repository.getEvents()
                validateData()

                setState(State.SUCCESS)
            } catch (ex: Exception) {
                setState(State.ERROR)
            }
        }
    }
}