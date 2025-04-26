import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<User> users;
    private Admin admin;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.admin = new Admin();
    }

    public void addBook(Book book) {
        admin.addBook(book);
        books.add(book);
    }

    public void removeBook(Book book) {
        admin.removeBook(book);
        books.remove(book);
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user.getName());
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public void displayAvailableBooks() {
        System.out.println("\nAvailable Books:");
        for (Book book : getAvailableBooks()) {
            System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
        }
    }
}