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
class EventsDetailViewModelTest {

    private val repository: EventListRepository = mockk(relaxed = true)
    private lateinit var viewModel: EventsDetailViewModel

    @Before
    fun setup() {
        viewModel = EventsDetailViewModel(repository)
    }


    @Test
    fun `when event is found by its id then I must not show a message`() {
        val eventMockk: Event = mockk(relaxed = true)
        viewModel.run {
            event.value = eventMockk
            validateData()

            Assert.assertEquals(View.GONE, noEventFoundVisibility.value)
        }
    }
}