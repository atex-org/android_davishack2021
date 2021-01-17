package org.atex.app.ui.scan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import org.atex.app.R
import org.tensorflow.lite.examples.classification.ClassifierActivity

class ScanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_scan, container, false)
        val scanButton: MaterialButton = root.findViewById(R.id.scan_button)
//        val imageContainer: ImageView = root.findViewById(R.id.image_container)

        scanButton.setOnClickListener {
            Log.d("scan_button", "Click")
            startForResult.launch(Intent(requireContext(), ClassifierActivity::class.java))
        }
        root.findViewById<MaterialButton>(R.id.upload_image).setOnClickListener {
            Log.d("upload_button", "Click")
            findNavController().navigate(R.id.action_navigation_scan_to_navigation_upload)
        }


        return root
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            Log.d("scan_button", "ActivityResult" + result.data)
            if (result.resultCode == Activity.RESULT_OK) {

            }
        }

}