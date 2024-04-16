package com.library.repository;

import com.library.entity.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByCategoryAndIsDeletedFalse(String category);

    @Query(value = "select b.quantity from BookEntity b where b.id = ?1 and b.isDeleted = false ")
    int getQuantityByBookId(Long bookId);
}
