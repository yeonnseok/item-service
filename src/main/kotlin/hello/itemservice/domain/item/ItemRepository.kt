package hello.itemservice.domain.item

import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class ItemRepository {

    private val store = HashMap<Long, Item>()
    private var sequence: Long = 0L

    fun save(item: Item): Item {
        item.id = ++sequence
        store.put(item.id, item)
        return item
    }

    fun findById(id: Long): Item {
        if (store.containsKey(id)) {
            return store.get(id)!!
        }
        throw IllegalArgumentException()
    }

    fun findAll(): List<Item> {
        return store.values.toList()
    }

    fun update(itemId: Long, updateParam: Item) {
        val findItem = findById(itemId)
        val updated = findItem.copy(
                itemName = updateParam.itemName,
                price = updateParam.price,
                quantity = updateParam.quantity)
        store.put(itemId, updated)
    }

    fun clearStore() {
        store.clear()
    }
}