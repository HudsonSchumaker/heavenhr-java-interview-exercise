package br.com.schumaker.heavenhr.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.schumaker.heavenhr.model.Application;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
public class TriggerNotification {

	private static transient Logger logger = LoggerFactory.getLogger(TriggerNotification.class);
	
	public static void trigger(Application application) {
		logger.info("Application {} had its status changed to {}.", application.getApplicationId(), application.getStatus());
	}
}