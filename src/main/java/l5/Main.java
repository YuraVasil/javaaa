package l5;

import l1.Category;
import l1.Product;
import l1.Store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class Main {
    public static void main(String[] args) {
        // Створення категорій
        Category fruits = new Category.Builder("Fruits").build();
        Category vegetables = new Category.Builder("Vegetables").build();

        // Створення продуктів
        Product apple = new Product.Builder("Apple", 1.0, LocalDate.now(), 10).build();
        Product banana = new Product.Builder("Banana", 0.5, LocalDate.now(), 20).build();
        Product carrot = new Product.Builder("Carrot", 0.3, LocalDate.now(), 15).build();

        // Додавання продуктів до категорій
        fruits = new Category.Builder(fruits.getName())
                .addProduct(apple)
                .addProduct(banana)
                .build();

        vegetables = new Category.Builder(vegetables.getName())
                .addProduct(carrot)
                .build();

        // Створення магазину з категоріями
        Store store = new Store.Builder("Grocery Store")
                .addCategory(fruits)
                .addCategory(vegetables)
                .build();

        // Вивід результату
        System.out.println(store);
    }
}
