package com.heydar.simplemvp.data.network

import com.google.gson.Gson
import io.reactivex.rxjava3.core.Single
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor

class OkHttpHelper {

    private val gson = Gson()

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    fun get(url: String, headers: Map<String, String> = emptyMap()): Single<String> {
        return Single.create { emitter ->
            try {
                val requestBuilder = Request.Builder().url(url)

                headers.forEach { (key, value) -> requestBuilder.addHeader(key, value) }

                val request = requestBuilder.build()
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    emitter.onSuccess(response.body?.string() ?: "")
                } else {
                    emitter.onError(Exception("HTTP Error: ${response.code}"))
                }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    fun post(url: String, params: HashMap<String, Any>, headers: Map<String, String> = emptyMap()): Single<String> {
        return Single.create { emitter ->
            try {
                val jsonBody = gson.toJson(params)
                val requestBody = jsonBody.toRequestBody("application/json".toMediaType())

                val requestBuilder = Request.Builder()
                    .url(url)
                    .post(requestBody)

                headers.forEach { (key, value) -> requestBuilder.addHeader(key, value) }

                val request = requestBuilder.build()
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    emitter.onSuccess(response.body?.string() ?: "")
                } else {
                    emitter.onError(Exception("HTTP Error: ${response.code}"))
                }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }
}