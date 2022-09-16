/**Part of the package multiplexer*/

package multiplexer;

import java.util.Observable;
import java.util.Observer;

import binaryoperations.TransmitableObject;
import exceptions.NegativeExponetException;

/**This is the De-Multiplexer Class it handles the de-multiplexing process.
 * the class has singleton design pattern and observer design pattern.
 * it extends {@link Multiplexer#Multiplexer()} and implements the Observer
 * @version 1.3.2
 * @author Pascal S. PERSONAL INFORMATION REDACTED
 * Created on Jan 12, 2018
 * @see java.util.Observer
 * @see java.util.Observable
 */
public class SingletonDemultiplexer extends Multiplexer implements Observer{

	/**A variable with the amount of Inputs the Multiplexer has
	 * Values can be set by {@link #setAmountOfInputs(long)}
	 * and received by {@link #getAmountOfInputs()}
	 * */
	private int amountOfOutputs = 0;

	/**This is the "Data" Array with all Data witch is currently with the multiplexer
	 * The array contains at MAX 32 objects of the type
	 * {@link binaryoperations.TransmitableObject#TransmitableObject()}
	 */
	private TransmitableObject[] dataStream = new TransmitableObject[32];
	
	/**The one and only instance of {@link #SingletonDemultiplexer()}*/
	private static SingletonDemultiplexer instance = null;
	
	/**Checks if the
	 * {@link binaryoperations.TransmitableObject#TransmitableObject()}
	 * ever got a value setted.
	 * Is a clone of {@link binaryoperations.TransmitableObject#getHasReference()}
	 */
	private boolean hasReference = false;
	
	/**Checks if the
	 * {@link binaryoperations.TransmitableObject#TransmitableObject()}
	 * ever got a value setted.
	 * Is a clone of {@link binaryoperations.TransmitableObject#gethHasValues()}
	 */
	private boolean hasValues = false;
	
	/* ==================================================================== */
	
	/**The Default Singleton Constructor
	 * it will be accessed by {@link #getInstance()}
	 */
	
	private SingletonDemultiplexer() {}
	
	
	/**The Method getInstance is used to control that only one instance of
	 * {@link #SingletonDemultiplexer() will be created}
	 * 
	 * @return the current and only Instance
	 */
	
	public static SingletonDemultiplexer getInstance() {
		if(instance == null) {
			instance = new SingletonDemultiplexer();
		}
		return instance;
	}
	
	/* ==================================================================== */
	
	/**This is the getter Method for {@link #amountOfInputs}
	 * @return amountOfInputs
	 */
	
	public int getAmountOfOutputs() {
		return amountOfOutputs;
	}
	
	
	/**This is the getter Method for {@link #amountOfInputs}
	 * @param mux A object o type {@link multiplexer.Multiplexer#Multiplexer()}
	 * @return amountOfInputs The Amount of Inputs the Multiplexer has
	 */
	
	public int getAmountOfOutputs(Multiplexer mux) {
		int amountOfOutputs = mux.getAmountOfInputs();
		
		this.amountOfOutputs = amountOfOutputs;
		
		return amountOfOutputs;
	}
	
	
	/**This is the setter Method for {@link #dataStream}
	 * @param mux the {@link multiplexer.Multiplexer#Multiplexer()} to be set
	 */
	
	public void setDataStream(Multiplexer mux){
		instance.dataStream = mux.getDataStream();
	}
	
	
	/**This is the setter Method for {@link #dataStream}
	 * @param tob the 
	 * {@link binaryoperations.TransmitableObject#TransmitableObject()} 
	 * array to be set
	 */
	
	public void setDataStream(TransmitableObject[] tob){
		instance.dataStream = tob;
	}
	
	/**This is the getter Method for {@link #dataStream}
	 * @return The dataStream Array.
	 */
	
	public TransmitableObject[] getDataStream() {
		return instance.dataStream;
	}
	
	
	/**This is the getter Method for {@link #hasValues}
	 * Is a clone of {@link binaryoperations.TransmitableObject#gethHasValues()}
	 * @return The boolean hasValue
	 */
	
	public boolean getHasValues() {
		return hasValues;
	}


	/**This is the setter Method for {@link #hasValues}
	 * Is a clone of {@link binaryoperations.TransmitableObject#gethHasValues()}
	 * @param hasValues the hasValues to set
	 */
	
	public void setHasValues( boolean hasValues ) {
		this.hasValues = hasValues;
	}


	/**This is the getter Method for {@link #hasReference}
	 * Is a clone of {@link binaryoperations.TransmitableObject#getHasReference()}
	 * @return the boolean value of hasReference
	 */
	
	public boolean getHasReference() {
		return hasReference;
	}


	/**This is the setter Method for {@link #hasReference}
	 * Is a clone of {@link binaryoperations.TransmitableObject#getHasReference()}
	 * @param hasReference the hasReference to set
	 */
	
	public void setHasReference( boolean hasReference ) {
		this.hasReference = hasReference;
	}


	/**This Method creates an unsorted array of 
	 * {@link binaryoperations.TransmitableObject#getOutputID()}
	 * @param tob A array of 
	 * {@link binaryoperations.TransmitableObject#TransmitableObject()}
	 * @return A Sorted TransmitableObject Array received by the sorting method
	 * {@link #bubblesort(int[], int)} / 
	 * {@link #matching(TransmitableObject[], int[], int)}
	 */
	
	public TransmitableObject[] sortByOutputID (TransmitableObject[] tob) {
		
		//New Array with the size of the Inputs/Output of the (De)multiplexer.
		int range = getAmountOfOutputs();
		range+=2;
		int [] arrayOfOutputIDs = new int [range];
		
		for (int cnt = 0 ; cnt < range ; ++cnt) {
			if ((tob[cnt] != null)) {
				arrayOfOutputIDs[cnt] = tob[cnt].getOutputID();
			}
		}
		
		arrayOfOutputIDs = bubblesort(arrayOfOutputIDs, range);
		
		tob = matching(tob, arrayOfOutputIDs, range);
		
		return tob;
	}
	
	
	/** This is a Sorting algorithm using the Bubble-Sort. 
	 * It sort an Array of all Output Id's
	 * 	 * @param arrayOfOutputIDs The array to be sorted
	 * @param range The range to be sorted within
	 * @return a sorted array
	 */
	
	private int[] bubblesort (int[] arrayOfOutputIDs, int range) {
		
		boolean gotSortedInThisCicle;
		
		do {
			gotSortedInThisCicle = false;
			
			for (int cnt = 0 ; cnt < range-1 ; ++cnt) {
				
				if (arrayOfOutputIDs[cnt] > arrayOfOutputIDs[cnt+1]) {
					
					int temp = arrayOfOutputIDs[cnt];
					
					arrayOfOutputIDs[cnt] = arrayOfOutputIDs[cnt+1];
					
					arrayOfOutputIDs[cnt+1] = temp;
					
					gotSortedInThisCicle = true;
				}
			}
			
		}while(gotSortedInThisCicle);
		
		return arrayOfOutputIDs;
	}
	
	
	/**This Method matches the ArrayOfOutputIDs with the object containing
	 * this outputID
	 * 
	 * @param tob A Object array of the type
	 * {@link binaryoperations.TransmitableObject#TransmitableObject()}
	 * @param arrayOfOutputIDs The array with all outputIDs
	 * @return A array of TransmitableObjects machted by the sorted int array.
	 */
	
	private TransmitableObject[] matching (TransmitableObject[] tob, 
		int arrayOfOutputIDs[], int range) {
		
		TransmitableObject[] newTob = new TransmitableObject[32]; 
		
		for (TransmitableObject currentTob : tob) { 
			
			if (currentTob != null) {
				
				for ( int cnt = 0 ; cnt < range; ++cnt) {
					
					if (currentTob.getOutputID() == arrayOfOutputIDs[cnt]) {
						newTob[cnt] = currentTob;
						break; /*If double Assignment will be a thing in 
								the test just remove me*/
					}
				}
			}		
		}
		
		return newTob;
	}
	
	
	/**This method is called whenever the observed object is changed. 
	 * An application calls an Observable object's notifyObservers method to
	 * have all the object's observers notified of the change.
	 * @param o  the observable object.
	 * @param instance  an argument passed to the <code>notifyObservers</code>
     * method.
	 */
	
	@Override
	public void update( Observable o, Object instance) {
		
		TransmitableObject[] tobArray = 
			( (SingletonDemultiplexer) instance ).getDataStream();
		
		tobArray = sortByOutputID(tobArray);
		
		( (SingletonDemultiplexer) instance ).setDataStream( tobArray );
		
		main.InputOutput io = new main.InputOutput();
		
		try {
			
			io.output( (SingletonDemultiplexer) instance );
		
		}catch ( NegativeExponetException e ) {
			e.printStackTrace();
		}
		
	}
}