package ps.room.redcard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ps.room.redcard.R

class ListOfOffenseAdapter(private val mdata: ArrayList<ListOfOffenseItem>) :  RecyclerView.Adapter<ListOfOffenseAdapter.ListOfOffenseViewHolder>(){

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class ListOfOffenseViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val offense : TextView = itemView.findViewById(R.id.offenseTV)
        val tvHeading : TextView = itemView.findViewById(R.id.offensePoint)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOfOffenseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_of_offense_design,
            parent, false)
        return ListOfOffenseViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ListOfOffenseViewHolder, position: Int) {
        val currentItem = mdata[position]
        holder.tvHeading.text = currentItem.offense
        holder.offense.text = currentItem.heading
    }

    override fun getItemCount(): Int {
        return mdata.size
    }
}