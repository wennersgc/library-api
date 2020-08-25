package com.wennersanner.libraryapi.api.dto;


import javax.validation.constraints.NotEmpty;


public class BookDTO {

    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotEmpty
    private String isbn;

    public BookDTO(Long id, @NotEmpty String title, @NotEmpty String author, @NotEmpty String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public BookDTO() {
    }

    public static BookDTOBuilder builder() {
        return new BookDTOBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public @NotEmpty String getTitle() {
        return this.title;
    }

    public @NotEmpty String getAuthor() {
        return this.author;
    }

    public @NotEmpty String getIsbn() {
        return this.isbn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(@NotEmpty String title) {
        this.title = title;
    }

    public void setAuthor(@NotEmpty String author) {
        this.author = author;
    }

    public void setIsbn(@NotEmpty String isbn) {
        this.isbn = isbn;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BookDTO)) return false;
        final BookDTO other = (BookDTO) o;
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
        return other instanceof BookDTO;
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
        return "BookDTO(id=" + this.getId() + ", title=" + this.getTitle() + ", author=" + this.getAuthor() + ", isbn=" + this.getIsbn() + ")";
    }

    public static class BookDTOBuilder {
        private Long id;
        private @NotEmpty String title;
        private @NotEmpty String author;
        private @NotEmpty String isbn;

        BookDTOBuilder() {
        }

        public BookDTO.BookDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BookDTO.BookDTOBuilder title(@NotEmpty String title) {
            this.title = title;
            return this;
        }

        public BookDTO.BookDTOBuilder author(@NotEmpty String author) {
            this.author = author;
            return this;
        }

        public BookDTO.BookDTOBuilder isbn(@NotEmpty String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookDTO build() {
            return new BookDTO(id, title, author, isbn);
        }

        public String toString() {
            return "BookDTO.BookDTOBuilder(id=" + this.id + ", title=" + this.title + ", author=" + this.author + ", isbn=" + this.isbn + ")";
        }
    }
}
