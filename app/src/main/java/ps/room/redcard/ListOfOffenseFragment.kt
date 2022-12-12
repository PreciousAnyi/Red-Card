package ps.room.redcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ps.room.redcard.adapters.HowToAdapter
import ps.room.redcard.adapters.HowToItem
import ps.room.redcard.adapters.ListOfOffenseAdapter
import ps.room.redcard.adapters.ListOfOffenseItem


class ListOfOffenseFragment : Fragment() {
    private lateinit var RvCv: RecyclerView
    private lateinit var adapter: ListOfOffenseAdapter
    lateinit var items: List<ListOfOffenseItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RvCv = view.findViewById(R.id.listOfOffenseRV)
        items = arrayListOf(
            ListOfOffenseItem("5pts", "Talking in exam hall"),
            ListOfOffenseItem("10pts", "Carrying of electronic gadgets"),
            ListOfOffenseItem("15pts", "Sorting of courses"),
            ListOfOffenseItem("2pts", "Coming late to exam"),
            ListOfOffenseItem("15pts", "Impersonation")

        )
        adapter = ListOfOffenseAdapter(items as ArrayList<ListOfOffenseItem>)
        RvCv.layoutManager = LinearLayoutManager(context)
        RvCv.adapter = adapter

        adapter.setOnItemClickListener(object : ListOfOffenseAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                findNavController().navigate(R.id.action_listOfOffenseFragment_to_issueACardFragment)
            }

        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_of_offense, container, false)

//        val tv = view.findViewById<View>(R.id.loginWelcomeTV) as TextView
        val invigilatorSpNo = arguments?.getString("invigilatorSpNo")
        val studentRegNo = arguments?.getString("studentRegNo")

        Toast.makeText(context, invigilatorSpNo + " :::: " + studentRegNo, Toast.LENGTH_LONG).show()
        Toast.makeText(context, invigilatorSpNo + " :::: " + studentRegNo, Toast.LENGTH_LONG).show()
        Toast.makeText(context, invigilatorSpNo + " :::: " + studentRegNo, Toast.LENGTH_LONG).show()
        Toast.makeText(context, invigilatorSpNo + " :::: " + studentRegNo, Toast.LENGTH_LONG).show()
        Toast.makeText(context, invigilatorSpNo + " :::: " + studentRegNo, Toast.LENGTH_LONG).show()

        // Inflate the layout for this fragment
        return view
    }
    }


