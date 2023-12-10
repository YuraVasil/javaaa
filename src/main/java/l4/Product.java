package l4;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Product {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @Positive(message = "Price must be positive")
    private double price;


    private LocalDate expirationDate;
    @Positive(message = "Quantity must be positive")
    private int quantity;  // New field for quantity

    public Product() {}
    public Product(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
//        this.category = builder.category;
        this.expirationDate = builder.expirationDate;
        this.quantity = builder.quantity;
//        category.addProduct(this);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

//    public Category getCategory() {
//        return category;
//    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
//                ", category=" + category.getName() +
                ", expirationDate=" + expirationDate +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                quantity == product.quantity &&
                Objects.equals(name, product.name) &&
//                Objects.equals(category, product.category) &&
                Objects.equals(expirationDate, product.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, expirationDate, quantity);
    }

    public static class Builder {
        private final String name;
        private double price;
//        private final Category category;
        private LocalDate expirationDate;
        private int quantity;  // New field for quantity

        public Builder(String name) { this.name = name; }

        public Builder price(double price){
            this.price = price;
            return this;
        }
        public Builder expirationDate(LocalDate expirationDate){
            this.expirationDate = expirationDate;
            return this;
        }

        public Builder quantity(int quantity){
            this.quantity = quantity;
            return this;
        }


        public Product build() {
            Product product = new Product(this);
            validate(product);
            return product;
        }

        private void validate(Product product) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<String> validationMessages = new HashSet<>();
            Set<ConstraintViolation<Product>> violations = validator.validate(product);

            for (ConstraintViolation<Product> violation : violations) {
                validationMessages.add(violation.getInvalidValue() + ": " + violation.getMessage());
            }

            if (!violations.isEmpty()) {
                throw new IllegalArgumentException("Invalid fields: " + String.join(", ", validationMessages));
            }
        }
    }
}
