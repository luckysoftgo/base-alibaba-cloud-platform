package com.application.cloud.demo.test.entity;

import com.application.cloud.dynamic.datasource.datapage.query.QueryCondition;
import com.application.cloud.dynamic.datasource.datapage.query.WhereTypeEnum;
import com.application.cloud.dynamic.datasource.datatool.BasicEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 成绩信息
 * 
 * @author 孤狼
 * @email 1577620678@qq.com
 * @date 2020-07-02 18:29:16
 */
@Data
@TableName("cloud_test_score")
@NoArgsConstructor
@ApiModel(value="cloudTestScore",description="成绩信息", parent = CloudTestScoreEntity.class)
public class CloudTestScoreEntity extends BasicEntity {
	
    /**
    * 表名
    */
    public static final String TABLE_NAME = "cloud_test_score";

 	/**学生id*/
    @ApiModelProperty(value="学生id",name="userId",dataType="Long")
    private Long userId;
    /**学生id 对应的静态变量值*/
	public static final String FIELD_USER_ID = "userId";

 	/**用户名称*/
    @ApiModelProperty(value="用户名称",name="userName",dataType="String")
    @QueryCondition(filed = "user_name",type= WhereTypeEnum.LIKE)
    private String userName;
    /**用户名称 对应的静态变量值*/
	public static final String FIELD_USER_NAME = "userName";

 	/**语文*/
    @ApiModelProperty(value="语文",name="chineseSocre",dataType="Integer")
    @QueryCondition(filed = "chinese_socre",type= WhereTypeEnum.IN)
    private Integer chineseSocre;
    /**语文 对应的静态变量值*/
	public static final String FIELD_CHINESE_SOCRE = "chineseSocre";

 	/**数学*/
    @ApiModelProperty(value="数学",name="mathSocre",dataType="Integer")
    @QueryCondition(filed = "math_socre",type= WhereTypeEnum.IN)
    private Integer mathSocre;
    /**数学 对应的静态变量值*/
	public static final String FIELD_MATH_SOCRE = "mathSocre";

 	/**英语*/
    @ApiModelProperty(value="英语",name="englishSocre",dataType="Integer")
    @QueryCondition(filed = "english_socre",type= WhereTypeEnum.IN)
    private Integer englishSocre;
    /**英语 对应的静态变量值*/
	public static final String FIELD_ENGLISH_SOCRE = "englishSocre";


}
