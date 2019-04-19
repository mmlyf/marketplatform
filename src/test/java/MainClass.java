import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mtpt.config.BaseConfig;
import com.mtpt.extend.HttpRequest;
import com.mtpt.extend.MD5;
import com.mtpt.extend.MyDesUtils;

public class MainClass {
	public static void main(String[] args) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String datestr = simpleDateFormat.format(new Date());
		System.out.println(datestr);
		String pwd = datestr + MD5.md(BaseConfig.XIEER_PASSWORD);
		String mdpwd = MD5.md(pwd);
		String params = "{" + 
				"\"method\": \"order.confirm.result\"," + 
				"\"version\":\"1\"," + 
				"\"timestamp\":\""+datestr+"\"," + 
				"\"app_key\":\""+BaseConfig.XIEER_ACCOUNT+"\"," + 
				"\"app_pwd\":\""+mdpwd+"\"," + 
				"\"transaction_id\": \"AGW20190415133059352\"" + 
				"}" ;
		System.out.println(params);
		String result = HttpRequest.sendPostForSelectSecConfirm(BaseConfig.XIEER_URL, params);
		MyDesUtils myDesUtils = new MyDesUtils(BaseConfig.XIEER_SCRIPT);
		System.out.println("jieguo shi "+result);
		String dString = myDesUtils.decrypt(result);
		String decodestr = URLDecoder.decode(dString, "utf-8");
		System.out.println("jiemihou de"+decodestr);
	}
}
