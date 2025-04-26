import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        
        
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addUser(new User("Alice"));

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Display Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Admin Menu");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                    
                case 2:
                    System.out.print("Enter your name: ");
                    String userName = scanner.nextLine();
                    User user = new User(userName);
                    
                    System.out.print("Enter book title to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    
                    for (Book book : library.getAvailableBooks()) {
                        if (book.getTitle().equalsIgnoreCase(borrowTitle)) {
                            user.borrowBook(book);
                            break;
                        }
                    }
                    break;
                    
                case 3:
                    System.out.print("Enter your name: ");
                    String returnUserName = scanner.nextLine();
                    User returnUser = new User(returnUserName);
                    
                    System.out.print("Enter book title to return: ");
                    String returnTitle = scanner.nextLine();
                    
                    
                    for (Book book : library.getAvailableBooks()) {
                        if (book.getTitle().equalsIgnoreCase(returnTitle)) {
                            returnUser.returnBook(book);
                            break;
                        }
                    }
                    break;
                    
                case 4:
                    adminMenu(library, scanner);
                    break;
                    
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void adminMenu(Library library, Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Add a Book");
            System.out.println("2. Remove a Book");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int adminChoice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (adminChoice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(title, author));
                    break;
                    
                case 2:
                    System.out.print("Enter book title to remove: ");
                    String removeTitle = scanner.nextLine();
                    for (Book book : library.getAvailableBooks()) {
                        if (book.getTitle().equalsIgnoreCase(removeTitle)) {
                            library.removeBook(book);
                            break;
                        }
                    }
                    break;
                    
                case 3:
                    return;
                    
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}