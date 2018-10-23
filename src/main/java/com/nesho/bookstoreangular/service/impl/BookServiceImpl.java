package com.nesho.bookstoreangular.service.impl;

import com.nesho.bookstoreangular.domain.Book;
import com.nesho.bookstoreangular.repository.BookRepository;
import com.nesho.bookstoreangular.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        List activeBook = new ArrayList();

        for (Book book : books) {
            if (book.isActive()) {
                activeBook.add(book);
            }
        }
        return activeBook;
    }

    @Override
    public Optional<Book> findOne(Long id) {
        return (bookRepository.findById(id));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> blurrysearch(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        List activeBookbytitle = new ArrayList();

        for (Book book : books) {
            if (book.isActive()) {
                activeBookbytitle.add(book);
            }
        }
        return activeBookbytitle;
    }

    @Override
    public Void removeOne(Long id) {
        bookRepository.deleteById(id);
        return null;
    }

    @Override
    public Set<Book> blurrySearch(String keyword) {
        Set<Book> bookList = bookRepository.findByTitleContaining(keyword);

        Set<Book> activeBookList = new HashSet<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                activeBookList.add(book);
            }
        }

        return activeBookList;

    }
}
