import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mtpt.timework.TimeToOutputEquityData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-mvc.xml"})
public class TestCommects {

	@Test
	public void testOutputEquityData() {
		TimeToOutputEquityData timeToOutputEquityData = new TimeToOutputEquityData();
		timeToOutputEquityData.outputEquityData();
	}

}
