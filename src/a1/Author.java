package a1;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

/**
 * class to create and print author
 */
public class Author {

    private int authorID;
    private String firstName;
    private String lastName;
    private List<Book> bookList;

    /**
     * simple constructor for author class
     * @param authorID
     * @param firstName
     * @param lastName
     */
    public Author(int authorID, String firstName, String lastName) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookList = new LinkedList<>();
    }

//    public Author(String firstName, String lastName) {
//        this.firstName;
//        this.lastName;
//
//    }

    /**
     * getter for the book list
     * @return
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * setter for the book list
     * @param bookList
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    /**
     * getter for the author ID
     * @return
     */
    public int getAuthorID() {
        return authorID;
    }

    /**
     * setter for the author ID
     * @param authorID
     */
    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    /**
     * getter for the author's first name
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter for the author's first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getter for the author's last name
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter for the author's last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Print author information
     * @param printStream
     */
    public void printAuthorInformation(PrintStream printStream){
        printStream.printf("\nAuthor ID: %d \t\t First Name: %-20s Last Name: %s",
                this.getAuthorID(), this.getFirstName(), this.getLastName());
        bookList.stream().forEach(book -> {
            System.out.printf("\nISBN: /t/t/t Title: %-55s Edition #: %s \t",
                    book.getIsbn(), book.getTitle(), book.getEditionNumber(), book.getCopyright());
        });
    }
}
