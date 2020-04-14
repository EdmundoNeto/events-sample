package com.edmundo.events.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.edmundo.domain.model.Event
import com.edmundo.events.BR
import com.edmundo.events.R
import com.edmundo.events.extensions.toFormattedDate
import kotlinx.android.synthetic.main.event_item.view.*

class EventsAdapter(private val itemClickAction: (Event) -> Unit):
    RecyclerView.Adapter<EventsViewHolder>() {

    private var eventList: List<Event> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder =
        EventsViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.event_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val event = eventList[position]

        holder.apply {
            binding(event)

            itemView.setOnClickListener {
                itemClickAction.invoke(event)
            }
        }
        holder.binding(eventList[position])
    }

    override fun getItemCount(): Int = eventList.count()

    fun setEventList(list: List<Event>) {
        this.eventList = list
        notifyDataSetChanged()
    }
}

class EventsViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun binding(event: Event) {
        binding.root.tvEventPrice.text = event.toFormattedPrice(event.price)
        binding.root.tvEventDate.text = event.date.toFormattedDate("dd/MM/YY")
        binding.apply {
            setVariable(BR.eventsItem, event)
        }
    }
}