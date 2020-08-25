package com.wennersanner.libraryapi.api.dto;

public class LoanDTO {

    private Long id;
    private String isbn;
    private String customer;
    private BookDTO book;

    public LoanDTO(Long id, String isbn, String customer, BookDTO book) {
        this.id = id;
        this.isbn = isbn;
        this.customer = customer;
        this.book = book;
    }

    public LoanDTO() {
    }

    public static LoanDTOBuilder builder() {
        return new LoanDTOBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getCustomer() {
        return this.customer;
    }

    public BookDTO getBook() {
        return this.book;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof LoanDTO)) return false;
        final LoanDTO other = (LoanDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$isbn = this.getIsbn();
        final Object other$isbn = other.getIsbn();
        if (this$isbn == null ? other$isbn != null : !this$isbn.equals(other$isbn)) return false;
        final Object this$customer = this.getCustomer();
        final Object other$customer = other.getCustomer();
        if (this$customer == null ? other$customer != null : !this$customer.equals(other$customer)) return false;
        final Object this$book = this.getBook();
        final Object other$book = other.getBook();
        if (this$book == null ? other$book != null : !this$book.equals(other$book)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LoanDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $isbn = this.getIsbn();
        result = result * PRIME + ($isbn == null ? 43 : $isbn.hashCode());
        final Object $customer = this.getCustomer();
        result = result * PRIME + ($customer == null ? 43 : $customer.hashCode());
        final Object $book = this.getBook();
        result = result * PRIME + ($book == null ? 43 : $book.hashCode());
        return result;
    }

    public String toString() {
        return "LoanDTO(id=" + this.getId() + ", isbn=" + this.getIsbn() + ", customer=" + this.getCustomer() + ", book=" + this.getBook() + ")";
    }

    public static class LoanDTOBuilder {
        private Long id;
        private String isbn;
        private String customer;
        private BookDTO book;

        LoanDTOBuilder() {
        }

        public LoanDTO.LoanDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LoanDTO.LoanDTOBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public LoanDTO.LoanDTOBuilder customer(String customer) {
            this.customer = customer;
            return this;
        }

        public LoanDTO.LoanDTOBuilder book(BookDTO book) {
            this.book = book;
            return this;
        }

        public LoanDTO build() {
            return new LoanDTO(id, isbn, customer, book);
        }

        public String toString() {
            return "LoanDTO.LoanDTOBuilder(id=" + this.id + ", isbn=" + this.isbn + ", customer=" + this.customer + ", book=" + this.book + ")";
        }
    }
}
