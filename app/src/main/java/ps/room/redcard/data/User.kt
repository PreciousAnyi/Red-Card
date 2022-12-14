package ps.room.redcard.data

data class User(
    val __v: Int,
    val _id: String,
    val cards: List<Card>,
    val password: Any,
    val personnelNo: String,
    val phone: Any,
    val profession: String,
    val role: String
)