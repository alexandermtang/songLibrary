package songlib;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

/**
 * @author Alexander Tang 
 */
public class SongLib extends JFrame {
	
	OptionsPanel optionsPanel;
	SongListPanel songListPanel;
	DetailsPanel detailsPanel;
	
	private File songFile;
	
	DefaultListModel lib = new DefaultListModel();
	
	public SongLib(String str)
	throws IOException {
		super(str);
		
		load();
		
		optionsPanel = new OptionsPanel(this);
		songListPanel = new SongListPanel(this);
		detailsPanel = new DetailsPanel(this);
		
		setLayout(new BorderLayout());
		
		add(optionsPanel, BorderLayout.NORTH);
		add(songListPanel, BorderLayout.CENTER);
		add(detailsPanel, BorderLayout.SOUTH);
		
		if(lib.isEmpty()) {
			setEmptyLibButtons();
		}
		
		songListPanel.setSelectedSong(0);
	}
	
	// fills nameArray with song names from songlib.txt
	private void load() 
	throws IOException {
		this.songFile = new File("songlib.txt");
		Scanner sc = new Scanner(songFile);
		
		while(sc.hasNextLine()) {
			String[] line = sc.nextLine().split("\\|");
			
			String name = line[0];
			String artist = line[1];
			
			Song s = new Song(name, artist);

			if(line.length == 3) {
				String album = line[2];
				s.setAlbum(album);
			}
				
			if(line.length == 4) {
				String album = line[2];
				String year = line[3];
				s.setAlbum(album);
				s.setYear(year);
			}
			
			addSong(s);
		}
	}
	
	public void addSong(Song s) {
		if(!lib.contains(s)) {
			lib.addElement(s);
		}
	}
	
	public void deleteSong(Song s) {
		if(lib.contains(s)) {
			lib.removeElement(s);
		}
		if(lib.getSize() == 0) {
			setEmptyLibButtons();
		}
	}
	
	public Song getSelectedSong() {
		return (Song)songListPanel.getSelectedSong();
	}
	
	public int getSelectedSongIndex() {
		return songListPanel.getSelectedSongIndex();
	}
	
	public void setSelectedSong(int i) {
		songListPanel.setSelectedSong(i);
	}
	 
	public void setEditable(Boolean b) {
		detailsPanel.setEditable(b);
	}
	
	protected void clearDetails() {
		detailsPanel.clearDetails();
	}

	public int libSize() {
		return lib.getSize();
	}
	
	public void setEmptyLibButtons() {
		optionsPanel.setEmptyLibButtons();
	}

	public void save() 
	throws IOException {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < lib.getSize(); i++) {
			Song s = (Song)lib.getElementAt(i);
			
			String name = s.getName();
			String artist = s.getArtist();
			String album = s.getAlbum();
			String year = s.getYear();
			
			sb.append(name + "|" + artist + "|" + album + "|" + year + "\n");
		}
	
		FileWriter f = new FileWriter(this.songFile);
		f.write(sb.toString());
		f.close();
	}
	
	public static void main(String[] args) 
	throws IOException {
		final SongLib lib = new SongLib("Tango Tunes");
		lib.setDefaultCloseOperation(EXIT_ON_CLOSE);
		lib.setSize(965,596);
		lib.setLocationRelativeTo(null);
		lib.setVisible(true);		
		lib.addWindowListener(new WindowAdapter() {
			public void	windowClosing(WindowEvent e) {
				try {
					lib.save();
				} catch(Exception ex) {
				}
			}
		});
	}
}
