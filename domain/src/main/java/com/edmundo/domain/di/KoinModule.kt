package com.edmundo.domain.di

import com.edmundo.domain.EventsListApi
import com.edmundo.domain.repository.EventListRepository
import org.koin.dsl.module


val serviceModule = module {
    single {
        EventsListApi.getApiService()
    }
}

val repositoryModule = module {
    single {
        EventListRepository(service = get())
    }
}
