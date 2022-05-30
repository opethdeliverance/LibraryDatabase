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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

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
