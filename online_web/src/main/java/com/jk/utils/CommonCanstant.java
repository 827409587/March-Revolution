package com.jk.utils;


public class CommonCanstant {

	public static final String NAV_TREE_CACHE = "navTreeCache";//导航树的缓存
	
	
	public static final String POWER_TREE_CACHE = "powerTreeCache";//导航权限树的缓存
	
	
	
	public static final String MSG_VALCODE = "valcode";//短信验证码缓存key
	
	
	//短信验证码
	
	/**
	 * url前半部分
	 */
	public static final String MSG_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";

	/**
	 * 开发者注册后系统自动生成的账号，可在官网登录后查看
	 */
	public static final String MSG_ACCOUNT_SID = "ec3eab7c989543609353dd437834271d";

	
	/*ec3eab7c989543609353dd437834271d
	b52f84f838334ffcac438af2ac0b020b
	1458320866*/
	/**
	 * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
	 */
	public static final String MSG_TOKEN = "b52f84f838334ffcac438af2ac0b020b";
	
	
	
	/**
	 * 发送短信模版ID
	 */
	public static final String TEMPLATE_ID = "1458320866";

	//机器人
	/**
	 * 只能机器人地址
	 */
	public static final String ROBOT_URL = "http://api.qingyunke.com/api.php";
	/**
	 * 机器人固定参数free
	 */
	public static final String ROBOT_KEY = "free";
	/**
	 * 机器人表示智能识别，可忽略此参数
	 */
	public static final String ROBOT_APPID = "0";
	/**
	 * 机器人成功状态码
	 */
	public static final String ROBOT_RESULT = "0";
}
