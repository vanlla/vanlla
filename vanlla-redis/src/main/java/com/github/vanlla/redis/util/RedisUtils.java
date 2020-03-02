package com.github.vanlla.redis.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 *
 * @author Vanlla
 * @since 1.0
 */
@Component
public class RedisUtils {
    private static RedisUtil redisUtil;

    public RedisUtils() {
    }

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        RedisUtils.redisUtil = redisUtil;
    }

    public static void setRedisTemplate(StringRedisTemplate redisTemplate) {
        redisUtil.setRedisTemplate(redisTemplate);
    }

    public static StringRedisTemplate getRedisTemplate() {
        return redisUtil.getRedisTemplate();
    }

    public static void set(String key, Object value) {
        redisUtil.set(key, value);
    }

    public static void set(String key, Object value, long expire) {
        redisUtil.set(key, value, expire);
    }

    public static <T> T get(String key, Class<T> clazz, long expire) {
        return redisUtil.get(key, clazz, expire);
    }

    public static <T> T get(String key, Class<T> clazz) {
        return redisUtil.get(key, clazz);
    }

    public static void delete(String key) {
        redisUtil.getRedisTemplate().delete(key);
    }

    public static void delete(Collection<String> keys) {
        redisUtil.getRedisTemplate().delete(keys);
    }

    public static byte[] dump(String key) {
        return redisUtil.getRedisTemplate().dump(key);
    }

    public static Boolean hasKey(String key) {
        return redisUtil.getRedisTemplate().hasKey(key);
    }

    public static Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisUtil.getRedisTemplate().expire(key, timeout, unit);
    }

    public static Boolean expireAt(String key, Date date) {
        return redisUtil.getRedisTemplate().expireAt(key, date);
    }

    public static Set<String> keys(String pattern) {
        return redisUtil.getRedisTemplate().keys(pattern);
    }

    public static Boolean move(String key, int dbIndex) {
        return redisUtil.getRedisTemplate().move(key, dbIndex);
    }

    public static Boolean persist(String key) {
        return redisUtil.getRedisTemplate().persist(key);
    }

    public static Long getExpire(String key, TimeUnit unit) {
        return redisUtil.getRedisTemplate().getExpire(key, unit);
    }

    public static Long getExpire(String key) {
        return redisUtil.getRedisTemplate().getExpire(key);
    }

    public static String randomKey() {
        return (String) redisUtil.getRedisTemplate().randomKey();
    }

    public static void rename(String oldKey, String newKey) {
        redisUtil.getRedisTemplate().rename(oldKey, newKey);
    }

    public static Boolean renameIfAbsent(String oldKey, String newKey) {
        return redisUtil.getRedisTemplate().renameIfAbsent(oldKey, newKey);
    }

    public static void set(String key, String value) {
        redisUtil.getRedisTemplate().opsForValue().set(key, value);
    }

    public static String get(String key) {
        return (String) redisUtil.getRedisTemplate().opsForValue().get(key);
    }

    public static String getRange(String key, long start, long end) {
        return redisUtil.getRedisTemplate().opsForValue().get(key, start, end);
    }

    public static String getAndSet(String key, String value) {
        return (String) redisUtil.getRedisTemplate().opsForValue().getAndSet(key, value);
    }

    public static Boolean getBit(String key, long offset) {
        return redisUtil.getRedisTemplate().opsForValue().getBit(key, offset);
    }

    public static List<String> multiGet(Collection<String> keys) {
        return redisUtil.getRedisTemplate().opsForValue().multiGet(keys);
    }

    public static boolean setBit(String key, long offset, boolean value) {
        return redisUtil.getRedisTemplate().opsForValue().setBit(key, offset, value);
    }

    public static void setEx(String key, String value, long timeout, TimeUnit unit) {
        redisUtil.getRedisTemplate().opsForValue().set(key, value, timeout, unit);
    }

    public static boolean setIfAbsent(String key, String value) {
        return redisUtil.getRedisTemplate().opsForValue().setIfAbsent(key, value);
    }

    public static void setRange(String key, String value, long offset) {
        redisUtil.getRedisTemplate().opsForValue().set(key, value, offset);
    }

    public static Long size(String key) {
        return redisUtil.getRedisTemplate().opsForValue().size(key);
    }

    public static void multiSet(Map<String, String> maps) {
        redisUtil.getRedisTemplate().opsForValue().multiSet(maps);
    }

    public static boolean multiSetIfAbsent(Map<String, String> maps) {
        return redisUtil.getRedisTemplate().opsForValue().multiSetIfAbsent(maps);
    }

    public static Long incrBy(String key, long increment) {
        return redisUtil.getRedisTemplate().opsForValue().increment(key, increment);
    }

    public static Double incrByFloat(String key, double increment) {
        return redisUtil.getRedisTemplate().opsForValue().increment(key, increment);
    }

    public static Integer append(String key, String value) {
        return redisUtil.getRedisTemplate().opsForValue().append(key, value);
    }

    public static Object hGet(String key, String field) {
        return redisUtil.getRedisTemplate().opsForHash().get(key, field);
    }

    public static Map<Object, Object> hGetAll(String key) {
        return redisUtil.getRedisTemplate().opsForHash().entries(key);
    }

    public static List<Object> hMultiGet(String key, Collection<Object> fields) {
        return redisUtil.getRedisTemplate().opsForHash().multiGet(key, fields);
    }

    public static void hPut(String key, String hashKey, String value) {
        redisUtil.getRedisTemplate().opsForHash().put(key, hashKey, value);
    }

    public static void hPut(String key, String hashKey, Object value) {
        redisUtil.getRedisTemplate().opsForHash().put(key, hashKey, value);
    }

    public static void hPutAll(String key, Map<String, String> maps) {
        redisUtil.getRedisTemplate().opsForHash().putAll(key, maps);
    }

    public static Boolean hPutIfAbsent(String key, String hashKey, String value) {
        return redisUtil.getRedisTemplate().opsForHash().putIfAbsent(key, hashKey, value);
    }

    public static Long hDelete(String key, Object... fields) {
        return redisUtil.getRedisTemplate().opsForHash().delete(key, fields);
    }

    public static boolean hExists(String key, String field) {
        return redisUtil.getRedisTemplate().opsForHash().hasKey(key, field);
    }

    public static Long hIncrBy(String key, Object field, long increment) {
        return redisUtil.getRedisTemplate().opsForHash().increment(key, field, increment);
    }

    public static Double hIncrByFloat(String key, Object field, double delta) {
        return redisUtil.getRedisTemplate().opsForHash().increment(key, field, delta);
    }

    public static Set<Object> hKeys(String key) {
        return redisUtil.getRedisTemplate().opsForHash().keys(key);
    }

    public static Long hSize(String key) {
        return redisUtil.getRedisTemplate().opsForHash().size(key);
    }

    public static List<Object> hValues(String key) {
        return redisUtil.getRedisTemplate().opsForHash().values(key);
    }

    public static Cursor<Map.Entry<Object, Object>> hScan(String key, ScanOptions options) {
        return redisUtil.getRedisTemplate().opsForHash().scan(key, options);
    }

    public static String lIndex(String key, long index) {
        return (String) redisUtil.getRedisTemplate().opsForList().index(key, index);
    }

    public static List<String> lRange(String key, long start, long end) {
        return redisUtil.getRedisTemplate().opsForList().range(key, start, end);
    }

    public static Long lLeftPush(String key, String value) {
        return redisUtil.getRedisTemplate().opsForList().leftPush(key, value);
    }

    public static Long lLeftPushAll(String key, String... value) {
        return redisUtil.getRedisTemplate().opsForList().leftPushAll(key, value);
    }

    public static Long lLeftPushAll(String key, Collection<String> value) {
        return redisUtil.getRedisTemplate().opsForList().leftPushAll(key, value);
    }

    public static Long lLeftPushIfPresent(String key, String value) {
        return redisUtil.getRedisTemplate().opsForList().leftPushIfPresent(key, value);
    }

    public static Long lLeftPush(String key, String pivot, String value) {
        return redisUtil.getRedisTemplate().opsForList().leftPush(key, pivot, value);
    }

    public static Long lRightPush(String key, String value) {
        return redisUtil.getRedisTemplate().opsForList().rightPush(key, value);
    }

    public static Long lRightPushAll(String key, String... value) {
        return redisUtil.getRedisTemplate().opsForList().rightPushAll(key, value);
    }

    public static Long lRightPushAll(String key, Collection<String> value) {
        return redisUtil.getRedisTemplate().opsForList().rightPushAll(key, value);
    }

    public static Long lRightPushIfPresent(String key, String value) {
        return redisUtil.getRedisTemplate().opsForList().rightPushIfPresent(key, value);
    }

    public static Long lRightPush(String key, String pivot, String value) {
        return redisUtil.getRedisTemplate().opsForList().rightPush(key, pivot, value);
    }

    public static void lSet(String key, long index, String value) {
        redisUtil.getRedisTemplate().opsForList().set(key, index, value);
    }

    public static String lLeftPop(String key) {
        return (String) redisUtil.getRedisTemplate().opsForList().leftPop(key);
    }

    public static String lBLeftPop(String key, long timeout, TimeUnit unit) {
        return (String) redisUtil.getRedisTemplate().opsForList().leftPop(key, timeout, unit);
    }

    public static String lRightPop(String key) {
        return (String) redisUtil.getRedisTemplate().opsForList().rightPop(key);
    }

    public static String lBRightPop(String key, long timeout, TimeUnit unit) {
        return (String) redisUtil.getRedisTemplate().opsForList().rightPop(key, timeout, unit);
    }

    public static String lRightPopAndLeftPush(String sourceKey, String destinationKey) {
        return (String) redisUtil.getRedisTemplate().opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
    }

    public static String lBRightPopAndLeftPush(String sourceKey, String destinationKey, long timeout, TimeUnit unit) {
        return (String) redisUtil.getRedisTemplate().opsForList().rightPopAndLeftPush(sourceKey, destinationKey, timeout, unit);
    }

    public static Long lRemove(String key, long index, String value) {
        return redisUtil.getRedisTemplate().opsForList().remove(key, index, value);
    }

    public static void lTrim(String key, long start, long end) {
        redisUtil.getRedisTemplate().opsForList().trim(key, start, end);
    }

    public static Long lLen(String key) {
        return redisUtil.getRedisTemplate().opsForList().size(key);
    }

    public static Long sAdd(String key, String... values) {
        return redisUtil.getRedisTemplate().opsForSet().add(key, values);
    }

    public static Long sRemove(String key, Object... values) {
        return redisUtil.getRedisTemplate().opsForSet().remove(key, values);
    }

    public static String sPop(String key) {
        return (String) redisUtil.getRedisTemplate().opsForSet().pop(key);
    }

    public static Boolean sMove(String key, String value, String destKey) {
        return redisUtil.getRedisTemplate().opsForSet().move(key, value, destKey);
    }

    public static Long sSize(String key) {
        return redisUtil.getRedisTemplate().opsForSet().size(key);
    }

    public static Boolean sIsMember(String key, Object value) {
        return redisUtil.getRedisTemplate().opsForSet().isMember(key, value);
    }

    public static Set<String> sIntersect(String key, String otherKey) {
        return redisUtil.getRedisTemplate().opsForSet().intersect(key, otherKey);
    }

    public static Set<String> sIntersect(String key, Collection<String> otherKeys) {
        return redisUtil.getRedisTemplate().opsForSet().intersect(key, otherKeys);
    }

    public static Long sIntersectAndStore(String key, String otherKey, String destKey) {
        return redisUtil.getRedisTemplate().opsForSet().intersectAndStore(key, otherKey, destKey);
    }

    public static Long sIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return redisUtil.getRedisTemplate().opsForSet().intersectAndStore(key, otherKeys, destKey);
    }

    public static Set<String> sUnion(String key, String otherKeys) {
        return redisUtil.getRedisTemplate().opsForSet().union(key, otherKeys);
    }

    public static Set<String> sUnion(String key, Collection<String> otherKeys) {
        return redisUtil.getRedisTemplate().opsForSet().union(key, otherKeys);
    }

    public static Long sUnionAndStore(String key, String otherKey, String destKey) {
        return redisUtil.getRedisTemplate().opsForSet().unionAndStore(key, otherKey, destKey);
    }

    public static Long sUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return redisUtil.getRedisTemplate().opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    public static Set<String> sDifference(String key, String otherKey) {
        return redisUtil.getRedisTemplate().opsForSet().difference(key, otherKey);
    }

    public static Set<String> sDifference(String key, Collection<String> otherKeys) {
        return redisUtil.getRedisTemplate().opsForSet().difference(key, otherKeys);
    }

    public static Long sDifference(String key, String otherKey, String destKey) {
        return redisUtil.getRedisTemplate().opsForSet().differenceAndStore(key, otherKey, destKey);
    }

    public static Long sDifference(String key, Collection<String> otherKeys, String destKey) {
        return redisUtil.getRedisTemplate().opsForSet().differenceAndStore(key, otherKeys, destKey);
    }

    public static Set<String> setMembers(String key) {
        return redisUtil.getRedisTemplate().opsForSet().members(key);
    }

    public static String sRandomMember(String key) {
        return (String) redisUtil.getRedisTemplate().opsForSet().randomMember(key);
    }

    public static List<String> sRandomMembers(String key, long count) {
        return redisUtil.getRedisTemplate().opsForSet().randomMembers(key, count);
    }

    public static Set<String> sDistinctRandomMembers(String key, long count) {
        return redisUtil.getRedisTemplate().opsForSet().distinctRandomMembers(key, count);
    }

    public static Cursor<String> sScan(String key, ScanOptions options) {
        return redisUtil.getRedisTemplate().opsForSet().scan(key, options);
    }

    public static Boolean zAdd(String key, String value, double score) {
        return redisUtil.getRedisTemplate().opsForZSet().add(key, value, score);
    }

    public static Long zAdd(String key, Set<TypedTuple<String>> values) {
        return redisUtil.getRedisTemplate().opsForZSet().add(key, values);
    }

    public static Long zRemove(String key, Object... values) {
        return redisUtil.getRedisTemplate().opsForZSet().remove(key, values);
    }

    public static Double zIncrementScore(String key, String value, double delta) {
        return redisUtil.getRedisTemplate().opsForZSet().incrementScore(key, value, delta);
    }

    public static Long zRank(String key, Object value) {
        return redisUtil.getRedisTemplate().opsForZSet().rank(key, value);
    }

    public static Long zReverseRank(String key, Object value) {
        return redisUtil.getRedisTemplate().opsForZSet().reverseRank(key, value);
    }

    public static Set<String> zRange(String key, long start, long end) {
        return redisUtil.getRedisTemplate().opsForZSet().range(key, start, end);
    }

    public static Set<TypedTuple<String>> zRangeWithScores(String key, long start, long end) {
        return redisUtil.getRedisTemplate().opsForZSet().rangeWithScores(key, start, end);
    }

    public static Set<String> zRangeByScore(String key, double min, double max) {
        return redisUtil.getRedisTemplate().opsForZSet().rangeByScore(key, min, max);
    }

    public static Set<TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max) {
        return redisUtil.getRedisTemplate().opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    public static Set<TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max, long start, long end) {
        return redisUtil.getRedisTemplate().opsForZSet().rangeByScoreWithScores(key, min, max, start, end);
    }

    public static Set<String> zReverseRange(String key, long start, long end) {
        return redisUtil.getRedisTemplate().opsForZSet().reverseRange(key, start, end);
    }

    public static Set<TypedTuple<String>> zReverseRangeWithScores(String key, long start, long end) {
        return redisUtil.getRedisTemplate().opsForZSet().reverseRangeWithScores(key, start, end);
    }

    public static Set<String> zReverseRangeByScore(String key, double min, double max) {
        return redisUtil.getRedisTemplate().opsForZSet().reverseRangeByScore(key, min, max);
    }

    public static Set<TypedTuple<String>> zReverseRangeByScoreWithScores(String key, double min, double max) {
        return redisUtil.getRedisTemplate().opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    public static Set<String> zReverseRangeByScore(String key, double min, double max, long start, long end) {
        return redisUtil.getRedisTemplate().opsForZSet().reverseRangeByScore(key, min, max, start, end);
    }

    public static Long zCount(String key, double min, double max) {
        return redisUtil.getRedisTemplate().opsForZSet().count(key, min, max);
    }

    public static Long zSize(String key) {
        return redisUtil.getRedisTemplate().opsForZSet().size(key);
    }

    public static Long zZCard(String key) {
        return redisUtil.getRedisTemplate().opsForZSet().zCard(key);
    }

    public static Double zScore(String key, Object value) {
        return redisUtil.getRedisTemplate().opsForZSet().score(key, value);
    }

    public static Long zRemoveRange(String key, long start, long end) {
        return redisUtil.getRedisTemplate().opsForZSet().removeRange(key, start, end);
    }

    public static Long zRemoveRangeByScore(String key, double min, double max) {
        return redisUtil.getRedisTemplate().opsForZSet().removeRangeByScore(key, min, max);
    }

    public static Long zUnionAndStore(String key, String otherKey, String destKey) {
        return redisUtil.getRedisTemplate().opsForZSet().unionAndStore(key, otherKey, destKey);
    }

    public static Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return redisUtil.getRedisTemplate().opsForZSet().unionAndStore(key, otherKeys, destKey);
    }

    public static Long zIntersectAndStore(String key, String otherKey, String destKey) {
        return redisUtil.getRedisTemplate().opsForZSet().intersectAndStore(key, otherKey, destKey);
    }

    public static Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return redisUtil.getRedisTemplate().opsForZSet().intersectAndStore(key, otherKeys, destKey);
    }

    public static Cursor<TypedTuple<String>> zScan(String key, ScanOptions options) {
        return redisUtil.zScan(key, options);
    }
}

