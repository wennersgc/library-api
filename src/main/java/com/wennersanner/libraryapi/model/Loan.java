package com.wennersanner.libraryapi.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String customer;

    @JoinColumn (name = "id_book")
    @ManyToOne
    private Book book;

    @Column
    private LocalDate loanDate;

    private Boolean returned;

    public Loan(Long id, String customer, Book book, LocalDate loanDate, Boolean returned) {
        this.id = id;
        this.customer = customer;
        this.book = book;
        this.loanDate = loanDate;
        this.returned = returned;
    }

    public Loan() {
    }

    public static LoanBuilder builder() {
        return new LoanBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getCustomer() {
        return this.customer;
    }

    public Book getBook() {
        return this.book;
    }

    public LocalDate getLoanDate() {
        return this.loanDate;
    }

    public Boolean getReturned() {
        return this.returned;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Loan)) return false;
        final Loan other = (Loan) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$customer = this.getCustomer();
        final Object other$customer = other.getCustomer();
        if (this$customer == null ? other$customer != null : !this$customer.equals(other$customer)) return false;
        final Object this$book = this.getBook();
        final Object other$book = other.getBook();
        if (this$book == null ? other$book != null : !this$book.equals(other$book)) return false;
        final Object this$loanDate = this.getLoanDate();
        final Object other$loanDate = other.getLoanDate();
        if (this$loanDate == null ? other$loanDate != null : !this$loanDate.equals(other$loanDate)) return false;
        final Object this$returned = this.getReturned();
        final Object other$returned = other.getReturned();
        if (this$returned == null ? other$returned != null : !this$returned.equals(other$returned)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Loan;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $customer = this.getCustomer();
        result = result * PRIME + ($customer == null ? 43 : $customer.hashCode());
        final Object $book = this.getBook();
        result = result * PRIME + ($book == null ? 43 : $book.hashCode());
        final Object $loanDate = this.getLoanDate();
        result = result * PRIME + ($loanDate == null ? 43 : $loanDate.hashCode());
        final Object $returned = this.getReturned();
        result = result * PRIME + ($returned == null ? 43 : $returned.hashCode());
        return result;
    }

    public String toString() {
        return "Loan(id=" + this.getId() + ", customer=" + this.getCustomer() + ", book=" + this.getBook() + ", loanDate=" + this.getLoanDate() + ", returned=" + this.getReturned() + ")";
    }

    public static class LoanBuilder {
        private Long id;
        private String customer;
        private Book book;
        private LocalDate loanDate;
        private Boolean returned;

        LoanBuilder() {
        }

        public Loan.LoanBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Loan.LoanBuilder customer(String customer) {
            this.customer = customer;
            return this;
        }

        public Loan.LoanBuilder book(Book book) {
            this.book = book;
            return this;
        }

        public Loan.LoanBuilder loanDate(LocalDate loanDate) {
            this.loanDate = loanDate;
            return this;
        }

        public Loan.LoanBuilder returned(Boolean returned) {
            this.returned = returned;
            return this;
        }

        public Loan build() {
            return new Loan(id, customer, book, loanDate, returned);
        }

        public String toString() {
            return "Loan.LoanBuilder(id=" + this.id + ", customer=" + this.customer + ", book=" + this.book + ", loanDate=" + this.loanDate + ", returned=" + this.returned + ")";
        }
    }
}
