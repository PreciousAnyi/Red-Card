package ps.room.redcard.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ps.room.redcard.R
import ps.room.redcard.data.Card
import ps.room.redcard.data.Image

class SavedImagesRvAdapter(private var mdata: List<Image>,): RecyclerView.Adapter<SavedImagesRvAdapter.SavedImagesRvHolder>() {


    class SavedImagesRvHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        var image : ImageView
        var name: TextView
        init {
            image = itemView.findViewById(R.id.SavedImage)
            name = itemView.findViewById(R.id.SavedImageName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedImagesRvHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.saved_images_item_design, parent, false)
        return SavedImagesRvHolder(itemView)
    }

    override fun onBindViewHolder(holder: SavedImagesRvHolder, position: Int) {
        var currentItem = mdata[position]
        holder.name.text = mdata[position].name
        val imageUrl = "https://red-card-backend.onrender.com/file/" + mdata[position].filename
        Glide.with(holder.image.context)
            .load(imageUrl)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return mdata.size
    }
}