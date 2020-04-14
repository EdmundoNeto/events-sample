package com.edmundo.events.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.edmundo.domain.model.Event
import com.edmundo.events.BR
import com.edmundo.events.R
import com.edmundo.events.adapter.EventsAdapter
import com.edmundo.events.databinding.ActivityEventsBinding
import com.edmundo.events.extensions.bindingContentView
import com.edmundo.events.extensions.observe
import com.edmundo.events.viewmodel.EventsViewModel
import kotlinx.android.synthetic.main.activity_events.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.android.viewmodel.ext.android.viewModel

class EventsActivity: AppCompatActivity() {

    private val viewModel: EventsViewModel by viewModel()
    private val listAdapter: EventsAdapter by inject {
        parametersOf(
            { event: Event ->
                startActivity(Intent(this, EventDetailActivity::class.java)
                    .putExtra("eventId", event.id)
                    .putExtra("eventTitle", event.title)
                )
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingContentView(R.layout.activity_events).run {
            setVariable(BR.eventsViewModel, viewModel)
            lifecycleOwner = this@EventsActivity
        }
    }

    private fun setupObservable() {
        viewModel.getData()

        viewModel.run {
            observe(eventsList) {
                it?.run {
                    listAdapter.setEventList(this)
                }
            }

        }
    }

    private fun setupRecycler() {
        main.apply {
            this.adapter = listAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onStart() {
        super.onStart()
        setupRecycler()
        setupObservable()
    }

}