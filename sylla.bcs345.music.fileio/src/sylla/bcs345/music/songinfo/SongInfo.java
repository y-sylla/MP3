//*****************************************************
// File: SongInfo.java  
//
// Purpose: Hold general information about a song.
//
// Written By: Yasia Sylla 
//
// Date: 10/07/2013
//
// Update Information
// ------------------
//
// Name: Yasia Sylla
// Date: 11/10/2013
// Description: Edited member variable names
//
// Name: Yasia Sylla
// Date: 11/22/2013
// Description: Add call to new for Time in constructor
//				Add get methods for minutes and seconds
//
// Name: Yasia Sylla
// Date: 12/08/2013
// Description: Add method called toString
//*****************************************************



package sylla.bcs345.music.songinfo;

import java.io.PrintStream;
import java.util.Scanner;

public class SongInfo {
	
	//Member Variables
	
	private String m_SongName;
	private String m_FileName;
	private Time m_Length;

	//****************************************************
	// Method: SongInfo
	//
	// Purpose: Default constructor for class.
	//****************************************************
	
	public SongInfo()
	{
		m_SongName = "Rock City";
		m_FileName = "01 Rock City.mp3";
		m_Length = new Time();
	}
	
	//****************************************************
	// Method: SongInfo
	//
	// Purpose: Constructor with parameters for class.
	//****************************************************

	public SongInfo(String song, String file, Time t)
	{
		m_SongName = song;
		m_FileName = file;
		m_Length = t;
	}
	
	//Get & Set Methods
	
	public String GetSongName() { return m_SongName; }
	public String GetFileName() { return m_FileName; }
	public Time GetLength() { return m_Length; }
	public int GetMin () { return m_Length.GetMin(); }
	public int GetSec () { return m_Length.GetSec(); }
	
	public void SetSongName (String song) { m_SongName = song; }
	public void SetFileName (String file) { m_FileName = file; }
	public void SetLength (int min, int sec)
	{
		//Length = t;
		Time t = new Time();
		t.SetMin(min);
		t.SetSec(sec);
		
	}
	
	//Other Methods
	
	//****************************************************
	// Method: Write
	//
	// Purpose: Writes member variables to PrintStream.
	//****************************************************

	public void Write (PrintStream ps)
	{
		ps.println(m_SongName);
		ps.println(m_FileName);
		m_Length.Write(ps);
	}
	
	//****************************************************
	// Method: Read
	//
	// Purpose: Reads contents from Scanner.
	//****************************************************
	public void Read (Scanner s)
	{
		m_SongName = s.nextLine();
		m_FileName = s.nextLine();
		m_Length.Read(s);
	}
	
	//****************************************************
	// Method: toString
	//
	// Purpose: Returns song name.
	//****************************************************
	
	public String toString()
	{
		return m_SongName;
	}
}