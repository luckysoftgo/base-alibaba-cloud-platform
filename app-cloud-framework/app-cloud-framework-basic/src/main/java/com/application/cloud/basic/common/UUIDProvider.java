package com.application.cloud.basic.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * UUID生产器
 *
 * @author : 孤狼
 * @NAME: BasicEntity
 * @DESC: BasicEntity 类设计
 **/
public class UUIDProvider {

	static String[] numbers = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	public static void main(String[] args) throws Exception {
		System.out.println(getId());
		System.out.println(uuid());
		Map<String, Object> result = getResult();
		System.out.println(result.get("uuid"));
    	System.out.println(result.get("number"));
    	System.out.println(result.get("letter"));
	}
	
	/**
	 * 产生一个32位的GUID
	 * 
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * 返回数字 .
	 * @param uuid
	 * @return
	 */
	public static String number(String uuid) {
		List<String> nums = Arrays.asList(numbers);
		StringBuffer buffer = new StringBuffer("");
		for (int i = 0; i < uuid.length(); i++) {
			String tmp = uuid.charAt(i) + "";
			if (nums.contains(tmp)) {
				buffer.append(tmp);
			}
		}
		return buffer.toString();
	}

	/**
	 * 返回字母.
	 * @param uuid
	 * @return
	 */
	public static String letter(String uuid) {
		List<String> nums = Arrays.asList(numbers);
		StringBuffer buffer = new StringBuffer("");
		for (int i = 0; i < uuid.length(); i++) {
			String tmp = uuid.charAt(i) + "";
			if (!nums.contains(tmp)) {
				buffer.append(tmp);
			}
		}
		return buffer.toString();
	}

	/**
	 * 获得uuid的组合.
	 * @return
	 */
	public static Map<String, Object> getResult() {
		Map<String, Object> result = new HashMap<String, Object>();
		String uuid = uuid();
		result.put("uuid", uuid);
		result.put("number", number(uuid));
		result.put("letter", letter(uuid));
		return result;
	}

	/**
	 * 获取32位GUID
	 * @return
	 */
	public static String getId() {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			UUID uuid = UUID.randomUUID();
			String guidStr = uuid.toString();
			md.update(guidStr.getBytes(), 0, guidStr.length());
			return new BigInteger(1, md.digest()).toString(16);
		}
		catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/**
	 * 主键生成方式,年月日时分秒毫秒的时间戳+四位随机数保证不重复
	 */
	public static Long getNumId() {
		// 当前系统时间戳精确到毫秒
		Long simple = System.currentTimeMillis();
		// 三位随机数
		int random = new Random().nextInt(900000) + 100000;
		// 为变量赋随机值100000-99999;
		return Long.decode((simple.toString() + random));
	}

	/**
	 * 主键生成方式,年月日时分秒毫秒的时间戳+四位随机数保证不重复
	 */
	public static String getNumStringId() {
		// 当前系统时间戳精确到毫秒
		Long simple = System.currentTimeMillis();
		// 三位随机数
		int random = new Random().nextInt(900000) + 100000;
		// 为变量赋随机值100000-99999;
		return simple.toString() + random;
	}
}
