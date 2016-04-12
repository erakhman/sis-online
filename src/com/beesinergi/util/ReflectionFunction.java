package com.beesinergi.util;

import java.lang.reflect.Field;

public class ReflectionFunction {
    
    public static void setProperties(Class domainClass, String prop, String value) {
    	try {
			Field field = domainClass.getField(prop);
			if (field.getType().isAssignableFrom(Integer.class) ||
					field.getType().isAssignableFrom(int.class)) {
				field.setInt(null, Integer.valueOf(value));
			} else if (field.getType().isAssignableFrom(Long.class) ||
					field.getType().isAssignableFrom(long.class)) {
				field.setLong(null, Integer.valueOf(value));
			} else if (field.getType().isAssignableFrom(Boolean.class) ||
					field.getType().isAssignableFrom(boolean.class)) {
				field.setBoolean(null, Boolean.parseBoolean(value));
			} else if (field.getType().isAssignableFrom(Double.class) ||
					field.getType().isAssignableFrom(double.class)) {
				field.setDouble(null, Double.parseDouble(value));
			} else {
				field.set(null, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
