package pt.ipp.estg.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.BoardSize
import models.MemoryCard
import utils.DEFAULT_ICONS

class MainActivity : AppCompatActivity() {
    companion object{
        private const val TAG="MainActivity"
    }

    private lateinit var rvBoard:RecyclerView
    private lateinit var tvNumMoves:TextView
    private lateinit var tvNumPairs:TextView
    private lateinit var adapter:MemoryBoardAdapter
    private lateinit var memoryGame: MemoryGame

    private var boardSize:BoardSize=BoardSize.EASY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBoard=findViewById(R.id.Board)
        tvNumMoves=findViewById(R.id.txtNumMoves)
        tvNumPairs=findViewById(R.id.txtNumPairs)

        val chosenImages=DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages=(chosenImages+chosenImages).shuffled()

        val memoryCards = randomizedImages.map{
            MemoryCard(it)
        }

        memoryGame=MemoryGame(boardSize)
        //RecyclerViews has two main componentes:LayoutManager and adapter
        //LayoutManager measures and positions items views and adapter provide a binding for the data set
        //to the views of the RecyclerView
         adapter = MemoryBoardAdapter(this,boardSize,memoryGame.cards, object : MemoryBoardAdapter.CardClickListener {
            override fun onCardClicked(position: Int) {
                updateGameWithFlip(position)
            }
        })

        rvBoard.adapter=adapter
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager=GridLayoutManager(this,boardSize.getWidth())
    }

    private fun updateGameWithFlip(position: Int) {
        memoryGame.flipCard(position)
        adapter.notifyDataSetChanged()
    }
}