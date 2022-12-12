package ps.room.redcard.api

import ps.room.redcard.data.User
import ps.room.redcard.data.UserData
import ps.room.redcard.data.UserLoginData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface LoginApi {
    @FormUrlEncoded
    @POST("login")
    fun postData(
        @Field("personnelNo") personnelNo: String,
        @Field("password") password: String
    ): Call<UserData>
}