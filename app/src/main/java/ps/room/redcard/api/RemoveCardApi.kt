package ps.room.redcard.api

import ps.room.redcard.data.IssueCard
import ps.room.redcard.data.issueCardResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoveCardApi {
    @POST("remove-card")
    fun removeCard(
        @Body card: IssueCard
    ):Call<issueCardResponse>

}