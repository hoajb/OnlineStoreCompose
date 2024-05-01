package vn.hoanguyen.compose.onlinestore.data_providers

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductService @Inject constructor(
    private val gson: Gson
) {
    suspend fun getProductList(
        listCategory: List<String> = emptyList()
    ): List<Product> {
        return withContext(Dispatchers.IO) {
            val productsArray = gson.fromJson(productListJson, Array<Product>::class.java)
            val toList = productsArray.toList()
            if (listCategory.isNotEmpty()) {
                toList.filter { listCategory.contains(it.category) }
            } else {
                toList
            }

        }
    }

    suspend fun getProductFilter(): List<String> {
        return withContext(Dispatchers.IO) {
            listOf(
                "All",
                "TShirts",
                "Jeans",
                "Shoes",
                "Jackets",
                "Dresses",
                "Sweaters",
                "Shorts",
                "Skirts",
                "Accessories",
                "Activewear",
                "Outerwear",
                "Pants",
                "Tops"
            )
        }
    }
}


private const val productListJson =
    "[\n" +
            "  {\n" +
            "    \"discount\": 10,\n" +
            "    \"id\": \"1\",\n" +
            "    \"name\": \"Striped T-Shirt\",\n" +
            "    \"description\": \"A comfortable and stylish t-shirt with classic stripes.\",\n" +
            "    \"price\": 25.99,\n" +
            "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125\",\n" +
            "    \"category\": \"TShirts\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"discount\": 0,\n" +
            "    \"id\": \"2\",\n" +
            "    \"name\": \"Skinny Jeans\",\n" +
            "    \"description\": \"Slim-fit jeans for a modern and trendy look.\",\n" +
            "    \"price\": 39.99,\n" +
            "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/955/610/3241955610_4_1_8.jpg?t=1712674638757&imwidth=563\",\n" +
            "    \"category\": \"Jeans\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"discount\": 15,\n" +
            "    \"id\": \"3\",\n" +
            "    \"name\": \"Hooded Sweatshirt\",\n" +
            "    \"description\": \"A cozy hooded sweatshirt perfect for casual wear.\",\n" +
            "    \"price\": 45.50,\n" +
            "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/4248/901/802/4248901802_4_1_8.jpg?t=1714377795252&imwidth=850\",\n" +
            "    \"category\": \"Tops\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"discount\": 20,\n" +
            "    \"id\": \"4\",\n" +
            "    \"name\": \"Floral Dress\",\n" +
            "    \"description\": \"A beautiful floral dress for any special occasion.\",\n" +
            "    \"price\": 55.00,\n" +
            "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/573/800/3241573800_4_1_8.jpg?t=1713773726829&imwidth=563\",\n" +
            "    \"category\": \"Dresses\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"discount\": 0,\n" +
            "    \"id\": \"5\",\n" +
            "    \"name\": \"Leather Belt\",\n" +
            "    \"description\": \"A classic leather belt that adds style to any outfit.\",\n" +
            "    \"price\": 19.99,\n" +
            "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3201/502/526/3201502526_4_1_8.jpg?t=1712319520207&imwidth=563\",\n" +
            "    \"category\": \"Accessories\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"discount\": 10,\n" +
            "    \"id\": \"6\",\n" +
            "    \"name\": \"Casual Sneakers\",\n" +
            "    \"description\": \"Comfortable sneakers perfect for everyday wear.\",\n" +
            "    \"price\": 34.99,\n" +
            "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/592/251/3241592251_4_1_8.jpg?t=1713168674410&imwidth=563\",\n" +
            "    \"category\": \"Shoes\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"discount\": 0,\n" +
            "    \"id\": \"7\",\n" +
            "    \"name\": \"Denim Jacket\",\n" +
            "    \"description\": \"A stylish denim jacket that never goes out of fashion.\",\n" +
            "    \"price\": 49.99,\n" +
            "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3243/561/800/3243561800_4_1_8.jpg?t=1711381451267&imwidth=563\",\n" +
            "    \"category\": \"Jackets\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"discount\": 25,\n" +
            "    \"id\": \"8\",\n" +
            "    \"name\": \"Designer Sunglasses\",\n" +
            "    \"description\": \"High-quality sunglasses with a sleek design.\",\n" +
            "    \"price\": 79.99,\n" +
            "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/1/2/p/5028/340/040/5028340040_4_1_8.jpg?t=1710420307772&imwidth=750\",\n" +
            "    \"category\": \"Accessories\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"discount\": 5,\n" +
            "    \"id\": \"9\",\n" +
            "    \"name\": \"Button-Up Shirt\",\n" +
            "    \"description\": \"A classic button-up shirt suitable for any occasion.\",\n" +
            "    \"price\": 29.99,\n" +
            "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/4248/900/505/4248900505_4_1_8.jpg?t=1714386391517&imwidth=850\",\n" +
            "    \"category\": \"TShirts\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"discount\": 0,\n" +
            "    \"id\": \"10\",\n" +
            "    \"name\": \"Maxi Skirt\",\n" +
            "    \"description\": \"A flowy maxi skirt perfect for summer days.\",\n" +
            "    \"price\": 37.50,\n" +
            "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/593/300/3241593300_4_1_8.jpg?t=1713168078766&imwidth=563\",\n" +
            "    \"category\": \"Skirts\"\n" +
            "  }\n" +
            "]"