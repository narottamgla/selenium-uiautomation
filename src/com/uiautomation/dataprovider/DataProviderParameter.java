package com.uiautomation.dataprovider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for feeding arguments to methods conforming to the "@DataProvider"
 * annotation type.
 * 
 * @author snarottam
 */
 
@Retention(RetentionPolicy.RUNTIME)
public @interface DataProviderParameter {
	/**
*/
	String[] value();

}