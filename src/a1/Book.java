package a1;
import java.io.PrintStream;
import java.util.List;
import java.util.LinkedList;

/**
 * create a book
 */
public class Book {

    private String isbn;
    private String title;
    private int editionNumber;
    private String copyright;
    private List<Author> authorList = new LinkedList<>();

    /**
     * simple constructor for book class
     * @param isbn
     * @param title
     * @param editionNumber
     * @param copyright
     */
    public Book(String isbn, String title, int editionNumber, String copyright) {
        this.isbn = isbn;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyright = copyright;

    }

    /**
     * constructor for book class with author list
     * @param isbn
     * @param title
     * @param editionNumber
     * @param copyright
     * @param authorList
     */
    public Book(String isbn, String title, int editionNumber, String copyright, List<Author> authorList) {
        this(isbn, title, editionNumber, copyright);
        this.authorList = authorList;
    }

    /**
     * getter for author list
     * @return
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * setter for author list
     * @param authorList
     */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    /**
     * getter for isbn
     * @return
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * getter for book title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter for edition number
     * @return
     */
    public int getEditionNumber() {
        return editionNumber;
    }

    /**
     * getter for copyright
     * @return
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Print book information
     * @param printStream
     */
    public void printBookInformation(PrintStream printStream){
        printStream.printf("\nISBN: %s \t\t Title: %-55s \t\t Edition #: %d \t\t Copyright: %s",
                this.getIsbn(), this.getTitle(), this.getEditionNumber(), this.getCopyright());
        authorList.stream().forEach(author -> {
            System.out.printf("\nFirst Name: %-15s Last Name: %s", author.getFirstName(), author.getLastName());
        });
    }

}
