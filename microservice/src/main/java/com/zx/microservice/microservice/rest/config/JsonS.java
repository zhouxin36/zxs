package com.zx.microservice.microservice.rest.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.*;
import com.zx.microservice.microservice.bean.User;
import com.fasterxml.jackson.databind.*;
import org.springframework.boot.jackson.*;

import java.io.IOException;

/**
 * the standard Jackson
 *  {@link JsonObjectSerializer}
 *  {@link JsonObjectDeserializer}
 *
 * 实体类注解
 *  {@link JsonSerialize}
 *  {@link JsonDeserialize}
 *
 * @author zhouxin
 * @date 2019-02-09
 */
//@JsonComponent
public class JsonS {

    public static class Serializer extends JsonSerializer<User> {
        @Override
        public void serialize(User value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString("hehe-serialize");
        }
    }
    public static class Deserializer extends JsonDeserializer<User> {
        @Override
        public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

            return new User(1,"2");
        }
    }
}
