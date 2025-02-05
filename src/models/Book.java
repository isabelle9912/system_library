package models;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int yearPublication;
    private boolean available;

    public Book(String title, String author, int yearPublication) {
        setTitle(title);
        setAuthor(author);
        setYearPublication(yearPublication);
        setAvaliable(true);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvaliable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return yearPublication == book.yearPublication &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    public String showDetails() {
        return "Título: " + getTitle() + " | Autor: " + getAuthor() + " | Ano: " + getYearPublication() + " | Disponível: " + (isAvailable() ? "Sim" : "Não");
    }

    // Métodos de empréstimo e devolução
    public void loanBook() {
        if (!available) {
            System.out.println("O livro já está emprestado.");
            return;
        }
        this.available = false;
        System.out.println("Livro emprestado: " + showDetails());
    }

    public void returnBook() {
        if (available) {
            System.out.println("O livro já está disponível na biblioteca.");
            return;
        }
        this.available = true;
        System.out.println("Livro devolvido: " + showDetails());
    }
}
