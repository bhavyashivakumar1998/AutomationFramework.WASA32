package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionsPractice
{

	@Test
	public void sample1()
	{
		System.out.println("Step1");
		System.out.println("Step2");
		Assert.assertEquals(true, false);
		
		System.out.println("Step3");
		System.out.println("Step4");
	}
	
	@Test
	public void sample2()
	{
		SoftAssert sa= new SoftAssert();
		System.out.println(" SecomdStep1");
		Assert.assertTrue(false);
		System.out.println(" second Step2");
		Assert.assertEquals("A", "A");//failure
		System.out.println(" second Step3");
		sa.assertTrue(false);
		System.out.println("second Step4");
		sa.assertAll(); // log all the assertions failure
	}
}
