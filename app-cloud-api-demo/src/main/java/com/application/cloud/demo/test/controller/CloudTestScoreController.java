package com.application.cloud.demo.test.controller;

import com.application.cloud.demo.test.entity.CloudTestScoreEntity;
import com.application.cloud.demo.test.service.CloudTestScoreService;
import com.application.cloud.dynamic.datasource.datapage.HelperProcessor;
import com.application.cloud.dynamic.datasource.datapage.PageProcessor;
import com.application.cloud.dynamic.datasource.datatool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;


/**
 * 成绩信息访问入口.
 *
 * @author 孤狼
 * @email 1577620678@qq.com
 * @date 1970-01-01 00:00:00
 */
@Slf4j
@RestController
@RequestMapping("test/cloudtestscore")
@Api(tags = "成绩信息接口API服务")
public class CloudTestScoreController {

    @Autowired
    private CloudTestScoreService cloudTestScoreService;

    /**
     * 列表
     */
    @ApiOperation(value = "成绩信息分页信息",notes="成绩信息分页信息", httpMethod = "GET")
    @ApiImplicitParams({
		    @ApiImplicitParam(name="pageNum" ,value="当前页码" ,required=true ,dataType="int",paramType="query"),
		    @ApiImplicitParam(name="pageSize" ,value="每页多少条" ,required=true ,dataType="int",paramType="query"),
    })
    @GetMapping("/list1")
    @RequiresPermissions("test:cloudtestscore:list")
    public AjaxResult list1(@RequestParam Map<String, Object> params){
        PageProcessor page = cloudTestScoreService.queryMapPage(params);
        return AjaxResult.success(page);
    }
    
	/**
	 * 列表
	 */
	@ApiOperation(value = "成绩信息分页信息",notes="成绩信息分页信息", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageNum" ,value="当前页码" ,required=true ,dataType="int",paramType="query"),
			@ApiImplicitParam(name="pageSize" ,value="每页多少条" ,required=true ,dataType="int",paramType="query"),
	})
	@GetMapping("/list2")
	@RequiresPermissions("test:cloudtestscore:list")
	public AjaxResult list2(@RequestParam Map<String, Object> params){
		HelperProcessor page = cloudTestScoreService.queryMapInfoPage(params);
		return AjaxResult.success(page);
	}

    /**
     * 信息
     */
    @ApiOperation(value = "成绩信息查询单个信息",notes="成绩信息查询单个信息", httpMethod = "GET")
    @ApiImplicitParams({
		    @ApiImplicitParam(name="Long" ,value="信息主键" ,required=true ,dataType="Long",paramType="path"),
    })
    @GetMapping("/info/{id}")
    @RequiresPermissions("test:cloudtestscore:info")
    public AjaxResult info(@PathVariable("id") Long id){
		CloudTestScoreEntity cloudTestScore = cloudTestScoreService.getById(id);
        return AjaxResult.success(cloudTestScore);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "成绩信息信息保存",notes="成绩信息信息保存", httpMethod = "POST")
    @PostMapping(value = "/save",produces = "application/json")
    @RequiresPermissions("test:cloudtestscore:save")
    public AjaxResult save(@RequestBody @ApiParam CloudTestScoreEntity cloudTestScore){
		cloudTestScoreService.save(cloudTestScore);
        return AjaxResult.success("保存成功!");
    }

    /**
     * 修改
     */
    @ApiOperation(value = "成绩信息信息修改",notes="成绩信息信息修改", httpMethod = "POST")
    @PostMapping(value = "/update",produces = "application/json")
    @RequiresPermissions("test:cloudtestscore:update")
    public AjaxResult update(@RequestBody  @ApiParam CloudTestScoreEntity cloudTestScore){
		cloudTestScoreService.updateById(cloudTestScore);
        return AjaxResult.success("修改成功!");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "成绩信息信息删除",notes="成绩信息信息删除", httpMethod = "POST")
    @ApiImplicitParams({
		    @ApiImplicitParam(name="ids" ,value="信息主键集合" ,required=true ,dataType="array",paramType="query"),
    })
    @PostMapping("/delete")
    @RequiresPermissions("test:cloudtestscore:delete")
    public AjaxResult delete(@RequestBody Long[] ids){
		cloudTestScoreService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success("删除成功!");
    }

}
