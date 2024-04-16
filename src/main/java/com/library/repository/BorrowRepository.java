package com.library.repository;

import com.library.entity.book.BorrowStatus;
import com.library.entity.book.BorrowedBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BorrowRepository extends JpaRepository<BorrowedBookEntity, Long> {

    @Query(value = "select br from BorrowedBookEntity br " +
            "where br.borrowStatus in ?1 ")
    BorrowedBookEntity findByListStatus(List<BorrowStatus> listStatus);

    @Query(value = "select br from BorrowedBookEntity br " +
            "where br.bookId = ?1 and br.studentId = ?2 and br.borrowStatus = 'NOT_YET' ")
    BorrowedBookEntity findByBookIdAndStudentId(Long bookId, Long studentId);

}
