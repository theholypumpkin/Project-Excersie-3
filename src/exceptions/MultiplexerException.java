package exceptions;


/**This Method is for handling Multiplexer related exceptions
 * @author Pascal S. PERSONAL INFORMATION REDACTED
 * Created on Dec 17, 2017
 */
public class MultiplexerException extends Exception{
	/**I have no Idea why the compiler want me to add this and if not
	 * he throws me a warning. 
	 * I'll like the javadoc entry about java.io.Serializable below. 
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = -1764333295289612411L;
	
	/**This is the String Exception Message*/
	private String message = null;	
	
	/* ==================================================================== */
	
	
	/**This is the Constructor for a new NoMultiplexerException
	 * @param message The Exception message to be set.
	 */
	
	public MultiplexerException( String message ){
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
