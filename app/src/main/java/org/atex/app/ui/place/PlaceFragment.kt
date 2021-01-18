package org.atex.app.ui.place

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.atex.app.utils.CustomInfoWindow
import org.atex.app.activity.MainActivity
import org.atex.app.R
import org.atex.app.TAG
import org.atex.app.model.event
import org.atex.app.model.Location
import org.atex.app.model.event_location
import org.atex.app.ui.home.HomeViewModel
import org.bson.types.ObjectId
import java.util.*


class PlaceFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    GoogleMap.OnInfoWindowClickListener {
    private lateinit var mMap: GoogleMap
    private lateinit var main: MainActivity
    private lateinit var marker1: Marker
    var position = LatLng(38.5382322, -121.7617125)
    private lateinit var placeViewModel: PlaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = activity as MainActivity
        placeViewModel = ViewModelProvider(main).get(PlaceViewModel::class.java)
    }

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
        mMap.uiSettings.isMyLocationButtonEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isTiltGesturesEnabled = true
        val location = Location()
        location.latitude = 38.5322122
        location.longitude = -121.7613125
        location.address = "1 Shields Ave, Davis, CA 95616"
        placeViewModel.loadEvent()
        placeViewModel.eventUpdated.observe(viewLifecycleOwner, {
            placeViewModel.event.forEach {
                Log.d(TAG(), "title: " + it.title)
            }
        })

        val event1 = event()
        event1.title = "100 Tree Planting"
        event1.detail ="Tree planting efforts will speed up the recovery of the forest habitat for area wildlife ..."
        event1.thumbnailUrl = "https://atex.org/images/events/event.jpg"
        event1.credit = 20
        event1.location= event_location("944 Garrod Dr, Davis, CA 95616",38.5322122, -121.7613125)
        createMarker(mMap, event1)

        val event2 = event()
        event2.title = "Schools Cleanup Challenge"
        event2.detail="Coastal Cleanup Day is the largest volunteer event in California and is a part of"
        event2.thumbnailUrl = "https://atex.org/images/events/event1.jpg"
        event2.credit = 50
        event2.location= event_location("23 El Dorado Dr, Dixon, CA 95620",38.4382352, -121.7112525)
        createMarker(mMap, event2)

        val event3 = event()
        event3.title = "Waste Audits"
        event3.detail =" Hold a waste audit to show the students of your school how much of their waste could have been diverted from landfill"
        event3.thumbnailUrl = "https://atex.org/images/events/event3.jpg"
        event3.credit = 50
        event3.location= event_location("CQMQ+7G Wilton, Californias",38.4332352, -121.2112125)
        createMarker(mMap, event3)

        val customInfoWindow = CustomInfoWindow(this)
        googleMap.setInfoWindowAdapter(customInfoWindow)
    }

    private fun createMarker(googleMap: GoogleMap, event: event) {
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
        val event = marker.tag as event
        Log.d("Event", "id: " + event._id)
//        findNavController().navigate(R.id.action_mapFragment_to_pointsAwardedFragment)
    }

}