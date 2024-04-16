package com.library.entity.book;

import com.library.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="borrowed_book")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BorrowedBookEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Long employeeId;
    private Long bookId;
    private Date borrowDate;
    private Date dueDate;
    private int renewTime;

    @Enumerated(EnumType.STRING)
    private BorrowStatus borrowStatus;

    private boolean isAcceptedByLibrarian;

}
