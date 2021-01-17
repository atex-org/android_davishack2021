package org.atex.app.ui.place

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.atex.app.utils.CustomInfoWindow
import org.atex.app.activity.MainActivity
import org.atex.app.R
import org.atex.app.model.Event
import org.atex.app.model.Location


class PlaceFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    GoogleMap.OnInfoWindowClickListener {
    private lateinit var mMap: GoogleMap
    private lateinit var main: MainActivity
    private lateinit var marker1: Marker
    var position = LatLng(38.5382322, -121.7617125)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        main = activity as MainActivity
        return inflater.inflate(R.layout.fragment_place, container, false)
    }
    override fun onResume() {
        super.onResume()
        main.appBarVisible(false)
    }

    override fun onStop() {
        super.onStop()
        main.appBarVisible(true)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 12f))
        mMap.setOnMarkerClickListener(this)
        mMap.setOnInfoWindowClickListener(this)
        mMap.uiSettings.setMyLocationButtonEnabled(true)
        val location = Location()
        location.latitude = 38.5382322
        location.longitude = -121.7617125
        location.address = "1 Shields Ave, Davis, CA 95616"
//        val event1 = Event(
//            1,
//            "100 Tree Planting",
//            "",
//            "",
//            "Are you looking for opportunities to brainstorm concepts and think about how to tackle environmental challenges differently in 2019?  Applications for the Young Champions of the Earth Prize 2019 are now open. Seven winners aged 18-30 from around the globe will win seed funding and skilled mentoring for their groundbreaking ideas to create positive environmental impact.  In the meantime, why not explore these events and festivals for 2019, which aim to inspire environmental action and positive opportunities to network?",
//            location
//        )
//        createMarker(mMap, event1)
        val customInfoWindow = CustomInfoWindow(this)
        googleMap.setInfoWindowAdapter(customInfoWindow)
    }

    private fun createMarker(googleMap: GoogleMap, event: Event) {
        val markerOptions = MarkerOptions()
        event.location?.latitude?.let { latitude ->
            event.location?.longitude?.let { longitude ->
                position = LatLng(latitude, longitude)
            }
        }
        markerOptions.position(position)
            .title(event.title)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        marker1 = googleMap.addMarker(markerOptions)
        marker1.tag = event
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        if (marker.id.equals(marker1.id)) {
            marker.showInfoWindow()
        }
        return false
    }

    override fun onInfoWindowClick(marker: Marker) {
        Log.d("onInfoWindowClick", "" + marker.title)
        val event = marker.tag as Event
        Log.d("Event", "id: " + event._id)
//        findNavController().navigate(R.id.action_mapFragment_to_pointsAwardedFragment)
    }

}