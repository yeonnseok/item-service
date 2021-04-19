package hello.itemservice.domain.item

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

internal class ItemRepositoryTest {

    private val itemRepository = ItemRepository()

    @AfterEach
    fun afterEach() {
        itemRepository.clearStore()
    }

    @Test
    fun save() {
        val item = Item(
                itemName = "itemA",
                price = 10000,
                quantity = 10
        )

        val savedItem = itemRepository.save(item)

        val findItem = itemRepository.findById(savedItem.id)

        assertThat(findItem.id).isEqualTo(1)
        assertThat(findItem.itemName).isEqualTo("itemA")
        assertThat(findItem.price).isEqualTo(10)
    }

    @Test
    fun findAll() {
        val item1 = Item(
                itemName = "itemA",
                price = 10000,
                quantity = 10
        )
        val item2 = Item(
                itemName = "itemB",
                price = 20000,
                quantity = 20
        )

        itemRepository.save(item1)
        itemRepository.save(item2)

        val result = itemRepository.findAll()

        assertThat(result.size).isEqualTo(2)
        assertThat(result).contains(item1, item2)
    }
}