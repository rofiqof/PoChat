package app.pochat.rofiqoff.com.pochat.tab.datatimeline;

/**
 * Created by RofiqoFF on 13/12/2016.
 */

public class TimelineList {
	private String namaUser;
	private int imageUser;
	private int imagePoster;
	private String deskripsi;

	public TimelineList(String namaUser, int imageUser, int imagePoster, String deskripsi) {
		this.namaUser = namaUser;
		this.imageUser = imageUser;
		this.imagePoster = imagePoster;
		this.deskripsi = deskripsi;
	}

	public String getNamaUser() {
		return namaUser;
	}

	public void setNamaUser(String namaUser) {
		this.namaUser = namaUser;
	}

	public int getImageUser() {
		return imageUser;
	}

	public void setImageUser(int imageUser) {
		this.imageUser = imageUser;
	}

	public int getImagePoster() {
		return imagePoster;
	}

	public void setImagePoster(int imagePoster) {
		this.imagePoster = imagePoster;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
}

