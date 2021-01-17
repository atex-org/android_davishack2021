package org.atex.app.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.atex.app.R
import org.atex.app.model.Event
import java.lang.Exception


class CustomInfoWindow(private val context: Fragment) : GoogleMap.InfoWindowAdapter {
    private lateinit var view: View
    override fun getInfoWindow(marker: Marker?): View {
        getInfoContents(marker)
        return view
    }

    override fun getInfoContents(marker: Marker?): View {
        view = context.layoutInflater.inflate(R.layout.map_custom_infowindow, null)
        marker?.let {
            val textTitle = view.findViewById<TextView>(R.id.textTitle)
            val textMessage = view.findViewById<TextView>(R.id.textMessage)
            val textType = view.findViewById<TextView>(R.id.textType)
            val imageEvent = view.findViewById<ImageView>(R.id.imageEvent)
            val event = marker.tag as Event
            textTitle.text = event.title
            textMessage.text = event.detail
            textType.text = event.location?.address
            val url = event.thumbnailUrl
            if (!url.isNullOrEmpty()) {
                Picasso.get().load(url).error(R.drawable.maps_sv_error_icon)
                    .placeholder(R.drawable.ic_app_icon)
                    .into(imageEvent, MarkerCallback(marker))
            }
        }
        return view
    }

    private class MarkerCallback(m: Marker) : Callback {
        private var marker: Marker = m
        override fun onSuccess() {
            if (!marker.isInfoWindowShown) {
                return
            }
            marker.hideInfoWindow()
            marker.showInfoWindow()   // refresh contents
        }

        override fun onError(e: Exception?) {
        }
    }
}
