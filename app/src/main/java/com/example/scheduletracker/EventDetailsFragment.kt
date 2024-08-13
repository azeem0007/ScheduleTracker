package com.example.scheduletracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class EventDetailsFragment : Fragment() {

    companion object {
        fun newInstance(eventName: String?, eventDescription: String?, eventDate: String?, eventTime: String?): EventDetailsFragment {
            val fragment = EventDetailsFragment()
            val args = Bundle()
            args.putString("eventName", eventName)
            args.putString("eventDescription", eventDescription)
            args.putString("eventDate", eventDate)
            args.putString("eventTime", eventTime)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_event_details, container, false)

        // Get arguments passed from the activity
        val eventName = arguments?.getString("eventName")
        val eventDescription = arguments?.getString("eventDescription")
        val eventDate = arguments?.getString("eventDate")
        val eventTime = arguments?.getString("eventTime")

        // Display the event details
        view.findViewById<TextView>(R.id.eventNameTextView).text = eventName
        view.findViewById<TextView>(R.id.eventDescriptionTextView).text = eventDescription
        view.findViewById<TextView>(R.id.eventDateTextView).text = eventDate
        view.findViewById<TextView>(R.id.eventTimeTextView).text = eventTime

        return view
    }
}
