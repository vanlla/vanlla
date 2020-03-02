package com.github.vanlla.core.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Fast Json 工具类
 *
 * @author Vanlla
 * @since 1.0
 */
public class FastJsonUtils {

    protected static Logger LOGGER = LoggerFactory.getLogger(FastJsonUtils.class);
    private static final SerializeConfig FASTJSON_CONFIG = new SerializeConfig();
    private static final SerializerFeature[] SERIALIZER_FEATURES;
    private static final SerializerFeature[] SERIALIZER_PRETTY_FEATURES;
    private static final SerializeFilter[] SERIALIZER_FILTERS;
    private static final Feature[] features;

    /**
     * object转json字符串
     *
     * @param object
     * @return json字符串
     */
    public static String toString(Object object) {
        return JSON.toJSONString(object, FASTJSON_CONFIG, SERIALIZER_FILTERS, SERIALIZER_FEATURES);
    }

    /**
     * object转pretty json字符串
     *
     * @param object
     * @return
     */
    public static String toPrettyString(Object object) {
        String json = JSON.toJSONString(object, FASTJSON_CONFIG, SERIALIZER_FILTERS, SERIALIZER_PRETTY_FEATURES);
        return StrUtil.replace(json, "\t", "  ");
    }

    public static Object toBean(String text) {
        return JSON.parse(text, features);
    }

    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz, features);
    }

    public static <T> T toBean(InputStream content, Class<T> clazz) {
        try {
            return JSON.parseObject(content, clazz, features);
        } catch (IOException e) {
            LOGGER.error("InputStream toBean error ", e);
            return null;
        }
    }

    public static <T> Object[] toArray(String text) {
        return toArray(text, (Class) null);
    }

    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    public static byte[] toBytes(Object object) {
        return toString(object).getBytes();
    }

    public static <T> T toBean(byte[] bytes, Class<T> clazz) {
        return toBean(new String(bytes), clazz);
    }

    public static Map toMap(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }

    public static FastJsonHttpMessageConverter getConverter() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializeConfig(FASTJSON_CONFIG);
        fastJsonConfig.setSerializerFeatures(SERIALIZER_FEATURES);
        fastJsonConfig.setSerializeFilters(SERIALIZER_FILTERS);
        fastJsonConfig.setFeatures(features);
        List<MediaType> fastMediaTypes = new ArrayList();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return fastConverter;
    }

    static {
        FASTJSON_CONFIG.put(Date.class, new DataFormatSerializer("yyyy-MM-dd HH:mm:ss"));
        FASTJSON_CONFIG.put(java.sql.Date.class, new DataFormatSerializer("yyyy-MM-dd HH:mm:ss"));
        SERIALIZER_FEATURES = new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.DisableCircularReferenceDetect};
        SERIALIZER_PRETTY_FEATURES = new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.PrettyFormat};
        SERIALIZER_FILTERS = new SerializeFilter[0];
        features = new Feature[]{Feature.IgnoreNotMatch};
    }
}
