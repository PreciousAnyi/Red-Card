package ps.room.redcard.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import ps.room.redcard.data.issueCardResponse
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface SaveImage {
        @Multipart
        @POST("api/save-image")
        fun saveImage(
            @Part("spNo") spNo: RequestBody?,
            @Part("name") name: RequestBody?,
            @Part file: MultipartBody.Part?,
        ): Call<issueCardResponse>

}