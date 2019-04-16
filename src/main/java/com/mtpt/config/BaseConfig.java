package com.mtpt.config;

import com.sun.org.glassfish.external.statistics.Statistic;

public class BaseConfig {
	/**
     * 正则：手机号（精确）
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198</p>
     * <p>联通：130、131、132、145、155、156、175、176、185、186、166</p>
     * <p>电信：133、153、173、177、180、181、189、199</p>
     * <p>全球星：1349</p>
     * <p>虚拟运营商：170</p>
     */
    public static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
    
    /**
     * 河北支付宝绑定用户数据导出保存的路径
     */
    public static final String ALIPATH = "D://NEW_HSDTMarket_Platform/aliuserexport/";
    
    /**
     * 河北维度筛选任务数据导出保存的路径
     */
    public static final String MODELPATH = "D://NEW_HSDTMarket_Platform/modeldataexport/";
    
    /**
     * 河北标签任务数据导出保存的路径
     */
    public static final String SCENEPATH = "D://NEW_HSDTMarket_Platform/exportscenedata/";
    
    /**
     * 河北冰激凌数据导出保存路径(预约数据和申请客诉数据)
     */
    public static final String ICEPATH = "D://NEW_HSDTMarket_Platform/icedataexport/";
    
    /**
     * log日志文件的配置文件的路径
     */
    public static final String LOGPATH = "D://NEW_HSDTMarket_Platform/config/log.properties";
    
    /**
     * 是获取每天运营数据的FTP地址
     */
    public static final String FTPURL = "133.96.79.52";
    
    /**
     * 是获取每天运营数据的FTP服务器的端口
     */
    public static final int FTPPORT = 21;
    
    /**
     * FTP服务器登录名
     */
    public static final String FTPUSER = "data_combine";
    
    /**
     * FTP服务器登录密码
     */
    public static final String FTPPASS = "Celue2018)&)(";
    
    /**
     * 实际运营文件在FTP服务器中所在路径位置
     */
    public static final String FTPPATH = "/data_out/";
    
    /**
     * 运营文件保存在河北服务器中的位置
     */
    public static final String URLPATH = "D:/data_out/";
    
    /**
     * 存放抽奖数据的文件路径（FTP文件的实际路径）
     */
    public static final String EQUITYOUT = "D://webwww/FTPdsj/QYDATA/";
    
    /**
     * 存放抽奖路径的测试文件路径
     */
    public static final String TESTPATH = "/Users/lvgordon/Downloads/little/";
    
    /**
     * 请求号码的详细内容的地址
     */
    public static final String DNDETAILPATH = "http://mobile99.uninforun.com/unicom-hb/api/Unicom/GetUserInfo";
    
    /**
     * 营销任务数据文件存放在服务器上的位置
     */
    public static final String SCENE_MARKET_FILEPATH = "D://NEW_HSDTMarket_Platform/upload/scenedataup";
    
    /**
     * 订单导出数据文件的存储位置
     */
    public static final String ORDERS_OUTFILE_PATH = "D://NEW_HSDTMarket_Platform/ordersfile";
    
    //public static final String ORDERS_TEST_PATH = "/Users/lvgordon/Downloads/little";
    
    /**
     * 协尔平台二次确认接口参数
     */
    //二次确认账号
    public static final String XIEER_ACCOUNT = "HSDT"; 
    
    //二次确认请求接口的密码
    public static final String XIEER_PASSWORD = "1097467";
    
    //二次确认请求接口的秘钥
    public static final String XIEER_SCRIPT = "hsdt2019)$!^";
    
    //二次确认请求接口的地址
    public static final String XIEER_URL = "http://221.192.155.55:8082/impgw-api/api";
    
    
}
