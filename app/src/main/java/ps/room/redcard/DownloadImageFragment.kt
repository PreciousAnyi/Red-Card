package ps.room.redcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class DownloadImageFragment : Fragment() {
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_download_image, container, false)
        image = view.findViewById(R.id.classListImageView)

        val captureAttendanceFragment =
            requireFragmentManager().findFragmentById(R.id.captureAttendanceFragment) as CaptureAttendanceFragment
        val bitmap = captureAttendanceFragment.getImage()
        image.setImageBitmap(bitmap)

        return view
    }
}
