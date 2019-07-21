package com.amdocs.Avinash.common.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("mail")
@Data
public class Configuration {

	private Map<String, String> properties = new HashMap<>();

	public String getPropertyValue(String system) {
    	if (this.properties != null && this.properties.get(system) != null)
    		return this.properties.get(system);
    	return null;
    }
}
