package songlib;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class DetailsPanel extends JPanel {

	SongLib songLib;
	
	JTextField nameField = new JTextField();
	JTextField artistField = new JTextField();
	JTextField albumField = new JTextField();
	JTextField yearField = new JTextField();
	
	JLabel nameLabel = new JLabel("Name");
	JLabel artistLabel = new JLabel("Artist");
	JLabel albumLabel = new JLabel("Album");
	JLabel yearLabel = new JLabel("Year");
	
	public DetailsPanel(SongLib songLib) {
		this.songLib = songLib;
		
		setLayout(new BorderLayout());

		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		artistLabel.setHorizontalAlignment(JLabel.RIGHT);
		albumLabel.setHorizontalAlignment(JLabel.RIGHT);
		yearLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		JPanel labelPane = new JPanel();
		labelPane.setLayout(new GridLayout(4,1));
		labelPane.add(nameLabel);
		labelPane.add(artistLabel);
		labelPane.add(albumLabel);
		labelPane.add(yearLabel);
		labelPane.setBorder(BorderFactory.createEmptyBorder(5,15,5,0));

		JPanel fieldPane = new JPanel();
		fieldPane.setLayout(new GridLayout(4,1));
		fieldPane.add(nameField);
		fieldPane.add(artistField);
		fieldPane.add(albumField);
		fieldPane.add(yearField);
		fieldPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		add(labelPane, BorderLayout.WEST);
		add(fieldPane, BorderLayout.CENTER);
		
		setEditable(false);
	}
	
	protected void updateDetailsTo(Song s) {
		if(s != null) {
			nameField.setText(s.getName());
			artistField.setText(s.getArtist());
			albumField.setText(s.getAlbum());
			yearField.setText(s.getYear());
			
			setEditable(false);
			// disable done button, enable others
			songLib.optionsPanel.enableButton(songLib.optionsPanel.add);
			songLib.optionsPanel.enableButton(songLib.optionsPanel.edit);
			songLib.optionsPanel.enableButton(songLib.optionsPanel.delete);
			songLib.optionsPanel.disableButton(songLib.optionsPanel.done);
		} else {
			clearDetails();
		}
	}
	
	protected void clearDetails() {
		nameField.setText("");
		artistField.setText("");
		albumField.setText("");
		yearField.setText("");
	}
	
	protected void setEditable(Boolean b) {
		if(b) {
			nameField.setEditable(true);
			artistField.setEditable(true);
			albumField.setEditable(true);
			yearField.setEditable(true);
		} else {
			nameField.setEditable(false);
			artistField.setEditable(false);
			albumField.setEditable(false);
			yearField.setEditable(false);
		}
	}
	
}
