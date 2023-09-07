package com.platformX.web.core;

public class EnvParameters {
	public static String globalPropFilePath = System.getProperty("user.dir") + "//src//test//resources//globalConfig.properties";
	public static String envPropFilePath = System.getProperty("user.dir") + "/src/test/resources/TestData/TestData_" + ConfigFileReader.getProperty(globalPropFilePath, "env") + ".properties";
    public static String prelemPropFilePath = System.getProperty("user.dir") + "src/test/resources/TestData/Prelems/TestData_Prelem.properties";
}
