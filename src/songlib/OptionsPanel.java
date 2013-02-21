package songlib;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * @author Alexander Tang 
 */
class OptionsPanel extends JPanel {
	
	SongLib songLib;
	
	JButton add = new JButton("Add");
	JButton edit = new JButton("Edit");
	JButton delete = new JButton("Delete");
	JButton done = new JButton("Done");
	
	private addListener aL = new addListener();
	private editListener eL = new editListener();
	private deleteListener delL = new deleteListener();
	private doneListener doneL = new doneListener();
	
	private String currAction;
	
	public OptionsPanel(SongLib songLib) {
		this.songLib = songLib;

		setLayout(new GridLayout(1,4));
	
		setBorder(BorderFactory.createEmptyBorder(5,0,3,0));
		add.addActionListener(aL);
		edit.addActionListener(eL);
		delete.addActionListener(delL);
		done.addActionListener(doneL);
		
		add(add);
		add(edit);
		add(delete);
		add(done);
		disableButton(done);
	}
	
	public void enableButton(JButton b) {
		b.setEnabled(true);
	}
	
	public void disableButton(JButton b) {
		b.setEnabled(false);
	}

	// if lib.isEmpty(), only enable add button
	public void setEmptyLibButtons() {
		enableButton(add);
		disableButton(edit);
		disableButton(delete);			
		disableButton(done);
	}
	
	protected class addListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currAction = "add";
			songLib.clearDetails();
			songLib.setEditable(true);
			
			// enable done, disable others
			enableButton(done);
			disableButton(add);
			disableButton(edit);
			disableButton(delete);
		}
	}

	protected class editListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			currAction = "edit";
			songLib.setEditable(true);
			
			// enable done, disable others
			enableButton(done);
			disableButton(add);
			disableButton(edit);
			disableButton(delete);
		}
	}
	
	protected class deleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Song s = songLib.getSelectedSong();
			int i = songLib.getSelectedSongIndex();
			int last = songLib.libSize() - 1;
			
			if(i == last) {
				songLib.setSelectedSong(i-1);
			} else {
				songLib.setSelectedSong(i+1);
			}
			
			songLib.deleteSong(s);
		}
	}
	
	protected class doneListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			
			JTextField nameField = songLib.detailsPanel.nameField;
			JTextField artistField = songLib.detailsPanel.artistField;
			JTextField albumField = songLib.detailsPanel.albumField;
			JTextField yearField = songLib.detailsPanel.yearField;
			
			String name = nameField.getText().trim();
			String artist = artistField.getText().trim();
			String album = albumField.getText().trim();
			String year = yearField.getText().trim();
			
			// get index of current song
			int i = songLib.getSelectedSongIndex();
			
			// if user pressed edit first, do this when done
			if(currAction.equals("edit")) {
				Song s = songLib.getSelectedSong();
				if(!name.equals("")) s.setName(name);
				if(!artist.equals("")) s.setArtist(artist);
				s.setAlbum(album);
				s.setYear(year);
			}
			
			// if user pressed add first, do this when done
			if(currAction.equals("add")) {

				// check that name, artist fields are not empty
				if(!name.equals("") && !artist.equals("")) {
					Song s = new Song(name, artist, album, year);
					songLib.addSong(s);
					
					// set selected song to new song
					i = songLib.libSize() - 1;
				}
			}
			
			songLib.setSelectedSong(i);
		}
	}
}
