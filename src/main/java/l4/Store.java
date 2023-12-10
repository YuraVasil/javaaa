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

public class Store {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    private List<Category> categories = new ArrayList<>();
    public Store(String name, List<Category> categories) {
        this.name = name;
        this.categories = new ArrayList<>(categories); // Use the provided categories
    }

    public Store(Builder builder) {
        this.name = builder.name;
        this.categories = (builder.categories != null) ? new ArrayList<>(builder.categories) : new ArrayList<>();
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
        private List<Category> categories;

        /**
         * Constructor for the store builder.
         *
         * @param name The name of the store.
         */
        public Builder(String name) {
            this.name = name;
        }

        public Builder categories(List<Category> categories){
            this.categories = new ArrayList<>(categories);
            return this;
        }

        /**
         * Set the list of categories associated with the store.
         *
         * @param category The list of categories.
         * @return A store builder.
         */
        public Builder addCategory(Category category) {
            if (this.categories == null) {
                this.categories = new ArrayList<>();
            }
            this.categories.add(category);
            return this;
        }

        /**
         * Build a store object.
         *
         * @return A store object.
         */
        public Store build() {
            // Use the setters to initialize the private fields in Store
            Store store = new Store(this);
            validate(store);
            return store;
        }

        private void validate(Store store) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<String> validationMessages = new HashSet<>();
            Set<ConstraintViolation<Store>> violations = validator.validate(store);

            for (ConstraintViolation<Store> violation : violations) {
                validationMessages.add(violation.getInvalidValue() + ": " + violation.getMessage());
            }

            if (!violations.isEmpty()) {
                throw new IllegalArgumentException("Invalid fields: " + String.join(", ", validationMessages));
            }
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


}
