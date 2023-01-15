package ps.room.redcard.api

import android.graphics.Bitmap
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ps.room.redcard.data.IssueCard
import ps.room.redcard.data.issueCardResponse
import retrofit2.Call
import retrofit2.http.*


//interface IssueCardApi {
//    @POST("issue-card")
//    fun issueCard(
//        @Body card: IssueCard
//    ):Call<issueCardResponse>
//}
interface IssueCardApi {
    @Multipart
    @POST("api/issue-card")
    fun issueCard(
        @Part("personnelNo") personnelNo: RequestBody?,
        @Part("points") points: RequestBody?,
        @Part("offense") offense: RequestBody?,
        @Part("studentRegNo") studentRegNo: RequestBody?,

        @Part("chiefSpNo") chiefSpNo: RequestBody?,
        @Part("examOfficerSpNo") examOfficerSpNo: RequestBody?,
//        @Part("spsign") spsign: Bitmap,
//        @Part("chiefsign") chiefsign: Bitmap,
//        @Part("examOffsign") examsign: Bitmap,
//        @Part spsign: MultipartBody.Part,
        @Part spsign: MultipartBody.Part?,
        @Part chiefsign: MultipartBody.Part?,
        @Part examOffsign: MultipartBody.Part?

    ):Call<issueCardResponse>
}

//@Multipart
//@PUT("user/photo")
//fun updateUser(
//    @Part("photo") photo: RequestBody?,
//    @Part("description") description: RequestBody?
//): Call<User?>?