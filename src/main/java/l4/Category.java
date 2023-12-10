package l4;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;


public class Category {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    private List<Product> products;


    public Category(Builder builder) {
        this.name = builder.name;
        this.products = new ArrayList<>(builder.products);
    }

    public Category(String name) {
        this.name = name;
        this.products =  new ArrayList<>();
    }
    public Category() {}

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) &&
                Objects.equals(products, category.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, products);
    }

    /**
     * A builder class for constructing Category objects.
     */
    public static class Builder {

        private final String name;
        private List<Product> products;

        /**
         * Constructor for the category builder.
         *
         * @param name The name of the category.
         */
        public Builder(String name) {
            this.name = name;
        }

        public Builder products(List<Product> products) {
            this.products = new ArrayList<>(products);
            return this;
        }

        /**
         * Set the list of products associated with the category.
         *
         * @param product The product to add.
         * @return A category builder.
         */
        public Builder addProduct(Product product) {
            if (this.products == null) {
                this.products = new ArrayList<>();
            }
            this.products.add(product);
            return this;
        }

        /**
         * Set the list of products associated with the category.
         *
         * @param products The list of products.
         * @return A category builder.
         */
        public Builder addAllProducts(List<Product> products) {
            if (this.products == null) {
                this.products = new ArrayList<>();
            }
            this.products.addAll(products);
            return this;
        }

        /**
         * Build a category object.
         *
         * @return A category object.
         */
        public Category build() {
            Category category = new Category(this);
            validate(category);
            return category;
        }

        private void validate(Category category) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<String> validationMessages = new HashSet<>();
            Set<ConstraintViolation<Category>> violations = validator.validate(category);

            for (ConstraintViolation<Category> violation : violations) {
                validationMessages.add(violation.getInvalidValue() + ": " + violation.getMessage());
            }

            if (!violations.isEmpty()) {
                throw new IllegalArgumentException("Invalid fields: " + String.join(", ", validationMessages));
            }
        }
    }

}

