package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyser {

	@Test(retryAnalyzer = vtiger.GenericUtilities.RetryAnalyserImplementation.class)
	public void practice()
	{
		Assert.fail();
		System.out.println("Hi");
	}
}
