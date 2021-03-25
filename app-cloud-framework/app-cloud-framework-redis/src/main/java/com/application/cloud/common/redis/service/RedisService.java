package com.application.cloud.common.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * spring redis 工具类
 * https://blog.csdn.net/xinhuashudiao/article/details/84906538
 * https://blog.csdn.net/qq_19734597/article/details/92798699
 *
 * @author cloud
 **/
@Slf4j
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisService {
	@Autowired
	public RedisTemplate redisTemplate;
	
	/**
	 * 指定缓存失效时间
	 *
	 * @param key  键
	 * @param time 时间(秒)
	 * @return
	 */
	public boolean expire(String key, long time) {
		try {
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			log.error("给key指定失效时间异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 根据key 获取过期时间
	 *
	 * @param key 键 不能为null
	 * @return 时间(秒) 返回0代表为永久有效
	 */
	public long getExpire(String key) {
		try {
			return redisTemplate.getExpire(key, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("获取key失效时间异常，异常信息是:{}", e.getMessage());
			return 0L;
		}
	}
	
	/**
	 * 获得过期时间
	 *
	 * @param key
	 * @return
	 */
	public Long getExpire(String key, TimeUnit timeUnit) {
		try {
			return redisTemplate.getExpire(key, timeUnit);
		} catch (Exception e) {
			log.error("获取key失效时间异常，异常信息是:{}", e.getMessage());
			return 0L;
		}
		
	}
	
	/**
	 * 判断key是否存在
	 *
	 * @param key 键
	 * @return true 存在 false不存在
	 */
	public boolean hasKey(String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			log.error("获取key是否存在异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 删除缓存
	 *
	 * @param key 可以传一个值 或多个
	 */
	@SuppressWarnings("unchecked")
	public void delete(String... key) {
		try {
			if (key != null && key.length > 0) {
				if (key.length == 1) {
					redisTemplate.delete(key[0]);
				} else {
					redisTemplate.delete(CollectionUtils.arrayToList(key));
				}
			}
		} catch (Exception e) {
			log.error("删除指定的缓存key发生异常，异常信息是:{}", e.getMessage());
		}
	}
	
	/**
	 * 删除缓存
	 *
	 * @param keys 可以传一个值 或多个
	 */
	@SuppressWarnings("unchecked")
	public void delete(Collection<String> keys) {
		try {
			redisTemplate.delete(keys);
		} catch (Exception e) {
			log.error("删除指定的缓存key发生异常，异常信息是:{}", e.getMessage());
		}
	}
	
	/**
	 * 删除缓存
	 *
	 * @param keys 可以传一个值 或多个
	 */
	@SuppressWarnings("unchecked")
	public void delete(List<String> keys) {
		try {
			redisTemplate.delete(keys);
		} catch (Exception e) {
			log.error("删除指定的缓存key发生异常，异常信息是:{}", e.getMessage());
		}
	}
	
	/**
	 * 普通缓存获取
	 *
	 * @param key 键
	 * @return 值
	 */
	public Object get(String key) {
		try {
			return key == null ? null : redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			log.error("通过指定的key获取缓存对象异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 通过指定的key集合获取对应的值集合
	 *
	 * @param keys
	 * @return
	 */
	public List<Object> getByKeys(Collection keys) {
		try {
			return redisTemplate.opsForValue().multiGet(keys);
		} catch (Exception e) {
			log.error("通过指定的key集合获取对应的值集合发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 普通缓存放入
	 *
	 * @param key   键
	 * @param value 值
	 * @return true成功 false失败
	 */
	public boolean put(String key, Object value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			log.error("往缓存中放入键值对象发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 普通缓存放入并设置时间
	 *
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * @return true成功 false 失败
	 */
	public boolean put(String key, Object value, long time) {
		try {
			if (time > 0) {
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			} else {
				redisTemplate.opsForValue().set(key, value);
			}
			return true;
		} catch (Exception e) {
			log.error("往缓存中放入键值对象发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 普通缓存放入并设置时间
	 *
	 * @param key      键
	 * @param value    值
	 * @param time     时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * @param timeUnit 时间颗粒度
	 * @return true成功 false 失败
	 */
	public boolean put(String key, Object value, long time, TimeUnit timeUnit) {
		try {
			if (time > 0) {
				redisTemplate.opsForValue().set(key, value, time, timeUnit);
			} else {
				redisTemplate.opsForValue().set(key, value);
			}
			return true;
		} catch (Exception e) {
			log.error("往缓存中放入键值对象发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 给key重新设置value值,并将原来的值返回回来.
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Object replace(String key, String value) {
		try {
			return redisTemplate.opsForValue().getAndSet(key, value);
		} catch (Exception e) {
			log.error("给key重新设置value值发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 追加值到指定的key上
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean append(String key, String value) {
		try {
			Integer result = redisTemplate.opsForValue().append(key, value);
			if (result != null && result.intValue() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("往缓存中放入键值对象发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 递增 1
	 *
	 * @param key 键
	 * @return
	 */
	public long incr(String key) {
		try {
			return redisTemplate.opsForValue().increment(key);
		} catch (Exception e) {
			log.error("给指定的key顺势添加1发生异常，异常信息是:{}", e.getMessage());
			return 0;
		}
	}
	
	/**
	 * 递增
	 *
	 * @param key   键
	 * @param delta 要增加几(大于0)
	 * @return
	 */
	public long incr(String key, long delta) {
		try {
			if (delta < 0) {
				throw new RuntimeException("递增因子必须大于0");
			}
			return redisTemplate.opsForValue().increment(key, delta);
		} catch (Exception e) {
			log.error("给指定的key顺势添加指定因子发生异常，异常信息是:{}", e.getMessage());
			return 0;
		}
	}
	
	/**
	 * 递减1
	 *
	 * @param key   键
	 * @return
	 */
	public long decr(String key) {
		try {
			return redisTemplate.opsForValue().decrement(key);
		} catch (Exception e) {
			log.error("给指定的key顺势减少1发生异常，异常信息是:{}", e.getMessage());
			return 0;
		}
	}
	
	/**
	 * 递减
	 *
	 * @param key   键
	 * @param delta 要减少几(小于0)
	 * @return
	 */
	public long decr(String key, long delta) {
		try {
			if (delta < 0) {
				throw new RuntimeException("递减因子必须大于0");
			}
			return redisTemplate.opsForValue().decrement(key, delta);
		} catch (Exception e) {
			log.error("给指定的key顺势减少指定因子发生异常，异常信息是:{}", e.getMessage());
			return 0;
		}
	}
	
	
	/**
	 * 获取hash中Key对应的所有键值对
	 *
	 * @param key 键
	 * @return 对应的多个键值
	 */
	public Map<Object, Object> getMaps(String key) {
		try {
			return redisTemplate.opsForHash().entries(key);
		} catch (Exception e) {
			log.error("获取hash中Key对应的所有键值对发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取hash中Key对应的所有value键值
	 *
	 * @param key 键
	 * @return 对应的多个键值
	 */
	public List<Object> getMapValues(String key) {
		try {
			return redisTemplate.opsForHash().values(key);
		} catch (Exception e) {
			log.error("获取hash中Key对应的所有value键值发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取Hash中的hkey对应的value数据
	 *
	 * @param key  Redis键
	 * @param hKey Hash键
	 * @return Hash中的对象
	 */
	public Object getMapValue(String key, String hKey) {
		try {
			return redisTemplate.opsForHash().get(key, hKey);
		} catch (Exception e) {
			log.error("获取Hash中的hkey对应的value数据发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取hash中Key对应的集合大小
	 *
	 * @param key 键
	 * @return 对应的多个键值
	 */
	public Long getMapSize(String key) {
		try {
			return redisTemplate.opsForHash().size(key);
		} catch (Exception e) {
			log.error("获取hash中Key对应的集合大小发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 以集合的方式获取变量中的值,获取多个Hash中的数据
	 *
	 * @param key 键
	 * @return 对应的多个键值
	 */
	public List<Object> getMultiMapValue(String key, Collection<Object> hKeys) {
		try {
			return redisTemplate.opsForHash().multiGet(key, hKeys);
		} catch (Exception e) {
			log.error("以集合的方式获取变量中的值,获取多个Hash中的数据发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 往hash中放入map数值
	 *
	 * @param key 键
	 * @param map 对应多个键值
	 * @return true 成功 false 失败
	 */
	public boolean putMapValues(String key, Map<String, Object> map) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			log.error("通过key获取hash值对应的Map信息发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 往hash中放入map数值,并设置时间
	 *
	 * @param key  键
	 * @param map  对应多个键值
	 * @param time 时间(秒)
	 * @return true成功 false失败
	 */
	public boolean putMapValues(String key, Map<String, Object> map, long time) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			log.error("给缓存中放入Map集合发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 向一张hash表中放入数据,如果不存在将创建
	 *
	 * @param key   键
	 * @param item  项
	 * @param value 值
	 * @return true 成功 false失败
	 */
	public boolean putMapValue(String key, String item, Object value) {
		try {
			redisTemplate.opsForHash().put(key, item, value);
			return true;
		} catch (Exception e) {
			log.error("向hash表中放入数据发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 向一张hash表中放入数据,如果不存在将创建
	 *
	 * @param key   键
	 * @param item  项
	 * @param value 值
	 * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
	 * @return true 成功 false失败
	 */
	public boolean putMapValue(String key, String item, Object value, long time) {
		try {
			redisTemplate.opsForHash().put(key, item, value);
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			log.error("向hash表中放入数据发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 删除hash表中的多个key对应的value值
	 *
	 * @param key     键 不能为null
	 * @param hashKey 项 可以使多个 不能为null
	 */
	public boolean delMapValues(String key, Object... hashKey) {
		try {
			redisTemplate.opsForHash().delete(key, hashKey);
			return true;
		} catch (Exception e) {
			log.error("删除hash表中的多个key对应的value值发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 判断hash表中是否有该hashKey的值
	 *
	 * @param key     键 不能为null
	 * @param hashKey 项 不能为null
	 * @return true 存在 false不存在
	 */
	public boolean mapKeyExists(String key, String hashKey) {
		try {
			return redisTemplate.opsForHash().hasKey(key, hashKey);
		} catch (Exception e) {
			log.error("判断hash表中是否有该hashKey的值发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * hash表的key对应value的值递增 如果不存在,就会创建一个 并把新增后的值返回
	 *
	 * @param key   键
	 * @param item  项
	 * @param delta 要增加几(大于0)
	 * @return
	 */
	public double incrMapVal(String key, String item, long delta) {
		try {
			return redisTemplate.opsForHash().increment(key, item, delta);
		} catch (Exception e) {
			log.error("hash表的key对应value的值递增发生异常，异常信息是:{}", e.getMessage());
			return 0d;
		}
	}
	
	/**
	 * hash表的key对应value的值递减
	 *
	 * @param key   键
	 * @param item  项
	 * @param delta 要减少记(小于0)
	 * @return
	 */
	public double decrMapVal(String key, String item, long delta) {
		try {
			return redisTemplate.opsForHash().increment(key, item, -delta);
		} catch (Exception e) {
			log.error("hash表的key对应value的值递减发生异常，异常信息是:{}", e.getMessage());
			return 0d;
		}
	}
	
	/**
	 * 根据key获取Set中的所有值
	 *
	 * @param key 键
	 * @return
	 */
	public Set<Object> getSetVals(String key) {
		try {
			return redisTemplate.opsForSet().members(key);
		} catch (Exception e) {
			log.error("根据key获取Set中的所有值发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取两个集合中的相同同值并返回.
	 *
	 * @param sourceKey
	 * @param destKey
	 * @return
	 */
	public Set<Object> getSameSetVals(String sourceKey, String destKey) {
		try {
			return redisTemplate.opsForSet().intersect(sourceKey, destKey);
		} catch (Exception e) {
			log.error("获取两个集合中的相同同值并返回发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取两个集合中的不同值并返回.
	 *
	 * @param sourceKey
	 * @param destKey
	 * @return
	 */
	public Set<Object> getDiffSetVals(String sourceKey, String destKey) {
		try {
			return redisTemplate.opsForSet().difference(sourceKey, destKey);
		} catch (Exception e) {
			log.error("获取两个集合中的不同值并返回发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取两个集合中的所有值并返回.
	 *
	 * @param sourceKey
	 * @param destKey
	 * @return
	 */
	public Set<Object> getAllSetVals(String sourceKey, String destKey) {
		try {
			return redisTemplate.opsForSet().union(sourceKey, destKey);
		} catch (Exception e) {
			log.error("获取两个集合中的所有值并返回发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 检查set中是否存在这个value,是否存在
	 *
	 * @param key   键
	 * @param value 值
	 * @return true 存在 false不存在
	 */
	public boolean isSetMember(String key, Object value) {
		try {
			return redisTemplate.opsForSet().isMember(key, value);
		} catch (Exception e) {
			log.error("检查set中是否存在这个value发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 将数据放入set缓存
	 *
	 * @param key    键
	 * @param values 值 可以是多个
	 * @return 成功个数
	 */
	public long setAdd(String key, Object... values) {
		try {
			return redisTemplate.opsForSet().add(key, values);
		} catch (Exception e) {
			log.error("将数据放入set缓存发生异常，异常信息是:{}", e.getMessage());
			return 0;
		}
	}
	
	/**
	 * 将set数据放入缓存
	 *
	 * @param key    键
	 * @param time   时间(秒)
	 * @param values 值 可以是多个
	 * @return 成功个数
	 */
	public long setAdd(String key, long time, Object... values) {
		try {
			Long count = redisTemplate.opsForSet().add(key, values);
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return count;
		} catch (Exception e) {
			log.error("将set数据放入缓存发生异常，异常信息是:{}", e.getMessage());
			return 0;
		}
	}
	
	/**
	 * 获取set缓存的长度
	 *
	 * @param key 键
	 * @return
	 */
	public long getSetSize(String key) {
		try {
			return redisTemplate.opsForSet().size(key);
		} catch (Exception e) {
			log.error("根据key获取set缓存的长度值发生异常，异常信息是:{}", e.getMessage());
			return 0;
		}
	}
	
	/**
	 * 移除值为value的
	 *
	 * @param key    键
	 * @param values 值 可以是多个
	 * @return 移除的个数
	 */
	public long setDel(String key, Object... values) {
		try {
			Long count = redisTemplate.opsForSet().remove(key, values);
			return count;
		} catch (Exception e) {
			log.error("根据key移除set缓存的value发生异常，异常信息是:{}", e.getMessage());
			return 0;
		}
	}
	
	/**
	 * 添加数据到zset集合中去
	 *
	 * @param key   key
	 * @param value value
	 * @param score 得分
	 */
	public boolean putZSet(String key, String value, double score) {
		try {
			return redisTemplate.opsForZSet().add(key, value, score);
		} catch (Exception e) {
			log.error("添加数据到zset集合中去发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 获得zset集合中开始位置和结束位置的集合
	 *
	 * @param key   SET的key
	 * @param start 开始位置
	 * @param end   结束位置
	 */
	public Set<Object> getZsetRangeVals(String key, long start, long end) {
		try {
			return redisTemplate.opsForZSet().range(key, start, end);
		} catch (Exception e) {
			log.error("获得zset集合中开始位置和结束位置的集合发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获得zset集合中最大值和最小值的集合
	 *
	 * @param key SET的key
	 * @param min 最小值
	 * @param max 最大值
	 */
	public Set<Object> getZsetRangeVals(String key, double min, double max) {
		try {
			return redisTemplate.opsForZSet().rangeByScore(key, min, max);
		} catch (Exception e) {
			log.error("获得zset集合中最大值和最小值的集合发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获得zset集合中最大值和最小值的集合
	 *
	 * @param key    SET的key
	 * @param min    最小值
	 * @param max    最大值
	 * @param offset 开始位置
	 * @param count  总个数
	 * @return
	 */
	public Set<Object> getZsetRangeVals(String key, double min, double max, long offset, long count) {
		try {
			return redisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count);
		} catch (Exception e) {
			log.error("获得zset集合中最大值和最小值的集合发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取zset集合中元素为element的得分值
	 *
	 * @param key     zset集合的key
	 * @param element 元素的名称
	 * @return
	 */
	public Double getZsetScore(String key, Object element) {
		try {
			return redisTemplate.opsForZSet().score(key, element);
		} catch (Exception e) {
			log.error("获取zset集合中元素为element的得分值发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 给zset集合中元素为element的得分值再添加score的值
	 *
	 * @param key     zset集合的key
	 * @param element 元素的名称
	 * @param score   得分
	 * @return
	 */
	public Double incrZsetScore(String key, Object element, double score) {
		try {
			return redisTemplate.opsForZSet().incrementScore(key, element, score);
		} catch (Exception e) {
			log.error("给zset集合中元素为element的得分值再添加操作发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取list缓存的内容
	 *
	 * @param key   键
	 * @param start 开始
	 * @param end   结束 0 到 -1代表所有值
	 * @return
	 */
	public List<Object> listGet(String key, long start, long end) {
		try {
			return redisTemplate.opsForList().range(key, start, end);
		} catch (Exception e) {
			log.error("获取list缓存的信息发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取list缓存的长度
	 *
	 * @param key 键
	 * @return
	 */
	public long listGetSize(String key) {
		try {
			return redisTemplate.opsForList().size(key);
		} catch (Exception e) {
			log.error("获取list缓存的长度发生异常，异常信息是:{}", e.getMessage());
			return 0;
		}
	}
	
	/**
	 * 通过索引 获取list中的值
	 *
	 * @param key   键
	 * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
	 * @return
	 */
	public Object listValByIndex(String key, long index) {
		try {
			return redisTemplate.opsForList().index(key, index);
		} catch (Exception e) {
			log.error("通过索引index获取list中的值发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 删除并返回存储在{@code key}的列表中的最后一个元素。
	 *
	 * @param key 键
	 * @return 移除的个数
	 */
	public Object listRightPop(String key) {
		try {
			return redisTemplate.opsForList().rightPop(key);
		} catch (Exception e) {
			log.error("删除并返回存储在的列表中的最后一个元素发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 给已经存在的list集合添加数据,从右追加
	 *
	 * @param key   键
	 * @param value 值
	 * @returnrightPush
	 */
	public boolean listRightPush(String key, Object value) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			return true;
		} catch (Exception e) {
			log.error("给已经存在的list集合处理从右追加数据发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * * 给已经存在的list集合添加数据,从右追加
	 *
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒)
	 * @return
	 */
	public boolean listRightPush(String key, Object value, long time) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			log.error("给已经存在的list集合处理从右追加数据发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 给已经存在的list集合添加list集合数据,从右追加
	 *
	 * @param key   键
	 * @param value 值
	 * @return
	 */
	public boolean listRightPushAll(String key, List<Object> value) {
		try {
			redisTemplate.opsForList().rightPushAll(key, value);
			return true;
		} catch (Exception e) {
			log.error("给已经存在的list集合处理从右追加List数据发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 给已经存在的list集合添加list集合数据,从右追加
	 *
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒)
	 * @return
	 */
	public boolean listRightPushAll(String key, List<Object> value, long time) {
		try {
			redisTemplate.opsForList().rightPushAll(key, value);
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			log.error("给已经存在的list集合处理从右追加List数据发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 根据索引修改list中的某条数据
	 *
	 * @param key   键
	 * @param index 索引
	 * @param value 值
	 * @return
	 */
	public boolean listUpdateValByIndex(String key, long index, Object value) {
		try {
			redisTemplate.opsForList().set(key, index, value);
			return true;
		} catch (Exception e) {
			log.error("根据索引修改list中的某条数据发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 移除集合List中count个值为value的操作
	 *
	 * @param key   键
	 * @param count 移除多少个
	 * @param value 值
	 * @return 移除的个数
	 */
	public boolean listDelSameVal(String key, long count, Object value) {
		try {
			Long remove = redisTemplate.opsForList().remove(key, count, value);
			return remove > 0 ? true : false;
		} catch (Exception e) {
			log.error("移除集合List中count个值为value的操作发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 删除并返回存储在{@code key}的列表中的第一个元素。
	 *
	 * @param key 键
	 * @return 移除的个数
	 */
	public Object leftPop(String key) {
		try {
			return redisTemplate.opsForList().leftPop(key);
		} catch (Exception e) {
			log.error("删除并返回存储在的列表中的第一个元素发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 给已经存在的list集合添加数据,从左追加
	 *
	 * @param key   键
	 * @param value 值
	 * @returnrightPush
	 */
	public boolean listLeftPush(String key, Object value) {
		try {
			redisTemplate.opsForList().leftPush(key, value);
			return true;
		} catch (Exception e) {
			log.error("给已经存在的list集合处理从右追加数据发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * * 给已经存在的list集合添加数据,从左追加
	 *
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒)
	 * @return
	 */
	public boolean listLeftPush(String key, Object value, long time) {
		try {
			redisTemplate.opsForList().leftPush(key, value);
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			log.error("给已经存在的list集合处理从右追加数据发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 给已经存在的list集合添加list集合数据,从左追加
	 *
	 * @param key   键
	 * @param value 值
	 * @return
	 */
	public boolean listLeftPushAll(String key, List<Object> value) {
		try {
			redisTemplate.opsForList().leftPushAll(key, value);
			return true;
		} catch (Exception e) {
			log.error("给已经存在的list集合处理从右追加List数据发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 给已经存在的list集合添加list集合数据,从左追加
	 *
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒)
	 * @return
	 */
	public boolean listLeftPushAll(String key, List<Object> value, long time) {
		try {
			redisTemplate.opsForList().leftPushAll(key, value);
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			log.error("给已经存在的list集合处理从右追加List数据发生异常，异常信息是:{}", e.getMessage());
			return false;
		}
	}
	
	/**
	 * 用于移除列表的最后一个元素，并将该元素添加到另一个列表第一个并返回，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
	 *
	 * @param sourceKey      源list的最右边的值
	 * @param destinationKey 目的list的最左边的值
	 * @return
	 */
	public Object listRightPopAndLeftPush(String sourceKey, String destinationKey) {
		try {
			return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
		} catch (Exception e) {
			log.error("用于移除列表的最后一个元素，并将该元素添加到另一个列表第一个并返回发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获得缓存的list对象
	 *
	 * @param key 缓存的键值
	 * @return 缓存键值对应的数据
	 */
	public List<Object> getAllListVals(String key) {
		try {
			return redisTemplate.opsForList().range(key, 0, -1);
		} catch (Exception e) {
			log.error("获得缓存的list对象发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获得缓存的起始和结束的list对象
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Object> getRangeListVals(String key,long start,long end){
		try {
			return redisTemplate.opsForList().range(key, start, end);
		} catch (Exception e) {
			log.error("获得缓存的list对象发生异常，异常信息是:{}", e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获得操作list的实例
	 * @return
	 */
	public ListOperations getListOperations(){
		return redisTemplate.opsForList();
	}
	
	/**
	 * 获得操作set的实例
	 * @return
	 */
	public SetOperations getSetOperations() {
		return redisTemplate.opsForSet();
	}
	
	/**
	 * 获得操作hash的实例
	 * @return
	 */
	public HashOperations getHashOperations(){
		return redisTemplate.opsForHash();
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
