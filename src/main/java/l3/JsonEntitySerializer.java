package l3;

import l2.EntitySerializer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.*;

public class JsonEntitySerializer<T> implements EntitySerializer<T> {
    //

    // Метод для сортування списку об'єктів за допомогою Comparable
    public void sortEntities(List<T> entities) {
        if (entities.get(0) instanceof Comparable) {
            sort(unmodifiableList(entities));
        }
    }

    private void sort(List<T> ts) {
    }

    // Метод для сортування списку об'єктів за допомогою Comparator
    public void sortEntitiesWithComparator(List<T> entities, Comparator<? super T> comparator) {
        sort(entities, comparator);
    }

    private void sort(List<T> entities, Comparator<? super T> comparator) {

    }

    // Метод для фільтрації списку об'єктів за певною умовою за допомогою Stream API
    public List<T> filterEntities(List<T> entities, SomeCondition<T> condition) {
        return entities.stream()
                .filter(condition::test)
                .toList();
    }

    @Override
    public String serialize(T entity) {
        return null;
    }

    @Override
    public T deserialize(String data) {
        return null;
    }

    @Override
    public void writeToFile(T entity, String fileName) {

    }

    @Override
    public T readFromFile(String fileName) {
        return null;
    }
}
