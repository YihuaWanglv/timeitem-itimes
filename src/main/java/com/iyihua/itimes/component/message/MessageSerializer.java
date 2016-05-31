package com.iyihua.itimes.component.message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

@Component
public class MessageSerializer implements RedisSerializer<Object> {

	@Override
    public byte[] serialize(Object object) throws SerializationException {
        try {
            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            ObjectOutputStream ous = new ObjectOutputStream(boas);
            ous.writeObject(object);
            byte[] bytes = boas.toByteArray();
            ous.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        try {
            ByteArrayInputStream boas = new ByteArrayInputStream(bytes);
            ObjectInputStream ous = new ObjectInputStream(boas);
            Object object = ous.readObject();
            ous.close();
            return object;
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
