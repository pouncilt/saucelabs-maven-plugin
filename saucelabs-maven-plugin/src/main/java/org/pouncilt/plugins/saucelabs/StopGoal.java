package org.pouncilt.plugins.saucelabs;

import java.util.Map;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.pouncilt.saucelabs.SauceLabClient;

/**
 * Goal which touches a timestamp file.
 * 
 * @goal stop
 */
public class StopGoal extends AbstractSauceLabsMojo {
	@Override
	public void doExecute() throws MojoExecutionException, MojoFailureException {
		@SuppressWarnings("unchecked")
		Map<Object, Object> pluginContext = this.getPluginContext();
		SauceLabClient sauceLabClient = (SauceLabClient) pluginContext.get("sauce-lab-client");
		if(sauceLabClient != null){
			sauceLabClient.stop();
		}
	}

}
