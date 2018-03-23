/**
 * 
 */
package com.lou.userservice.validator;

import com.lou.userservice.util.ParamUtil;

/**
 * @author louxiaobo
 *
 */
public class Validator {
	
	public static void validateValue(Object value, String valueName, VRC... rules) {
		if (!ParamUtil.isEmpty(rules)) {
			for (VRC vr : rules) {
				vr.validate(valueName, value);
			}
		}
	}

}
