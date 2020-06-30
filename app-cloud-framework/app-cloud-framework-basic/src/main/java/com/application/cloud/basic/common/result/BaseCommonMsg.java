package com.application.cloud.basic.common.result;

/**
 * @author : 孤狼
 * @NAME: BasicEntity
 * @DESC: 通用信息描述定义
 **/
public enum BaseCommonMsg {
	
	/**
	 *Informational 1xx  信息
	 */
	HTTPSTATUS_CONTINUE(100,"Continue","继续"),
	HTTPSTATUS_SWITCHING_PROTOCOL(101,"Switching Protocols","交换协议"),
	
	/**
	 * Successful 2xx  成功
	 */
	HTTPSTATUS_OK(200,"OK","成功"),
	HTTPSTATUS_CREATED(201,"Created","创建"),
	HTTPSTATUS_ACCEPTED(202,"Accepted","已接受"),
	HTTPSTATUS_NON_AUTHORITATIVE(203,"Non-Authoritative Information","非权威信息"),
	HTTPSTATUS_NO_CONTENT(204,"No Content","没有内容"),
	HTTPSTATUS_RESET_CONTENT(205,"Reset Content","重置内容"),
	HTTPSTATUS_PARTIAL_CONTENT(206,"Partial Content","部分内容"),
	
	/**
	 * Redirection 3xx  重定向
	 */
	HTTPSTATUS_MULTIPLE_CHOICES(300,"Multiple Choices","多种选择"),
	HTTPSTATUS_MOVED_PERMANENTLY(301,"Moved Permanently","永久移动"),
	HTTPSTATUS_FOUND(302,"Found","找到"),
	HTTPSTATUS_SEE_OTHER(303,"See Other","参见其他"),
	HTTPSTATUS_NOT_MODIFIED(304,"Not Modified","未修改"),
	HTTPSTATUS_USE_PROXY(305,"Use Proxy","使用代理"),
	HTTPSTATUS_UNUSED(306,"Unused","未使用"),
	HTTPSTATUS_TEMPORARY_REDIRECT(307,"Temporary Redirect","暂时重定向"),
	
	/**
	 * Client Error 4xx  客户端错误
	 */
	HTTPSTATUS_BAD_REQUEST(400,"Bad Request","错误的请求"),
	HTTPSTATUS_UNAUTHORIZED(401,"Unauthorized","未经授权"),
	HTTPSTATUS_PAYMENT_REQUIRED(402,"Payment Required","付费请求"),
	HTTPSTATUS_FORBIDDEN(403,"Forbidden","禁止"),
	HTTPSTATUS_NOT_FOUND(404,"Not Found","没有找到"),
	HTTPSTATUS_METHOD_NOT_ALLOWED(405,"Method Not Allowed","方法不允许"),
	HTTPSTATUS_NOT_ACCEPTABLE(406,"Not Acceptable","不可接受"),
	HTTPSTATUS_PROXY_AUTHENTICATION_REQUIRED(407,"Proxy Authentication Required","需要代理身份验证"),
	HTTPSTATUS_REQUEST_TIMEOUT(408,"Request Timeout","请求超时"),
	HTTPSTATUS_CONFLICT(409,"Conflict","指令冲突"),
	HTTPSTATUS_GONE(410,"Gone","文档永久地离开了指定的位置"),
	HTTPSTATUS_LENGTH_REQUIRED(411,"Length Required","需要Content-Length头请求"),
	HTTPSTATUS_PRECONDITION_FAILED(412,"Precondition Failed","前提条件失败"),
	HTTPSTATUS_REQUEST_ENTITY_TOO_LARGE(413,"Request Entity Too Large","请求实体太大"),
	HTTPSTATUS_REQUEST_URI_TOO_LONG(414,"Request-URI Too Long","请求URI太长"),
	HTTPSTATUS_UNSUPPORTED_MEDIA_TYPE(415,"Unsupported Media Type","不支持的媒体类型"),
	HTTPSTATUS_REQUESTED_RANGE_NOT_SATISFIABLE(416,"Requested Range Not Satisfiable","请求的范围不可满足"),
	HTTPSTATUS_EXPECTATION_FAILED(417,"Expectation Failed","期望失败"),
	
	/**
	 * Server Error 5xx  服务器错误
	 */
	HTTPSTATUS_INTERNAL_SERVER_ERROR(500,"Internal Server Error","内部服务器错误"),
	HTTPSTATUS_NOT_IMPLEMENTED(501,"Not Implemented","未实现"),
	HTTPSTATUS_BAD_GATEWAY(502,"Bad Gateway","错误的网关"),
	HTTPSTATUS_SERVICE_UNAVAILABLE(503,"Service Unavailable","服务不可用"),
	HTTPSTATUS_GATEWAY_TIMEOUT(504,"Gateway Timeout","网关超时"),
	HTTPSTATUS_HTTP_VERSION_NOT_SUPPORTED(505,"HTTP Version Not Supported","HTTP版本不支持"),
	
	/**
	 * 系统消息设置
	 */
	SYSTEM_AUTH_LOST_MSG(100,"System Auth Timeout ","登录超时"),
	SYSTEM_SUCCESS_MSG(200,"Operate Success","操作成功"),
	SYSTEM_ERROR_MSG(500,"Server Error","服务器内部错误"),
	INTERFACE_IS_NULL_MSG(404,"Interface Is Null Exists","请求接口不存在"),
	DATA_ACCESS_EXCEPTION_MSG(525,"Data Access Exception","数据访问异常"),
	DATA_OPERATE_EXCEPTION_MSG(535,"Data Operate Exception","数据操作异常"),
	
	/**
	 * 通用消息设置
	 */
	SESSION_ID_LOST_MSG(100000,"Session Id Lost","缺少sessionId参数"),
	PARAMS_MISSING_MSG(100001,"Params Missing Error","参数输入不完整,请检查输入参数"),
	VALIDATED_PARAMS_MSG(100002,"Validated Params Error","参数格式不符合要求,请检查参数"),
	COPY_BEAN_PROP_MSG(100003,"Copy Bean Properties Error","对象之间属性拷贝异常"),
	CONVERT_BEAN_TO_MAP_MSG(100004,"Convert Bean To Map Error","Bean对象转换Map异常"),
	CONVERT_MAP_TO_BEAN_MSG(100005,"Convert Map To Bean Error","Map转换Bean对象异常"),
	REMOTE_CALL_FAILED_MSG(100006,"Remote Call Failed","远程调用失败"),
	REMOTE_CALL_TIMEOUT_MSG(100007,"Remote Call Timeout ","远程调用超时"),
	RESULT_OBJECT_CONVERT_FAILED_MSG(100008,"Result Object Convert Failed","结果对象转换异常"),
	LOGIN_TYPE_LOST_MSG(100009,"Login Type Lost Error","缺少登录类型"),
	
	/**
	 * 增删改提示信息
	 */
	ADD_DATA_TO_DB_FAIL_MSG(200001,"Add Data To Db Fail","保存对象操作失败"),
	ADD_DATA_TO_DB_SUCCESS_MSG(200002,"Add Data To Db success","保存对象操作成功"),
	UPDATE_DATA_TO_DB_FAIL_MSG(200003,"Update Data To Db Fail","修改数据对象操作失败"),
	UPDATE_DATA_TO_DB_SUCCESS_MSG(200004,"Update Data To Db Success","修改数据对象操作成功"),
	DELETE_DATA_TO_DB_FAIL_MSG(200005,"Delete Data To Db Fail","删除数据对象操作失败"),
	DELETE_DATA_TO_DB_SUCCESS_MSG(200006,"Delete Data To Db Success","删除数据对象操作成功"),
	
	/**
	 * 查询提示信息
	 */
	DATA_ALREADY_EXIST_MSG(200007,"Data Already Exist","操作数据已经存在"),
	SELECT_DATA_FROM_DB_FAIL_MSG(200008,"Select Data From Db Fail","查询数据失败"),
	SELECT_DATA_FROM_DB_SUCCESS_MSG(200009,"Select Data From Db Success","查询数据成功"),
	QUERY_DB_NO_DATA_MSG(200010,"Query Db No Data","没有找到符合条件的结果"),
	QUERY_PAGE_DATA_FAIL_MSG(200011,"Query Page Data Fail","分页查询失败"),
	QUERY_PAGE_DATA_SUCCESS_MSG(200012,"Query Page Data Success","分页查询成功"),
	QUERY_TOTAL_DATA_FAIL_MSG(200013,"Query Total Data Fail","查询总条数失败"),
	QUERY_TOTAL_DATA_SUCCESS_MSG(200014,"Query Total Data Success","查询总条数成功"),
	
	
	;
	
	/**
	 * 构造器
	 * @param code
	 * @param desc
	 * @param message
	 */
	BaseCommonMsg(Integer code,String desc,String message){
		this.code = code;
		this.desc = desc;
		this.message = message;
	}
	
	/**
	 * 获得实例.
	 * @param key
	 * @return
	 */
	public static BaseCommonMsg getData(String key) {
		for (BaseCommonMsg msg : BaseCommonMsg.values()) {
			if (key.equalsIgnoreCase(msg.toString())){
				return msg;
			}
		}
		return null;
	}
	
	/**
	 * 返回对象.
	 */
	private Integer code;
	private String desc;
	private String message;
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
