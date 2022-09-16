package main;

import java.util.Observable;
import java.util.Scanner;

import binaryoperations.Arithmetic;
import binaryoperations.TransmitableObject;
import binaryoperations.Values;

import exceptions.NegativeExponetException;
import exceptions.MultiplexerException;
import exceptions.WrongInputException;
import exceptions.WrongNumberException;
import multiplexer.Multiplexer;
import multiplexer.SingletonDemultiplexer;


/**Class Input_output does handle some basic input and output part
 * everything related to check the input is within the subclass
 * {@link main.ArgumentHander}
 * 
 * @version 3.2.1
 * @author Pascal S. PERSONAL INFORMATION REDACTED
 * <br>
 * Created on Nov 11, 2017
 */

public class InputOutput extends Observable{
	
	/**
	 * New String Variable called input.
	 * input -Creates new String
	 */
	private String input;
	
	/**
	 * Creates a new Array of Strings. Contains the spit String of the
	 * input.
	 */
	private String [] inputArray;
	
	/**
	 * Creates a new Variable.
	 * isPositive -Same as isPositive in Values
	 * 
	 * @see Values#isPositive
	 */
	private boolean isPositive = true;
	
	/**
	 * Creates a new Variable.
	 * decimal -Same as isPositive in Values
	 * 
	 * @see Values#decimal
	 */
	private byte decimal;
	
	/**
	 * Creates a new Variable to save the location within the String Array
	 * where the individual character check shall start.
	 */
	private byte startsAtPos = 0;
	
	/** This Variable keeps errors of non existing multiplexers in check */
	private boolean isMultiplexerCreated = false;

	/* ==================================================================== */
	
	
	/**
	 * This is the getter Method for {@link #input}
	 * 
	 * @return The String of the Variable input
	 */
	
	protected String getInput() {
		return input;
	}
	
	
	/**
	 * This is the setter Method for {@link #input}
	 * 
	 * @param input
	 * The String to be set.
	 */
	
	protected void setInput( String input ) {
		this.input = input;
	}
	
	
	/**
	 * This is the getter Method for {@link #inputArray}
	 * 
	 * @return inputArray -A array pf Strings.
	 */
	
	public String [] getInputArray() {
		return inputArray;
	}
	
	
	/**
	 * This it the setter method for {@link #inputArray}
	 * 
	 * @param inputArray
	 * the inputArray to set
	 */
	
	public void setInputArray( String [] inputArray ) {
		this.inputArray = inputArray;
	}
	
	
	/**
	 * This is the getter Method for {@link #isPositive}
	 * 
	 * @return A boolean value of the Variable isPositive
	 */
	
	protected boolean getIsPositive() {
		return isPositive;
	}
	
	
	/**
	 * This is the setter Method for {@link #isPositive}
	 * 
	 * @param isPositive
	 * -The boolean value to be set.
	 */
	
	protected void setIsPositive( boolean isPositive ) {
		this.isPositive = isPositive;
	}
	
	
	/**
	 * This is the getter Method for {@link #decimal}
	 * 
	 * @return A byte value of the Variable decimal
	 */
	
	protected byte getDecimal() {
		return decimal;
	}
	
	
	/**
	 * This is the setter Method for {@link #decimal}
	 * 
	 * @param decimal
	 * The decimal value to be set.
	 */
	
	protected void setDecimal( byte decimal ) {
		this.decimal = decimal;
	}
	
	
	/**
	 * This it the getter method for {@link #startsAtPos}
	 * 
	 * @return startsAtPos
	 */
	
	public byte getStartsAtpos() {
		return startsAtPos;
	}
	
	
	/**
	 * This it the setter method for {@link #startsAtPos}
	 * 
	 * @param startsAtPos
	 * the Value to be set
	 */
	
	public void setStartsAtpos( byte startsAtPos ) {
		this.startsAtPos = startsAtPos;
	}
	
	
	/**
	 * This is the getter for {@link #isMultiplexerCreated}
	 * 
	 * @return A boolean value if the user ever created a multiplexer
	 * Default is false
	 */
	
	public boolean getIsMultiplexerCreated() {
		return isMultiplexerCreated;
	}
	
	
	/**
	 * This it the getter for {@link #isMultiplexerCreated}
	 * 
	 * @param isMultiplexerCreated
	 * The boolean value to be set
	 */
	
	public void setIsMultiplexerCreated( boolean isMultiplexerCreated ) {
		this.isMultiplexerCreated = isMultiplexerCreated;
	}

	/* ==================================================================== */

	
	/**This Method contains the scanner to read in text inputed by the user
	 * @param io
	 * A object of the type {@link InputOutput#InputOutput() InputOutput}
	 * @throws IndexOutOfBoundsException Will be thrown if the
	 * {@code input.charAt ( 0 )}
	 * Index does not exist.
	 */
	
	public void scanner( InputOutput io ) throws IndexOutOfBoundsException {
		String input;
		
		@SuppressWarnings( "resource" )
		Scanner inputScanner = new Scanner( System.in );
		input = inputScanner.nextLine();
		input = input.trim();
		
		input = input.toLowerCase();
		if (input.length() != 0) {
			if ( input.charAt( 0 ) == 'q' ) {
				//System.out.println( "Goodby :)" );
				System.exit( 1 );
			}
		}
		io.setInput( input );
		//inputScanner.close();
	}
	
	/* ==================================================================== */
	
	/**This method is responsible to output the 
	 * {@link multiplexer.Multiplexer#dataStream}
	 * Which stores all values in an array of 
	 * {@link binaryoperations.TransmitableObject#TransmitableObject()}.
	 * <br>
	 * Within that array is the channel ID Stored as well as a boolean array
	 * {@link binaryoperations.TransmitableObject#binary}
	 * <br>
	 * <br>
	 * The method also fills in all TransmitableObjects with a NULL value to
	 * avoid a Null Pointer exception
	 * @param demux 
	 * A Object of the type {@link multiplexer.SingletonDemultiplexer#getInstance()}
	 * @throws NegativeExponetException Is thrown when a negative Exponent in
	 * {@link binaryoperations.Arithmetic#twoToBytePower(byte, byte)} was detected
	 */
	
	public void output( SingletonDemultiplexer demux) 
		throws NegativeExponetException{
		
		Arithmetic arithmetic = new Arithmetic();
		Values currentValue = new Values();
		Values newValue = new Values();
		
		TransmitableObject [] tob = demux.getDataStream();
		
		
		for (TransmitableObject currentTob : tob) {
			
			if (currentTob != null) {
				
				if ((currentTob.getHasReference()) 
					&& (currentTob.getHasValues())){
					
					boolean [] binary = currentTob.getBinary();
					boolean isPositive = currentTob.getIsPositive();
				
					currentValue.setBinary( binary );
					currentValue.setIsPositive( isPositive );
				
					newValue = arithmetic.convertToDecimal(currentValue, newValue);
				
					byte decimal = newValue.getDecimal();
					
					System.out.print( decimal + " " );
				}
			}
		}
		//demux = null;
		//arithmetic = null;
		//currentValue = null;
		//newValue = null;	
	}
	
	/* ==================================================================== */
	
	/** This is the main Method it executes all other methods
	* The Method creates multiple objects after being executed<br>
	* It calls all exceptions every exception which is created somewhere in
	* the code will handled in one single try catch.
	* 1 Object of the type {@link multiplexer.Multiplexer#Multiplexer()}<br>
	* 1 Object of the Type {@link 
	* binaryoperations.TransmitableObject#TransmitableObject()}
	* @param args NULL
	*/
	
	public static void main( String [] args ) {
		
		ArgumentHander io = new ArgumentHander();
		
		multiplexer.Multiplexer mux = new Multiplexer();
		
		TransmitableObject tob = new TransmitableObject();
		
		Object[] oj = new Object[2];
		
		try {
			/* ========================================================== */
			do{
				System.out.println( "\n" );
				System.out.println( "Input: " );
				
				io.scanner( io );
				
				oj = io.prepareCommand( io, mux, tob );
				
				tob = (TransmitableObject) oj[0];
				mux = (Multiplexer) oj[1];
				
				
			}while (true);
			/* ===========================================================*/
		}catch ( IndexOutOfBoundsException ex ) {
			
			System.err.println( ex.getMessage() );
			//ex.printStackTrace();
			
		}catch ( NegativeExponetException ex ) {
			
			System.err.println( ex.getMessage() );
			//ex.printStackTrace();
		
		}catch ( WrongInputException ex ) {
			
			System.err.println( ex.getMessage() );
			//ex.printStackTrace();
		
		}catch ( WrongNumberException ex ) {
			
			System.err.println( ex.getMessage() );
			//ex.printStackTrace();
		
		}catch (NumberFormatException ex) {
			
			System.err.println( ex.getMessage() );
			//ex.printStackTrace();
		
		}catch ( MultiplexerException ex ) {
			
			System.err.println( ex.getMessage() );
			//ex.printStackTrace();
		}
		tob = null;
		mux = null;
		InputOutput.main( args ); //Note from 2022: Well that is bad code. It resucrivly calls main after an exeption and after a long time would lead to to a stack overflow.
	}
}