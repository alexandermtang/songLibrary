package songlib;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


class SongListPanel extends JPanel 
				    implements ListSelectionListener {
	
	SongLib songLib;
	JList songList;
	DefaultListModel lib;

	public SongListPanel(SongLib songLib) 
	throws IOException {
		this.songLib = songLib;
		this.lib = songLib.lib;
		
		songList = new JList(lib);
		songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		songList.setLayoutOrientation(JList.VERTICAL);
		songList.setSelectedIndex(0);
		songList.addListSelectionListener(this);
		
		JScrollPane listScrollPane = new JScrollPane(songList);

		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		add(listScrollPane);
	}
	
	public Song getSelectedSong() {
		return (Song)songList.getSelectedValue();
	}
	
	public int getSelectedSongIndex() {
		return songList.getSelectedIndex();
	}
	
	public void setSelectedSong(int i) {
		if(lib.getSize() > 0 && i < lib.getSize()) {
			songList.setSelectedIndex(i);
			Song s = getSelectedSong();
			songLib.detailsPanel.updateDetailsTo(s);
		}
	}
	
	public void valueChanged(ListSelectionEvent e) {
		Song s = getSelectedSong();
		songLib.detailsPanel.updateDetailsTo(s);
	}
}
