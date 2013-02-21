package songlib;

/*
 * @author Alexander Tang 
 */
public class Song {
	
	private String name = "";
	private String artist = "";
	private String album = "";
	private String year = "";
	
	public Song(String name, String artist) {
		this.name = name;
		this.artist = artist;
	}
	
	public Song(String name, String artist, String album) {
		this.name = name;
		this.artist = artist;
		this.album = album;
	}
	
	public Song(String name, String artist, String album, String year) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public String getName() {
		return name;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Song)) {
			return false;
		}
		Song other = (Song)o;
		return name.equals(other.getName()) && 
				artist.equals(other.getArtist());
	}
	
	public String toString() {
		return name;
	}
}
