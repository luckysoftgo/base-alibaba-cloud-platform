package com.application.cloud.osscloud.controller;

import com.application.cloud.common.core.utils.poi.ExcelUtil;
import com.application.cloud.common.core.web.controller.BaseController;
import com.application.cloud.common.core.web.domain.AjaxResult;
import com.application.cloud.common.core.web.page.TableDataInfo;
import com.application.cloud.common.log.annotation.Log;
import com.application.cloud.common.log.enums.BusinessType;
import com.application.cloud.osscloud.domain.OssFileManager;
import com.application.cloud.osscloud.service.IOssFileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 文件/附件管理Controller
 * 
 * @author app-cloud
 * @date 2020
 */
@RestController
@RequestMapping("/file")
public class OssFileManagerController extends BaseController
{
    @Autowired
    private IOssFileManagerService ossFileManagerService;
   
    /**
     * 查询文件/附件管理列表
     */
    @PreAuthorize("@auth.hasPermi('oss:file:list')")
    @GetMapping("/list")
    public TableDataInfo list(OssFileManager ossFileManager)
    {
        startPage();
        List<OssFileManager> list = ossFileManagerService.selectOssFileManagerList(ossFileManager);
        return getDataTable(list);
    }

    /**
     * 导出文件/附件管理列表
     */
    @PreAuthorize("@auth.hasPermi('oss:file:export')")
    @Log(title = "文件/附件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OssFileManager ossFileManager) throws IOException
    {
        List<OssFileManager> list = ossFileManagerService.selectOssFileManagerList(ossFileManager);
        ExcelUtil<OssFileManager> util = new ExcelUtil<OssFileManager>(OssFileManager.class);
        util.exportExcel(response, list, "file");
    }

    /**
     * 获取文件/附件管理详细信息
     */
    @PreAuthorize("@auth.hasPermi('oss:file:query')")
    @GetMapping(value = "/{ossId}")
    public AjaxResult getInfo(@PathVariable("ossId") Long ossId)
    {
        return AjaxResult.success(ossFileManagerService.selectOssFileManagerById(ossId));
    }

    /**
     * 新增文件/附件管理
     */
    @PreAuthorize("@auth.hasPermi('oss:file:add')")
    @Log(title = "文件/附件管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OssFileManager ossFileManager)
    {
        return toAjax(ossFileManagerService.insertOssFileManager(ossFileManager));
    }

    /**
     * 修改文件/附件管理
     */
    @PreAuthorize("@auth.hasPermi('oss:file:edit')")
    @Log(title = "文件/附件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OssFileManager ossFileManager)
    {
        return toAjax(ossFileManagerService.updateOssFileManager(ossFileManager));
    }

    /**
     * 删除文件/附件管理
     */
    @PreAuthorize("@auth.hasPermi('oss:file:remove')")
    @Log(title = "文件/附件管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ossIds}")
    public AjaxResult remove(@PathVariable Long[] ossIds)
    {
        return toAjax(ossFileManagerService.deleteOssFileManagerByIds(ossIds));
    }
}
