package com.edmundo.events.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edmundo.domain.repository.EventListRepository
import com.edmundo.events.extensions.isValidEmail
import com.edmundo.events.utils.State
import kotlinx.coroutines.launch

class CheckInViewModel(val repository: EventListRepository) : BaseViewModel() {

    val checkinFinished = MutableLiveData<Boolean>()
    val userName: MutableLiveData<String> = MutableLiveData()
    val userEmail: MutableLiveData<String> = MutableLiveData()

    val invalidDataVisibility = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    fun validateData(email: String, name: String): Boolean {
        val isEmailDataEmpty = !email.isValidEmail()
        val isNameEmpty = name.isEmpty()

        invalidDataVisibility.apply {
            value = when {
                isEmailDataEmpty || isNameEmpty -> getVisibility(true)
                else -> getVisibility(false)
            }
        }

        return !isEmailDataEmpty && !isNameEmpty

    }

    fun checkIn(eventId: String) {
        if(validateData(userEmail.value.orEmpty(), userName.value.orEmpty())) {
            setState(State.LOADING)

            viewModelScope.launch {
                try {
                    val name = userName.value.orEmpty()
                    val email = userEmail.value.orEmpty()

                    checkinFinished.value = true

                    repository.checkIn(eventId, name, email)

                    setState(State.SUCCESS)
                } catch (ex: Exception) {
                    setState(State.ERROR)
                }
            }
        }
    }

}