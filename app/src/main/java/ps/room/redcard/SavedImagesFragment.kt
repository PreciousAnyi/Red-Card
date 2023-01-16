package ps.room.redcard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ps.room.redcard.adapters.HistoryRvAdapter
import ps.room.redcard.adapters.SavedImagesRvAdapter
import ps.room.redcard.api.LoginApi
import ps.room.redcard.data.Card
import ps.room.redcard.data.Image
import ps.room.redcard.data.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SavedImagesFragment : Fragment() {


    private lateinit var items: ArrayList<Image>
    private lateinit var RvCv: RecyclerView
    private lateinit var adapter: SavedImagesRvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_saved_images, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personnelNo = arguments?.getString("personnelNo")
        val password = arguments?.getString("password")

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://RedCard.onotuumoru.repl.co/")
            .build()
            .create(LoginApi::class.java)
        val retrofitData = retrofitBuilder.postData(personnelNo!!, password!!)
            .enqueue(object: Callback<UserData>{
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    if(response.body()?.user == null){
                        Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
                    } else{
                        if(response.body()!!.user.images.isEmpty()){
                            Toast.makeText(context, "You've got no saved images", Toast.LENGTH_LONG).show()
                        }
                        val theImages = response.body()!!.user.images
                        items = arrayListOf()
                        for(i in theImages.indices){
                            val image = Image(theImages[i].filename, theImages[i].name)
                            items.add(image)
                        }
                        RvCv = view.findViewById(R.id.SavedImageRv)
                        adapter = SavedImagesRvAdapter(items)
                        RvCv.layoutManager = LinearLayoutManager(context)
                        RvCv.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    Log.d("MainActivity", "onFailure: " + t.message)
                }

            })
    }


}