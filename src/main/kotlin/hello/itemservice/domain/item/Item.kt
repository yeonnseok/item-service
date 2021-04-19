package hello.itemservice.domain.item

data class Item(
    var id: Long = 0,
    val itemName: String,
    val price: Int,
    val quantity: Int
)