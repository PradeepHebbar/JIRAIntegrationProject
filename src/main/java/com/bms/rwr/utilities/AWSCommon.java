package com.bms.rwr.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;

public class AWSCommon {
	private static final Logger logger = LogManager.getLogger(AWSCommon.class);
	
	public static ClientConfiguration returnClientConfigs(){
		ClientConfiguration clientConfig=null;
		try{
		clientConfig = new ClientConfiguration();
		clientConfig.setProtocol(Protocol.HTTP);
		clientConfig.setProxyHost("proxy-server.bms.com");
		clientConfig.setProxyPort(8080);
		}catch(Exception ex){
			logger.info("Error while setting the client configuration" +ex);
		}
		return clientConfig;
	}

}
