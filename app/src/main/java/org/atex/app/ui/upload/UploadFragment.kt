package org.atex.app.ui.upload

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.button.MaterialButton
import org.atex.app.activity.MainActivity
import org.atex.app.R
import java.io.File


class UploadFragment : Fragment() {

    lateinit var uploadImage: ImageView
    private lateinit var main: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = this.activity as MainActivity
        main.bottomAppBarVisible(false)
    }

    override fun onResume() {
        super.onResume()
        main.bottomAppBarVisible(false)
    }

    override fun onStop() {
        super.onStop()
        main.bottomAppBarVisible(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_upload, container, false)
        val spinner = root.findViewById<Spinner>(R.id.spinner)
        val targetList: ArrayList<String> = ArrayList()
        targetList.add("Paper")
        targetList.add("Cardboard")
        targetList.add("Glass")
        targetList.add("Metal")
        targetList.add("E-Waste")
        targetList.add("Plastic")
        targetList.add("Trash")
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, targetList)
        spinner.adapter = adapter
        spinner.setSelection(0)
        uploadImage = root.findViewById(R.id.image_view)
        root.findViewById<MaterialButton>(R.id.upload_image).setOnClickListener {
            ImagePicker.with(this).crop().compress(1024)
                .maxResultSize(1080, 1080).start()
        }
        root.findViewById<MaterialButton>(R.id.upload_button).setOnClickListener {
            Toast.makeText(requireContext(), "Thank you for your contribution", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigateUp()
        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data
            uploadImage.setImageURI(fileUri)
            val file: File? = ImagePicker.getFile(data)
            val filePath: String? = ImagePicker.getFilePath(data)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }


}