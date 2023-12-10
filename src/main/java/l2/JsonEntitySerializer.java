package l2;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import l2.EntitySerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonEntitySerializer<T> implements EntitySerializer<T> {
    private final ObjectMapper objectMapper;
    private final Class<T> entityClass;
    private static final Logger logger = LoggerFactory.getLogger(JsonEntitySerializer.class);

    public JsonEntitySerializer(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.enable(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION);
    }


    @Override
    public String serialize(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (IOException e) {
            logger.error("Error occurred during serialization:", e);
            return null;
        }
    }

    @Override
    public T deserialize(String data) {
        try {
            return objectMapper.readValue(data, entityClass);
        } catch (IOException e) {
            logger.error("Error occurred during deserialization:", e);
            return null;
        }
    }

    @Override
    public void writeToFile(T entity, String fileName) {
        try {
            objectMapper.writeValue(new File(fileName), entity);
        } catch (IOException e) {
            logger.error("Error occurred while writing to file:", e);
        }
    }

    @Override
    public T readFromFile(String fileName) {
        try {
            return objectMapper.readValue(new File(fileName), entityClass);
        } catch (IOException e) {
            logger.error("Error occurred while reading from file:", e);
            return null;
        }
    }

}
