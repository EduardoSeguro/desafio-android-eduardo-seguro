package br.com.ebss.desafio_android_eduardo_seguro.api

import br.com.ebss.desafio_android_eduardo_seguro.model.character.CharacterResponse
import br.com.ebss.desafio_android_eduardo_seguro.model.comic.ComicResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("v1/public/characters?")
    fun getCharacterList(
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int
    ): Single<CharacterResponse>

    @GET("/v1/public/characters/{characterId}/comics?limit=60")
    fun getComicList(
        @Path("characterId") characterId: Int,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int
    ): Single<ComicResponse>
}
