package l1;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import l2.LocalDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement
public class Product {
    @JsonProperty
    @XmlElement
    private String name;
    @JsonProperty
    @XmlElement
    private double price;

    @JsonProperty
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate expirationDate;
    @JsonProperty
    @XmlElement
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
        private final double price;
//        private final Category category;
        private final LocalDate expirationDate;
        private final int quantity;  // New field for quantity

        public Builder(String name, double price, LocalDate expirationDate, int quantity) {
            this.name = name;
            this.price = price;
            this.expirationDate = expirationDate;
            this.quantity = quantity;
        }


        public Product build() {
            return new Product(this);
        }
    }
}
