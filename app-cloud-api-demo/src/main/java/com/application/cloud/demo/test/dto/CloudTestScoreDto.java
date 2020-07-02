package com.application.cloud.demo.test.dto;

import com.application.cloud.dynamic.datasource.datapage.query.QueryCondition;
import com.application.cloud.dynamic.datasource.datapage.query.WhereTypeEnum;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * @author : 孤狼
 * @NAME: CloudTestScoreDto
 * @DESC: CloudTestScoreDto类设计
 **/
public class CloudTestScoreDto {
	
	@TableId
	private Long id = null;
	private String createBy = null;
	private LocalDateTime createTime = null;
	private String updateBy = null;
	private LocalDateTime updateTime = null;
	
	/**学生id*/
	@ApiModelProperty(value="学生id",name="userId",dataType="Long")
	private Long userId;

	/**用户名称*/
	@ApiModelProperty(value="用户名称",name="userName",dataType="String")
	@QueryCondition(filed = "user_name",type= WhereTypeEnum.LIKE)
	private String userName;
	
	
	/**语文*/
	@ApiModelProperty(value="语文",name="chineseSocre",dataType="Integer")
	@QueryCondition(filed = "chinese_socre",type= WhereTypeEnum.IN)
	private Integer chineseSocre;

	
	/**数学*/
	@ApiModelProperty(value="数学",name="mathSocre",dataType="Integer")
	@QueryCondition(filed = "math_socre",type= WhereTypeEnum.IN)
	private Integer mathSocre;
	
	/**英语*/
	@ApiModelProperty(value="英语",name="englishSocre",dataType="Integer")
	@QueryCondition(filed = "english_socre",type= WhereTypeEnum.IN)
	private Integer englishSocre;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCreateBy() {
		return createBy;
	}
	
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	
	public String getUpdateBy() {
		return updateBy;
	}
	
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Integer getChineseSocre() {
		return chineseSocre;
	}
	
	public void setChineseSocre(Integer chineseSocre) {
		this.chineseSocre = chineseSocre;
	}
	
	public Integer getMathSocre() {
		return mathSocre;
	}
	
	public void setMathSocre(Integer mathSocre) {
		this.mathSocre = mathSocre;
	}
	
	public Integer getEnglishSocre() {
		return englishSocre;
	}
	
	public void setEnglishSocre(Integer englishSocre) {
		this.englishSocre = englishSocre;
	}
}
