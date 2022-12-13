package ps.room.redcard

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonElement
import ps.room.redcard.adapters.HistoryRvAdapter
import ps.room.redcard.api.LoginApi
import ps.room.redcard.data.Card
import ps.room.redcard.data.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HistoryFragment : Fragment() {

    private lateinit var RvCv: RecyclerView
    private lateinit var adapter: HistoryRvAdapter
    private lateinit var items: ArrayList<Card>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val personnelNo = arguments?.getString("personnelNo")
        val password = arguments?.getString("password")


        Toast.makeText(context, personnelNo + " ::: "+ password, Toast.LENGTH_LONG).show()


        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://RedCard.onotuumoru.repl.co/")
            .build()
            .create(LoginApi::class.java)


        val retrofitData = retrofitBuilder.postData(personnelNo!!, password!!)
            .enqueue(object: Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    if(response.body()?.user == null){
                        Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
                    } else{
                        Toast.makeText(context, response.body()!!.user.cards.toString(), Toast.LENGTH_LONG).show()
                        val theCards = response.body()!!.user.cards
                        items = arrayListOf()
                        for(i in theCards.indices){
                            val card = Card(theCards[i].offence, theCards[i].points, theCards[i].studentRegNo)
                            items.add(card)
                        }

//                        items = arrayListOf(theCards)
                        RvCv = view.findViewById(R.id.historyRecyclerView)
//                        items = theCards as List<Card>
                        adapter = HistoryRvAdapter(items)
                        RvCv.layoutManager = LinearLayoutManager(context)
                        RvCv.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

            })

    }

}