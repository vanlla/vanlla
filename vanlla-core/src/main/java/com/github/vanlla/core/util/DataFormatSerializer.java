package com.github.vanlla.core.util;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 日期格式化
 *
 * @author Vanlla
 * @since 1.0
 */
public class DataFormatSerializer extends SimpleDateFormatSerializer {

    public DataFormatSerializer(String pattern) {
        super(pattern);
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (object == null) {
            serializer.out.writeString("");
        } else {
            super.write(serializer, object, fieldName, fieldType, features);
        }

    }

}
