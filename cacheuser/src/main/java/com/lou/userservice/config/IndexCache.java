/**
 * 
 */
package com.lou.userservice.config;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author louxiaobo
 *
 */
public class IndexCache {

	private volatile ArrayList<Long> idIndex = new ArrayList<>();
	private volatile ArrayList<String> usernameIndex = new ArrayList<>();

	public IndexCache() {

	}

	public ArrayList<Long> getIdIndex() {
		return this.idIndex;
	}

	public ArrayList<String> getUsernameIndex() {
		return this.usernameIndex;
	}

	public void addIdIndex(Long id) {

		synchronized (idIndex) {
			idIndex.add(id);
		}
	}
	
	public void evalIdIndex(Long id) {

		synchronized (idIndex) {
			idIndex.remove(id);
		}
	}

	public void addUsernameIndex(String username) {

		synchronized (usernameIndex) {
			usernameIndex.add(username);
			Collections.sort(usernameIndex, String.CASE_INSENSITIVE_ORDER);
		}
	}
	
	public void evalUsernameIndex(String username) {

		synchronized (usernameIndex) {
			usernameIndex.remove(username);
			Collections.sort(usernameIndex, String.CASE_INSENSITIVE_ORDER);
		}
	}
	

}
