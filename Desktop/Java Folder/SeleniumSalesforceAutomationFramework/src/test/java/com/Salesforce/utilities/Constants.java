package com.Salesforce.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {
	public static final String CURR_DIR = System.getProperty("user.dir");
	public static final String APPLICATION_PROPERTIES = CURR_DIR + "/src/test/resources/ApplicationProperties.properties";
	public static final String SPARKS_HTML_REPORT_PATH=CURR_DIR+"/Reports/report.html";
	public static final String SCREENSHOTS_DIRECTORY_PATH=CURR_DIR+"/Reports/Screenshots/";
	public static final String UniqueName = new SimpleDateFormat("HHmmss").format(new Date());
}
