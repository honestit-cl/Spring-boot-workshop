package pl.coderslab.springbootworkshop.books;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank private String title;

  @Size(min = 20, max = 600)
  private String description;

  @NotBlank private String author;
  @Positive private Integer pages;
}
