package com.example.library.Entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vote")
@EqualsAndHashCode(of = "id")
@Getter@Setter
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Votes {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String value;

    @Column(name = "book_id")
    private Date bookId;

    private String username;


}
