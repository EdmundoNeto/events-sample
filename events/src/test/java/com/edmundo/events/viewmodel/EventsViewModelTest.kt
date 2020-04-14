package com.edmundo.events.viewmodel

import android.view.View
import com.edmundo.domain.model.Event
import com.edmundo.domain.repository.EventListRepository
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EventsViewModelTest {

    private val repository: EventListRepository = mockk(relaxed = true)
    private lateinit var viewModel: EventsViewModel

    @Before
    fun setup() {
        viewModel = EventsViewModel(repository)
    }

    @Test
    fun `when there is no events then I must show a message`() {
        viewModel.run {
            eventsList.value = emptyList()
            validateData()

            Assert.assertEquals(View.VISIBLE, noListFoundVisibility.value)
        }
    }


    @Test
    fun `when eventsList is not empty then I must not show a message`() {
        val event: Event = mockk(relaxed = true)
        viewModel.run {
            eventsList.value = listOf(event)
            validateData()

            Assert.assertEquals(View.GONE, noListFoundVisibility.value)
        }
    }
}