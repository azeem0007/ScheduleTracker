package com.example.scheduletracker

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EventsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_list)

        val eventsTableLayout = findViewById<TableLayout>(R.id.eventsTableLayout)

        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val eventsGroupedByMonth = EventStorage.getEventsGroupedByMonth(currentYear)

        eventsTableLayout.removeAllViews() // Clear existing views

        eventsGroupedByMonth.forEach { (month, events) ->
            val headerRow = TableRow(this)

            val monthTextView = TextView(this)
            monthTextView.text = SimpleDateFormat("MMMM", Locale.getDefault()).format(Calendar.getInstance().apply { set(Calendar.MONTH, month) }.time)
            monthTextView.setTextAppearance(this, R.style.CustomTextStyle) // Apply style
            monthTextView.setPadding(16, 8, 16, 8)
            monthTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
            headerRow.addView(monthTextView)

            eventsTableLayout.addView(headerRow)

            events.forEach { event ->
                val eventRow = TableRow(this)

                val nameTextView = TextView(this)
                nameTextView.text = event.name
                nameTextView.setTextAppearance(this, R.style.CustomTextStyle) // Apply style
                nameTextView.setPadding(16, 8, 16, 8)
                nameTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                eventRow.addView(nameTextView)

                val dateTextView = TextView(this)
                dateTextView.text = event.date
                dateTextView.setTextAppearance(this, R.style.CustomTextStyle) // Apply style
                dateTextView.setPadding(16, 8, 16, 8)
                dateTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                eventRow.addView(dateTextView)

                val timeTextView = TextView(this)
                timeTextView.text = event.time
                timeTextView.setTextAppearance(this, R.style.CustomTextStyle) // Apply style
                timeTextView.setPadding(16, 8, 16, 8)
                timeTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                eventRow.addView(timeTextView)

                eventsTableLayout.addView(eventRow)
            }
        }
    }
}
