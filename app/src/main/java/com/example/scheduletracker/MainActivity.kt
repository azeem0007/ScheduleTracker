package com.example.scheduletracker

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val eventNameInput = findViewById<EditText>(R.id.eventNameInput)
        val eventDescriptionInput = findViewById<EditText>(R.id.eventDescriptionInput)
        val eventDateText = findViewById<TextView>(R.id.eventDateText)
        val eventTimeText = findViewById<TextView>(R.id.eventTimeText)
        val pickDateButton = findViewById<Button>(R.id.pickDateButton)
        val pickTimeButton = findViewById<Button>(R.id.pickTimeButton)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val viewEventsButton = findViewById<Button>(R.id.viewEventsButton)

        // Calendar instance to get the current date and time
        val calendar = Calendar.getInstance()

        // Date Picker
        pickDateButton.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    eventDateText.text = "$year-${monthOfYear + 1}-$dayOfMonth"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        // Time Picker
        pickTimeButton.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    eventTimeText.text = String.format("%02d:%02d", hourOfDay, minute)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()
        }

        submitButton.setOnClickListener {
            val eventName = eventNameInput.text.toString()
            val eventDescription = eventDescriptionInput.text.toString()
            val eventDate = eventDateText.text.toString()
            val eventTime = eventTimeText.text.toString()

            // Save event to storage
            EventStorage.addEvent(eventName, eventDescription, eventDate, eventTime)

            val intent = Intent(this, EventDetailsActivity::class.java).apply {
                putExtra("eventName", eventName)
                putExtra("eventDescription", eventDescription)
                putExtra("eventDate", eventDate)
                putExtra("eventTime", eventTime)
            }
            startActivity(intent)
        }

        viewEventsButton.setOnClickListener {
            val intent = Intent(this, EventsListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // Handle settings action
                true
            }
            R.id.action_about -> {
                showAboutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAboutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("About ScheduleTracker")
        builder.setMessage("ScheduleTracker is an application that helps you track and manage your events efficiently. Organize your schedule and never miss an event!")
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }
}
