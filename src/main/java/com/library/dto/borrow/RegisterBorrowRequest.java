package com.library.dto.borrow;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterBorrowRequest {
    private Long bookId;
    private Date dueDate;
}
