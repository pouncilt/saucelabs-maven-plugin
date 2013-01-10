package org.pouncilt.plugins.saucelabs;

import java.io.File;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

public class SauceLabMojo extends AbstractMojoTestCase {
	private StartGoal start = null;
	private StopGoal stop = null;
	private File pom;
	
	public SauceLabMojo(String pomFileLocation) throws Exception {
		this.pom = getTestFile(pomFileLocation);
		this.start = (StartGoal) lookupMojo( "start", pom );
		this.stop = (StopGoal) lookupMojo( "stop", pom );
	}

	public static void main(String[] args) throws Exception {
		SauceLabMojo mojo = new SauceLabMojo(args[0]);
		if(args[1].equalsIgnoreCase("start")) mojo.start();
		if(args[1].equalsIgnoreCase("stop")) mojo.stop();
	}

	private void stop() throws Exception {
		stop.execute();
	}

	private void start() throws Exception {
		start.execute();
	}

}
