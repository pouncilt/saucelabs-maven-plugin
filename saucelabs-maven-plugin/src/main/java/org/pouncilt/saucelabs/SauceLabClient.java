package org.pouncilt.saucelabs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceLabClient{
	private List<WebDriver> drivers = new ArrayList<WebDriver>();
	private List<DesiredCapabilities> platforms = null;
	private URL remoteAddressUrl = null; //http://pouncilt:0aaf398d-07cf-4d23-8fd2-b8d4435b46cd@ondemand.saucelabs.com:80/wd/hub
	private int implicitlyWait = 30;
	
	/*
	capabilities.setCapability("version", "5");
    capabilities.setCapability("platform", Platform.XP);
    capabilities.setCapability("name", "Testing Selenium 2 with Java on Sauce");
	*/
	
	
	public SauceLabClient(List<DesiredCapabilities> platforms, URL remoteAddress, int implicitlyWait){
		this.platforms = platforms;
		this.remoteAddressUrl = remoteAddress;
		this.implicitlyWait = implicitlyWait;
	}
	
	public void start(URL targetUrl){ //"http://localhost:9876/capture"
		for(DesiredCapabilities capabilities: platforms){
			WebDriver driver = new RemoteWebDriver(remoteAddressUrl, capabilities);
			driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
			driver.get(targetUrl.toString());
			drivers.add(driver);
		}
	}
	
	public void stop(){
		for(WebDriver driver: drivers){
			driver.quit();
		}
	}

	public List<WebDriver> getDrivers() {
		return drivers;
	}

	public List<DesiredCapabilities> getPlatforms() {
		return platforms;
	}

	public URL getRemoteAddressUrl() {
		return remoteAddressUrl;
	}

	public int getImplicitlyWait() {
		return implicitlyWait;
	}
}
