//****************************************************
// File: Playlist.java  
//
// Purpose: Create a play list of songs.
//
// Written By: Yasia Sylla 
//
// Date: 11/6/2013
//
// Name: Yasia Sylla
// Date: 11/22/2013
// Description: Edit functions
//
// Update Information
// ------------------
//
// Name: Yasia Sylla
// Date: 12/12/2013
// Description: Changes to GetSong and add
//				GetSongCount function
//****************************************************

package sylla.bcs345.music.playlist;

import java.io.*;
import java.util.*;

import sylla.bcs345.music.songinfo.*;

public class Playlist {
	
	//Member Variables
	
	private int m_MaxCount;
	private int m_SongCount;
	private SongInfo[] m_Songs;
	
	//DEFAULT CONSTRUCTOR
	
	//****************************************************
	// Method: Play list
	//
	// Purpose: Default constructor for class.
	//****************************************************
	
	
	public Playlist()
	{
		m_MaxCount = 10;
		m_SongCount = 0;
		m_Songs = new SongInfo[m_MaxCount]; //Allocating the SongInfo
		
		for (int i = 0; i < m_MaxCount; i++)
			m_Songs[i] = new SongInfo();
	}
	
	//METHODS
	
	//****************************************************
	// Method: AddSong
	//
	// Purpose: Adds SongInfo to array at given index.
	//****************************************************
	public void AddSong (int index, SongInfo si)
	{
		m_Songs[index] = si;	
	}
	
	//****************************************************
	// Method: AddSongToBeginning
	//
	// Purpose: Add song to beginning of list of songs.
	//****************************************************
	public void AddSongToBeginning (SongInfo si)
	{
		int index = 0;
		m_Songs[index + 1] = m_Songs[index];
		
		while ( index < m_SongCount)
		{
			m_Songs[index + 1] = m_Songs[index];
			index++;
		}
	}
	
	//****************************************************
	// Method: AddSongToEnd
	//
	// Purpose: Add song to end of list of songs.
	//****************************************************
	public void AddSongToEnd (SongInfo si)
	{
		int index = m_SongCount;
		m_Songs[index - 1] = m_Songs[index];
		
		while ( index >= 0)
		{
			m_Songs[index - 1] = m_Songs[index];
			index--;
		}
	}
	
	//****************************************************
	// Method: Write
	//
	// Purpose: Writes member variables to PrintStream.
	//****************************************************
	public void Write (PrintStream ps)
	{
		ps.println(m_SongCount);
		
		for (int i = 0; i < m_SongCount; i++)
			m_Songs[i].Write(ps);
	}
	
	//****************************************************
	// Method: Read
	//
	// Purpose: Reads contents from Scanner.
	//****************************************************
	public void Read (Scanner s)
	{
		m_SongCount = s.nextInt();
		s.nextLine(); //To read in \n after int
		
		m_MaxCount = m_SongCount + 15;
		
		m_Songs = new SongInfo[m_MaxCount];
		
		for (int i = 0; i < m_SongCount; i++)
		{
			m_Songs[i] = new SongInfo();
			m_Songs[i].Read(s);
			s.nextLine();
		}
	}
	
	//****************************************************
	// Method: GetSong
	//
	// Purpose: Returns SongInfo at given index.
	//****************************************************
	public SongInfo GetSong (int index)
	{
		if (index >= 0 && index < m_SongCount)
		{
			//System.out.println(m_Songs[index].GetSongName());
			//System.out.println(m_Songs[index].GetLength());
			//m_Songs[index].Write(System.out);
			return m_Songs[index];
		}
		else
			return null;
	}
	
	//****************************************************
	// Method: GetRandomSong
	//
	// Purpose: Returns random SongInfo.
	//****************************************************
	public SongInfo GetRandomSong()
	{
		if (m_Songs == null)
			return null;
		else
		{
			Random rand = new Random();
			int index = rand.nextInt(m_SongCount);
			
			GetSong(index);
		
			return m_Songs[index];
		}
	}
	
	//****************************************************
	// Method: GetSongCount
	//
	// Purpose: Returns m_SongCount.
	//****************************************************
	
	public int GetSongCount()
	{
		return m_SongCount;
	}
}
