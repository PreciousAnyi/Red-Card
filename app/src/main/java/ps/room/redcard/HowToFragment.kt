package ps.room.redcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ps.room.redcard.adapters.HowToAdapter
import ps.room.redcard.adapters.HowToItem


class HowToFragment : Fragment() {
    private lateinit var RvCv: RecyclerView
    private lateinit var adapter: HowToAdapter
    lateinit var items: List<HowToItem>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RvCv = view.findViewById(R.id.recyclerView_howto)
        items = arrayListOf(
            HowToItem(R.drawable.woman_image, "Futo Assigns New", "VC role to a..."),
            HowToItem(R.drawable.woman_image, "Futo Assigns New", "VC role to a..."),
            HowToItem(R.drawable.woman_image, "Futo Assigns New", "VC role to a..."),
            HowToItem(R.drawable.woman_image, "Futo Assigns New", "VC role to a..."),
            HowToItem(R.drawable.woman_image, "Futo Assigns New", "VC role to a..."),
            HowToItem(R.drawable.woman_image, "Futo Assigns New", "VC role to a..."),
        )
        adapter = HowToAdapter(items as ArrayList<HowToItem>)
        RvCv.layoutManager = LinearLayoutManager(context)
        RvCv.adapter = adapter

        adapter.setOnItemClickListener(object : HowToAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(context, "Cool Stuff", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_how_to, container, false)
    }

}