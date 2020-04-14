package com.edmundo.events.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edmundo.domain.model.Event
import com.edmundo.domain.repository.EventListRepository
import com.edmundo.events.extensions.toFormattedDate
import com.edmundo.events.utils.State
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class EventsDetailViewModel(val repository: EventListRepository) : BaseViewModel() {

    val event: MutableLiveData<Event> = MutableLiveData()
    val noEventFoundVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    fun validateData() {
        event.value?.takeIf { it.id.isEmpty() }?.run {
            noEventFoundVisibility.value = getVisibility(true)
        }
    }

    private fun clearEmptyWarning() {
        noEventFoundVisibility.value = getVisibility(false)
    }

    fun getEvent(eventId: String) {
        clearEmptyWarning()

        setState(State.LOADING)

        viewModelScope.launch {
            try {
                event.value = repository.getEventDetails(eventId)

                validateData()

                setState(State.SUCCESS)
            } catch (ex: Exception) {
                setState(State.ERROR)
            }
        }
    }
}