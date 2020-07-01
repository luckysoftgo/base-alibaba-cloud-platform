package com.application.cloud.generater.controller;

import com.application.cloud.generater.service.SysGeneratorService;
import com.application.cloud.generater.utils.GenResultVO;
import com.application.cloud.generater.utils.page.PageProcessor;
import com.application.cloud.generater.utils.page.PageQuery;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author : 孤狼
 * @NAME: SysGeneratorController
 * @DESC: SysGeneratorController 类设计
 **/
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {

	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public GenResultVO list(@RequestParam Map<String, Object> params){
		PageProcessor pageUtil = sysGeneratorService.queryList(new PageQuery(params));
		return GenResultVO.success().put("page", pageUtil);
	}
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	public void code(String tables, HttpServletResponse response) throws IOException{
		byte[] data = sysGeneratorService.generatorCode(tables.split(","));
		response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
        IOUtils.write(data, response.getOutputStream());
	}
}
