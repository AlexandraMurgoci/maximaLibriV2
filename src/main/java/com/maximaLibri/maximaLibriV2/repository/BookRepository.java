package com.maximaLibri.maximaLibriV2.repository;

import com.maximaLibri.maximaLibriV2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    //List<Book> findTopByTitle10OrderByTitle(String title);
}
