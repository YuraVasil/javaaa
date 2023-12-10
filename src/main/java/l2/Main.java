package l2;

import l1.Category;
import l1.Product;
import l1.Store;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create sample data
        Category category1 = new Category.Builder("Electronics").build();
        category1.addProduct(new Product.Builder("Laptop", 999.99, LocalDate.now(), 10).build());
        category1.addProduct(new Product.Builder("Smartphone", 499.99, LocalDate.now(), 20).build());

        Category category2 = new Category.Builder("Clothing").build();
        category2.addProduct(new Product.Builder("T-Shirt", 19.99, LocalDate.now(), 30).build());
        category2.addProduct(new Product.Builder("Jeans", 49.99, LocalDate.now(), 15).build());

        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        Store store = new Store.Builder("MyStore")
                .addAllCategories(categories)
                .build();

        // Serialize and deserialize using TxtEntitySerializer
        EntitySerializer<Store> txtSerializer = new TxtEntitySerializer<>();
        String txtFileName = "store.txt";
        txtSerializer.writeToFile(store, txtFileName);
        Store deserializedStoreFromTxt = txtSerializer.readFromFile(txtFileName);

        // Serialize and deserialize using JsonEntitySerializer
        EntitySerializer<Store> jsonSerializer = new JsonEntitySerializer<>(Store.class);
        String jsonFileName = "store.json";
        jsonSerializer.writeToFile(store, jsonFileName);
        Store deserializedStoreFromJson = jsonSerializer.readFromFile(jsonFileName);

        // Serialize and deserialize using XmlEntitySerializer
        EntitySerializer<Store> xmlSerializer = new XmlEntitySerializer<>(Store.class);
        String xmlFileName = "store.xml";
        xmlSerializer.writeToFile(store, xmlFileName);
        Store deserializedStoreFromXml = xmlSerializer.readFromFile(xmlFileName);

        // Print the deserialized objects
        System.out.println("Deserialized Store from Txt: " + deserializedStoreFromTxt);
        System.out.println("Deserialized Store from Json: " + deserializedStoreFromJson);
        System.out.println("Deserialized Store from Xml: " + deserializedStoreFromXml);
    }
}
