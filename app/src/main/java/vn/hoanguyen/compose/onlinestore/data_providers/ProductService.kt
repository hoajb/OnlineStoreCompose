package vn.hoanguyen.compose.onlinestore.data_providers

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductService @Inject constructor(
    private val gson: Gson
) {
    suspend fun getProductList(
        jsonString: String = productListJson, category: String = ""
    ): List<Product> {
        return withContext(Dispatchers.IO) {
            val productsArray = gson.fromJson(jsonString, Array<Product>::class.java)
            val toList = productsArray.toList()
            if (category.isNotEmpty()) {
                toList.filter { it.category == category }
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
    "[\n" + "  {\n" + "    \"discount\": 10,\n" + "    \"id\": \"1\",\n" + "    \"name\": \"Striped T-Shirt\",\n" + "    \"description\": \"A comfortable and stylish t-shirt with classic stripes.\",\n" + "    \"price\": 25.99,\n" + "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125\",\n" + "    \"category\": \"Men's Clothing\"\n" + "  },\n" + "  {\n" + "    \"discount\": 0,\n" + "    \"id\": \"2\",\n" + "    \"name\": \"Skinny Jeans\",\n" + "    \"description\": \"Slim-fit jeans for a modern and trendy look.\",\n" + "    \"price\": 39.99,\n" + "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125\",\n" + "    \"category\": \"Women's Clothing\"\n" + "  },\n" + "  {\n" + "    \"discount\": 15,\n" + "    \"id\": \"3\",\n" + "    \"name\": \"Hooded Sweatshirt\",\n" + "    \"description\": \"A cozy hooded sweatshirt perfect for casual wear.\",\n" + "    \"price\": 45.50,\n" + "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125\",\n" + "    \"category\": \"Men's Clothing\"\n" + "  },\n" + "  {\n" + "    \"discount\": 20,\n" + "    \"id\": \"4\",\n" + "    \"name\": \"Floral Dress\",\n" + "    \"description\": \"A beautiful floral dress for any special occasion.\",\n" + "    \"price\": 55.00,\n" + "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125\",\n" + "    \"category\": \"Women's Clothing\"\n" + "  },\n" + "  {\n" + "    \"discount\": 0,\n" + "    \"id\": \"5\",\n" + "    \"name\": \"Leather Belt\",\n" + "    \"description\": \"A classic leather belt that adds style to any outfit.\",\n" + "    \"price\": 19.99,\n" + "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125\",\n" + "    \"category\": \"Accessories\"\n" + "  },\n" + "  {\n" + "    \"discount\": 10,\n" + "    \"id\": \"6\",\n" + "    \"name\": \"Casual Sneakers\",\n" + "    \"description\": \"Comfortable sneakers perfect for everyday wear.\",\n" + "    \"price\": 34.99,\n" + "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125\",\n" + "    \"category\": \"Men's Clothing\"\n" + "  },\n" + "  {\n" + "    \"discount\": 0,\n" + "    \"id\": \"7\",\n" + "    \"name\": \"Denim Jacket\",\n" + "    \"description\": \"A stylish denim jacket that never goes out of fashion.\",\n" + "    \"price\": 49.99,\n" + "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125\",\n" + "    \"category\": \"Women's Clothing\"\n" + "  },\n" + "  {\n" + "    \"discount\": 25,\n" + "    \"id\": \"8\",\n" + "    \"name\": \"Designer Sunglasses\",\n" + "    \"description\": \"High-quality sunglasses with a sleek design.\",\n" + "    \"price\": 79.99,\n" + "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125\",\n" + "    \"category\": \"Accessories\"\n" + "  },\n" + "  {\n" + "    \"discount\": 5,\n" + "    \"id\": \"9\",\n" + "    \"name\": \"Button-Up Shirt\",\n" + "    \"description\": \"A classic button-up shirt suitable for any occasion.\",\n" + "    \"price\": 29.99,\n" + "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125\",\n" + "    \"category\": \"Men's Clothing\"\n" + "  },\n" + "  {\n" + "    \"discount\": 0,\n" + "    \"id\": \"10\",\n" + "    \"name\": \"Maxi Skirt\",\n" + "    \"description\": \"A flowy maxi skirt perfect for summer days.\",\n" + "    \"price\": 37.50,\n" + "    \"imageUrl\": \"https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125\",\n" + "    \"category\": \"Women's Clothing\"\n" + "  }\n" + "]\n"