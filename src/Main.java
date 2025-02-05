import models.Book;
import models.User;
import models.Library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library("Minha Biblioteca");

        while (true) {
            System.out.println("\n===== MENU BIBLIOTECA =====");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Listar Livros Disponíveis");
            System.out.println("3. Registrar Usuário");
            System.out.println("4. Emprestar Livro");
            System.out.println("5. Devolver Livro");
            System.out.println("6. Remover Usuário");
            System.out.println("7. Remover Livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer do scanner

            switch (choice) {
                case 1 -> addBook(library, scanner);
                case 2 -> library.showBooks();
                case 3 -> registerUser(library, scanner);
                case 4 -> borrowBook(library, scanner);
                case 5 -> returnBook(library, scanner);
                case 6 -> removeUser(library, scanner);
                case 7 -> removeBook(library, scanner);
                case 0 -> {
                    System.out.println("Saindo... Obrigado por usar a biblioteca!");
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void addBook(Library library, Scanner scanner) {
        System.out.print("Título do livro: ");
        String title = scanner.nextLine();
        System.out.print("Autor do livro: ");
        String author = scanner.nextLine();
        System.out.print("Ano de publicação: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Book book = new Book(title, author, year);
        library.addBook(book);
        System.out.println("Livro adicionado com sucesso!");
    }

    private static void registerUser(Library library, Scanner scanner) {
        System.out.print("Nome do usuário: ");
        String name = scanner.nextLine();
        System.out.print("ID do usuário: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        User user = new User(id, name);
        library.registerUser(user);
    }

    private static void borrowBook(Library library, Scanner scanner) {
        System.out.print("ID do usuário: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User user = library.getUsers().stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
        if (user == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.print("Título do livro a emprestar: ");
        String title = scanner.nextLine();

        Book book = library.getBooks().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title) && b.isAvailable())
                .findFirst()
                .orElse(null);

        if (book == null) {
            System.out.println("Livro não encontrado ou já emprestado!");
            return;
        }

        user.takeBorrowed(book);
        System.out.println("Livro emprestado com sucesso!");
    }

    private static void returnBook(Library library, Scanner scanner) {
        System.out.print("ID do usuário: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User user = library.getUsers().stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
        if (user == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.print("Título do livro a devolver: ");
        String title = scanner.nextLine();

        Book book = user.getBooksLoaned().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

        if (book == null) {
            System.out.println("Livro não encontrado na lista de empréstimos!");
            return;
        }

        user.returnBorrowed(book);
        System.out.println("Livro devolvido com sucesso!");
    }

    private static void removeUser(Library library, Scanner scanner) {
        System.out.print("ID do usuário a remover: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User user = library.getUsers().stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
        if (user == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        library.removeUser(user);
        System.out.println("Usuário removido com sucesso!");
    }

    private static void removeBook(Library library, Scanner scanner) {
        System.out.print("Título do livro a remover: ");
        String title = scanner.nextLine();

        Book book = library.getBooks().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

        if (book == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        library.removeBook(book);
        System.out.println("Livro removido com sucesso!");

    }


    // Para testar Rapidamente
    /*
    public static void main(String[] args) {
        Library biblioteca = new Library("Biblioteca Central");

        // Criando livros
        Book livro1 = new Book("Dom Quixote", "Miguel de Cervantes", 1605);
        Book livro2 = new Book("1984", "George Orwell", 1949);
        Book livro3 = new Book("O Senhor dos Anéis", "J.R.R. Tolkien", 1954);
        Book livro4 = new Book("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943);

        // Adicionando livros à biblioteca
        biblioteca.addBook(livro1);
        biblioteca.addBook(livro2);
        biblioteca.addBook(livro3);
        biblioteca.addBook(livro4);

        // Criando usuários
        User usuario1 = new User(1,"Alice");
        User usuario2 = new User(2,"Bob");

        // Registrando usuários
        biblioteca.registerUser(usuario1);
        biblioteca.registerUser(usuario2);

        // Listando todos livros disponíveis
        biblioteca.showBooks();

        // Usuário pega emprestado livros
        usuario1.takeBorrowed(livro1);
        usuario1.takeBorrowed(livro2);
        usuario1.takeBorrowed(livro3);
        usuario1.takeBorrowed(livro4); // Deve falhar pois já pegou 3

        // Listando livros disponíveis após o empréstimo
        biblioteca.showBooksAvailable();

        // Exibindo livros emprestados pelo usuário
        usuario1.getBooksLoaned();

        // Devolvendo um livro
        usuario1.returnBorrowed(livro1);

        // Listando livros disponíveis novamente
        biblioteca.showBooksAvailable();
    }

 */
}