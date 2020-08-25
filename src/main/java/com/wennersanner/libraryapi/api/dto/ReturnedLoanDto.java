package com.wennersanner.libraryapi.api.dto;

public class ReturnedLoanDto {

    private Boolean returned;

    public ReturnedLoanDto(Boolean returned) {
        this.returned = returned;
    }

    public ReturnedLoanDto() {
    }

    public static ReturnedLoanDtoBuilder builder() {
        return new ReturnedLoanDtoBuilder();
    }

    public Boolean getReturned() {
        return this.returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ReturnedLoanDto)) return false;
        final ReturnedLoanDto other = (ReturnedLoanDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$returned = this.getReturned();
        final Object other$returned = other.getReturned();
        if (this$returned == null ? other$returned != null : !this$returned.equals(other$returned)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ReturnedLoanDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $returned = this.getReturned();
        result = result * PRIME + ($returned == null ? 43 : $returned.hashCode());
        return result;
    }

    public String toString() {
        return "ReturnedLoanDto(returned=" + this.getReturned() + ")";
    }

    public static class ReturnedLoanDtoBuilder {
        private Boolean returned;

        ReturnedLoanDtoBuilder() {
        }

        public ReturnedLoanDto.ReturnedLoanDtoBuilder returned(Boolean returned) {
            this.returned = returned;
            return this;
        }

        public ReturnedLoanDto build() {
            return new ReturnedLoanDto(returned);
        }

        public String toString() {
            return "ReturnedLoanDto.ReturnedLoanDtoBuilder(returned=" + this.returned + ")";
        }
    }
}
