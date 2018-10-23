package com.nesho.bookstoreangular.domain;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Book implements Serializable {
    private static final Long serialVersionUID = 1234525l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;
    private String author;
    private String publisher;
    private String publicationDate;
    private String language;
    private String category;
    private int numberOfPages;
    private String format;
    private String isbn;
    private double shippingWeight;
    private double listPrice;
    private double ourPrice;
    private boolean active;
    @Column(columnDefinition = "text")
    private String description;
    private int inStockNumber;
    @Transient
    private MultipartFile bookImage;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getShippingWeight() {
        return shippingWeight;
    }

    public void setShippingWeight(double shippingWeight) {
        this.shippingWeight = shippingWeight;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(double ourPrice) {
        this.ourPrice = ourPrice;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInStockNumber() {
        return inStockNumber;
    }

    public void setInStockNumber(int inStockNumber) {
        this.inStockNumber = inStockNumber;
    }

    public MultipartFile getBookImage() {
        return bookImage;
    }

    public void setBookImage(MultipartFile bookImage) {
        this.bookImage = bookImage;
    }
}
