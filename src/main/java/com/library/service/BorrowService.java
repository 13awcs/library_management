package com.library.service;

import com.library.dto.ServerResponseDto;
import com.library.dto.borrow.RegisterBorrowRequest;
import com.library.entity.book.BorrowStatus;
import com.library.entity.book.BorrowedBookEntity;
import com.library.repository.BorrowRepository;
import com.library.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookService bookService;

    public ServerResponseDto registerBorrow(RegisterBorrowRequest request, Long studentId) {

        Long bookId = request.getBookId();
        Date dueDate = request.getDueDate();
        int quantityOfBook = bookService.getQuantityByBookId(bookId);
        if (quantityOfBook > 0) {
            BorrowedBookEntity borrowedBook = new BorrowedBookEntity();
            borrowedBook.setBookId(bookId);
            borrowedBook.setStudentId(studentId);
            borrowedBook.setAcceptedByLibrarian(false);
            borrowedBook.setBorrowDate(new Date());
            borrowedBook.setDueDate(dueDate);
            borrowedBook.setRenewTime(0);
            borrowedBook.setBorrowStatus(BorrowStatus.TEMP);
            borrowRepository.save(borrowedBook);
            return ServerResponseDto.SUCCESS;
        }
        return ServerResponseDto.ERROR;
    }

    public ServerResponseDto renewBorrow(Long bookId, Long studentId) {

        BorrowedBookEntity borrowedBookFromDb = borrowRepository.findByBookIdAndStudentId(bookId, studentId);
        if (borrowedBookFromDb != null) {
            borrowedBookFromDb.setBorrowStatus(BorrowStatus.RE_NEW);
            borrowedBookFromDb.setDueDate(DateUtils.plusDayFromDate(7, borrowedBookFromDb.getDueDate()));
            borrowedBookFromDb.setUpdatedTime(new Date());
            borrowRepository.save(borrowedBookFromDb);
            return ServerResponseDto.SUCCESS;
        }
        return ServerResponseDto.ERROR;
    }

}

