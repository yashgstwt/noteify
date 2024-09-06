package com.example.noteify.RoomDB

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class DataConvertersTest {

    private val dataConverter = DataConverters() // Your converter instance

    @Test
    fun `fromDrawLinesList converts empty list to empty string`() {
        val drawLinesList = mutableListOf<DrawLines?>()
        val result = dataConverter.fromDrawLinesList(drawLinesList)
        assertThat(result).isEmpty()
    }

    @Test
    fun `fromDrawLinesList converts list with nulls to string`() {
        val drawLinesList = mutableListOf<DrawLines?>(
            DrawLines(path = mutableListOf(Pair(10f, 10f))),
            DrawLines(path = mutableListOf(Pair(10f, 10f))),

        )
        val result = dataConverter.fromDrawLinesList(drawLinesList)
        assertThat(result).isNotEmpty()
        // Assert that the string contains "null" for the null values
        assertThat(result).contains("null")
    }

    @Test
    fun `fromDrawLinesList converts list with DrawLines to string`() {
        val drawLinesList = mutableListOf<DrawLines?>(
            DrawLines(path = mutableListOf(Pair(10f, 10f), Pair(20f, 20f))),
            DrawLines(path = mutableListOf(), color = 0xFF00FF00, strokeWidth = 10f, alpha = 0.5f)
        )
        val result = dataConverter.fromDrawLinesList(drawLinesList)
        assertThat(result).isNotEmpty()
        // Assert that the string contains expected values (e.g., "10.0", "20.0", "0xFF00FF00")
        assertThat(result).contains("10.0")
        assertThat(result).contains("20.0")
       // assertThat(result).contains("0xFF00FF00")
        assertThat(result).isEqualTo("[ {\"type\": \"DrawLines\",\"path\": [\"{\"first\": 10.0,\"second\": 10.0 }, {\"first\": 20.0,\"second\": 20.0 }] }, {\"type\": \"DrawLines\",\"path\": [],\"color\": \" 0xFF00FF00\",\"strokeWidth\": 10.0,\"alpha\": 0.5 }]")
    }



    @Test
    fun `toDrawLinesList converts string with nulls to list`() {
        val jsonString = "[{\"path\":[{\"first\":10.0,\"second\":10.0},{\"first\":20.0,\"second\":20.0}]},{\"path\":[],\"color\":4278255360,\"strokeWidth\":10.0,\"alpha\":0.5}]"
        val result = dataConverter.toDrawLinesList(jsonString)
        assertThat(result[0]).isEqualTo( DrawLines(path = mutableListOf(Pair(10f, 10f), Pair(20f, 20f))))


    }

//    @Test
//    fun `toDrawLinesList converts string with DrawLines to list`() {
//        val jsonString = "[{\"id\":0,\"path\":[{\"first\":10.0,\"second\":10.0},{\"first\":20.0,\"second\":20.0}],\"color\":-1,\"strokeWidth\":5.0,\"alpha\":1.0},{\"id\":0,\"path\":[],\"color\":-16711936,\"strokeWidth\":10.0,\"alpha\":0.5}]"
//        val result = dataConverter.toDrawLinesList(jsonString)
//        assertThat(result).hasSize(2)
//        assertThat(result[0].path.get(0).first).isEqualTo(10f)
//        assertThat(result[0]?.path?.get(1)?.second).isEqualTo(20f)
//        assertThat(result[1]?.color).isEqualTo(0xFF00FF00.toInt())
//        assertThat(result)
//    }
}