package org;

import java.io.IOException;
import java.math.BigDecimal;

public class Registration {
	final static String INCORRECT_USER_ID = "Incorect user id";
	final static String INCORRECT_PARKING_ID = "Incorect parking id";
	final static CURRENCY activeCurrency=CURRENCY.PLN;

	static Result register(String userId, String parkingId) {

		if (!validateUserId(userId)) {
			return new Result(false, INCORRECT_USER_ID);
		}

		if (!validateParkingId(parkingId)) {
			return new Result(false, INCORRECT_PARKING_ID);
		}

		// TODO: perform real register

		return new Result(true, null);

	}

	public static Result unregister(String userId, String parkingId) {
		if (!validateUserId(userId)) {
			return new Result(false, INCORRECT_USER_ID);
		}

		if (!validateParkingId(parkingId)) {
			return new Result(false, INCORRECT_PARKING_ID);
		}

		// TODO: perform real unregister

		return new Result(true, null);

	}

	static private boolean validateUserId(String userId) {
		if(userId==null)
		{
			return false;
		}
		//TODO perform real validation
		return true;
	}

	static private boolean validateParkingId(String parkingId) {
		if(parkingId==null)
		{
			return false;
		}
		//TODO perform real validation
		return true;
	}

	public static BigDecimal getCurrentCost(String userId) throws IOException {
		if(!validateUserId(userId))
		{
			throw new IOException();
		}
		long time=DbHelper.getCurrentRegistrationTime(userId);
		User user=DbHelper.getUser(userId);
		BigDecimal price=Billing.getPayment(activeCurrency,time,user);
		return price;
	}

}
