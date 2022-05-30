package a1;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Class to manage all queries within book DB
 */

public class BookDatabaseManager {

    public static LibraryManager getLibraryManager() {

        return new LibraryManager(new BookDatabaseManager());
    }

//    public List<AuthorISBN> getAllISBN(){
//        List<AuthorISBN> authorISBNList = new LinkedList<>();


    /**
     * Retrieve all the books from database
     *
     * @return book list
     */
    public static List<Book> getAllBooks() {
        LinkedList bookList = new LinkedList();

        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "SELECT * from " + BooksDatabaseSQL.BOOK_TABLE_NAME;
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                bookList.add(new Book(
                        resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_ISBN),
                        resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_TITLE),
                        resultSet.getInt(BooksDatabaseSQL.BOOK_COL_NAME_EDITION_NUMBER),
                        resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_COPYRIGHT)
                ));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;

    }

    /**
     * Method to get a book by looking up ISBN
     * @param isbn
     * @return
     */
    public static Book getBookByISBN(String isbn) {
        LinkedList bookList = new LinkedList();

        try (
                Connection connection = getConnection();

        ) {
            String sqlQuery = "SELECT * from " + BooksDatabaseSQL.BOOK_TABLE_NAME +
                    "where " + BooksDatabaseSQL.BOOK_COL_NAME_ISBN + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return new Book(
                        resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_ISBN),
                        resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_TITLE),
                        resultSet.getInt(BooksDatabaseSQL.BOOK_COL_NAME_EDITION_NUMBER),
                        resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_COPYRIGHT)
                );
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Method to insert a book into the DB.
     * @param book
     * @return
     */
    public static boolean insertBook(Book book) {

        try (
                Connection connection = getConnection();

        ) {
            String sqlQuery = "INSERT INTO " + BooksDatabaseSQL.BOOK_TABLE_NAME +
                    " VALUES (?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getEditionNumber());
            preparedStatement.setString(4, book.getCopyright());
            preparedStatement.executeQuery();

            String AuthorSQL = "insert into authorisbn(authorID, isbn)" + "values(?, ?)";

            PreparedStatement preparedStatement1 = connection.prepareStatement(AuthorSQL);
            preparedStatement1.setString(2, book.getIsbn());

            for(Author author : book.getAuthorList()){
                preparedStatement1.setInt(1, author.getAuthorID());
                preparedStatement1.execute();
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Method to return get all authors in the DB.
     * @return list of authors
     */
    public static List<Author> getAllAuthors() {
        LinkedList authorList = new LinkedList();

        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
        ) {
            String sqlQuery = "SELECT * from " + AuthorDatabaseSQL.AUTHORS_TABLE_NAME;
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                authorList.add(new Author(
                        resultSet.getInt(AuthorDatabaseSQL.AUTHOR_COL_NAME_AUTHOR_ID),
                        resultSet.getString(AuthorDatabaseSQL.AUTHOR_COL_NAME_FIRST_NAME),
                        resultSet.getString(AuthorDatabaseSQL.AUTHOR_COL_NAME_LAST_NAME)
                ));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;

    }

    /**
     * Method to insert author.
     * @param author
     * @return
     */
    public static boolean insertAuthor(Author author) {

        try (
                Connection connection = getConnection();

        ) {
            String sqlQuery = "INSERT INTO " + AuthorDatabaseSQL.AUTHORS_TABLE_NAME +
                    " VALUES (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());

            preparedStatement.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Get a connection to Book DB and details in the inner class of BooksDatabaseSQL
     *
     * @return connection
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                BooksDatabaseSQL.DB_URL,
                BooksDatabaseSQL.USER,
                BooksDatabaseSQL.PASS);
    }

    /**
     * Inner class to abstract all SQL information
     */
    private class BooksDatabaseSQL {


        public static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        public static final String DB_URL = "jdbc:mariadb://localhost:3304/books";
        public static final String USER = "root";
        public static final String PASS = "OdinRaiden66";

        public static final String BOOK_TABLE_NAME = "titles";
        public static final String BOOK_COL_NAME_ISBN = "isbn";
        public static final String BOOK_COL_NAME_TITLE = "title";
        public static final String BOOK_COL_NAME_EDITION_NUMBER = "editionNumber";
        public static final String BOOK_COL_NAME_COPYRIGHT = "copyright";

    }

    private class AuthorDatabaseSQL{
        public static final String AUTHORS_TABLE_NAME = "authors";
        public static final String AUTHOR_COL_NAME_AUTHOR_ID = "authorID";
        public static final String AUTHOR_COL_NAME_FIRST_NAME = "firstName";
        public static final String AUTHOR_COL_NAME_LAST_NAME = "lastName";

    }
}


