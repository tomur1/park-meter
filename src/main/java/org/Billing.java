package org;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Billing {
	/**
	 * Calculates payment for every starting parking hour
	 * @param activecurrency
	 * @param time
	 * @param user
	 * @return amount of money to pay
	 */

	public static BigDecimal getPayment(CURRENCY activecurrency, long time, User user) {
		boolean vip=user.vip;
		int hours = (int) Math.ceil(time / 3600.0);
		double sum=0;
		if(vip){
			if(hours<=1){
				sum = 0;
			}else
			{
				hours--;
				double multiplayer =1.2;
				sum+=((1-Math.pow(multiplayer, hours))/(1-multiplayer)) * 2;
			}

			
		}else
		{
			if(hours < 1){
				sum = 0;
			}else if(hours == 1){
				sum = 1;
			}else
			{
				hours--;
				sum = 1;
			double multiplayer =1.5;
			sum+=((1-Math.pow(multiplayer, hours))/(1-multiplayer)) * 2;
			}
			
		}
		BigDecimal result = new BigDecimal(sum);
		result = result.setScale(2,RoundingMode.HALF_UP);
		
		return result;
	}

	public static class BillingTest {

		@Before
		public void setUp() throws Exception {

		}

		@Test
		public void testGetPayment() {
			//vip 0 hours
			long time=0;
			User user=new User();
			user.vip=true;

			BigDecimal expected=new BigDecimal(0);
			expected = expected.setScale(2,RoundingMode.HALF_UP);
			BigDecimal actual= getPayment(CURRENCY.PLN, time, user);
			assertEquals(expected,actual);
			//vip 1 hours
			time=3600;
			user.vip=true;

			expected = new BigDecimal(0);
			expected = expected.setScale(2,RoundingMode.HALF_UP);
			actual= getPayment(CURRENCY.PLN, time, user);
			assertEquals(expected,actual);
			//vip 2 hours
			time=7200;
			user.vip=true;

			expected = new BigDecimal(2);
			expected = expected.setScale(2,RoundingMode.HALF_UP);
			actual= getPayment(CURRENCY.PLN, time, user);
			assertEquals(expected,actual);
			//vip 3 hours
			time=10800;
			user.vip=true;

			expected = new BigDecimal(4.4);
			expected = expected.setScale(2,RoundingMode.HALF_UP);
			actual= getPayment(CURRENCY.PLN, time, user);
			assertEquals(expected,actual);

			//vip 10 hours
			time=36000;
			user.vip=true;

			expected = new BigDecimal(41.60);
			expected = expected.setScale(2,RoundingMode.HALF_UP);
			actual= getPayment(CURRENCY.PLN, time, user);
			assertEquals(expected,actual);

			//regular 0 hours
			time=0;
			user.vip=true;

			expected = new BigDecimal(0);
			expected = expected.setScale(2,RoundingMode.HALF_UP);
			actual= getPayment(CURRENCY.PLN, time, user);
			assertEquals(expected,actual);

			//regular 1 hours
			time=3600;
			user.vip=false;

			expected = new BigDecimal(1);
			expected = expected.setScale(2,RoundingMode.HALF_UP);
			actual= getPayment(CURRENCY.PLN, time, user);
			assertEquals(expected,actual);

			//regular 2 hours
			time=7200;
			user.vip=false;

			expected = new BigDecimal(3);
			expected = expected.setScale(2,RoundingMode.HALF_UP);
			actual= getPayment(CURRENCY.PLN, time, user);
			assertEquals(expected,actual);

			//regular 3 hours
			time=10800;
			user.vip=false;

			expected = new BigDecimal(6);
			expected = expected.setScale(2,RoundingMode.HALF_UP);
			actual= getPayment(CURRENCY.PLN, time, user);
			assertEquals(expected,actual);

			//regular 10 hours
			time=36000;
			user.vip=false;

			expected = new BigDecimal(150.77);
			expected = expected.setScale(2,RoundingMode.HALF_UP);
			actual= getPayment(CURRENCY.PLN, time, user);
			assertEquals(expected,actual);
		}

	}
}
