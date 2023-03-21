package com.project.aladin.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.project.aladin.entity.Book;
import jakarta.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import net.bytebuddy.asm.Advice.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
@Rollback(false)
class BookRepositoryTest {
@Autowired
  BookRepository br;



@Test
  void test() {

  DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
  LocalDate ld= LocalDate.parse("2022-10-28");
  LocalDateTime ldt= LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());

  System.out.println(ldt);

  for (int i = 0; i < 10; i++) {
    Book book1 = Book.builder().bookName("책제목").writer("작가명").publisher("출판사명").category("카테고리3")
        .publishDate(ld).price(20000).build();

    br.save(book1);
  }
}
}

