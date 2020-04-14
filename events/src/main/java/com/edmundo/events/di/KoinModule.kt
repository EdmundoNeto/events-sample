package com.edmundo.events.di

import com.edmundo.domain.model.Event
import com.edmundo.events.adapter.EventsAdapter
import com.edmundo.events.viewmodel.CheckInViewModel
import com.edmundo.events.viewmodel.EventsDetailViewModel
import com.edmundo.events.viewmodel.EventsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { EventsViewModel(repository = get()) }
    viewModel { EventsDetailViewModel(repository = get()) }
    viewModel { CheckInViewModel(repository = get()) }
}

val adapterModule = module {
    factory { (action: (Event) -> Unit) ->
        EventsAdapter(
            itemClickAction = action
        )
    }
}