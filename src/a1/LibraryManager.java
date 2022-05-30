package a1;
import java.util.*;


public class LibraryManager {

    private List<Author> authorList;
    private List<Book> bookList;
    private BookDatabaseManager bookDatabaseManager;

    public LibraryManager(BookDatabaseManager bookDatabaseManager) {
        this.bookDatabaseManager = bookDatabaseManager;
        reloadFromSource();
    }

    public void reloadFromSource(){
        authorList = bookDatabaseManager.getAllAuthors();
        bookList = bookDatabaseManager.getAllBooks();


    }

    public List<Author> getAuthorList() {

        return authorList;
    }

    public List<Book> getBookList() {
        return bookList;
    }


}
