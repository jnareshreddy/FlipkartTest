package com.flipkart.pages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;

	/*
	 * Locators
	 */
	private String closeLoginFrame = "//button[text()='✕']";
	private String appliances = "//img[@alt='Appliances']";
	private String screen_size = "//a[text()='50-55 Inch']";// If needed, we can parameterize inches
	private String minRangeSelectDropdown = "//div[text()='to']/preceding-sibling::div/select";
	private String maxRangeSelectDropdown = "//option[@value='Max']/parent::select";
	private String resolution = "//div[text()='Resolution']/..//*[@class='IIvmWM']";
	private String ultraHDcheckbox = "//div[text()='Ultra HD (4K)']/preceding-sibling::div";
	private String compare = "//span[text()='COMPARE']";
	private String help_center = "//a[text()='Help Center']";

	private String pre_order_link = "//p[contains(text(),'Forthcoming')]";

	private String yes_btn = "//button[text()='Yes']";

	private String place_order_btn = "//span[text()='Place Order']";

	/*
	 * constructor
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Explicit Wait
	 */
	public void waitTillClickable(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}

	/*
	 * Methods
	 */

	public void closeLoginFrame() {
		waitTillClickable(closeLoginFrame);
		driver.findElement(By.xpath(closeLoginFrame)).click();
	}

	public void hoverAppliances() {
		Actions ac = new Actions(driver);
		ac.moveToElement(driver.findElement(By.xpath(appliances))).build().perform();
	}

	public void clickScreensize() {
		waitTillClickable(screen_size);
		driver.findElement(By.xpath(screen_size)).click();
	}

	public void selectMinPricerange(String min) {
		Select st = new Select(driver.findElement(By.xpath(minRangeSelectDropdown)));
		st.selectByValue(min);
	}

	public void selectMaxPricerange(String max) {
		Select st = new Select(driver.findElement(By.xpath(maxRangeSelectDropdown)));
		st.selectByValue(max);
	}

	public void expandResolution() {
		waitTillClickable(resolution);
		driver.findElement(By.xpath(resolution)).click();
	}

	public void selectUltraHDcheckbox() {
		waitTillClickable(ultraHDcheckbox);
		driver.findElement(By.xpath(ultraHDcheckbox)).click();
	}

	public void capturetop10() {
		List<WebElement> top_10 = driver.findElements(By.xpath(
				"//h1[text()='Televisions']/ancestor::div[contains(@style,'background-color')]/following-sibling::div"));

		for (int i = 0; i < 10; i++) {
			System.out.println("Top " + i + " is : " + top_10.get(i).getText());
			System.out.println("*************");
		}
	}

	public void selectAnyThreeTVToCompare() {
		List<WebElement> addToCompareList = driver.findElements(By.xpath(
				"//h1[text()='Televisions']/ancestor::div[contains(@style,'background-color')]/following-sibling::div//input/following-sibling::div"));

		for (int i = 0; i < 3; i++) {
			addToCompareList.get(i).click();
		}
	}

	public void click_compare() {
		waitTillClickable(compare);
		driver.findElement(By.xpath(compare)).click();
	}

	public void takeScreenshot() {
		// Take the screenshot
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\Screenshots\\Capture.png";
		try {

			FileUtils.copyFile(screenshot, new File(path));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void clickLowestAddTocart() {
		List<WebElement> prices = driver.findElements(
				By.xpath("//div[@class='row']//div[@class='col col-1-5 _13lGXD']//div[@class='_30jeq3']"));
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < prices.size(); i++) {

			String value = prices.get(i).getText().replace("$", "").replace(",", "").trim();
			// String after = value.replace("?", "");
			String after = value.substring(1, value.length());
			System.out.println("*********" + after);
			int parseInt = Integer.parseInt(after);
			list.add(parseInt);
		}
		System.out.println("List is : " + list);
		Integer min = Collections.min(list);
		System.out.println("Min Index is : " + min);
		Integer index_min = list.indexOf(min);

		List<WebElement> findElements = driver.findElements(By.xpath(
				"//div[@class='row']//div[@class='_30jeq3']/../../../following-sibling::div//button[text()='ADD TO CART']"));

		for (int i = 0; i < findElements.size(); i++) {
			if (i == index_min) {
				findElements.get(i).click();
				break;
			}
		}
	}

	public void click_HelpCenter() {
		waitTillClickable(help_center);
		driver.findElement(By.xpath(help_center)).click();
	}

	public void placeOrder() {
		waitTillClickable(place_order_btn);
		driver.findElement(By.xpath(place_order_btn)).click();
	}

	public void click_Preorder() {
		waitTillClickable(pre_order_link);
		driver.findElement(By.xpath(pre_order_link)).click();
	}

	public void click_yes() {
		waitTillClickable(yes_btn);
		driver.findElement(By.xpath(yes_btn)).click();
	}

	/*
	 * This method will switch to the window based on title provided as argument
	 */
	public void switchToWindowTitle(String title) {
		Set<String> windows = driver.getWindowHandles();
		for (String eachWindow : windows) {
			driver.switchTo().window(eachWindow);
			String actualWindowTitle = driver.getTitle();
			if (actualWindowTitle.equalsIgnoreCase(title) || actualWindowTitle.contains(title)) {
				driver.manage().window().maximize();
				break;
			}
		}
	}

}
