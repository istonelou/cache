/**
 * 
 */
package com.lou.userservice.util;

import java.util.List;
import java.util.Map;


/**
 * @author louxiaobo
 *
 */
public class ParamUtil {
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		boolean result = false;
		if (obj == null) {
			result = true;
		} else if (obj instanceof String) {
			result = ((String) obj).isEmpty();
		} else if (obj instanceof Object[]) {
			result = ((Object[]) obj).length == 0;
		} else if (obj instanceof List) {
			result = ((List) obj).size() == 0;
		} else if (obj instanceof Map) {
			result = ((Map) obj).size() == 0;
		}
		return result;
	}
	
	
}
