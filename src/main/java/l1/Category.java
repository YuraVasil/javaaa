package l1;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@XmlRootElement
public class Category {
    @JsonProperty
    @XmlElement
    private String name;
    @JsonProperty
    @XmlElement
    private List<Product> products;


    public Category(Builder builder) {
        this.name = builder.name;
        this.products = builder.products;
    }

    public Category(String name) {
        this.name = name;
        this.products = Collections.unmodifiableList(new ArrayList<>());
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
        private final List<Product> products;

        /**
         * Constructor for the category builder.
         *
         * @param name The name of the category.
         */
        public Builder(String name) {
            this.name = name;
            this.products = new ArrayList<>();
        }

        /**
         * Set the list of products associated with the category.
         *
         * @param product The list of products.
         * @return A category builder.
         */
        public Builder addProduct(Product product) {
            this.products.add(product);
            return this;
        }

        /**
         * Build a category object.
         *
         * @return A category object.
         */
        public Category build() {
            return new Category(this);
        }

        /**
         * Set the list of products associated with the category.
         *
         * @param products The list of products.
         * @return A category builder.
         */
        public Builder addAllProducts(List<Product> products) {
            this.products.addAll(products);
            return this;
        }
    }
}

