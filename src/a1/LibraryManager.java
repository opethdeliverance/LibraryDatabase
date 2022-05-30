package a1;
import java.util.*;

/**
 * Manager for connecting author's and books
 */
public class LibraryManager {

    private List<Author> authorList;
    private List<Book> bookList;
    private BookDatabaseManager bookDatabaseManager;

    /**
     * simple constructor for the library manager
     * @param bookDatabaseManager
     */
    public LibraryManager(BookDatabaseManager bookDatabaseManager) {
        this.bookDatabaseManager = bookDatabaseManager;
        reloadFromSource();
    }

    /**
     * method for reloading the library from the source
     */
    public void reloadFromSource(){
        authorList = bookDatabaseManager.getAllAuthors();
        bookList = bookDatabaseManager.getAllBooks();
    }

    /**
     * getter for the author list
     * @return
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * getter for the book list
     * @return
     */
    public List<Book> getBookList() {
        return bookList;
    }
}
