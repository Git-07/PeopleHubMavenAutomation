package Maven;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailed implements IRetryAnalyzer {
	int count = 0;
    int retry = 1;
	public boolean retry(ITestResult result) {
		if(count < retry){
			//System.out.println("Retry for failed test");
			Logging.getTheLogForRetryFailedTest("Retry the failed test");
			count ++;
			return true;
		}
		return false;
	}
}
