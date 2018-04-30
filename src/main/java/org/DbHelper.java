package org;

public class DbHelper {
	
/**
 * Gets the time elapsed since registration
 * @param userId
 * @return long in seconds
 */
	public static long getCurrentRegistrationTime(String userId) {
		// TODO Get how long car is parked from DB
		return 0;
	}
	/**
	 * Gets User
	 * @param userId
	 * @return null if User doesn't exist
	 */
	public static User getUser(String userId) {
		// TODO get real user from DB
		User user = new User();
		return user;
	}

}
