package $package$.$name;format="normalize"$.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vavr.control.Try;

/**
 * JsonUtil
 */
@Component
public class JsonUtil {

    @Autowired
    private ObjectMapper mapper;

    public Try<String> stringify(Object object) {
        return Try.of(() -> mapper.writeValueAsString(object));
    }

    public <T> Try<T> parse(String json, Class<T> valueType) {
        return Try.of(() -> mapper.readValue(json, valueType));
    }

    public <T> Try<T> parse(String json, TypeReference<T> valueTypeRef) {
        return Try.of(() -> mapper.readValue(json, valueTypeRef));
    }

}
