import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.json.JSONObject;
import org.junit.Test;

import com.mtpt.extend.OtherMethod;

public class TestMainClass {

	@Test
	public void test() {
		String agw = "AGW20190417160522505";
		try {
			String result = OtherMethod.selectSecondConfirmForXier(agw);
			System.out.println(result);
			JSONObject json = new JSONObject(result);
			
			JSONObject confirmmo = json.getJSONObject("confirmMo");
			String deliver_time = confirmmo.getString("deliver_time");
			String mocontent = confirmmo.getString("ud");
			JSONObject confirmmt = json.getJSONObject("confirmMt");
			String submit_time = confirmmt.getString("submit_time");
			String mtcontent = confirmmt.getString("ud");
			System.out.println("二次确认时间："+deliver_time+"\n二次确认的时间："+mocontent+"\n提交的时间："+submit_time+"\n下行的内容："+mtcontent);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
