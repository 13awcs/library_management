package com.library.controller.app;

import com.library.dto.ServerResponseDto;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.library.util.Constant.CLIENT_PATH;

@RestController
@CrossOrigin(origins = CLIENT_PATH)
@RequestMapping("app")
@RequiredArgsConstructor
public class AppController {

    private final BookService bookService;

    @GetMapping("/list-book")
    public ResponseEntity<ServerResponseDto> getListBookForApp(String category) {
        return ResponseEntity.ok(bookService.getListBookForApp(category));
    }

}
