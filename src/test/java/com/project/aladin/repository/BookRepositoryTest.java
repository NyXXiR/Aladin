package com.project.aladin.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.project.aladin.entity.Book;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
@Rollback(false)
class BookRepositoryTest {
@Autowired
  BookRepository br;



@Test
  void test(){
  Book book1= Book.builder().bookName("책제목").writer("작가명").publisher("출판사명").category("카테고리").publishDate("23-03-02").price(30000).discountRate(10).savingRate(5).build();

  br.save(book1);
}

}