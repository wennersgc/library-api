package com.wennersanner.libraryapi.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String customer;

    @Column (name = "customer_email")
    private String customerEmail;

    @JoinColumn (name = "id_book")
    @ManyToOne
    private Book book;

    @Column
    private LocalDate loanDate;

    private Boolean returned;


}
