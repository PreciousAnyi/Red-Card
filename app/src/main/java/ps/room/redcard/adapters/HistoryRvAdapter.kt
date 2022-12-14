package ps.room.redcard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import ps.room.redcard.R
import ps.room.redcard.data.Card

class HistoryRvAdapter(private val mdata: List<Card>) : RecyclerView.Adapter<HistoryRvAdapter.HistoryRvHolder>() {
    class HistoryRvHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val offencePts : TextView = itemView.findViewById(R.id.offencePtsDisplay)
        val studentRegNo : TextView = itemView.findViewById(R.id.studentRegNoDisplay)
//        val invigilatorSpNo : TextView = itemView.findViewById(R.id.invigilatorSpNoDisplay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryRvHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.history_item_design,
            parent, false)
        return HistoryRvHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryRvHolder, position: Int) {
        val currentItem = mdata[position]
        holder.offencePts.text = currentItem.points
        holder.studentRegNo.text = "Student Reg: " + currentItem.studentRegNo
    }

    override fun getItemCount(): Int {
        return mdata.size
    }
}