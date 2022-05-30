package a1;

import java.util.List;
import java.util.LinkedList;

/**
 * Test class for DB Manager
 */
public class BookDatabaseManagerTester {

    public static void main(String[] args) {
        System.out.println("test DB manager");

        List<Book> bookList = BookDatabaseManager.getAllBooks();
        bookList.forEach(book -> book.printBookInformation(System.out));

        List<Author> authorList = BookDatabaseManager.getAllAuthors();
        authorList.forEach(author -> author.printAuthorInformation(System.out));


        //These work ok:

        //BookDatabaseManager.insertBook(new Book("12345438", "Aces High - Bruce Dickinson Story", 2, "2021"));

        //BookDatabaseManager.getBookByISBN("12345434").printBookInformation(System.out);

    }
}
