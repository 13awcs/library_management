package com.library.repository;

import com.library.entity.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByCategoryAndIsDeletedFalse(String category);
}
