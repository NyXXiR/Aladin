package com.project.aladin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.project.aladin.entity.Book;
import com.project.aladin.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

 final  BookRepository br;
 
 
 public Page<Book> getBookList(int page){
   Pageable pageable = PageRequest.of(page,10);
   return this.br.findAll(pageable);
   
 }
  
}
