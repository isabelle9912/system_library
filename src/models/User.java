package models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private int id;
    private String name;
    final List<Book> booksLoaned;

    public User(int id, String name) {
        setId(id);
        setName(name);
        this.booksLoaned = new ArrayList<>(); // Inicializa uma lista vazia
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooksLoaned() {
        return booksLoaned;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return id == user.id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", booksLoaned=" + booksLoaned +
                '}';
    }

    // Implementação de métodos

    public void takeBorrowed(Book book) {
        if (booksLoaned.size() >= 3) {
            System.out.println("Usuário já atingiu o limite de 3 livros emprestados.");
            return;
        }
        if(book.isAvailable()) {
            book.loanBook();
            booksLoaned.add(book);
        } else {
            System.out.println("O livro '" + book + "' não está disponível para empréstimo.");
        }
    }

    public void returnBorrowed(Book book) {
        if (!booksLoaned.contains(book)) {
            System.out.println("O livro não pertence a este usuário.");
            return;
        }
        book.returnBook();
        booksLoaned.remove(book);
    }
}
