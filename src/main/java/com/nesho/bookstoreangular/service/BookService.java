package com.nesho.bookstoreangular.service;

import com.nesho.bookstoreangular.domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findOne(Long id);

    Book save(Book book);

    List<Book> blurrysearch(String title);

    Void removeOne(Long id);

    Set<Book> blurrySearch(String title);
}
