package com.library.entity.book;

import com.library.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="student")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private Date publishedDate;
    private String category;
    private int quantity;

}
