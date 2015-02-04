package testing;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import bigram.cf.logisticRegression.ControllerMainClass;

public class Testlog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		PropertyConfigurator.configure("build/classes/demo/log4j/Log4j.properties");
		Logger logger = Logger.getLogger(ControllerMainClass.class);
		logger.debug("message1");
		logger.info("message2");
		logger.debug("message3");
		logger.debug("finish");
	}

}
