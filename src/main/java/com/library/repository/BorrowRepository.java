package com.library.repository;

import com.library.entity.book.BorrowedBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<BorrowedBookEntity, Long> {
}
