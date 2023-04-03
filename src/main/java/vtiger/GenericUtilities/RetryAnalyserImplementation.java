package vtiger.GenericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * this class provides implementation for rertyanalyser interface of TestNG
 * @author admin
 *
 */
public class RetryAnalyserImplementation implements IRetryAnalyzer {

	int count=0;
	int retryCount=3;
	
	/**
	 * this method will retry for 3 times.
	 */
	public boolean retry(ITestResult result) {
		
		while(count<retryCount)
		{
			count++;
			return true; // retry()
		}
		
		return false;
	}

	
}
