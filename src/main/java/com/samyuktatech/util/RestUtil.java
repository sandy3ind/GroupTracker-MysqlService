package com.samyuktatech.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RestUtil {

	public static <T> HttpEntity<?> getHttpEntityJson(T entity) {
		HttpHeaders requestHeaders = new HttpHeaders();
	    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return new HttpEntity<>(entity, requestHeaders);
	}
}
