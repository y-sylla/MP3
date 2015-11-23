package sylla.bcs345.music.advancedmp3player;

import jaco.mp3.player.MP3Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import sylla.bcs345.music.playlist.Playlist;
import sylla.bcs345.music.songinfo.SongInfo;

public class PlayerWindow extends JFrame implements ActionListener{
	
	//Member Variables
	private JButton m_Load, m_Play, m_Stop, m_Pause, m_Resume, m_StartPlaylist, m_StopPlaylist, m_NextSong, m_PrevSong;
	private JLabel m_SongLabel, m_FileLabel, m_MinLabel, m_SecLabel;
	private JTextArea m_SongText, m_FileText, m_MinText, m_SecText, m_Status;
	private JMenuBar m_MenuBar;
	private JMenu m_Menu;
	private JMenuItem m_Item1, m_Item2;
	private SongInfo m_Song;
	private MP3Player m_Player = null;
	private JTree m_Tree;
	private DefaultMutableTreeNode m_Root;
	private Playlist m_Playlist;
	
	//Constructor
	public PlayerWindow()
	{
		//Sets up window
		setTitle("BCS 345 - Advanced MP3 Player - Sylla");
		setSize(650, 550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false);
		
		//Creates panels
		JFrame JFrame = new JFrame();
	
		JPanel main = new JPanel();
		main.setBackground(Color.BLUE);
		
		JPanel main2 = new JPanel();
		main2.setBackground(Color.CYAN);
		
		JPanel PlayListPanel = new JPanel();
		PlayListPanel.setBackground(Color.GREEN);
		
		JPanel buttons = new JPanel();
		buttons.setBackground(Color.MAGENTA);
		
		JPanel songName = new JPanel();
		songName.setBackground(Color.ORANGE);
		
		JPanel fileName = new JPanel();
		fileName.setBackground(Color.PINK);
		
		JPanel minutes = new JPanel();
		minutes.setBackground(Color.RED);
		
		JPanel seconds = new JPanel();
		seconds.setBackground(Color.YELLOW);
		
		//Creates tab pane
		JTabbedPane tab = new JTabbedPane();
		
		//Main panels
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		main2.setLayout(new FlowLayout());
		JFrame.add(main2);
		JFrame.add(main);
		
		//Creates tabs
		tab.addTab("PlayList", main2);
		tab.addTab("SongInfo", main);
		add(tab);
		
		//Menu Bar
		m_MenuBar = new JMenuBar();
		m_Menu = new JMenu("File");
		m_Item1 = new JMenuItem("Load Playlist....");
		m_Item2 = new JMenuItem("Load Songinfo....");
		m_Menu.add(m_Item1);
		m_Menu.add(m_Item2);
		m_MenuBar.add(m_Menu);

		add(m_MenuBar, BorderLayout.NORTH);

		//Creates buttons
		m_Load = new JButton("Load");
		m_Play = new JButton("Play");
		m_Stop = new JButton("Stop");
		m_Pause = new JButton("Pause");
		m_Resume = new JButton("Resume");
		
		m_StartPlaylist = new JButton("Start PlayList");
		m_StopPlaylist = new JButton("Stop Playlist");
		m_NextSong = new JButton("Next Song in Playlist");
		m_PrevSong = new JButton("Previous Song in Playlist");
		
		//Creates labels
		m_SongLabel = new JLabel("Name", SwingConstants.CENTER);
		m_SongLabel.setPreferredSize(new Dimension(55, 25));
		
		m_FileLabel = new JLabel("File", SwingConstants.CENTER);
		m_FileLabel.setPreferredSize(new Dimension(55, 25));
		
		m_MinLabel = new JLabel("Minutes", SwingConstants.CENTER);
		m_MinLabel.setPreferredSize(new Dimension(55, 25));
		
		m_SecLabel = new JLabel("Seconds", SwingConstants.CENTER);		
		m_SecLabel.setPreferredSize(new Dimension(55, 25));
		
		//Creates text areas
		m_SongText = new JTextArea(1,10);		
		m_FileText = new JTextArea(1,10);
		m_MinText = new JTextArea(1,10);
		m_SecText = new JTextArea(1,10);
		m_Status = new JTextArea("Waiting");
		
		//Buttons panel
		buttons.add(m_Load);
		buttons.add(m_Play);
		buttons.add(m_Stop);
		buttons.add(m_Pause);
		buttons.add(m_Resume);
		buttons.setLayout(new GridLayout(0, 5));
		main.add(buttons);
		
		//SongName panel
		songName.add(m_SongLabel);
		songName.add(m_SongText);
		songName.setLayout(new FlowLayout());
		main.add(songName);
		
		//FileName panel
		fileName.add(m_FileLabel);
		fileName.add(m_FileText);
		fileName.setLayout(new FlowLayout());
		main.add(fileName);
		
		//Minutes panel
		minutes.add(m_MinLabel);
		minutes.add(m_MinText);
		minutes.setLayout(new FlowLayout());
		main.add(minutes);
		
		//Seconds panel
		seconds.add(m_SecLabel);
		seconds.add(m_SecText);
		seconds.setLayout(new FlowLayout());
		main.add(seconds);
		
		//Playlist tab
		
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(2, 2));
		buttons.add(m_StartPlaylist);
		buttons.add(m_StopPlaylist);
		buttons.add(m_NextSong);
		buttons.add(m_PrevSong);
		PlayListPanel.add(buttons);
		
		//Set up root node
		m_Root = new DefaultMutableTreeNode("Playlist");
		
		//Create tree with root node
		m_Tree = new JTree(m_Root);
		
		JPanel tree = new JPanel();
		tree.setBackground(Color.WHITE);
		tree.setLayout(new FlowLayout());
		tree.add(m_Tree);
		PlayListPanel.add(tree);
		
		PlayListPanel.setLayout(new BoxLayout(PlayListPanel, BoxLayout.Y_AXIS));
		
		main2.add(PlayListPanel);
		
		//PlayListPanel.add(m_Tree);
		
		m_Item1.addActionListener(this);
		m_Item2.addActionListener(this);
		
		//Makes window listen to buttons
		m_Load.addActionListener(this);
		m_Play.addActionListener(this);
		m_Stop.addActionListener(this);
		m_Pause.addActionListener(this);
		m_Resume.addActionListener(this);
		m_StartPlaylist.addActionListener(this);
		m_StopPlaylist.addActionListener(this);
		m_PrevSong.addActionListener(this);
		m_NextSong.addActionListener(this);
		
		/*
		m_Status = new JTextArea("Waiting");
		//m_Status.setPreferredSize(new Dimension (800, 2));
		m_Status.setBackground(Color.lightGray);
		JFrame.add(m_Status, BorderLayout.SOUTH);
		*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Sets actions for Load button
		if (e.getSource() == m_Load || e.getSource() == m_Item2)
		{
			m_Status.setText("Loading");
			
			//Stops if a song is playing
			if(m_Player != null)
				m_Player.stop();
			
			JFileChooser chooser = new JFileChooser();
			chooser.setApproveButtonText("Load");
			
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				m_Song = new SongInfo();
				
				try 
				{
					Scanner input = new Scanner(new FileReader(chooser.getSelectedFile().getName()));
					m_Song.Read(input);
					m_SongText.setText(m_Song.GetSongName());
					m_FileText.setText(m_Song.GetFileName());
					m_MinText.setText(m_Song.GetMin() + " ");
					m_SecText.setText(m_Song.GetSec() + " ");
					
				}
				catch (FileNotFoundException e1) 
				{
					System.out.println("Error - File not found");
				}
				
				MP3Player player = new MP3Player(new File(m_FileText.getText()));					
				m_Player = player;
				
				//m_Status.setText("Waiting");
			}
		}
		
		//Sets actions for play button
		if (e.getSource() == m_Play)
		{
			//Stops if song is playing then plays song
			if(m_Player != null)
			{
				m_Player.stop();
				m_Player.play();
			}
			
			//m_Status.setText("Playing: " + m_SongText.getText());
			
		}
		
		//Sets actions for stop button
		if (e.getSource() == m_Stop)
		{
			if(m_Player != null)
				m_Player.stop();
			
			//m_Status.setText("Stopped");
		}
		
		//Sets actions for pause button
		if (e.getSource() == m_Pause)
		{
			if(m_Player != null)
				m_Player.pause();
			
			//m_Status.setText("Paused");
		}
		
		//Sets actions for resume button
		if (e.getSource() == m_Resume)
		{
			if(m_Player != null)
				m_Player.play();
		}
		
		//Sets action for menu item "Load Play list info"
		if (e.getSource() == m_Item1)
		{
			if(m_Player != null)
				m_Player.stop();
			
			
			m_Root.removeAllChildren();
			JFileChooser chooser = new JFileChooser();
			chooser.setApproveButtonText("Load");
			
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				m_Player = new MP3Player();
				m_Playlist = new Playlist();
				
				try 
				{
					Scanner input = new Scanner(new FileReader(chooser.getSelectedFile().getName()));
					
					m_Playlist.Read(input);	
					for (int i = 0; i < m_Playlist.GetSongCount(); i++)
					{
						m_Song = new SongInfo();
						m_Song = m_Playlist.GetSong(i);
						
						//Add node to tree
						DefaultMutableTreeNode n;
						n = new DefaultMutableTreeNode(m_Song.GetSongName());
						m_Root.add(n);
						m_Tree.updateUI();
						
						//Add song to playlist
						File song = new File (m_Song.GetFileName());
						m_Player.addToPlayList(song);
					}
				}
				catch (FileNotFoundException e1) 
				{
					System.out.println("Error - File not found");
				}
			}
		}
		
		//Sets actions for Start PlayList button
		if (e.getSource() == m_StartPlaylist)
		{
			if(m_Player != null)
				m_Player.play();
		}
		
		//Sets actions for Stop PlayList button
		if (e.getSource() == m_StopPlaylist)
		{
			if(m_Player != null)
				m_Player.stop();
		}
		
		//Sets actions for Next Song button
		if (e.getSource() == m_NextSong)
		{
			if(m_Player != null)
				m_Player.skipForward();
		}
		
		//Sets actions for Previous Song button
		if (e.getSource() == m_PrevSong)
		{
			if(m_Player != null)
				m_Player.skipBackward();
		}
	}

}
