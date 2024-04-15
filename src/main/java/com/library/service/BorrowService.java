package com.library.service;

import com.library.dto.ServerResponseDto;
import com.library.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;

    public ServerResponseDto registerBorrow(Long studentId) {
        return ServerResponseDto.SUCCESS;
    }

}

