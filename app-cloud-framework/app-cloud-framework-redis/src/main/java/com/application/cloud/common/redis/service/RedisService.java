package com.application.cloud.common.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * spring redis 工具类
 * https://blog.csdn.net/xinhuashudiao/article/details/84906538
 * @author cloud
 **/
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class RedisService
{
    @Autowired
    public RedisTemplate redisTemplate;
    
	/**
	 * 设置有效时间
	 *
	 * @param key Redis键
	 * @param timeout 超时时间
	 * @return true=设置成功；false=设置失败
	 */
	public Boolean expire(final String key, final long timeout)
	{
		return expire(key, timeout, TimeUnit.SECONDS);
	}
	
	/**
	 * 设置有效时间
	 *
	 * @param key Redis键
	 * @param timeout 超时时间
	 * @param unit 时间单位
	 * @return true=设置成功；false=设置失败
	 */
	public Boolean expire(final String key, final long timeout, final TimeUnit unit)
	{
		return redisTemplate.expire(key, timeout, unit);
	}
	
	/**
	 * 获得过期时间
	 * @param key
	 * @return
	 */
	public Long getExpire(final String key,final TimeUnit timeUnit) {
		return redisTemplate.getExpire(key,timeUnit);
	}
	
	/**
	 * 获得过期时间
	 * @param key
	 * @return
	 */
	public Long getExpire(final String key) {
		return redisTemplate.getExpire(key);
	}
	
	/**
	 * 是否有key
	 * @param key
	 * @return
	 */
	public Boolean hasKey(final String key) {
		return redisTemplate.hasKey(key);
	}
    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value)
    {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param expireTime 时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final long expireTime, final TimeUnit timeUnit)
    {
        redisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
    }
	
	/**
	 * 追加值到指定的key上
	 * @param key
	 * @param value
	 * @return
	 */
	public Integer appendObject(final String key ,final String value)
	{
		return redisTemplate.opsForValue().append(key,value);
	}
	
	/**
	 * 追加值到指定的key上
	 * @param key
	 * @param value
	 * @return
	 */
	public Integer appendObject(final String key ,final String value,final long expireTime)
	{
		Integer count = redisTemplate.opsForValue().append(key,value);
		expire(key,expireTime);
		return count == null ? 0 : count;
	}
	
	/**
	 * 通过key获取所有的值
	 * @param keys
	 * @return
	 */
	public <T> List<T> getAndSetObject(final Collection keys)
	{
		return redisTemplate.opsForValue().multiGet(keys);
	}
	
	/**
	 * 追加值到指定的key上
	 * @param key
	 * @param value
	 * @return
	 */
	public Object getAndSetObject(final String key ,final String value)
	{
		return redisTemplate.opsForValue().getAndSet(key,value);
	}
	
	
	
	/**
	 * 追加值到指定的key上
	 * @param key
	 * @param value
	 * @return
	 */
	public Object getAndSetObject(final String key ,final String value,final long expireTime)
	{
		Object object =  redisTemplate.opsForValue().getAndSet(key,value);
		expire(key,expireTime);
		return object == null ? "" : object;
	}
	
	/**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public Object getCacheString(final String key)
    {
        Object object = redisTemplate.opsForValue().get(key);
	    return object == null ? "" : object;
    }
	
	/**
	 * 获得缓存的基本对象。
	 *
	 * @param key 缓存键值
	 * @return 缓存键值对应的数据
	 */
	public <T> T getCacheObject(final String key)
	{
		ValueOperations<String, T> operation = redisTemplate.opsForValue();
		return operation.get(key);
	}
	
    /**
     * 删除单个对象
     *
     * @param key
     */
    public Boolean deleteObject(final String key)
    {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public Long deleteObject(final Collection collection)
    {
        return redisTemplate.delete(collection);
    }
	
	
	/**
	 * 删除集合对象
	 *
	 * @param keys 多个对象
	 * @return
	 */
	public Long deleteObject(final List<String> keys)
	{
		return redisTemplate.delete(keys);
	}
	
	/**
	 * 增加 1
	 * @param key
	 * @return
	 */
	public Long incrObject(String key) {
		return redisTemplate.opsForValue().increment(key);
	}
	
	/**
	 * 增加
	 * @param key
	 * @param delta
	 * @return
	 */
	public Long incrObject(String key, Long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}
	
	/**
	 * 减去 1
	 * @param key
	 * @return
	 */
	public Long decrObject(String key) {
		return redisTemplate.opsForValue().decrement(key);
	}
	
	/**
	 * 减去
	 * @param key
	 * @param delta
	 * @return
	 */
	public Long decrObject(String key, Long delta) {
		return redisTemplate.opsForValue().decrement(key, delta);
	}
	
	/**
	 * 缓存List数据
	 *
	 * @param key 缓存的键值
	 * @param value 待缓存的值
	 * @return 缓存的对象
	 */
	public <T> Long listRightPush(final String key, final Object value)
	{
		Long count = redisTemplate.opsForList().rightPush(key, value);
		return count == null ? 0 : count;
	}
	
	/**
	 * 缓存List数据
	 *
	 * @param key 缓存的键值
	 * @param value 待缓存的值
	 * @param expireTime 时间秒
	 * @param <T> 缓存的对象
	 * @return
	 */
	public <T> Long listRightPush(final String key, final Object value,final long expireTime)
	{
		Long count = redisTemplate.opsForList().rightPush(key, value);
		expire(key,expireTime);
		return count == null ? 0 : count;
	}
	
	/**
	 * 缓存List数据
	 *
	 * @param key 缓存的键值
	 * @param values 待缓存的值
	 * @return 缓存的对象
	 */
	public <T> Long listRightPush(final String key, final Object... values)
	{
		Long count = redisTemplate.opsForList().rightPushAll(key, values);
		return count == null ? 0 : count;
	}
 
	/**
	 * 缓存List数据
	 *
	 * @param key 缓存的键值
	 * @param values 待缓存的值
	 * @return 缓存的对象
	 */
	public <T> Long listRightPush(final String key,final long expireTime, final Object... values)
	{
		Long count = redisTemplate.opsForList().rightPushAll(key, values);
		expire(key,expireTime);
		return count == null ? 0 : count;
	}
	
	/**
	 * 缓存List数据
	 *
	 * @param key 缓存的键值
	 * @param dataList 待缓存的List数据
	 * @return 缓存的对象
	 */
	public <T> Long listRightPush(final String key, final List<T> dataList)
	{
		Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
		return count == null ? 0 : count;
	}
	
	/**
	 * 缓存List数据
	 *
	 * @param key 缓存的键值
	 * @param expireTime 失效时间
	 * @param dataList 待缓存的List数据
	 * @param <T>
	 * @return
	 */
	public <T> Long listRightPush(final String key,final long expireTime, final List<T> dataList)
	{
		Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
		expire(key,expireTime);
		return count == null ? 0 : count;
	}
	
	/**
	 * 获取 List 数据
	 *
	 * @param key 缓存的键值
	 * @return 缓存的对象
	 */
	public <T> T listLeftPop(final String key)
	{
		ListOperations<String,T> operations = redisTemplate.opsForList();
		return operations.leftPop(key);
	}
	
	/**
	 * 缓存List数据
	 *
	 * @param key 缓存的键值
	 * @param value 待缓存的值
	 * @return 缓存的对象
	 */
	public <T> Long listLeftPush(final String key, final Object value)
	{
		Long count = redisTemplate.opsForList().leftPush(key, value);
		return count == null ? 0 : count;
	}
	
	/**
	 * 缓存List数据
	 *
	 * @param key 缓存的键值
	 * @param value 待缓存的值
	 * @param expireTime 时间秒
	 * @param <T> 缓存的对象
	 * @return
	 */
	public <T> Long listLeftPush(final String key, final Object value,final long expireTime)
	{
		Long count = redisTemplate.opsForList().leftPush(key, value);
		expire(key,expireTime);
		return count == null ? 0 : count;
	}
	
	/**
	 * 缓存List数据
	 *
	 * @param key 缓存的键值
	 * @param values 待缓存的值
	 * @return 缓存的对象
	 */
	public <T> Long listLeftPush(final String key, final Object... values)
	{
		Long count = redisTemplate.opsForList().leftPushAll(key, values);
		return count == null ? 0 : count;
	}
	
	/**
	 * 缓存List数据
	 *
	 * @param key 缓存的键值
	 * @param values 待缓存的值
	 * @return 缓存的对象
	 */
	public <T> Long listLeftPush(final String key,final long expireTime, final Object... values)
	{
		Long count = redisTemplate.opsForList().leftPushAll(key, values);
		expire(key,expireTime);
		return count == null ? 0 : count;
	}
	
	/**
	 * 缓存List数据
	 *
	 * @param key 缓存的键值
	 * @param dataList 待缓存的List数据
	 * @return 缓存的对象
	 */
	public <T> Long listLeftPush(final String key, final List<T> dataList)
	{
		Long count = redisTemplate.opsForList().leftPushAll(key, dataList);
		return count == null ? 0 : count;
	}
	
	/**
	 * 缓存List数据
	 *
	 * @param key 缓存的键值
	 * @param expireTime 失效时间
	 * @param dataList 待缓存的List数据
	 * @param <T>
	 * @return
	 */
	public <T> Long listLeftPush(final String key,final long expireTime, final List<T> dataList)
	{
		Long count = redisTemplate.opsForList().leftPushAll(key, dataList);
		expire(key,expireTime);
		return count == null ? 0 : count;
	}
	
	/**
	 * 获取 List 数据
	 *
	 * @param key 缓存的键值
	 * @return 缓存的对象
	 */
	public <T> T listRightPop(final String key)
	{
		ListOperations<String,T> operations = redisTemplate.opsForList();
		return operations.rightPop(key);
	}
	
	/**
	 * 用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
	 *
	 * @param sourceKey 源list的最右边的值
	 * @param destinationKey 目的list的最左边的值
	 * @param <T> 返回结果
	 * @return
	 */
	public <T> T rightPopAndLeftPush(final String sourceKey,final String destinationKey)
	{
		ListOperations<String,T> operations = redisTemplate.opsForList();
		return operations.rightPopAndLeftPush(sourceKey,destinationKey);
	}
	
	/**
	 * 删除一个key
	 * @param key
	 * @param index
	 * @param value
	 * @param <T>
	 * @return
	 */
	public <T> Long listRemoveValue(final String key,final long index, final Object value)
	{
		Long count = redisTemplate.opsForList().remove(key, index,value);
		return count == null ? 0 : count;
	}
	
   /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getAllList(final String key)
    {
        return redisTemplate.opsForList().range(key, 0, -1);
    }
    
	/**
	 * 获得缓存的list对象
	 *
	 * @param key 缓存的键值
	 * @return 缓存键值对应的数据
	 */
	public <T> List<T> getListRange(final String key,final long start,final long end)
	{
		return redisTemplate.opsForList().range(key, start, end);
	}
	
	/**
	 * 获得缓存的list对象的一个
	 *
	 * @param key 缓存的键值
	 * @return 缓存键值对应的数据
	 */
	public <T> Object getListOne(final String key,final long index)
	{
		return redisTemplate.opsForList().index(key,index);
	}
	
	/**
	 * 获得缓存的list大小
	 *
	 * @param key 缓存的键值
	 * @return 缓存键值对应的数据
	 */
	public Long getListSize(final String key)
	{
		return redisTemplate.opsForList().size(key);
	}
	
	/**
	 * 获得操作list的实例
	 * @return
	 */
	public ListOperations getListOperations(){
		return redisTemplate.opsForList();
	}
	
	
	/**
	 * 缓存Set
	 *
	 * @param key 缓存键值
	 * @param datas 缓存的数据
	 * @return 缓存数据的对象
	 */
	public <T> Long addSet(final String key, final Object... datas)
	{
		Long count = redisTemplate.opsForSet().add(key, datas);
		return count == null ? 0 : count;
	}
	
	/**
	 * 缓存Set
	 * @param key 缓存键值
	 * @param datas 缓存的数据
	 * @param expireTime 失效时间
	 * @param <T>
	 * @return
	 */
	public <T> Long addSet(final String key,final long expireTime, final Object... datas)
	{
		Long count = redisTemplate.opsForSet().add(key, datas);
		expire(key,expireTime);
		return count == null ? 0 : count;
	}
	
    /**
     * 缓存Set
     *
     * @param key 缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> Long addSet(final String key, final Set<T> dataSet)
    {
        Long count = redisTemplate.opsForSet().add(key, dataSet);
        return count == null ? 0 : count;
    }
	
	/**
	 * 缓存Set
	 * @param key 缓存键值
	 * @param dataSet 缓存的数据
	 * @param expireTime 失效时间
	 * @param <T>
	 * @return
	 */
	public <T> Long addSet(final String key,final long expireTime, final Set<T> dataSet)
	{
		Long count = redisTemplate.opsForSet().add(key, dataSet);
		expire(key,expireTime);
		return count == null ? 0 : count;
	}
	
	/**
	 * 是否是set中的元素
	 *
	 * @param key 缓存键值
	 * @param value 缓存的数据
	 * @return 缓存数据的对象
	 */
	public boolean isSetMember(final String key, final Object value)
	{
		return redisTemplate.opsForSet().isMember(key, value);
	}
	
	/**
	 * set 的值大小
	 *
	 * @param key 缓存键值
	 * @return 缓存数据的对象
	 */
	public Long getSetSize(final String key)
	{
		return redisTemplate.opsForSet().size(key);
	}
	
	/**
	 * 删除set中的某些元素
	 *
	 * @param key 缓存键值
	 * @return 缓存数据的对象
	 */
	public Long removeSetValue(final String key,final Object... values)
	{
		return redisTemplate.opsForSet().remove(key,values);
	}
	
    /**
     * 获得缓存的 set 所有值
     *
     * @param key
     * @return
     */
    public <T> Set<T> getSetVals(final String key)
    {
        return redisTemplate.opsForSet().members(key);
    }
	
	/**
	 * 弹出变量中的元素的值
	 *
	 * @param key
	 * @return
	 */
	public <T> T getSetVal(final String key)
	{
		SetOperations<String,T> operations = redisTemplate.opsForSet();
		return operations.pop(key);
	}
	
	/**
	 * 获取两个集合中的不同值并返回.
	 * @param sourceKey
	 * @param destKey
	 * @param <T>
	 * @return
	 */
	public <T> Set<T> getDiffSetVals(final String sourceKey,final String destKey)
	{
		SetOperations<String, T> operations = redisTemplate.opsForSet();
		return operations.difference(sourceKey,destKey);
	}
	
	/**
	 * 获取两个集合中的相同同值并返回.
	 * @param sourceKey
	 * @param destKey
	 * @param <T>
	 * @return
	 */
	public <T> Set<T> getSameSetVals(final String sourceKey,final String destKey)
	{
		SetOperations<String, T> operations = redisTemplate.opsForSet();
		return operations.intersect(sourceKey,destKey);
	}
	
	/**
	 * 获取两个集合中的所有值并返回.
	 * @param sourceKey
	 * @param destKey
	 * @param <T>
	 * @return
	 */
	public <T> Set<T> getAllSetVals(final String sourceKey,final String destKey)
	{
		SetOperations<String, T> operations = redisTemplate.opsForSet();
		return operations.union(sourceKey,destKey);
	}
	
	/**
	 * 缓存Map
	 * @param key
	 * @param hashKey
	 * @param hashValue
	 * @param <T>
	 */
	public <T> void putMapValue(final String key, final String hashKey, final Object hashValue)
	{
		redisTemplate.opsForHash().put(key,hashKey,hashValue);
	}
	
	/**
	 * 缓存Map
	 * @param key
	 * @param hashKey
	 * @param hashValue
	 * @param <T>
	 */
	public <T> void putMapValue(final String key, final String hashKey, final Object hashValue,final long expireTime)
	{
		redisTemplate.opsForHash().put(key,hashKey,hashValue);
		expire(key,expireTime);
	}
	
	/**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void putMapValues(final String key, final Map<String, T> dataMap)
    {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }
	
	/**
	 * 缓存Map
	 *
	 * @param key
	 * @param dataMap
	 */
	public <T> void putMapValues(final String key, final Map<String, T> dataMap,final long expireTime)
	{
		if (dataMap != null) {
			redisTemplate.opsForHash().putAll(key, dataMap);
			expire(key,expireTime);
		}
	}
	
    /**
     * 获取变量中的键值对 map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getMaps(final String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }
	
	/**
	 * 获得缓存的Map值
	 *
	 * @param key
	 * @return
	 */
	public <T> List<T> getMapValues(final String key)
	{
		return redisTemplate.opsForHash().values(key);
	}

	/**
	 * 获得缓存的Map key
	 *
	 * @param key
	 * @return
	 */
	public <T> Set<T> getMapKeys(final String key)
	{
		return redisTemplate.opsForHash().keys(key);
	}
	
	
	/**
	 * 获得缓存的Map key
	 *
	 * @param key
	 * @return
	 */
	public <T> Long getMapSize(final String key)
	{
		return redisTemplate.opsForHash().size(key);
	}

    /**
     * 获取Hash中的数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getMapValue(final String key, final String hKey)
    {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }
    
	/**
	 * 获取Hash中的数据
	 *
	 * @param key Redis键
	 * @param hashKey Hash键
	 * @return Hash中的对象
	 */
	public Long delMapValues(final String key, final String... hashKey)
	{
		return redisTemplate.opsForHash().delete(key, hashKey);
	}
	
	/**
	 * 获取Hash中的数据
	 *
	 * @param key Redis键
	 * @param hashKey Hash键
	 * @return Hash中的对象
	 */
	public Boolean mapHasKey (final String key, final String hashKey)
	{
		return redisTemplate.opsForHash().hasKey(key, hashKey);
	}
	
	/**
	 * 以 long 型增加
	 * @param key
	 * @param delta
	 * @return
	 */
	public Long incrMapObject(final String key,final String hashKey, final long delta) {
		return redisTemplate.opsForHash().increment(key,hashKey, delta);
	}
	
    /**
     * 以集合的方式获取变量中的值,获取多个Hash中的数据
     *
     * @param key Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys)
    {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }
	
	/**
	 * 获得 hash 的集合
	 * @return
	 */
	public HashOperations getHashOperations()
	{
		return redisTemplate.opsForHash();
	}
	
	/**
	 * 添加数据到集合中去
	 * @param key key
	 * @param value value
	 * @param score 得分
	 * @return
	 */
	public Boolean addZSet(final String key,final String value, final double score) {
		return redisTemplate.opsForZSet().add(key,value,score);
	}
	
	/**
	 * 获得range返回的集合
	 * @param key SET的key
	 * @param start 开始位置
	 * @param end 结束位置
	 * @param <T>
	 * @return
	 */
	public <T> Set<T> getRangeSet(final String key,final long start,final long end)
	{
		return redisTemplate.opsForZSet().range(key,start,end);
	}
	
	/**
	 * 获得range返回的集合
	 * @param key SET的key
	 * @param min 最小值
	 * @param max 最大值
	 * @param <T>
	 * @return
	 */
	public <T> Set<T> getRangeSet(final String key,final double min,final double max)
	{
		return redisTemplate.opsForZSet().rangeByScore(key,min,max);
	}
	
	/**
	 *根据设置的score获取区间值从给定下标和给定长度获取最终值
	 * @param key SET的key
	 * @param min 最小值
	 * @param max 最大值
	 * @param <T>
	 * @return
	 */
	public <T> Set<T> getRangeSet(final String key,final double min,final double max,final long offset,final long count)
	{
		return redisTemplate.opsForZSet().rangeByScore(key,min,max,offset,count);
	}
	
	
	/**
	 *根据设置的score获取区间值从给定下标和给定长度获取最终值
	 * @param key SET的key
	 * @param min 最小值
	 * @param max 最大值
	 * @param <T>
	 * @return
	 */
	public <T> Long getRangeSize(final String key,final double min,final double max)
	{
		return redisTemplate.opsForZSet().count(key,min,max);
	}
	
	/**
	 *根据设置的score获取区间值从给定下标和给定长度获取最终值
	 * @param key SET的key
	 * @param obj 对象对应的值
	 * @param <T>
	 * @return
	 */
	public <T> Double getZsetScore(final String key,Object obj)
	{
		return redisTemplate.opsForZSet().score(key,obj);
	}
	
	/**
	 * 修改变量中的元素的分值
	 * @param key SET的key
	 * @param obj 对象对应的值
	 * @param <T>
	 * @return
	 */
	public <T> Double updateZsetScore(final String key,Object obj,final double score)
	{
		return redisTemplate.opsForZSet().incrementScore(key,obj,score);
	}
	
	/**
	 * 获得 Zset 对象
	 * @return
	 */
	public ZSetOperations getZSetOperations(){
		return redisTemplate.opsForZSet();
	}
	
	/**
     * 获得缓存的基本对象列表
     * 
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern)
    {
        return redisTemplate.keys(pattern);
    }
}
