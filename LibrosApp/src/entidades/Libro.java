package entidades;

/**
 * @author cfco5
 * @version 1.0
 * @created 17-Nov-2022 8:07:21 PM
 */
public class Libro {

	private String copyright;
	private int editionNumber;
	private String isbn;
	private String title;

	public Libro(){

	}

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Libro(String copyright, int editionNumber, String isbn, String title) {
        this.copyright = copyright;
        this.editionNumber = editionNumber;
        this.isbn = isbn;
        this.title = title;
    }

	
}//end Libro