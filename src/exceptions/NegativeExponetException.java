package exceptions;


/**This Class is an Exception class for handling Exceptions of the Type:
 * NegativeExponentException.
 * When the exponent of a 
 * {@link binaryoperations.Arithmetic#twoToBytePower(byte, byte)}
 * is less or than 1 (in this case I thread 0 as a negative number)
 * @author Pascal S. PERSONAL INFORMATION REDACTED
 * Created on Dec 13, 2017
 */

public class NegativeExponetException extends Exception{
	
	
	/**I have no Idea why the compiler want me to add this and if not
	 * he throws me a warning. 
	 * I'll like the javadoc entry about java.io.Serializable below. 
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 4503145507410611810L;
	
	/**This is the String Exception Message*/
	private String message = null;
	
	/* ==================================================================== */
	
	
	/**This is the Constructor for a new NegativeExponetException
	 * @param message The Exception message to be set.
	 */
	
	public NegativeExponetException( String message){
		this.message = message;
	}
	
	/* ==================================================================== */
	
	
	/**This is the getter method for this Exception.
	* @return message -The Message String
	*/
	
	public String getMessage() {
		return message;
	}
}
