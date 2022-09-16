package exceptions;



/**This Class is an Exception class for handling Exceptions of the Type:
 * WrongInputException.
 * This Exception is called in the Methods:
 * @author Pascal S. PERSONAL INFORMATION REDACTED
 * Created on Dec 15, 2017
 */

public class WrongInputException extends Exception{
	
	
	/**I have no Idea why the compiler want me to add this and if not
	 * he throws me a warning. 
	 * I'll like the javadoc entry about java.io.Serializable below. 
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = -5222767359239543199L;
	
	/**This is the String Exception Message*/
	private String message = null;	
	
	/* ==================================================================== */
	
	
	/**This is the Constructor for a new WrongInputException
	 * @param message The Exception message to be set.
	 */
	
	public WrongInputException( String message ){
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
