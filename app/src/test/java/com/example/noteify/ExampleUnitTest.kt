package com.example.noteify

import com.example.noteify.RoomDB.DataConverters
import com.example.noteify.RoomDB.DrawLines
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    val converters: DataConverters = DataConverters()

    @Test
    fun fromStringToListOfListOfDrawlines(){
        var drawLines: MutableList<DrawLines> = mutableListOf()

        for (i in 1..10){
            var pairList : MutableList<Pair<Float,Float>> = mutableListOf(Pair(i.toFloat(),i.toFloat()),Pair(2f,2f))
            drawLines.add(DrawLines(i, path = pairList ))
        }

        var drawLinesList:MutableList<MutableList<DrawLines>> = mutableListOf()
        for (j in 1..10){


        }
    }

    @Test
    fun fromListOfListOfDrawlinesToString(): Unit {
        var drawLines: MutableList<DrawLines?> = mutableListOf()

        for (i in 1..10){
            var pairList : MutableList<Pair<Float,Float>> = mutableListOf(Pair(i.toFloat(),i.toFloat()),Pair(2f,2f))
            drawLines.add(DrawLines(i, path = pairList ))
        }


       // Log.d("TEST" , converters.fromDrawLinesList(drawLines) )

    }

}