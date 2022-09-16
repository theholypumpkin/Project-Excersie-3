package binaryoperations;

import exceptions.NegativeExponetException;

/**
 * Class Values stores all important Values for the program
 * It is the sub class of {@link Values#Values()} and the super class of
 * {@link TransmitableObject#TransmitableObject()}
 * @version 1.4.1
 * @author Pascal S. PERSONAL INFORMATION REDACTED
 * <br>
 * <br>
 * <br>
 * Created on Nov 11, 2017 <br>
 */

public class Values extends Arithmetic{
	
	
	/**
	 * Creates a new Constant called AMOUNTOFBITS.
	 * AMOUNTOFBITS -Creates a constant with the amount of bits the
	 * binary value should
	 * have, this makes it easy and fast to adjust the program in the future
	 * to to work with longer values. Value is used to calculate the array
	 * length of the Arrays
	 * {@link #binary} and {@link #hex}
	 */
	
	private static final byte AMOUNTOFBITS = 8;
	
	
	/**
	 * Creates new Variable called isPositive.
	 * isPositve -Creates a new boolean variable and sets it's default value
	 * to true. Value can be retrieved by the Method {@link #getIsPositive()}
	 * and changed by the Method {@link #setIsPositive(boolean)}
	 * The Decimal representation of the Value is stored in
	 * {@link #isPositiveInt}
	 * <br>
	 * <br>
	 * DEFAULT == TRUE
	 */
	
	private boolean isPositive = true;
	
	
	/**
	 * Creates new Variable called binary
	 * binary -Creates a new boolean Array with the size of the
	 * {@link #AMOUNTOFBITS constant AMOUNTOFBITS.} Each field within
	 * the Array can be toggled between true of false. False is representing
	 * a Binary 0, True is representing a binary 1. Value can be retrieved
	 * by the Method {@link #getBinary()} and set by the
	 * Method {@link #setBinary(boolean[])}
	 */
	
	protected boolean [] binary = new boolean [AMOUNTOFBITS];
	
	
	/**
	 * Creates new Variable called decimal.
	 * decimal -Creates a new byte variable which can store a decimal value
	 * between -128 and +127. Value can be retrieved by the Method
	 * {@link #getDecimal()} and changed by the Method {@link #setDecimal(byte)}
	 */
	
	private byte decimal = 0;
	
	
	/**
	 * Creates new Variable called weighting.
	 * weighting -Creates a new byte variable which can store a decimal
	 * value between -128 and +127. Value can be retrieved by the Method
	 * {@link #getWeighting()}
	 * <br>
	 * The weighting is equal to the power of 2^{@link #AMOUNTOFBITS}-2
	 */
	
	private byte weighting = 0;
	
	
	/**
	 * Creates new Variable called isPositiveInt
	 * isPositiveInt -Creates a new Variable which is an int-cast of
	 * the boolean variable {@link #isPositive}<br>
	 * 1 represents true and 0 represents false
	 * Value can be retrieved by the Method {@link #getIsPositiveInt()}
	 */
	
	@SuppressWarnings( "unused" )
	private int isPositiveInt = 0;
	
	
	/**
	 * Creates new Variable called hex
	 * hex -Creates an Char Array of Hexadecimal values. The size is equal to
	 * the size of
	 * {@link #AMOUNTOFBITS Constant AMOUNTOFBITS} divided by 4
	 * 
	 * @deprecated Currently not in use
	 */
	
	private char [] hex = new char [AMOUNTOFBITS / 4];
	
	
	/* ==================================================================== */
	
	/**
	 * This is the getter Method for {@link #isPositive}
	 * 
	 * @return A boolean value of the Variable isPositive
	 */
	
	public boolean getIsPositive() {
		return isPositive;
	}
	
	
	/**
	 * This is the Setter Method for {@link #isPositive}
	 * 
	 * @param isPositive
	 * Value to be set for Variable is_positve
	 */
	
	public void setIsPositive( boolean isPositive ) {
		this.isPositive = isPositive;
	}
	
	
	/**
	 * This is the getter Method for {@link #weighting}
	 * 
	 * @return A byte value of the Variable weighting.
	 * <br>
	 * To calculate the weighting it uses the Method
	 * {@link binaryoperations.Arithmetic#twoToBytePower(byte, byte)}
	 * <br>
	 * Can return a positive or negative value.
	 * @throws NegativeExponetException
	 * Forward exception to caller of this method
	 * This exception was already forwarded by
	 * {@link binaryoperations.Arithmetic#twoToBytePower(byte, byte)}
	 */
	
	public byte getWeighting()
	throws NegativeExponetException {
		
		boolean isPositive = getIsPositive();
		
		byte base = (byte) ( isPositive ? 2 : -2 );
		
		this.weighting = twoToBytePower( AMOUNTOFBITS, base );
		return weighting;
	}
	
	
	/**
	 * This is the getter Method for {@link #AMOUNTOFBITS}
	 * 
	 * @return A byte value of the Constant AMOUNTOFBITS
	 */
	
	public byte getAMOUNTOFBITS() {
		return AMOUNTOFBITS;
	}
	
	
	/**
	 * This is the getter Method for {@link #binary}
	 * 
	 * @return A boolean array value of the Variable binary
	 */
	
	public boolean [] getBinary() {
		return binary;
	}
	
	
	/**
	 * This is the Setter Method for {@link #binary}
	 * 
	 * @param binary
	 * Value to be set for Variable binary
	 */
	
	public void setBinary( boolean [] binary ) {
		this.binary = binary;
	}
	
	
	/**
	 * This is the getter Method for {@link #decimal}
	 * 
	 * @return A byte value of the Variable decimal
	 */
	
	public byte getDecimal() {
		return decimal;
	}
	
	
	/**
	 * This is the Setter Method for {@link #decimal}
	 * 
	 * @param decimal
	 * Value to be set for Variable decimal
	 */
	
	public void setDecimal( byte decimal ) {
		this.decimal = decimal;
	}
	
	
	/**
	 * This is the getter Method for {@link #hex}
	 * 
	 * @return A char array value of the Variable hex
	 */
	
	public char [] getHex() {
		return hex;
	}
	
	
	/**
	 * This is the Setter Method for {@link #hex}
	 * 
	 * @param hex
	 * Value to be set for Variable hex
	 */
	
	public void setHex( char [] hex ) {
		this.hex = hex;
	}
	
	
	/**
	 * his is the getter Method for {@link #isPositiveInt}<br>
	 * In this case the getter Method also sets the values this is due to
	 * that the user shall not be allowed to set the value him/her self.
	 * if he could this would possible lead to an inconsistency between the
	 * boolean value if {@link #isPositive} and this decimal representation
	 * 
	 * @return A integer of 1 or 0 value of the Variable isPositiveInt
	 */
	
	public int getIsPositiveInt() {
		return isPositiveInt = ( this.isPositive ) ? 1 : 0;
	}
}