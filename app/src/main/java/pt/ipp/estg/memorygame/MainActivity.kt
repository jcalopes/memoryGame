package pt.ipp.estg.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.BoardSize

class MainActivity : AppCompatActivity() {
    private lateinit var rvBoard:RecyclerView
    private lateinit var tvNumMoves:TextView
    private lateinit var tvNumPairs:TextView

    private var boardSize:BoardSize=BoardSize.EASY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBoard=findViewById(R.id.Board)
        tvNumMoves=findViewById(R.id.txtNumMoves)
        tvNumPairs=findViewById(R.id.txtNumPairs)

        //RecyclerViews has two main componentes:LayoutManager and adapter
        //LayoutManager measures and positions items views and adapter provide a binding for the data set
        //to the views of the RecyclerView
        rvBoard.adapter=MemoryBoardAdapter(this,boardSize)
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager=GridLayoutManager(this,boardSize.getWidth())
    }
}