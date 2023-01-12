package ps.room.redcard

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapRegionDecoder.newInstance
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import ps.room.redcard.IssueACardFragment.Companion.newInstance
import java.lang.reflect.Array.newInstance


class CaptureAttendanceFragment : Fragment() {
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PERMISSION_CAMERA = 2
    private lateinit var button_capture_attendance: Button
    private lateinit var button_save_attendance: Button
    private lateinit var attendance_imageview: ImageView
    private lateinit var imageBitmap: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_capture_attendance, container, false)
        attendance_imageview = view.findViewById(R.id.attendance_imageview)
        button_capture_attendance = view.findViewById(R.id.button_capture_attendance)
        button_save_attendance = view.findViewById(R.id.button_save_attendance)
        button_save_attendance.setOnClickListener {
//            findNavController().navigate(R.id.action_captureAttendanceFragment_to_invigilatorFragment)
            findNavController().popBackStack()
            findNavController().popBackStack()
            findNavController().navigate(R.id.invigilatorFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Check for camera permission
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request permission
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_PERMISSION_CAMERA
            )
        } else {
            // Permission has already been granted, set up the camera button
            setupCameraButton()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSION_CAMERA) {
            // If request is cancelled, the result arrays are empty
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission has been granted, set up the camera button
                setupCameraButton()
            } else {
                // Permission denied, disable the functionality that depends on this permission
                // In this case, we can't use the camera
            }
            return
        }
    }


    private fun setupCameraButton() {
        button_capture_attendance.setOnClickListener {
            takePicture()
        }
    }

    private fun takePicture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // Get the image captured by the camera
            val imageBitmap = data?.extras?.get("data") as Bitmap
            // Set the ImageView to display the image
            attendance_imageview.setImageBitmap(imageBitmap)
        }
    }
    fun getImage():Bitmap {
       return imageBitmap
    }

}





