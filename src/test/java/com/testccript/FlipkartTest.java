package com.testccript;

import org.testng.annotations.Test;

import com.flipkart.base.Base;
import com.flipkart.pages.LoginPage;

public class FlipkartTest extends Base {

	@Test
	public void flow() throws Exception {

		LoginPage loc = new LoginPage(driver);
		loc.closeLoginFrame();
		loc.hoverAppliances();
		loc.clickScreensize();
		loc.selectMinPricerange("20000");
		loc.selectMaxPricerange("60000");
		loc.expandResolution();
		loc.selectUltraHDcheckbox();
		Thread.sleep(5000);
		loc.capturetop10();
		loc.selectAnyThreeTVToCompare();
		loc.click_compare();
		loc.takeScreenshot();
		loc.clickLowestAddTocart();
		loc.click_HelpCenter();
		loc.switchToWindowTitle("Help center");
		loc.click_Preorder();
		loc.click_yes();
		loc.switchToWindowTitle("Shopping Cart");
		loc.placeOrder();

	}

}
