package main;

import binaryoperations.Arithmetic;
import binaryoperations.TransmitableObject;
import binaryoperations.Values;
import exceptions.MultiplexerException;
import exceptions.NegativeExponetException;
import exceptions.WrongInputException;
import exceptions.WrongNumberException;
import multiplexer.Multiplexer;
import multiplexer.SingletonDemultiplexer;

/**This class mainly exists to reduce the complexity if the 
 * super class {@link main.InputOutput}
 * it contains a lot of methods previously within InputOutput
 * 
 * @author Pascal S. PERSONAL INFORMATION REDACTED
 * <br>
 * Created on Dec 22, 2017
 * @version 1.5.0
 */

public class ArgumentHander extends InputOutput{

	/**The Default constructor*/
	
	public ArgumentHander() {}
	
	
	/**This method is checking if the number is negative or positive or 0
	 * @param io
	 * A object of the type {@link InputOutput#InputOutput() InputOutput }
	 * @throws WrongInputException
	 * This Exception was forwarded by
	 * {@link #isValid(InputOutput)} or {@link #handelWrongInput(InputOutput)}
	 * @throws IndexOutOfBoundsException
	 * This Exception was forwarded by
	 * isValid or handleWrongInput
	 * @throws WrongNumberException
	 * This Exception was being forwarded by
	 * isValid or handleWrongInput
	 */
	
	private void isValue( InputOutput io ) throws IndexOutOfBoundsException,
	WrongInputException, WrongNumberException {
		String input = io.getInput();
		/* if there is a minus sign and the string length is greater than 1 */
		if ( ( input.startsWith( "-" ) ) && ( input.length() > 1 ) ) {
			
			io.setIsPositive( false );
			io.setStartsAtpos( (byte) 1 );
			
			isValid( io );
			
		}else if ( ( input.startsWith( "+" ) ) && ( input.length() > 1 ) ) {
			
			io.setIsPositive( true );
			io.setStartsAtpos( (byte) 1 );
			
			isValid( io );
			
		}else if ( ( input.startsWith( "0" ) ) && ( input.length() > 1 ) ) {
			
			handelWrongInput( io );
			
		}else {
			
			io.setIsPositive( true );
			io.setStartsAtpos( (byte) 0 );
			isValid( io );
		}
	}
	
	
	/**This method will be called if already, before the
	 * {@link #isValid(InputOutput)} method will be called, an invalid user
	 * input was detected.
	 * @param io
	 * A object of the type {@link InputOutput#InputOutput() InputOutput }
	 * @throws WrongInputException
	 * This Exception will be thrown when, the User
	 * enters an letter or everything else which is not a numeral as a number
	 * The Exception is being forwarded to the caller
	 * to the caller
	 * @throws WrongNumberException
	 * This Exception will be thrown when the entered
	 * number with leading 0 or spaces in between numbers.
	 * The Exception is being forwarded to the caller.
	 */
	
	private void handelWrongInput( InputOutput io )
	throws WrongInputException, WrongNumberException {
		String input = io.getInput();
		boolean isItaChar = false;
		// Checks for any character within the array if there is one it a Wrong Input
		for ( char currentChar : input.toCharArray() ) {
			if ( ( currentChar < 48 ) || ( currentChar > 57 ) ) {
				// If it is within that range it must be a letter
				isItaChar = true;
			}
		}
		if ( isItaChar ) {
			throw new WrongInputException( "Wrong Input " + input
			+ " is not a Number" );
		}
		throw new WrongNumberException( "Wrong Number " + input
		+ " leading Zeros/Nulls or spaces in between the term, are not allowed" );
	}
	
	
	/**This method checks if the users entered an Valid number which only is
	 * Contains numerals and nothing else
	 * @param io
	 * A object of the type {@link InputOutput#InputOutput() InputOutput }
	 * @throws IndexOutOfBoundsException
	 * This Exception will be thrown when,
	 * the program tries to access a char at a location within the String which
	 * doesn't exist. The Exception is being forwarded to the caller
	 * @throws WrongInputException
	 * This Exception will be thrown when, the User
	 * enters an letter or everything else which is not a numeral as a number
	 * The Exception is being forwarded to the caller
	 */
	
	private void isValid( InputOutput io ) throws IndexOutOfBoundsException,
	WrongInputException {
		
		byte startsAtPos = io.getStartsAtpos();
		
		@SuppressWarnings( "unused" )
		boolean isANumber = false;
		
		String input = io.getInput();
		
		while ( startsAtPos < input.length() ) {
			
			// ignores a minus or plus sign in front by starting at index one if set.
			
			if ( ( input.charAt( startsAtPos ) > 47 )
				&& ( ( input.charAt( startsAtPos ) < 58 ) ) ) {
				
				isANumber = true;
			
			}else {
				
				throw new WrongInputException( "Wrong Input");
				// If it is within that range it must be a letter
				
			}
			
			startsAtPos++;
		}
	}
	
	
	/**This Method handers the parsing of the String into an Integer and
	 * following the conversion of this integer into a Byte.
	 * @param io
	 * A object of the type {@link InputOutput#InputOutput() InputOutput }
	 * @param integerMIN the lowest value that is allowed
	 * @param integerMAX the highest values that is allowed
	 * @throws WrongNumberException
	 * This Exception will be thrown when the entered
	 * number is out of the One Byte Range. The Exception is being forwarded
	 * to the caller.
	 * @throws WrongInputException x
	 */
	
	private void parseInt( InputOutput io , int integerMAX, int integerMIN) 
	throws NumberFormatException, WrongNumberException, WrongInputException {
		String input = io.getInput();
		
		int decimallong = Integer.parseInt( input );
		
		if ( ( decimallong > integerMAX ) || ( decimallong < integerMIN ) ) {
			
			throw new WrongNumberException( "Wrong Number " + input
			+ " number is out of range.\n Range is "+integerMIN+" to "+ integerMAX );
			
		}else {
			// makes it a byte
			io.setDecimal( (byte) decimallong );
		}
	}
	/* ==================================================================== */
	
	/**This it the Argument parser to check what the users has entered.
	 * It does split the string at every space the user enter except for leading
	 * and trailing spaces (Which where removed with trim in
	 * {@link #scanner(InputOutput)}) it does sort out every input with empty
	 * strings or more than two Strings within the array
	 * 
	 * @param io A object of the type {@link main.InputOutput#InputOutput()}
	 * @param mux A object of the type {@link multiplexer.Multiplexer#Multiplexer()}
	 * @param tob A object of the type 
	 * {@link binaryoperations.TransmitableObject#TransmitableObject()}
	 * @return A Object Array with two fields one containing the
	 * Multiplexer the second one containing
	 * TransmitableObject
	 * @throws WrongInputException When the user enters a invalid string
	 * Also forwarded by: {@link ArgumentHander#inizilazeCommand}
	 * @throws NumberFormatException Forwarded by: 
	 * {@link ArgumentHander#inizilazeCommand}
	 * @throws IndexOutOfBoundsException Forwarded by: 
	 * {@link ArgumentHander#inizilazeCommand}
	 * @throws WrongNumberException Forwarded by: 
	 * {@link ArgumentHander#inizilazeCommand}
	 * @throws NegativeExponetException Forwarded by: 
	 * {@link ArgumentHander#inizilazeCommand}
	 * @throws MultiplexerException Forwarded by: 
	 * {@link ArgumentHander#inizilazeCommand}
	 */
	
	public Object[] prepareCommand( InputOutput io, Multiplexer mux,
		TransmitableObject tob) 
		throws WrongInputException, NumberFormatException, 
			IndexOutOfBoundsException, WrongNumberException, 
			NegativeExponetException, MultiplexerException {
		Object[] oj = new Object[2];
		
		String input = io.getInput();
		
		String [] inputArr = new String[2];
		
		inputArr = input.split( " " ); // Splits on space
		
		for ( String currentArr : inputArr ) {
			if ( currentArr.length() == 0 ) {
				/*
				 * Checks if string was created with nothing inside
				 * This will indicate that the user entered two spaces in a row
				 * because I split first on a space an than in a later stage
				 * on the colon
				 */
				throw new WrongInputException( "Wrong Input \nToo many spaces" );
			}
		}
		if ( inputArr.length > 2 ) {
			throw new WrongInputException( "too many Arguments" );
			// If the program found multiple arguments separated by space throw error
		}
		oj[0] = tob; //I think its pointless to have that
		oj[1] = mux;
		
		oj = inizilazeCommand(io, mux, tob, input, inputArr);
		
		return oj;
	}
	
	
	/**This Method checks what command the user entered if it is {@code mux} 
	 * the program
	 * will jump to the Method {@link main.InputOutput#output(Multiplexer)} <br>
	 * if the user entered {@code create} the program will jump into the
	 * {@link ArgumentHander#interpretCreate(InputOutput, String[])}<br>
	 * if the user entered {@code assign} the program will jump into the Method
	 * {@link ArgumentHander#interpretAssign(InputOutput, Multiplexer, String[])}
	 * and if the user entered {@code input} {@link ArgumentHander#interpretInput(
	 * InputOutput, Multiplexer, TransmitableObject, String[])}
	 * @param io A object f the type {@link main.InputOutput#InputOutput()}
	 * @param mux A object f the type {@link multiplexer.Multiplexer#Multiplexer()}
	 * @param tob A object f the type 
	 * {@link binaryoperations.TransmitableObject#TransmitableObject()}
	 * @param input A string Variable contains the whole input
	 * @param inputArr A String Array contains a Spited version of input
	 * @return A Object Array with two fields one containing the
	 * Multiplexer the second one containing
	 * TransmitableObject
	 * @throws WrongInputException If non of the "filter" above apply
	 * @throws NumberFormatException Forwarded by methods called within this method
	 * @throws IndexOutOfBoundsException if {@code inputArr.length-1} is negative
	 * and also forwarded by other methods
	 * @throws WrongNumberException forwarded by other methods
	 * @throws NegativeExponetException forwarded by other methods
	 * @throws MultiplexerException If no multiplexer was created
	 */
	
	public Object[] inizilazeCommand (InputOutput io, Multiplexer mux, 
		TransmitableObject tob, String input, String [] inputArr) 
			throws WrongInputException, NumberFormatException, 
			IndexOutOfBoundsException, WrongNumberException, 
			NegativeExponetException, MultiplexerException {
		
		Object [] oj = new Object[2];
		
		input = inputArr[0];
		
		inputArr = inputArr[inputArr.length-1].split( ":" );
		
		if (io.getIsMultiplexerCreated()) {
			
			if (input.matches( "assign" )) {
				
				tob = interpretAssign(io, mux ,inputArr);
			
			}else if (input.matches( "refer" )){
				
				tob = interpetRefer(io, mux, tob, inputArr);
				
			}else if (input.matches( "input" )) {
			
				interpretInput(io, mux ,tob, inputArr);
		
			}else if (input.matches( "mux" )) {
				//Swap even - odd frame
				mux.setEvenFrame();
				
				SingletonDemultiplexer instance 
					= SingletonDemultiplexer.getInstance();
				
				instance.setDataStream( mux ); 
				//Clones the dataStream from Multiplexer
				instance.getAmountOfOutputs(mux);
				
				io.addObserver( instance );
				
				//Object arg;
				instance.update( this, instance );
				//io.setIsMultiplexerCreated( false );
				//instance = null;
				//mux = null;
				//tob = null;
			}
			
		}else if (input.matches( "create" ) && (io.getIsMultiplexerCreated())) {
			
			throw new MultiplexerException("You already created a multiplexer");
		
		}else if (input.matches( "create" ) && !(io.getIsMultiplexerCreated())) {
			
			io.setIsMultiplexerCreated( false );
			mux = interpretCreate(io ,inputArr);
			
		}else if (inputArr[0].matches( "cddr" ) && inputArr[1].matches("32")){
			//Not fancy but simple solution without change huge chucks of code
			throw new WrongInputException("Wrong Input");
			
		}else if ((input.matches( "mux" ) || input.matches( "assign" ) 
			|| input.matches( "input" ) || input.matches("refer"))
			&& !(io.getIsMultiplexerCreated())){
			
			throw new MultiplexerException("Please create multiplexer");
		}else {
			throw new WrongInputException("Wrong Input");
		}
		oj [0] = tob;
		oj [1] = mux;
		return oj;
	}


	/**This method interprets the {@code create} command
	 * 
	 * @param io A object f the type {@link main.InputOutput#InputOutput()}
	 * @param inputArr A String array with the part of the input behind the
	 * {@code create} tag.
	 * @return The newly created {@link multiplexer.Multiplexer#Multiplexer()}
	 * @throws WrongInputException if the user had wrong Data behind the create Tag
	 * @throws IndexOutOfBoundsException if not enough data is behind the create Tag
	 * @throws WrongNumberException Forwarded by 
	 * {@link ArgumentHander#parseInt(InputOutput, int, int)} 
	 * @throws MultiplexerException if user tries to create a non inplemeted type
	 * of multiplexer
	 */
	
	private Multiplexer interpretCreate(InputOutput io,
		String[] inputArr) throws WrongInputException, IndexOutOfBoundsException,
			WrongNumberException, MultiplexerException {
		
		Multiplexer newMux = new Multiplexer();
		
		if(inputArr.length > 1) {
			
			io.setInput( inputArr[1] );
			isValue( io );
		
			parseInt(io, Integer.MAX_VALUE, 1);
		
			int amountOfInputs = io.getDecimal();
		
		
			newMux.setAmountOfInputs( amountOfInputs );
		
			io.setDecimal( (byte) 0 );
		
		
		
			if (inputArr[0].matches( "stdm" )) {
				
				System.out.println( "STDM multiplexer created" );
			
			}else if(inputArr[0].matches( "atdm" ) || inputArr[0].matches( "cdm" )){
				
				throw new MultiplexerException("This type is not implemented");
			
			}
		}else {
			
			throw new WrongInputException("Wrong Input");
			
		}
		io.setIsMultiplexerCreated( true );
		
		return newMux;
	}
	
	
	/**This method interprets the {@code assign} command
	 * 
	 * @param io A Object of the type {@link main.InputOutput#InputOutput()}
	 * @param mux A Object of the type {@link multiplexer.Multiplexer#Multiplexer()}
	 * @param inputArr A String Array with the Data behind the {@code Assign} tag
	 * @return A {@link binaryoperations.TransmitableObject#TransmitableObject(
	 * int, byte, Multiplexer)} Object
	 * @throws NumberFormatException Forwarded by 
	 * {@link ArgumentHander#parseInt(InputOutput, int, int)}
	 * @throws WrongNumberException Forwarded by 
	 * {@link ArgumentHander#parseInt(InputOutput, int, int)}
	 * @throws IndexOutOfBoundsException if user didn't enter enough argumets
	 * @throws WrongInputException Forwarded by 
	 * {@link ArgumentHander#parseInt(InputOutput, int, int)}
	 * @throws MultiplexerException Forwarded by 
	 * {@link binaryoperations.TransmitableObject#TransmitableObject
	 * (int, byte, Multiplexer)}
	 */
	
	private TransmitableObject interpretAssign(InputOutput io, Multiplexer mux, 
		String[] inputArr) throws NumberFormatException, 
			WrongNumberException, IndexOutOfBoundsException, WrongInputException,
			MultiplexerException {
		
		io.setInput( inputArr[0] );
		isValue( io );
		
		parseInt(io, 30, 1);
		
		byte channelID = io.getDecimal();
		
		io.setDecimal( (byte) 0 );
		if (channelID > 15) {
			channelID++;
			//System.out.println( "DEBUG: channel ID is incremetend" );
		}
		io.setInput( inputArr[1] );
		isValue( io );
		
		parseInt(io, Integer.MAX_VALUE, 1);
		
		int inputWhichChannelIsAssignedTo = io.getDecimal();
		
		io.setDecimal( (byte) 0 );
		
		TransmitableObject tob = new TransmitableObject(
			inputWhichChannelIsAssignedTo, channelID, mux);
		
		mux.setDataStream( tob );
		System.out.println( "Assignment created" );
		
		return tob;
	}
	
	
	/**This method interprets the {@code refer} command
	 * 
	 * @param io A Object of the type {@link main.InputOutput#InputOutput()}
	 * @param mux A Object of the type {@link multiplexer.Multiplexer#Multiplexer()}
	 * @param inputArr A String Array with the Data behind the {@code Assign} tag
	 * @param tob A Object array of the type 
	 * {@link binaryoperations.TransmitableObject#TransmitableObject()}
	 * @return A {@link binaryoperations.TransmitableObject#TransmitableObject(
	 * int, byte, Multiplexer)} Object
	 * @throws NumberFormatException Forwarded by 
	 * {@link ArgumentHander#parseInt(InputOutput, int, int)}
	 * @throws WrongNumberException Forwarded by 
	 * {@link ArgumentHander#parseInt(InputOutput, int, int)}
	 * @throws IndexOutOfBoundsException if user didn't enter enough argumets
	 * @throws WrongInputException Forwarded by 
	 * {@link ArgumentHander#parseInt(InputOutput, int, int)}
	 * @throws MultiplexerException Forwarded by 
	 * {@link binaryoperations.TransmitableObject#TransmitableObject
	 * (int, byte, Multiplexer)}
	 */
	
	private TransmitableObject interpetRefer( InputOutput io, Multiplexer mux,
		TransmitableObject tob, String [] inputArr ) throws NumberFormatException, 
			WrongNumberException, IndexOutOfBoundsException, WrongInputException,
			MultiplexerException {

		io.setInput( inputArr[0] );
		isValue( io );

		parseInt(io, 30, 1);

		byte channelID = io.getDecimal();

		io.setDecimal( (byte) 0 );
		
		if (channelID > 15) {
			channelID++;
			//System.out.println( "DEBUG: channel ID is incremetend" );
		}
		
		io.setInput( inputArr[1] );
		
		isValue( io );

		parseInt(io, Integer.MAX_VALUE, 1);

		int outputWhichChannelIsAssignedTo = io.getDecimal();

		io.setDecimal( (byte) 0 );
		
		if (tob.getChannelID() == channelID) {
			
			//If it's already the right tob don't start searching
			
			tob.setOutputID( outputWhichChannelIsAssignedTo );
			tob.setHasReference( true );
			
			mux.setDataStream( tob );
			
		}else{
			
			for (TransmitableObject currentTob : mux.getDataStream()) {
				
				//Avoids null pointer exception, and reduces the runtime
				
				if (currentTob != null) {
					//if tob is NOT the right tob search for it
					
					if( currentTob.getChannelID() == channelID) {
						
						currentTob.setOutputID( outputWhichChannelIsAssignedTo );
						
						//support multi assignment as well
						currentTob.setHasReference( true );
						
						mux.setDataStream( currentTob );
				
					}
				}
			}
		}
		System.out.println( "Assignment created" );
		
		return tob; //Do I ever need that?
	}
	
	
	/**This method interprets the {@code Input} Command 
	 * @param io A Object of the type {@link main.InputOutput#InputOutput()}
	 * @param mux A Object of the type {@link multiplexer.Multiplexer#Multiplexer()}
	 * @param inputArr A String Array with the Data behind the {@code Assign} tag
	 * @param tob A Object of the Type 
	 * {@link binaryoperations.TransmitableObject#TransmitableObject()}
	 * @throws IndexOutOfBoundsException if {@code inputArr[0]} doesn't exists
	 * @throws WrongInputException Forwarded by 
	 * {@link ArgumentHander#parseInt(InputOutput, int, int)}
	 * @throws WrongNumberException Forwarded by 
	 * {@link ArgumentHander#parseInt(InputOutput, int, int)}
	 * @throws NegativeExponetException Forwarded by 
	 * {@link binaryoperations.Arithmetic#prepareBinaryConvertion}
	 * @throws MultiplexerException If user enters command in the wrong order
	 */

	private void interpretInput(InputOutput io, Multiplexer mux, 
		TransmitableObject tob, String[] inputArr) 
			throws IndexOutOfBoundsException, WrongInputException,
			WrongNumberException, NegativeExponetException, MultiplexerException {

		
		io.setInput( inputArr[1] );
		isValue( io );
		
		parseInt(io,Integer.MAX_VALUE, 1);
		
		int valueAssignedToInputID = io.getDecimal();
		
		io.setDecimal( (byte) 0 );
		
		io.setInput( inputArr[0] );
		isValue( io );
		
		parseInt(io, Byte.MAX_VALUE, Byte.MIN_VALUE);
		
		Values values = new Values();
		Values returnValues = new Values();
		
		Arithmetic arithmetic = new Arithmetic();
		
		values.setDecimal( io.getDecimal() );
		values.setIsPositive( io.getIsPositive() );
		
		arithmetic.prepareBinaryConvertion( values, returnValues );
		
		if (tob.getInputID() == valueAssignedToInputID) {
			//If it's already the right tob don't start searching
			tob.setBinary( values.getBinary() );
			tob.setIsPositive( values.getIsPositive() );
			
			tob.setHasValues( true );
			
			mux.setDataStream( tob );
			
		}else if (tob.getChannelID() != 0) {
			
			for (TransmitableObject currentTob : mux.getDataStream()) {
				//Avoids null pointer exception, and reduces the runtime
				
				if (currentTob != null) {
					//if tob is NOT the right tob search for it
					
					if( currentTob.getInputID() == valueAssignedToInputID) {
						
						currentTob.setBinary( values.getBinary() );
						
						currentTob.setIsPositive( values.getIsPositive() );
						//support multi assignment as well
						
						currentTob.setHasValues( true );
						
						mux.setDataStream( currentTob );
					}
				}
			}
		}else {
			throw new MultiplexerException("Wrong Input can not assign"
			+ "value to channel.\nChannel is not connected to any input.");
		}
		System.out.println( "Value assigned" );
	}
}
