/**
 * 
 */
package com.corbo.musicstreaming.model;

/**
 * Encapsulates a return code and a return object to be sent back to a caller.
 * 
 * @author Oliver Corbisiero
 * 
 */
public class CallResult<T> {

	private int returnCode;
	private T resultObject;

	/**
	 * Create a resource result with the supplied return code and result object
	 * 
	 * @param returnCode
	 * @param resultObject
	 */
	public CallResult(int returnCode, T resultObject) {
		this.returnCode = returnCode;
		this.resultObject = resultObject;
	}

	/**
	 * @return the return code to send back
	 */
	public int getReturnCode() {
		return returnCode;
	}

	/**
	 * @return the object to send back
	 */
	public T getResultObject() {
		return resultObject;
	}
}
