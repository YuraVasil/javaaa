package l1;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement
public class Store {
    @JsonProperty
    @XmlElement
    private String name;
    @JsonProperty
    @XmlElement
    private List<Category> categories = new ArrayList<>();

    public Store(String name, List<Category> categories) {
        this.name = name;
        this.categories = categories;
    }
    public Store() {}
    public String getName() {
        return name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }
    public void addAllCategories(List<Category> categories) {
        this.categories.addAll(categories);
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", categories=" + categories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(name, store.name) &&
                Objects.equals(categories, store.categories);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, categories);
    }

    /**
     * A builder class for constructing store objects.
     */
    public static class Builder {
        private final String name;
        private final List<Category> categories;

        /**
         * Constructor for the store builder.
         *
         * @param name The name of the store.
         */
        public Builder(String name) {
            this.name = name;
            this.categories = new ArrayList<>();
        }

        /**
         * Set the list of categories associated with the store.
         *
         * @param category The list of categories.
         * @return A store builder.
         */
        public Builder addCategory(Category category) {
            this.categories.add(category);
            return this;
        }

        /**
         * Build a store object.
         *
         * @return A store object.
         */
        public Store build() {
            return new Store(this);
        }

        /**
         * Set the list of categories associated with the store.
         *
         * @param categories The list of categories.
         * @return A store builder.
         */
        public Builder addAllCategories(List<Category> categories) {
            this.categories.addAll(categories);
            return this;
        }
    }

    private Store(Builder builder) {
        this.name = builder.name;
        this.categories = builder.categories;
    }
}
