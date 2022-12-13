package ps.room.redcard.api

import ps.room.redcard.data.Card
import ps.room.redcard.data.IssueCard
import ps.room.redcard.data.issueCardResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IssueCardApi {
    @POST("issue-card")
    fun issueCard(
        @Body card: IssueCard
    ):Call<issueCardResponse>
}