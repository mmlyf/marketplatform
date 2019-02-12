import static org.junit.Assert.*;

import java.text.NumberFormat;

import org.junit.Test;

import com.mtpt.extend.HttpRequest;

public class TestRequest {

	@Test
	public void test() {
		NumberFormat nt = NumberFormat.getPercentInstance();//获取格式化对象
	    nt.setMinimumFractionDigits(2);//设置百分数精确度2即保留两位小数
	    String num = nt.format(0.03);
	    System.out.println(num);
	}

}
