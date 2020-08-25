package com.wennersanner.libraryapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (name = "book")
public class Book {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;

    public Book(Long id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Book() {
    }

    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Book)) return false;
        final Book other = (Book) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$author = this.getAuthor();
        final Object other$author = other.getAuthor();
        if (this$author == null ? other$author != null : !this$author.equals(other$author)) return false;
        final Object this$isbn = this.getIsbn();
        final Object other$isbn = other.getIsbn();
        if (this$isbn == null ? other$isbn != null : !this$isbn.equals(other$isbn)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Book;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $author = this.getAuthor();
        result = result * PRIME + ($author == null ? 43 : $author.hashCode());
        final Object $isbn = this.getIsbn();
        result = result * PRIME + ($isbn == null ? 43 : $isbn.hashCode());
        return result;
    }

    public String toString() {
        return "Book(id=" + this.getId() + ", title=" + this.getTitle() + ", author=" + this.getAuthor() + ", isbn=" + this.getIsbn() + ")";
    }

    public static class BookBuilder {
        private Long id;
        private String title;
        private String author;
        private String isbn;

        BookBuilder() {
        }

        public Book.BookBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Book.BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public Book.BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public Book.BookBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Book build() {
            return new Book(id, title, author, isbn);
        }

        public String toString() {
            return "Book.BookBuilder(id=" + this.id + ", title=" + this.title + ", author=" + this.author + ", isbn=" + this.isbn + ")";
        }
    }
}
