package binaryoperations;

import exceptions.NegativeExponetException;


/**
 * Class Arithmetic: <br>
 * This class does all of the Mathematics based operations. It is also the
 * superclass of {@link Values#Values() Values}
 * 
 * @version 1.5
 * @author Pascal S. PERSONAL INFORMATION REDACTED
 * <br>
 * Create on Nov 11, 2017
 */

public class Arithmetic{
	
	/* ==================================================================== */
	
	/**
	 * This Method is a very simplified version of the oracles <br>
	 * java.lang.Object.Math.pow()
	 * <br>
	 * Only Working with integers(mathematical space NOT the
	 * primitive data type). The Method takes the power of
	 * 2^{@link Values#AMOUNTOFBITS Values.AMOUNTOFBITS-2}
	 * 
	 * @param exponet
	 * -The constant AMOUNT_OF BITS
	 * @param base
	 * -The base of the number.
	 * positive or negative. passed by {@link Values#isPositive}
	 * @return 2^(AMOUNTOFBITS-2) as a positive or negative number.
	 * @throws NegativeExponetException
	 * When the exponent is less or equal 0,
	 * this exception will be thrown because the method is not equipped to handle
	 * a value of less or equal 0. To handle this is unnecessary for this
	 * specific kind of use-case.
	 */
	
	public byte twoToBytePower( byte exponet, byte base )
	throws NegativeExponetException {
		
		if ( exponet < 0 ) {
			throw new NegativeExponetException( "Can not take the power with "
				+ "a exponet of:" + exponet );
		}
		if (exponet-1 == 1) {
			if (base  == -2) {
				
				return -1;
				
			}else if ( base == 2) {
				
				return 1; 
			}
		}
		for ( byte cnt = 1; cnt < exponet - 2; ++cnt ) {
			base *= 2;
		}
		return base;
	}
	
	/* ==================================================================== */
	
	/**
	 * This Method prepares some values for the convention to binary.
	 * To make the Method {@link #convertToBinary(byte[], Values, Values)}
	 * work correctly
	 * 
	 * @param values
	 * -The reference to a object of the type {@link Values}
	 * @param return_values
	 * -Another reference to another object of the type
	 * {@link Values}
	 * @throws IndexOutOfBoundsException
	 * Will get thrown when the byte [2]
	 * arithmeticValues is called with an index out of Bounds eg.
	 * arithmeticValues[500].
	 * @throws NegativeExponetException
	 * Forward exception to caller of this method
	 * This exception was already forwarded by
	 * {@link Values#getWeighting()} which internally got the Exception
	 * Forwarded by {@link binaryoperations.Arithmetic#twoToBytePower(byte, byte)}
	 */
	
	public void prepareBinaryConvertion( Values values,
		Values return_values ) throws IndexOutOfBoundsException,
		NegativeExponetException {
		/*
		 * Creates a new array with two fields.
		 * Used to make the values swap-able
		 */
		byte [] arithmeticValues = new byte [2];
		
		boolean isPositive = values.getIsPositive();
		/*-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -*/
		if ( isPositive ) {
			arithmeticValues[1] = values.getWeighting();
			arithmeticValues[0] = values.getDecimal();
			/*-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -*/
		}else if ( !isPositive ) {
			/*
			 * Changes the field of the array the values go in.
			 * Also adds 1 to the decimal value to decimal value to make
			 * the conversion work properly.
			 */
			arithmeticValues[0] = values.getWeighting();
			arithmeticValues[1] = values.getDecimal();
			arithmeticValues[1] += 1;
		}
		/*-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -*/
		convertToBinary( arithmeticValues, values, return_values );
	}
	
	
	/**
	 * This is the actual method which converts the decimal value into a
	 * {@link Values#binary Binary boolean array}. It get it's values passed
	 * by the Method
	 * {@link #prepareBinaryConvertion(Values, Values)} which prepared the
	 * values for this Method.
	 * 
	 * @param arithmeticValues
	 * -A two field byte Array with one field
	 * containing the {@link Values#decimal decimal value} and the other
	 * containing the {@link Values#weighting weighting}
	 * @param values
	 * -The reference to a object of the type {@link Values}
	 * @param return_values
	 * -Another reference to another object of the type
	 * {@link Values}
	 * @throws IndexOutOfBoundsException
	 * Will get thrown when the byte [2]
	 * arithmeticValues, {@link Values#binary} is called with an index out of
	 * Bounds.
	 * <br>
	 * <br>
	 * <br>
	 * This loop within the method
	 * compares the decimal number with 2 to the power of 'n'
	 * if the decimal number is greater or equal 2^n. Than the program
	 * branches into the if Statement and is subtracting the 2^n from
	 * decimal number.
	 * Due to the fact that the method prepareBinaryConvertion
	 * swaps the values within the array arithmeticValues, if the
	 * decimal number is negative.
	 * This swapping creates some trouble for the subtraction of
	 * decimal - 2^n, a switch case statement is needed to correct this.
	 * <br>
	 * <br>
	 * <code>
	 * 	if (user_values (greater or equal) 2^n){<br>
	 *  &emsp;	user_values - 2^n<br>
	 * 	&emsp;	2^n = (2^n)/2<br>
	 * 	&emsp;	binary @ n place = 1<br>
	 * 	} else {<br>
	 * 	&emsp;	2^n = (2^n)/2<br>
	 * 	&emsp;	binary @ n place = 0<br>
	 * 	}
	 * </code>
	 */
	
	private void convertToBinary( byte [] arithmeticValues,
		Values values, Values return_values ) throws IndexOutOfBoundsException {
		/*
		 * Won't thrwo CourruptedBooleanException because the boolean value get
		 * set and not read
		 */
		byte amb = return_values.getAMOUNTOFBITS();
		
		byte decimal = values.getDecimal();
		boolean [] binary = values.getBinary();
		boolean [] returnBinary = return_values.getBinary();
		boolean isPositive = values.getIsPositive();
		int isPositiveInt = values.getIsPositiveInt();
		/*-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -*/
		for ( byte cnt = 1; cnt < amb; cnt++ ) {
			
			/*-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  */
			if ( arithmeticValues[0] >= arithmeticValues[1] ) {
				switch ( isPositiveInt ) {
				case 1:
					arithmeticValues[0] -= arithmeticValues[1];
					break;
				case 0:
					binary[0] = true;
					arithmeticValues[1] -= arithmeticValues[0];
					break;
				}
				arithmeticValues[isPositiveInt] /= 2;
				
				binary[cnt] = isPositive;
				/*-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  */
			}else {
				arithmeticValues[isPositiveInt] /= 2;
				binary[cnt] = !isPositive;
			}
			// Old code I thin kI don't need it anymore but I still will keep it?
			//Explicit handling just for the number -1
			if ( ( !isPositive ) && ( decimal ) == -1 ) {
				binary[0] = true;
			}
		}
		/*-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -*/
		values.setBinary( binary );
		return_values.setBinary( returnBinary );
	}
	
	/* ==================================================================== */
	
	/**
	 * This Method inverts every bit within the
	 * {@link Values#binary Binary boolean array}<br>
	 * true = false<br>
	 * false = true
	 * 
	 * @param values
	 * -The reference to a object of the type {@link Values}
	 * @param return_values
	 * -Another reference to another object of the type
	 * {@link Values}
	 * @throws IndexOutOfBoundsException
	 * Will get thrown when the byte [2]
	 * arithmeticValues, {@link Values#binary} is called with an index out of
	 * Bounds.
	 */
	
	public void twoBitComplmentInvertOnly( Values values,
		Values return_values ) throws IndexOutOfBoundsException {
		
		byte amb = return_values.getAMOUNTOFBITS();
		
		boolean [] binary = values.getBinary();
		boolean [] returnBinary = return_values.getBinary();
		/*-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -*/
		for ( int cnt = 0; cnt < amb; cnt++ ) {
			if ( binary[cnt] ) {
				returnBinary[cnt] = false;
			/*-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  */
			}else if ( !binary[cnt] ) {
				returnBinary[cnt] = true;
			}
		}
		return_values.setBinary( returnBinary );
	}
	
	
	/**
	 * This Method adds <i>'one' (binary)</i> to the
	 * {@link Values#binary Binary boolean array} so the two bit's
	 * complement is calculated correctly
	 * 
	 * @param return_values
	 * -The reference to a object of the type
	 * {@link Values}
	 */
	
	public void twoBitComplmentAddOne( Values return_values ) {
		
		byte amb = return_values.getAMOUNTOFBITS();
		
		boolean [] returnBinary = return_values.getBinary();
		boolean carry = true;
		/*-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -*/
		for ( int cnt = amb - 1; cnt >= 0; --cnt ) {
			// if booth true
			if ( returnBinary[cnt] && carry ) {
				returnBinary[cnt] = false;
				carry = true;
				/*-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  */
				// if returnBinary = false and carry = true
			}else if ( !returnBinary[cnt] && carry ) {
				returnBinary[cnt] = true;
				carry = false;
				/*-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  */
				// if carry = false stop loop
			}else if ( !carry ) {
				break;
			}
		}
		return_values.setBinary( returnBinary );
	}
	
	/* ==================================================================== */
	
	/**
	 * This method converts a {@link Values#binary Binary boolean array}
	 * back to a decimal value
	 * 
	 * @param returnvalues
	 * -The reference to a object of the type {@link Values}
	 * @param newValue
	 * -Another reference to a object of the type {@link Values}
	 * @throws NegativeExponetException
	 * This exception will be thrown when the
	 * exponent of the number is negative.
	 * @return the decimal Value computed
	 */
	
	public Values convertToDecimal( Values returnvalues, Values newValue )
		throws NegativeExponetException {
		
		boolean [] binary = returnvalues.getBinary();
		
		boolean isPositive = returnvalues.getIsPositive();
		
		byte amb = returnvalues.getAMOUNTOFBITS();
		
		byte decimal = 0;

		for ( byte cnt = 1 ; cnt < returnvalues.getAMOUNTOFBITS(); ++cnt) {
			
			if ( ( binary[cnt] && isPositive ) 
				|| ( !binary[cnt] && !isPositive ) ) {
				
				decimal += twoToBytePower( amb, (byte) ( isPositive ? 2 : -2 ) );
			}
			--amb;
		}
		
		// to adjust for the twos complement
		
		if ( !isPositive ) {
			decimal -= 1;
		}
		
		newValue.setDecimal( decimal );
		newValue.setBinary( binary );
		newValue.setIsPositive( isPositive );
		
		return newValue;
		
	}
	
	/* ==================================================================== */
	
	/**
	 * This method converts a {@link Values#binary Binary boolean array}
	 * into a hexadecimal representation of the binary value.
	 * 
	 * @param newvalues
	 * -The reference to a object of the type {@link Values}
	 */
	
	public void convertToHex( Values newvalues ) {
		newvalues.setHex( Integer.toHexString(
			newvalues.getDecimal() ).toCharArray() );
	}
}