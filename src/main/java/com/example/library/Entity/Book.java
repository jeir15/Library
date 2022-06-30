package com.example.library.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;


@Entity
@Table(name = "book")
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Book {

    public Book(){

    }

    public Book(Long id, String name, int pageCount, String uniqueNumber, Genre genre, Author author, Publisher publisher, int publishYear, byte[] image, String decription, long viewCount, long totalVoteCount, long totalRating, int avgRating) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
        this.uniqueNumber = uniqueNumber;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.image = image;
        this.decription = decription;
        this.viewCount = viewCount;
        this.totalVoteCount = totalVoteCount;
        this.totalRating = totalRating;
        this.avgRating = avgRating;
    }

    public Book(Long id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @Lob
    @Column(updatable = false)
    private byte[] content;

    @Column(name = "page_count")
    private int pageCount;

    @Column(name = "isbn")
    private String uniqueNumber;

    @ManyToOne
    @JoinColumn
    private Genre genre;
    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToOne
    @JoinColumn
    private Publisher publisher;

    @Column(name = "publish_year")
    private int publishYear;

    private byte[] image;

    private String decription;

    @Column(name = "view_count")
    private long viewCount;

    @Column(name = "total_vote_count")
    private long totalVoteCount;

    @Column(name = "total_rating")
    private long totalRating;

    @Column(name = "avg_rating")
    private int avgRating;


    @Override
    public String toString() {
        return name;
    }
}
