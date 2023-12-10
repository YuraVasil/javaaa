package l1;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створення категорій
        Category fruits = new Category.Builder("Fruits").build();
        Category vegetables = new Category.Builder("Vegetables").build();

        // Створення продуктів
        Product apple = new Product.Builder("Apple", 1.5, LocalDate.now().plusDays(7), 10).build();
        Product banana = new Product.Builder("Banana", 1.2, LocalDate.now().plusDays(5), 15).build();
        Product carrot = new Product.Builder("Carrot", 0.8, LocalDate.now().plusDays(3), 20).build();
        Product tomato = new Product.Builder("Tomato", 1.0, LocalDate.now().plusDays(4), 18).build();

        // Створення магазину
        Store groceryStore = new Store.Builder("Grocery Store")
                .addCategory(fruits)
                .addCategory(vegetables)
                .build();

        // Додавання продуктів до категорій
        fruits.addProduct(apple);
        fruits.addProduct(banana);
        vegetables.addProduct(carrot);
        vegetables.addProduct(tomato);

        // Вивід інформації про магазин
        System.out.println("Store Details:");
        System.out.println(groceryStore);

        // Отримання списку категорій
        List<Category> categories = groceryStore.getCategories();
        for (Category category : categories) {
            System.out.println("Category: " + category.getName());
            System.out.println("Products: " + category.getProducts());
        }
    }
}