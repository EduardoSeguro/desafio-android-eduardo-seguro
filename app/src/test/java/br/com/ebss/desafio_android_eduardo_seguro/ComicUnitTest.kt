package br.com.ebss.desafio_android_eduardo_seguro

import br.com.ebss.desafio_android_eduardo_seguro.model.Price
import br.com.ebss.desafio_android_eduardo_seguro.model.Thumbnail
import br.com.ebss.desafio_android_eduardo_seguro.model.character.Character
import br.com.ebss.desafio_android_eduardo_seguro.model.comic.Comic
import br.com.ebss.desafio_android_eduardo_seguro.ui.comic.ComicViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ComicUnitTest {

    @Test
    fun isHigherCostComic() {
        val char = Character(
            1,
            "Personagem teste",
            "",
            "",
            Thumbnail("", ""),
            ""
        )
        val comicList = mutableListOf(
            Comic(
                1,
                1,
                "HQ 1 Valor unico 10",
                "",
                "HQ 1 valor unico10",
                mutableListOf(Price("Física", 10.0)),
                Thumbnail("", "")
            ),
            Comic(
                2,
                1,
                "HQ 2 Valor duplo 2 e 6",
                "",
                "HQ 2 valor duplo 2 e 6",
                mutableListOf(Price("Física", 2.0), Price("Digital", 6.0)),
                Thumbnail("", "")
            ), Comic(
                3,
                1,
                "HQ 3 Valor duplo 7 e 1",
                "",
                "HQ 3 valor duplo 7 e 1",
                mutableListOf(Price("Física", 7.0), Price("Digital", 1.0)),
                Thumbnail("", "")
            ), Comic(
                4,
                1,
                "HQ 4 sem valor",
                "",
                "HQ 4 sem valor",
                mutableListOf(),
                Thumbnail("", "")
            ), Comic(
                5,
                1,
                "HQ 5 Valor triplo com negativo 9 / 18 / -20",
                "",
                "HQ 5 valor triplo com negativo 9 / 18 / -20",
                mutableListOf(Price("Física", 9.0), Price("Digital", 18.0), Price("Negativo", -20.0)),
                Thumbnail("", "")
            )
        )

        val expensiveComic = ComicViewModel.getMostExpensiveComic(comicList)
        assertEquals(expensiveComic.prices[0].price, 18.0, 0.1)
    }
}