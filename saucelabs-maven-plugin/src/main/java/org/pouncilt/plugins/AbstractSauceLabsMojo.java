package org.pouncilt.plugins;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.pouncilt.saucelabs.SauceLabClient;

public abstract class AbstractSauceLabsMojo extends AbstractMojo {
	/**
     * Sauce Labs remote address URL.
     *
     * @parameter
     */
    protected URL remoteUrl;  //http://pouncilt:0aaf398d-07cf-4d23-8fd2-b8d4435b46cd@ondemand.saucelabs.com:80/wd/hub
    
    /**
     * Target address URL.
     *
     * @parameter
     */
    protected URL targetUrl; //"http://localhost:9876/capture"
    
    /**
     * Implicityly Wait Integer.
     *
     * @parameter
     */
    protected Integer wait;
    
    /**
     * Desired Platforms.
     * 
     * @parameter
     */
    @SuppressWarnings("unused")
	private List<String> desiredPlatformDetails;
    protected List<DesiredCapabilities> desiredPlatforms = new ArrayList<DesiredCapabilities>();
    protected SauceLabClient sauceLabClient = null;
    
    /**
     * format
     * 	<desiredPlatformDetails>
     * 		<desiredPlatformDetail>browserName, browserVersion, Platform.type, description</desiredPlatformDetail>
     * 	</desiredPlatformDetails>
     * @param desiredPlatformDetails
     */
	public void setDesiredPlatformDetails(List<String> desiredPlatformDetails) {
		for(String desiredPlatformDetail: desiredPlatformDetails){
			String[] desiredPlatformDetailsArray = desiredPlatformDetail.split(",");
			String browserName = desiredPlatformDetailsArray[0].trim();
			String browserVersion = desiredPlatformDetailsArray[1].trim();
			String platformName = desiredPlatformDetailsArray[2].trim();
			String testDescription = desiredPlatformDetailsArray[3].trim();
			
			DesiredCapabilities desiredPlatform = new DesiredCapabilities();//getBrowserName(browserName), 
			desiredPlatform.setBrowserName(browserName);
			desiredPlatform.setVersion(browserVersion);
			desiredPlatform.setPlatform(getPlatform(platformName));
			desiredPlatform.setCapability("name", testDescription);
			desiredPlatforms.add(desiredPlatform);
		}
	}

	private Platform getPlatform(String platformName) {
		return Platform.valueOf(platformName.toUpperCase());
	}

	/*private DesiredCapabilities getBrowserName(String browserName) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Class<?> c = Class.forName(browserName.substring(0, browserName.lastIndexOf('.')));
		Method m = c.getMethod(browserName.substring(browserName.lastIndexOf('.') + 1, browserName.indexOf("(")));
		return (DesiredCapabilities) m.invoke(null, new Object[0]);
	}*/
    
	public URL getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(URL targetUrl) {
		this.targetUrl = targetUrl;
	}

	public URL getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(URL remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	public Integer getWait() {
		return wait;
	}

	public void setWait(Integer wait) {
		this.wait = wait;
	}

	public void execute() throws MojoExecutionException, MojoFailureException {
		doExecute();
	}

	protected abstract void doExecute()  throws MojoExecutionException, MojoFailureException;

}
