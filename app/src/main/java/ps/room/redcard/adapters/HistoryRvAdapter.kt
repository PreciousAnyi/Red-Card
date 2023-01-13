package ps.room.redcard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import ps.room.redcard.R
import ps.room.redcard.data.Card

class HistoryRvAdapter(private var mdata: List<Card>, val spNo: String) : RecyclerView.Adapter<HistoryRvAdapter.HistoryRvHolder>() {

    private lateinit var mListener : onItemClickListener
    private lateinit var sListener : onSwitchClickListener

    interface onItemClickListener{
        fun onItemClick(position : Int)
    }
    interface onSwitchClickListener{
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    fun switchDetailClickListener(listener: onSwitchClickListener){
        sListener = listener
    }

    fun setModifiedList(modifiedList: List<Card>){
        this.mdata = modifiedList
        notifyDataSetChanged()
    }

    class HistoryRvHolder(itemView : View, listener: onItemClickListener, switchListener: onSwitchClickListener) : RecyclerView.ViewHolder(itemView) {
        val offencePts : TextView = itemView.findViewById(R.id.offencePtsDisplay)
        val studentRegNo : TextView = itemView.findViewById(R.id.studentRegNoDisplay)
        val invigilatorSpNo : TextView = itemView.findViewById(R.id.invigilatorSpNoDisplay)


        val revokeHandle : MaterialCardView = itemView.findViewById(R.id.revoke)
        val detailContainer : RelativeLayout = itemView.findViewById(R.id.cardLittleDetail)
        init {
            detailContainer.setOnClickListener {
                switchListener.onItemClick(adapterPosition)
            }
            revokeHandle.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryRvHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.history_item_design,
            parent, false)
        return HistoryRvHolder(itemView, mListener, sListener)
    }

    override fun onBindViewHolder(holder: HistoryRvHolder, position: Int) {
        val currentItem = mdata[position]
        holder.offencePts.text = currentItem.points
        holder.studentRegNo.text = "Student Reg: " + currentItem.studentRegNo
        holder.invigilatorSpNo.text = "Invigilator SpNo: " + spNo
    }

    override fun getItemCount(): Int {
        return mdata.size
    }
}