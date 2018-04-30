package org;

public class Result {

	boolean success;
	String errorMessage;
	
	Result(boolean success,String message){
		this.success=success;
		this.errorMessage=message;
	}

}
