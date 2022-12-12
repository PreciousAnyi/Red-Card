package ps.room.redcard.api

import ps.room.redcard.data.SignUpResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignUpApi {
    @FormUrlEncoded
    @POST("register")
    fun postSignUpData(
        @Field("personnelNo") personnelNo: String,
        @Field("password") password: String
    ): Call<SignUpResponse>
}