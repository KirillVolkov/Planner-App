package io.techmeskills.an02onl_plannerapp.cloud

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @GET("importNotes")
    suspend fun importNotes(
        @Query("userName") userName: String,
        @Query("phoneId") idPhone: String
    ): Response<List<CloudNote>>

    @POST("exportNotes")
    suspend fun exportNotes(@Body body: ExportNotesRequestBody): Response<Any>

    companion object {
        private const val API_URL = "https://us-central1-plannerapi-2d0bf.cloudfunctions.net"

        fun get(): ApiInterface = Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().build()
            )
            .build().create(ApiInterface::class.java)
    }
}