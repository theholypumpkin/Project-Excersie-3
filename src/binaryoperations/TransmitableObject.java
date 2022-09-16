package binaryoperations;

import exceptions.MultiplexerException;
import multiplexer.Multiplexer;

/**This Class is intended to be a object to travel tru the multiplexer
 * it the sub class of {@link Values#Values()}
 * @author Pascal S. PERSONAL INFORMATION REDACTED
 * Created on Dec 18, 2017
 * @version 2.5
 */
public class TransmitableObject extends Values{
	
	/**A new boolean array with the size of {@link #getAMOUNTOFBITS()}*/
	private boolean binary[] = new boolean[super.getAMOUNTOFBITS()];
	
	/**A Variable containing the Channel ID
	 * Value can be set by {@link #setChannelID(byte)}
	 * and received by {@link #getChannelID()}
	 */
	private byte channelID = 0;
	
	/**A Variable containing the Input ID
	 * Value can be set by {@link #setInputID(long)}
	 * and received by {@link #getInputID()}
	 */
	private int inputID = 0;
	
	/**A Variable containing the Ouput ID
	 * Value can be set by {@link #setOutputID(long)}
	 * and received by {@link #getOutputID()}
	 */
	private int outputID = 0;
	
	/**Checks if the
	 * {@link #TransmitableObject()}
	 * ever got a value setted.
	 * Is a clone of {@link  multiplexer.SingletonDemultiplexer#getHasReference()}
	 */
	private boolean hasReference = false;
	
	/**Checks if the
	 * {@link #TransmitableObject()}
	 * ever got a value setted.
	 * Is a clone of {@link multiplexer.SingletonDemultiplexer#getHasValues()}
	 */
	private boolean hasValues = false;
	
	/* ==================================================================== */
	
	
	/**Default Constructor*/
	
	public TransmitableObject() {}
	
	
	/**Constructor with:
	 * 
	 * @param inputID The Input ID to be set
	 * @param channelID The Channel ID to be set
	 * @param mux A object of the type {@link multiplexer.Multiplexer#Multiplexer()}
	 * @throws MultiplexerException When Index of Multiplexer is out of Bounds
	 */
	
	public TransmitableObject(int inputID, byte channelID, Multiplexer mux) 
		throws MultiplexerException {
		if (inputID <= mux.getAmountOfInputs()) {
			
			this.inputID = inputID;
			this.channelID = channelID;

		}else {
			throw new MultiplexerException("Error Input Index ot of Bounds "
			+ "can't assign input to channel");
		}
	}
	
	
	/**Constructor with:
	 * @param channelID The Channel ID to be set
	 * @param mux A object of the type {@link multiplexer.Multiplexer#Multiplexer()}
	 */
	
	public TransmitableObject( byte channelID, Multiplexer mux) {
		if (channelID == 0) {
			if (mux.getIsEvenFrame()) {
				boolean [] binary = {false,false,false,true,true,false,true,true};
				this.channelID = channelID;
				this.binary = binary;
			}else {
				boolean [] binary = {false,true,false,false,false,false,false,false};
				this.channelID = channelID;
				this.binary = binary;
			}
		}else if (channelID == 16) {
			boolean [] binary = {false,true,false,true,true,true,true,true};
			this.channelID = channelID;
			this.binary = binary;
		}else {
			boolean [] binary = {false,false,false,false,false,false,false,false};
			this.channelID = channelID;
			this.binary = binary;
		}
	}
	
	/* ==================================================================== */
	
	/**This is the getter method for {@link #binary}
	 * This Method overwrites {@link Values#getBinary()}
	 * @return A boolean array
	 */
	
	public boolean[] getBinary() {
		return binary;
	}
	
	
	/**This is the setter Method for {@link #binary}
	 * This Method overwrites {@link Values#setBinary(boolean[])}
	 * @param binary The boolean array to be set.
	 */
	
	public void setBinary(boolean[] binary) {
		this.binary = binary;
	}
	
	
	/**This is the getter Method for {@link #channelID}
	 * @return the ChannelID
	 */
	
	public byte getChannelID() {
		return channelID;
	}
	
	
	/**This is the setter Method for {@link #channelID}
	 * @param channelID the ChannelId to be set
	 */
	
	public void setChannelID(byte channelID) {
		this.channelID = channelID;
	}

	
	/**This is the getter Method for {@link #inputID}
	 * @return the inputID
	 */
	
	public int getInputID() {
		return inputID;
	}

	
	/**This is the setter Method for {@link #inputID}
	 * @param inputID the inputID to be set
	 */
	
	public void setInputID( int inputID ) {
		this.inputID = inputID;
	}
	
	
	/**This is the getter Method for {@link #outputID}
	 * @return the outputID
	 */
	
	public int getOutputID() {
		return outputID;
	}
	
	
	/**This is the setter Method for {@link #outputID}
	 * @param outputID the outputID to set
	 */
	
	public void setOutputID( int outputID ) {
		this.outputID = outputID;
	}


	/**This is the getter Method for {@link #hasReference}
	 * @return the value of hasReference
	 */
	
	public boolean getHasReference() {
		return hasReference;
	}


	/**This is the setter Method for {@link #hasReference}
	 * @param hasReference the hasReference to set
	 */
	
	public void setHasReference( boolean hasReference ) {
		this.hasReference = hasReference;
	}


	/**This is the getter Method for {@link #hasValues}
	 * @return the value of hasValues
	 */
	
	public boolean getHasValues() {
		return hasValues;
	}


	/**This is the setter Method for {@link #hasValues}
	 * @param hasValues the hasValues to set
	 */
	
	public void setHasValues( boolean hasValues ) {
		this.hasValues = hasValues;
	}
}