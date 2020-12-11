package com.nbyapps.mymemory.models

import com.nbyapps.mymemory.utils.DEFAULT_ICONS

class MemoryGame(private val boardSize: BoardSize) {


    val cards: List<MemoryCard>
    val numPairsFound = 0

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages: List<Int> = (chosenImages + chosenImages).shuffled()
        cards = randomizedImages.map { MemoryCard(it) }
    }

    fun flipCard(position: Int) {
        val card = cards[position]

        // three cases:
        // 0 cards previously flipped over => flip over the selected card
        // 1 card previously flipped over => flip over the selected card + check if the images match
        // 2 cards previously flipped over => restore cards + flip over the selected card

        card.isFaceUp = !card.isFaceUp
    }
}