package com.library.service;

import com.library.dto.ServerResponseDto;
import com.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public ServerResponseDto getListBookForApp(String category) {
        return ServerResponseDto.success(bookRepository.findByCategoryAndIsDeletedFalse(category));
    }

}
