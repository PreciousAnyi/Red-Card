package ps.room.redcard

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapRegionDecoder.newInstance
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ps.room.redcard.IssueACardFragment.Companion.newInstance
import ps.room.redcard.api.IssueCardApi
import ps.room.redcard.api.SaveImage
import ps.room.redcard.data.issueCardResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.lang.reflect.Array.newInstance


class CaptureAttendanceFragment : Fragment() {
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PERMISSION_CAMERA = 2
    private lateinit var button_capture_attendance: Button
    private lateinit var button_save_attendance: Button
    private lateinit var attendance_imageview: ImageView
    private lateinit var theimageBitmap: Bitmap
    private lateinit var theimageFile: File

    val personnelNo = arguments?.getString("personnelNo")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_capture_attendance, container, false)
        attendance_imageview = view.findViewById(R.id.attendance_imageview)
        button_capture_attendance = view.findViewById(R.id.button_capture_attendance)


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
        button_save_attendance = view.findViewById(R.id.button_save_attendance)
        button_save_attendance.setOnClickListener {
//            findNavController().navigate(R.id.action_captureAttendanceFragment_to_invigilatorFragment)
            val imageNameTv = view.findViewById<View>(R.id.ImageNameEdit) as TextInputEditText
            val imageNameText = imageNameTv.text.toString()
//            if (personnelNo != null) {
//                Log.d("theveryy sp:   ", personnelNo)
//            } else {
//                Log.d("nulllly:   ", "it is veryn nullll")
//            }
            val personnelNo = arguments?.getString("personnelNo")
            theimageFile = File(context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES), imageNameText)
            val stream = FileOutputStream(theimageFile)
            theimageBitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
            stream.close()
            val name = RequestBody.create(MediaType.parse("text/plain"), imageNameText)
            val spNo = RequestBody.create(MediaType.parse("text/plain"), personnelNo)

            val theimageReqFile = RequestBody.create(MediaType.parse("image/png"), theimageFile)
            val file = MultipartBody.Part.createFormData("file", theimageFile.name, theimageReqFile)
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://red-card-backend.onrender.com/")
                .build()
                .create(SaveImage::class.java)
            val retrofitData = retrofitBuilder.saveImage(spNo, name, file)
                .enqueue(object: Callback<issueCardResponse>{
                    override fun onResponse(
                        call: Call<issueCardResponse>,
                        response: Response<issueCardResponse>
                    ) {
                        Toast.makeText(context, response.body()?.success.toString(), Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<issueCardResponse>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }
                })
//            findNavController().popBackStack()
//            findNavController().popBackStack()
//            findNavController().navigate(R.id.invigilatorFragment)
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
            theimageBitmap = imageBitmap
            // Set the ImageView to display the image
            attendance_imageview.setImageBitmap(imageBitmap)
        }
    }
    fun getImage():Bitmap {
       return theimageBitmap
    }

}





