package com.application.cloud.generater.exception;

import com.alibaba.fastjson.JSON;
import com.application.cloud.generater.utils.GenResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 * @author : 孤狼
 * @NAME: GenException
 * @DESC: GenException 类设计
 **/
@Slf4j
@Component
public class GenExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		GenResultVO vo = new GenResultVO();
		try {
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			if (ex instanceof GenException) {
				vo.put("code", ((GenException) ex).getCode());
				vo.put("msg", ((GenException) ex).getMessage());
			}else if(ex instanceof DuplicateKeyException){
				vo = GenResultVO.error("数据库中已存在该记录");
			}else{
				vo = GenResultVO.error();
			}
			//记录异常日志
			log.error(ex.getMessage(), ex);
			String json = JSON.toJSONString(vo);
			response.getWriter().print(json);
		} catch (Exception e) {
			log.error("RRExceptionHandler 异常处理失败", e);
		}
		return new ModelAndView();
	}
}
