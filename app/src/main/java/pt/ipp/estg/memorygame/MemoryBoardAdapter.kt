package pt.ipp.estg.memorygame

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import kotlin.math.min
import androidx.recyclerview.widget.RecyclerView
import models.BoardSize

class MemoryBoardAdapter(private val context: Context, private val boardSize: BoardSize) :
    RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder>() {

    companion object{
        private const val MARGIN_SIZE = 10
    }

    //Responsible for create one view of our recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardWidth=parent.width / boardSize.getWidth()  - (2 * MARGIN_SIZE)
        val cardHeight=parent.height / boardSize.getHeight() - (2 * MARGIN_SIZE)
        val cardSideLength=min(cardWidth,cardHeight)
        val view=LayoutInflater.from(context).inflate(R.layout.memory_card,parent,false)
        val layoutParams=view.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width=cardSideLength
        layoutParams.height=cardSideLength
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)
        return ViewHolder(view)
    }

    //Bind the data to specific view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = boardSize.numCards

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        private val imageButton=itemView.findViewById<ImageButton>(R.id.imageButton)

        fun bind(position:Int){
            imageButton.setOnClickListener{

            }
        }
    }
}
