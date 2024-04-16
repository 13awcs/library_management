package com.library.controller.app;

import com.library.auth.entity.CustomUserDetail;
import com.library.dto.ServerResponseDto;
import com.library.dto.borrow.RegisterBorrowRequest;
import com.library.service.BookService;
import com.library.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.library.util.Constant.CLIENT_PATH;

@RestController
@CrossOrigin(origins = CLIENT_PATH)
@RequestMapping("app")
@RequiredArgsConstructor
public class AppController {

    private final BookService bookService;
    private final BorrowService borrowService;

    @GetMapping("/list-book")
    public ResponseEntity<ServerResponseDto> getListBookForApp(@RequestParam String category) {
        return ResponseEntity.ok(bookService.getListBookForApp(category));
    }

    @PostMapping("/register-borrow")
    public ResponseEntity<ServerResponseDto> registerBorrow(@RequestBody RegisterBorrowRequest request,
                                                            @AuthenticationPrincipal CustomUserDetail customUserDetail) {
        Long studentId = customUserDetail.getStudentId();
        return ResponseEntity.ok(borrowService.registerBorrow(request, studentId));
    }

    @PostMapping("/renew-borrow")
    public ResponseEntity<ServerResponseDto> renewBorrow(@RequestParam Long bookId,
                                                         @AuthenticationPrincipal CustomUserDetail customUserDetail) {
        Long studentId = customUserDetail.getStudentId();
        return ResponseEntity.ok(borrowService.renewBorrow(bookId, studentId));
    }

}
