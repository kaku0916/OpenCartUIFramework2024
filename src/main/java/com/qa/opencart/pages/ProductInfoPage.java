package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eliUtil;
	private Map<String, String> productMap=new TreeMap<String, String>();

	private By productHeader=By.tagName("h1");
	private By images=By.cssSelector(".thumbnail img");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	
	ProductInfoPage(WebDriver driver){
		this.driver=driver;
		eliUtil=new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		String header= eliUtil.doGetElementText(productHeader);
		System.out.println(header);
		return header;
	}
	
	public int getProductImageCount() {
		int totalImages=eliUtil.waitForElementsVisible(images, 10).size();
		System.out.println("Image Count for "+getProductHeader()+" : "+totalImages);
		return totalImages;
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	public void getProductMetaData() {
		List<WebElement> metaList=eliUtil.getElements(productMetaData);
		
		for(WebElement e:metaList) {
			String text=e.getText();
		String	metaKey=text.split(":")[0].trim();
		String	metaValue=text.split(":")[1].trim();
		productMap.put(metaKey, metaValue);
			
		}
	}
	
//	$2,000.00
//	Ex Tax: $2,000.00
	
	public void getProductPricingData() {
		List<WebElement> PriceList=eliUtil.getElements(productPriceData);
		String price=PriceList.get(0).getText();
		String extTaxPrice=PriceList.get(1).getText().split(":")[1].trim();
			productMap.put("productPrice", price);
			productMap.put("externalTax",extTaxPrice) ;
		
	}
	
	public Map<String, String> getProductDetailsMap() {
		productMap.put("header", getProductHeader());
		productMap.put("imagesCount;", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getProductPricingData();
		System.out.println("Product Details : \n"+productMap);
		return productMap;
		
	}
}
