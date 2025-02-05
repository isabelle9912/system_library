package models;

import java.util.ArrayList;
import java.util.Objects;

public class Library {
    private String name;
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public Library(String name) {
        setName(name);
        setBooks(new ArrayList<>());
        setUsers(new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Library library)) return false;
        return Objects.equals(name, library.name);
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                ", books=" + books +
                ", users=" + users +
                '}';
    }

    // Implement methods
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Livro adicionado: " + book.showDetails());
    }

    public void removeBook(Book book) {
        books.remove(book);
        System.out.println("Livro removido");
    }

    public void registerUser(User user) {
        if (users.contains(user)) {
            System.out.println("Usuário " + user.getName() + " já está registrado!");
            return;
        }
        users.add(user);
        System.out.println("Usuário " + user.getName() + " registrado com sucesso!");
    }

    public void removeUser(User user) {
        users.remove(user);
        System.out.println("Usuário " + user.getName() + " removido com sucesso!");
    }

    public void showBooks() {
        for (Book book : books) {
            System.out.println(book.showDetails());
        }
    }

    public void showBooksAvailable() {
        for (Book book : books) {
            if(book.isAvailable()){
                System.out.println(book.showDetails());
            }
        }
    }
}
