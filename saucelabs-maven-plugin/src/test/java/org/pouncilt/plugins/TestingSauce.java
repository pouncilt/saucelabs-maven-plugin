package org.pouncilt.plugins;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

public class TestingSauce extends AbstractMojoTestCase {
	//private DefaultSelenium selenium;

	public void setUp() throws Exception {
		super.setUp();
		/*DefaultSelenium selenium = new DefaultSelenium(
				"ondemand.saucelabs.com",
				80,
				"{\"username\": \"pouncilt\","
						+ "\"access-key\": \"0aaf398d-07cf-4d23-8fd2-b8d4435b46cd\","
						+ "\"os\": \"Windows 2003\","
						+ "\"browser\": \"firefox\","
						+ "\"browser-version\": \"7\","
						+ "\"name\": \"Testing Selenium 1 with Java on Sauce\"}",
				"http://saucelabs.com/");
		selenium.start();
		this.selenium = selenium;*/
	}

	/*public void testSauce() throws Exception {
		this.selenium.open("/test/guinea-pig");
		//assertEquals("I am a page title - Sauce Labs", this.selenium.getTitle());
	}*/

	public void tearDown() throws Exception {
		super.tearDown();
		//this.selenium.stop();
	}

	/**
	 * @throws Exception if any
	 */
	public void testStartStop() throws Exception {
        File pom = getTestFile( "src/test/resources/projects/project-to-test/pom.xml" );
        assertNotNull( pom );
        assertTrue( pom.exists() );
        //start(pom);
        //stop(pom);
	}

    public void start(File pom) throws Exception {
        Start sauceLabsStartMojo = (Start) lookupMojo( "start", pom );
        assertNotNull( sauceLabsStartMojo );
        sauceLabsStartMojo.execute();
	}
	
	public void stop(File pom) throws Exception { 
        Stop sauceLabsStopMojo = (Stop) lookupMojo( "stop", pom );
        assertNotNull( sauceLabsStopMojo );
        sauceLabsStopMojo.execute();
    }
}
