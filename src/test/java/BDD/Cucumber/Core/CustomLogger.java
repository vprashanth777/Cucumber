package BDD.Cucumber.Core;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.*;;

public class CustomLogger {

	public static Logger log(Class sourceClass){
		Logger log = Logger.getLogger(sourceClass);
		PropertyConfigurator.configure("./src/test/resources/log4j.properties");
		return log;
	}
		
}
