package com.edmundo.events.viewmodel

import android.view.View
import com.edmundo.domain.repository.EventListRepository
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CheckInViewModelTest {

    private val repository: EventListRepository = mockk(relaxed = true)
    private lateinit var viewModel: CheckInViewModel

    @Before
    fun setup() {
        viewModel = CheckInViewModel(repository)
    }

    @Test
    fun `when email is empty invalid data message must be shown`() {
        viewModel.run {
            val name = "teste silva"
            val email = ""
            validateData(email, name)

            Assert.assertEquals(View.VISIBLE, invalidDataVisibility.value)
        }
    }

    @Test
    fun `when name is empty invalid data message must be shown`() {
        viewModel.run {
            val name = ""
            val email = "teste@teste.com"
            validateData(email, name)

            Assert.assertEquals(View.VISIBLE, invalidDataVisibility.value)
        }
    }

    @Test
    fun `when name and email arent empty invalid data message must be hidden`() {
        viewModel.run {
            val name = "teste1234"
            val email = "teste@teste.com"
            validateData(email, name)

            Assert.assertEquals(View.GONE, invalidDataVisibility.value)
        }
    }
}