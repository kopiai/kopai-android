package com.kopai.shinkansen

import com.kopai.shinkansen.data.remote.response.StoryItem

object DataDummy {
    fun generateDummyStories(): List<StoryItem> {
        val newsList = ArrayList<StoryItem>()
        for (i in 0..10) {
            val news =
                StoryItem(
                    id = "id_$i",
                    name = "name_$i",
                    description = "description_$i",
                    photoUrl = "photoUrl_$i",
                    createdAt = "createdAt_$i",
                    lat = i.toDouble(),
                    lon = i.toDouble(),
                )
            newsList.add(news)
        }
        return newsList
    }
}
