package multiplexer;

import binaryoperations.TransmitableObject;
import exceptions.WrongInputException;

/**This it the Multiplexer Class it stores processes the Mutiplexer
 * @author Pascal S. PERSONAL INFORMATION REDACTED
 * <br>
 * Created on Dec 22, 2017
 * @version 2.1.0
 */

public class Multiplexer{
	
	/**A variable with the amount of Inputs the Multiplexer has
	 * Values can be set by {@link #setAmountOfInputs(long)}
	 * and received by {@link #getAmountOfInputs()}
	 * */
	private int amountOfInputs = 0;
	
	/**This Variable keeps track if the current data running tru the
	 * Multiplexer is an even or Odd frame
	 * Default is falues because it gets swapped with the first mux creation
	 * Values can be set by {@link #setEvenFrame()}
	 * and received by {@link #getIsEvenFrame()}
	 */
	private boolean isEvenFrame = false; 

	/**This is the "Data" Array with all Data witch is currently with the multiplexer
	 * The array contains at MAX 32 objects of the type
	 * {@link binaryoperations.TransmitableObject#TransmitableObject()}
	 */
	private TransmitableObject[] dataStream = new TransmitableObject[32];
	
	/* ==================================================================== */
	
	
	/**This it a Default Constructor*/
	
	public Multiplexer() {}

	/* ==================================================================== */
	
	
	/**This is the getter Method for {@link #amountOfInputs}
	 * @return amountOfInputs
	 */
	
	public int getAmountOfInputs() {
		return amountOfInputs;
	}


	/**This is the setter Method for {@link #amountOfInputs}
	 * @param amountOfInputs The Value to be set
	 */
	
	public void setAmountOfInputs( int amountOfInputs ) {
		this.amountOfInputs = amountOfInputs;
	}


	/**This is the getter Method for {@link #isEvenFrame}
	 * @return isEvenFrame
	 */
	
	public boolean getIsEvenFrame() {
		return isEvenFrame;
	}

	
	/**This is the setter Method for {@link #isEvenFrame}
	 * The method just swaps the boolean value from true to false and vice versa
	 */
	
	public void setEvenFrame() {
		this.isEvenFrame = !isEvenFrame;
	}

	
	/**This is the getter Method for {@link #dataStream}
	 * @return The dataStream Array.
	 */
	
	public TransmitableObject[] getDataStream() {
		return dataStream;
	}


	/**This Method is setting <b>one</b>  new 
	 * {@link binaryoperations.TransmitableObject#TransmitableObject()} into
	 * {@link #dataStream}
	 * @param tob the tob to set
	 * @throws WrongInputException x
	 */
	
	public void setDataStream( TransmitableObject tob ) throws WrongInputException {
		
		byte channelID = tob.getChannelID();
		dataStream[channelID] = tob;
		//System.out.println( "DEBUG: tob setted in array at"+ channelID++ );

	}
}
