package ps.room.redcard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import ps.room.redcard.R

class HowToAdapter(private val mdata: ArrayList<HowToItem>) : RecyclerView.Adapter<HowToAdapter.HowToViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    class HowToViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val howToImage : ShapeableImageView = itemView.findViewById(R.id.howTo_image)
        val tvHeading : TextView = itemView.findViewById(R.id.howTo_heading)
        val tvSubHead : TextView = itemView.findViewById(R.id.howTo_subHead)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HowToViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.howto_item,
            parent, false)
        return HowToViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: HowToViewHolder, position: Int) {
        val currentItem = mdata[position]
        holder.howToImage.setImageResource(currentItem.howToImage)
        holder.tvHeading.text = currentItem.heading
        holder.tvSubHead.text = currentItem.subHead
    }

    override fun getItemCount(): Int {
        return mdata.size
    }
}