import java.util.*;

class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean available;

    Book(int id, String t, String a) {
        bookId = id;
        title = t;
        author = a;
        available = true;
    }

    int getBookId() { return bookId; }
    String getTitle() { return title; }
    String getAuthor() { return author; }
    boolean isAvailable() { return available; }

    void setAvailable(boolean val) {
        available = val;
    }

    void show() {
        System.out.println(bookId + " " + title + " " + author + " " + (available ? "Yes" : "No"));
    }
}

class Member {
    protected int memberId;
    protected String name;

    Member(int id, String n) {
        memberId = id;
        name = n;
    }

    int getMemberId() {
        return memberId;
    }
}

class StudentMember extends Member {
    StudentMember(int id, String n) {
        super(id, n);
    }
}

class Library {

    ArrayList<Book> books = new ArrayList<>();
    HashMap<Integer, ArrayList<Book>> issued = new HashMap<>();

   void addBook(Book b) {
        books.add(b);
    }

   void displayBooks() {
        for (Book b : books) {
            b.show();
        }
    }

   void issueBook(int memberId, int bookId) {

        for (Book b : books) {
            if (b.getBookId() == bookId && b.isAvailable()) {

                b.setAvailable(false);

                if (!issued.containsKey(memberId)) {
                    issued.put(memberId, new ArrayList<>());
                }

                issued.get(memberId).add(b);
                System.out.println("Book issued");
                return;
            }
        }

        System.out.println("Not available");
    }

   void returnBook(int memberId, int bookId) {

        if (issued.containsKey(memberId)) {

            ArrayList<Book> list = issued.get(memberId);

            for (Book b : list) {
                if (b.getBookId() == bookId) {
                    b.setAvailable(true);
                    list.remove(b);
                    System.out.println("Returned");
                    return;
                }
            }
        }

        System.out.println("Not found");
    }

   void search(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                b.show();
            }
        }
    }

    void search(String author, int dummy) {
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                b.show();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Library lib = new Library();

        Member m1 = new StudentMember(1, "Pranav");

        lib.addBook(new Book(1, "Java", "Gosling"));
        lib.addBook(new Book(2, "DSA", "Mark"));
        lib.addBook(new Book(3, "OS", "Galvin"));

        System.out.println("Books:");
        lib.displayBooks();

        lib.issueBook(m1.getMemberId(), 1);

        System.out.println("After issue:");
        lib.displayBooks();

        System.out.println("Search:");
        lib.search("Java");

        lib.returnBook(m1.getMemberId(), 1);

        System.out.println("Final:");
        lib.displayBooks();
    }
}