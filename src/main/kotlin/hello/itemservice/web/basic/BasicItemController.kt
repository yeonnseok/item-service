package hello.itemservice.web.basic

import hello.itemservice.domain.item.Item
import hello.itemservice.domain.item.ItemRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.annotation.PostConstruct

@Controller
@RequestMapping("/basic/items")
class BasicItemController(
    private val itemRepository: ItemRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun items(model: Model): String {
        val items = itemRepository.findAll()
        model.addAttribute("items", items)
        return "basic/items"
    }

    @GetMapping("/{itemId}")
    fun item(
        @PathVariable itemId: Long,
        model: Model): String {
        val item = itemRepository.findById(itemId)
        model.addAttribute("item", item)
        return "basic/item"
    }

    @GetMapping("/add")
    fun addForm(): String {
        return "basic/addForm"
    }

    @PostMapping("/add")
    fun addItem(item: Item, redirectAttributes: RedirectAttributes): String {
        val saved = itemRepository.save(item)
        redirectAttributes.addAttribute("itemId", saved.id)
        redirectAttributes.addAttribute("status", true)
        return "redirect:/basic/items/{itemId}"
    }

    @GetMapping("/{itemId}/edit")
    fun editForm(@PathVariable itemId: Long, model: Model): String {
        val item = itemRepository.findById(itemId)
        model.addAttribute("item", item)
        return "basic/editForm"
    }

    @PostMapping("/{itemId}/edit")
    fun updateItem(@PathVariable itemId: Long, updateParam: Item): String {
        itemRepository.update(itemId, updateParam)
        return "redirect:/basic/items/{itemId}"
    }

    @PostConstruct
    fun init() {
        itemRepository.save(Item(itemName = "itemA", price = 10000, quantity = 10))
        itemRepository.save(Item(itemName = "itemB", price = 20000, quantity = 20))
    }
}