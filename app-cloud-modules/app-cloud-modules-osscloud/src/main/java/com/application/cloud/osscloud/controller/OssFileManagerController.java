package com.application.cloud.osscloud.controller;

import com.application.cloud.common.core.text.UUID;
import com.application.cloud.common.core.utils.poi.ExcelUtil;
import com.application.cloud.common.core.web.controller.BaseController;
import com.application.cloud.common.core.web.domain.AjaxResult;
import com.application.cloud.common.core.web.page.TableDataInfo;
import com.application.cloud.common.log.annotation.Log;
import com.application.cloud.common.log.enums.BusinessType;
import com.application.cloud.osscloud.cloud.CloudStorageService;
import com.application.cloud.osscloud.domain.OssFileManager;
import com.application.cloud.osscloud.service.IOssFileManagerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 文件管理Controller
 * 
 * @author app-cloud
 * @date 2020
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class OssFileManagerController extends BaseController
{
    @Autowired
    private IOssFileManagerService ossFileManagerService;
	
	@Autowired(required = false)
	private CloudStorageService cloudStorageService;

	
	/**
     * 查询文件管理列表
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
     * 导出文件管理列表
     */
    @PreAuthorize("@auth.hasPermi('oss:file:export')")
    @Log(title = "文件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OssFileManager ossFileManager) throws IOException
    {
        List<OssFileManager> list = ossFileManagerService.selectOssFileManagerList(ossFileManager);
        ExcelUtil<OssFileManager> util = new ExcelUtil<OssFileManager>(OssFileManager.class);
        util.exportExcel(response, list, "file");
    }

    /**
     * 获取文件管理详细信息
     */
    @PreAuthorize("@auth.hasPermi('oss:file:query')")
    @GetMapping(value = "/{ossId}")
    public AjaxResult getInfo(@PathVariable("ossId") Long ossId)
    {
        return AjaxResult.success(ossFileManagerService.selectOssFileManagerById(ossId));
    }

    /**
     * 新增文件管理
     */
    @PreAuthorize("@auth.hasPermi('oss:file:add')")
    @Log(title = "文件管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OssFileManager ossFileManager)
    {
        return toAjax(ossFileManagerService.insertOssFileManager(ossFileManager));
    }

    /**
     * 修改文件管理
     */
    @PreAuthorize("@auth.hasPermi('oss:file:edit')")
    @Log(title = "文件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OssFileManager ossFileManager)
    {
        return toAjax(ossFileManagerService.updateOssFileManager(ossFileManager));
    }

    /**
     * 删除文件管理
     */
    @PreAuthorize("@auth.hasPermi('oss:file:remove')")
    @Log(title = "文件管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ossIds}")
    public AjaxResult remove(@PathVariable Long[] ossIds)
    {
        return toAjax(ossFileManagerService.deleteOssFileManagerByIds(ossIds));
    }
	
	/**
	 * 上传文件
	 */
	@PreAuthorize("@auth.hasPermi('oss:file:upload')")
	@Log(title = "文件管理", businessType = BusinessType.OTHER)
	@PostMapping("/upload")
	public AjaxResult upload(MultipartFile file, String bizId, String bizCode) {
		try {
			String path = cloudStorageService.upload(file.getInputStream(),file.getOriginalFilename());
			OssFileManager fileManager = new OssFileManager();
			fileManager.setFileName(file.getOriginalFilename());
			fileManager.setFilePath(path);
			fileManager.setFileSize(file.getSize());
			fileManager.setFileType(file.getContentType());
			fileManager.setModuleId(bizId);
			fileManager.setModuleCode(bizCode);
			fileManager.setUniqueKey(UUID.randomUUID().toString());
			ossFileManagerService.insertOssFileManager(fileManager);
			AjaxResult ajax = AjaxResult.success();
			ajax.put("fileName", file.getOriginalFilename());
			ajax.put("url", path);
			return ajax;
		} catch (IOException e) {
			log.error("存储文件失败,失败信息是:{}",e.getMessage());
			return AjaxResult.error("存储文件失败!");
		}
	}
	
	/**
	 * 下载文件
	 */
	@PreAuthorize("@auth.hasPermi('oss:file:download')")
	@Log(title = "文件管理", businessType = BusinessType.OTHER)
	@PostMapping("/download")
	public void download(@PathVariable Long ossId, HttpServletResponse response) {
		OssFileManager attachment = this.ossFileManagerService.selectOssFileManagerById(ossId);
		response.addHeader("Content-Type",attachment.getFileType());
		String encodedfileName = null;
		try {
			encodedfileName = new String(attachment.getFileName().getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
		response.setHeader("Content-Length",String.valueOf(attachment.getFileSize()));
		try {
			InputStream inputStream = new FileInputStream(new File(attachment.getFilePath()));
			IOUtils.copy(inputStream,response.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException("下载文件失败" + e.getMessage());
		}
	}
	
}
