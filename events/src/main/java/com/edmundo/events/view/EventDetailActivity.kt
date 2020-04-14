package com.edmundo.events.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.transition.TransitionManager
import com.edmundo.domain.model.Event
import com.edmundo.events.BR
import com.edmundo.events.R
import com.edmundo.events.databinding.ActivityEventDetailBinding
import com.edmundo.events.extensions.observe
import com.edmundo.events.extensions.toFormattedDate
import com.edmundo.events.viewmodel.EventsDetailViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.event_detail_content.*
import org.koin.android.viewmodel.ext.android.viewModel

class EventDetailActivity: AppCompatActivity(), OnMapReadyCallback {

    private val viewModel: EventsDetailViewModel by viewModel()
    private val eventId by lazy { intent.getStringExtra("eventId").orEmpty() }
    private val eventTitle by lazy { intent.getStringExtra("eventTitle").orEmpty() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityEventDetailBinding
                = setContentView(this@EventDetailActivity, R.layout.activity_event_detail)
        binding.eventDetailViewModel = viewModel
        binding.lifecycleOwner = this@EventDetailActivity
    }

    private fun setupObservable() {
        if(eventId.isEmpty())
            finish()

        viewModel.getEvent(eventId)

        viewModel.run {
            observe(event) {
                it?.run {
                    setupUi(this)
                }
            }

        }
    }

    private fun setupUi(event: Event) {
        val gMap = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        TransitionManager.beginDelayedTransition(rootLayout)

        tvEventDescription.text = event.description
        tvEventDate.text = event.date.toFormattedDate("dd/MM/YYYY")
        tvEventPrice.text = event.toFormattedPrice(event.price)
        btCheckIn.setOnClickListener {
            CheckinDialog.getDialog(eventId).show(supportFragmentManager, "")
        }

        gMap.getMapAsync(this)
    }

    private fun setupToolbar() {
        supportActionBar?.hide()

        collapsingToolbarLayout.title = eventTitle
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val lat = viewModel.event.value?.latitude?.toDouble() ?: 0.0
        val lng = viewModel.event.value?.longitude?.toDouble() ?: 0.0

        val location = LatLng(lat, lng)

        googleMap?.addMarker(MarkerOptions().position(location))

        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16f))
    }

    override fun onStart() {
        super.onStart()
        setupToolbar()
        setupObservable()
    }
}