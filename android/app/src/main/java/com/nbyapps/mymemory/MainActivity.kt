package com.nbyapps.mymemory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nbyapps.mymemory.models.BoardSize
import com.nbyapps.mymemory.models.MemoryGame

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG =  "MainActivity"
    }

    private lateinit var recyclerViewBoard: RecyclerView;
    private lateinit var textViewMoves: TextView;
    private lateinit var textViewPairs: TextView;

    private lateinit var memoryGame: MemoryGame;
    private lateinit var memoryBoardAdapter: MemoryBoardAdapter;

    private var boardSize: BoardSize = BoardSize.HARD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewBoard = findViewById(R.id.recycler_view_board)
        textViewMoves = findViewById(R.id.text_view_moves)
        textViewPairs = findViewById(R.id.text_view_pair)

        memoryGame = MemoryGame(boardSize)

        memoryBoardAdapter = MemoryBoardAdapter(this, boardSize, memoryGame.cards, object: MemoryBoardAdapter.CardClickListener {
            override fun onCardClicked(position: Int) {
                updateGameWithFlip(position)
            }

        })

        recyclerViewBoard.adapter = memoryBoardAdapter
        recyclerViewBoard.setHasFixedSize(true)
        recyclerViewBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }

    private fun updateGameWithFlip(position: Int) {
        memoryGame.flipCard(position)
        memoryBoardAdapter.notifyDataSetChanged()
    }


}