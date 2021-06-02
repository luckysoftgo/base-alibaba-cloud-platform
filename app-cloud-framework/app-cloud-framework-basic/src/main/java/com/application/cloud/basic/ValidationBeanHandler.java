package com.application.cloud.basic;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ：孤狼
 * @date ：2021-5-13
 * @description: bean验证处理类
 * @modified By：
 * @version: 1.0.0
 */
public final class ValidationBeanHandler {
	
	/**
	 * 实例验证工厂
	 */
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	/**
	 * 验证方法
	 *
	 * @param t
	 * @param <T>
	 * @return
	 */
	public static <T> List<String> validate(T t) {
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
		List<String> messageList = new ArrayList<>();
		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			messageList.add(constraintViolation.getMessage());
		}
		return messageList;
	}
	
	/**
	 * 校验是否填了必填参数.
	 *
	 * @param t
	 * @return
	 * @throws RuntimeException
	 */
	public static <T> boolean validated(T t) throws RuntimeException {
		List<String> messageList = validate(t);
		if (messageList.size() > 0) {
			throw new RuntimeException(messageList.toString());
		}
		return true;
	}
}
