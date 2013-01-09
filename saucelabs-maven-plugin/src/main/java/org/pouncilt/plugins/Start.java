package org.pouncilt.plugins;

import java.util.Hashtable;
import java.util.Map;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.pouncilt.saucelabs.SauceLabClient;

/**
 * Goal which touches a timestamp file.
 * 
 * @goal start
 */
public class Start extends AbstractSauceLabsMojo{

	@Override
	protected void doExecute() throws MojoExecutionException, MojoFailureException {
		SauceLabClient sauceLabClient = new SauceLabClient(desiredPlatforms, remoteUrl, wait);
		sauceLabClient.start(targetUrl);
		// TODO: Need to figure out how to save the SauceLabClient object in the plugin context.
		//		 And then retrieve SauceLabClient Object from the Stop Mojo.
		@SuppressWarnings("unchecked")
		Map<Object, Object> pluginContext = this.getPluginContext();
		if(pluginContext == null){
			pluginContext = new Hashtable<Object, Object>();
			this.setPluginContext(pluginContext);
		}
		pluginContext.put("sauce-lab-client", sauceLabClient);
	}
}
