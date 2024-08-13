package com.example.scheduletracker

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object EventStorage {
    private val events = mutableListOf<Event>()

    fun addEvent(name: String, description: String, date: String, time: String) {
        events.add(Event(name, description, date, time))
    }

    fun getEventsGroupedByMonth(year: Int): Map<Int, List<Event>> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        return events.filter { event ->
            val eventDate = dateFormat.parse(event.date)
            eventDate?.let {
                val cal = Calendar.getInstance()
                cal.time = it
                cal.get(Calendar.YEAR) == year
            } ?: false
        }.groupBy { event ->
            val eventDate = dateFormat.parse(event.date)
            eventDate?.let {
                val cal = Calendar.getInstance()
                cal.time = it
                cal.get(Calendar.MONTH)
            } ?: Calendar.DECEMBER // Default to December if parsing fails
        }
    }
}
