package pt.ipp.estg.memorygame

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import kotlin.math.min
import androidx.recyclerview.widget.RecyclerView
import models.BoardSize
import models.MemoryCard
import kotlin.math.log

class MemoryBoardAdapter(
    private val context: Context,
    private val boardSize: BoardSize,
    private val cards: List<MemoryCard>,
    private val cardClickListener: CardClickListener
) :
    RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder>() {

    companion object{
        private const val MARGIN_SIZE = 10
    }

    interface CardClickListener{
        fun onCardClicked(position:Int)
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
            val memoryCard=cards[position]
            imageButton.setImageResource(if(cards[position].isFaceUp) cards[position].identifier else R.drawable.ic_launcher_background)
            imageButton.alpha=if(memoryCard.isMatched) .4f else 1.0f

            imageButton.setOnClickListener(){
                cardClickListener.onCardClicked(position)
            }
        }
    }
}
