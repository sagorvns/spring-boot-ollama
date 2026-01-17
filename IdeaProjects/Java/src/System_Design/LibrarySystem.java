package System_Design;

import java.util.*;

class Book {
    String title, author;
    public Book(String title, String author) {
        this.title = title; this.author = author;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
    }

    public Book search(String title) {
        for (Book book : books)
            if
            (book.title.equals(title))
                return book;
        return null;
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Library lib = new Library();
        lib.addBook("Java Basics", "John");
        System.out.println(lib.search("Java Basics").author); // John
    }
}

