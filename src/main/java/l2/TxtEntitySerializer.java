package l2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import l1.Category;
import l1.Product;
import l1.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TxtEntitySerializer<T> implements EntitySerializer<T> {
    private static final Logger logger = LoggerFactory.getLogger(TxtEntitySerializer.class);

    @Override
    public String serialize(T entity) {
        if (entity instanceof Category) {
            Category category = (Category) entity;
            StringBuilder sb = new StringBuilder();
//            sb.append("Category\n");
            sb.append(category.getName()).append("\n");

            for (Product product : category.getProducts()) {
                sb.append("Product\n");
                sb.append(product.getName()).append("\n");
                sb.append(product.getPrice()).append("\n");
//                sb.append(product.getCategory().getName()).append("\n");
                sb.append(product.getExpirationDate()).append("\n");
                sb.append(product.getQuantity()).append("\n");
            }

            return sb.toString();
        } else if (entity instanceof Product) {
            Product product = (Product) entity;
            StringBuilder sb = new StringBuilder();
            sb.append(product.getName()).append("\n");
            sb.append(product.getPrice()).append("\n");
//            sb.append(product.getCategory().getName()).append("\n");
            sb.append(product.getExpirationDate()).append("\n");
            sb.append(product.getQuantity()).append("\n");

            return sb.toString();
        } else if (entity instanceof Store) {
            Store store = (Store) entity;
            StringBuilder sb = new StringBuilder();
            sb.append("Store\n");
            sb.append(store.getName()).append("\n");

            for (Category category : store.getCategories()) {
                sb.append("Category\n");
                sb.append(category.getName()).append("\n");

                for (Product product : category.getProducts()) {
                    sb.append("Product\n");
                    sb.append(product.getName()).append("\n");
                    sb.append(product.getPrice()).append("\n");
//                    sb.append(product.getCategory().getName()).append("\n");
                    sb.append(product.getExpirationDate()).append("\n");
                    sb.append(product.getQuantity()).append("\n");
                }
                sb.append("\n");
            }

            return sb.toString();
        } else {
            return null;
        }
    }

    @Override
    public T deserialize(String data) {
        try (Scanner scanner = new Scanner(new ByteArrayInputStream(data.getBytes()))) {
            if (scanner.hasNextLine()) {
                String type = scanner.nextLine();
                switch (type) {
                    case "Category":
                        return (T) readCategory(scanner);
                    case "Product":
                        return (T) readProduct(scanner);
                    case "Store":
                        return (T) readStore(scanner);
                    default:
                        return null;
                }
            }
            return null;
        } catch (Exception e) {
            logger.error("An error occurred:", e);
            return null;
        }
    }

    private Category readCategory(Scanner scanner) {
        String categoryName = scanner.nextLine();
        Category.Builder builder = new Category.Builder(categoryName);

        while (scanner.hasNextLine()) {
            String type = scanner.nextLine();
            if (type.equals("Product")) {
                builder.addProduct(readProduct(scanner));
            } else {
                break;
            }
        }

        return builder.build();
    }

    private Product readProduct(Scanner scanner) {
        String productName = scanner.nextLine();
        double price = Double.parseDouble(scanner.nextLine());
//        String categoryName = scanner.nextLine();
        LocalDate expirationDate = LocalDate.parse(scanner.nextLine());
        int quantity = Integer.parseInt(scanner.nextLine());

//        Category category = new Category.Builder(categoryName).build();
        Product.Builder builder = new Product.Builder(productName, price, expirationDate, quantity);

        return builder.build();
    }

    private Store readStore(Scanner scanner) {
        String storeName = scanner.nextLine();
        Store.Builder builder = new Store.Builder(storeName);

        while (scanner.hasNextLine()) {
            String type = scanner.nextLine();
            if (type.equals("Category")) {
                builder.addCategory(readCategory(scanner));
            } else {
                break;
            }
        }

        return builder.build();
    }

    @Override
    public void writeToFile(T entity, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(serialize(entity));
        } catch (IOException e) {
            logger.error("An error occurred:", e);
        }
    }

    @Override
    public T readFromFile(String fileName) {
        try {
            String data = Files.readString(Paths.get(fileName));
            return deserialize(data);
        } catch (IOException e) {
            logger.error("An error occurred:", e);
            return null;
        }
    }
}
