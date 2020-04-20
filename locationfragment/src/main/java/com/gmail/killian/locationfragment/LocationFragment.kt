package com.gmail.killian.locationfragment

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationFragment: Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(context,
            android.R.string.yes, Toast.LENGTH_SHORT).show()
        requestPermissions()
    }
    val negativeButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(context,
            android.R.string.no, Toast.LENGTH_SHORT).show()
        val labelPosition = view?.findViewById<TextView>(R.id.gpsPosition)
        labelPosition?.text = "On te laisse tranquille !"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.location_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val labelPosition = view.findViewById<TextView>(R.id.gpsPosition)
        fusedLocationClient = context?.let { LocationServices.getFusedLocationProviderClient(it) }!!
        setupPermissions(labelPosition)
    }

    private fun setupPermissions(labelPosition: TextView) {
        val permission = ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                val builder = AlertDialog.Builder(context)

                with(builder)
                {
                    setTitle("Permission requise")
                    setMessage("Nous avons besoin de votre localisation afin d'afficher votre position")
                    setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
                    setNegativeButton(android.R.string.no, negativeButtonClick)
                    show()
                }
            } else {
                requestPermissions()
            }
        } else {
            showPosition(labelPosition, true)
        }
    }

    private fun showPosition(labelPosition: TextView, choice: Boolean){
        if (choice) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    labelPosition.text = "Latitude: ${location?.latitude.toString()} \nLongitude: ${location?.longitude.toString()}"
                }
        } else {
            showPosition(labelPosition, false)
        }
    }

    private fun requestPermissions() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            2
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        val labelPosition = view?.findViewById<TextView>(R.id.gpsPosition)
        when (requestCode) {
            2 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location : Location? ->
                            if (labelPosition != null) {
                                showPosition(labelPosition, true)
                            }
                        }
                } else {
                    if (labelPosition != null) {
                        showPosition(labelPosition, false)
                    }
                }
            }
            else -> {

            }
        }
    }

}