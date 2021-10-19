package pt.ipp.estg.memorygame

import android.util.Log
import models.BoardSize
import models.MemoryCard
import utils.DEFAULT_ICONS

class MemoryGame (private val boardSize: BoardSize){
    val cards:List<MemoryCard>
    var numPairsFound=0
    private var numCardFlips=0
    private var indexOfSingleSelectorCard:Int?=null

    init{
        val chosenImages= DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages=(chosenImages+chosenImages).shuffled()
        cards = randomizedImages.map{ MemoryCard(it) }
    }

    //0 cards previously flipped over
    //1 card previously flipped over
    //2 cards previsously flipped
    fun flipCard(position: Int):Boolean {
        numCardFlips++
        val card = cards[position]
        var foundMatch=false
        if(indexOfSingleSelectorCard==null){
            restoreCards()
            indexOfSingleSelectorCard=position
        }
        else{
            foundMatch = checkForMatch(indexOfSingleSelectorCard!!,position)
            indexOfSingleSelectorCard=null
        }
        card.isFaceUp=!card.isFaceUp
        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if(cards[position1].identifier != cards[position2].identifier){
            return false
        }
        cards[position1].isMatched=true
        cards[position2].isMatched=true
        numPairsFound++
        return true
    }

    private fun restoreCards() {
        for(card in cards){
            if(!card.isMatched){
                card.isFaceUp=false
            }
        }
    }

    fun haveWonGame(): Boolean = numPairsFound == boardSize.getNumPairs()

    fun isCardFaceUp(position: Int): Boolean = cards[position].isFaceUp

    fun getNumMoves():Int=numCardFlips / 2
}


