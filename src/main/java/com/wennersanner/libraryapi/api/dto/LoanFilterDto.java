package com.wennersanner.libraryapi.api.dto;

public class LoanFilterDto {

    private String isbn;
    private String customer;

    public LoanFilterDto() {
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getCustomer() {
        return this.customer;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof LoanFilterDto)) return false;
        final LoanFilterDto other = (LoanFilterDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$isbn = this.getIsbn();
        final Object other$isbn = other.getIsbn();
        if (this$isbn == null ? other$isbn != null : !this$isbn.equals(other$isbn)) return false;
        final Object this$customer = this.getCustomer();
        final Object other$customer = other.getCustomer();
        if (this$customer == null ? other$customer != null : !this$customer.equals(other$customer)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LoanFilterDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $isbn = this.getIsbn();
        result = result * PRIME + ($isbn == null ? 43 : $isbn.hashCode());
        final Object $customer = this.getCustomer();
        result = result * PRIME + ($customer == null ? 43 : $customer.hashCode());
        return result;
    }

    public String toString() {
        return "LoanFilterDto(isbn=" + this.getIsbn() + ", customer=" + this.getCustomer() + ")";
    }
}
