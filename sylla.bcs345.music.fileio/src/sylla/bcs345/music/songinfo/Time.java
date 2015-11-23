//****************************************************
// File: Time.java  
//
// Purpose: Holds information about time.
//
// Written By: Yasia Sylla 
//
// Date: 10/07/2013   
// 
// Update Information
// ------------------
//
// Name: Yasia Sylla
// Date: 12/08/2013
// Description: Added comments to methods.
//****************************************************


package sylla.bcs345.music.songinfo;

import java.io.PrintStream;
import java.util.Scanner;

public class Time {

	//Member Variables
	
	private int Minutes;
	private int Seconds;
	
	//****************************************************
	// Method: Time
	//
	// Purpose: Default constructor for class.
	//****************************************************
	
	public Time ()
	{
		Minutes = 4;
		Seconds = 42;
	}
	
	//****************************************************
	// Method: Time
	//
	// Purpose: Constructor with parameters
	//****************************************************

	
	public Time (int min, int sec)
	{
		Minutes = min;
		Seconds = sec;
	}
	
	//****************************************************
	// Method: Time
	//
	// Purpose: Constructor with parameters
	//****************************************************

	public Time (Time other)
	{
		Minutes = other.Minutes;
		Seconds = other.Seconds;
	}
	
	//Get & Set Methods
	
	public int GetMin () { return Minutes; }
	public int GetSec () { return Seconds; }
	
	public void SetMin (int min) { Minutes = min; }
	public void SetSec (int sec) { Seconds = sec; }
	
	//Other Methods
	
	//****************************************************
	// Method: AddTime
	//
	// Purpose: Adds two instances of Time together
	//****************************************************

	
	public void AddTime (Time other)
	{
		Minutes += other.Minutes;
		int totalSec = Seconds + other.Seconds;
		
		Minutes += totalSec/60;
		Seconds = totalSec%60;
		
		System.out.printf("Minutes: %d\n", Minutes);
		System.out.printf("Seconds: %d\n\n", Seconds);
	}
	
	//****************************************************
	// Method: SubtractTime
	//
	// Purpose: Adds two instances of Time together
	//****************************************************

	public void SubtractTime (Time other)
	{
		Minutes -= other.Minutes;
		int totalSec = Seconds - other.Seconds;
		
		Minutes -= totalSec/60;
		Seconds = totalSec%60;
		
		System.out.printf("Minutes: %d\n", Minutes);
		System.out.printf("Seconds: %d\n\n", Seconds);

	}
	
	//****************************************************
	// Method: Write
	//
	// Purpose: Writes member variables to PrintStream.
	//****************************************************

	public void Write (PrintStream ps)
	{
		ps.println(Minutes);
		ps.println(Seconds);
	}
	
	//****************************************************
	// Method: Read
	//
	// Purpose: Reads contents from Scanner.
	//****************************************************
	
	public void Read (Scanner s)
	{
		Minutes = s.nextInt();
		Seconds = s.nextInt();
	}
	
}