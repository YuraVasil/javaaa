package l4;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Product invalidProduct = null;
        try {
            // Create a product with an invalid name (less than 3 characters)
            invalidProduct = new Product.Builder("Phhy")
                    .price(10.99)
                    .expirationDate(LocalDate.of(2023, 12, 31))
                    .quantity(50)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        System.out.println(invalidProduct);
//
        Category invalidCategory = null;
        try {
            // Create a category with an invalid name (more than 50 characters)
            invalidCategory = new Category.Builder("ThisIsAReallyLongCategoryNameThatExceedsFiftyCharactersLimit")
                    .addProduct(new Product.Builder("ValidProduct").price(19.99).expirationDate(null).quantity(1).build())
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        System.out.println(invalidCategory);


        Store invalidStore = null;
        try {
            // Create a store with an invalid name (blank)
            invalidStore = new Store.Builder("ggjii").build();
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        System.out.println(invalidStore);


    }
}
