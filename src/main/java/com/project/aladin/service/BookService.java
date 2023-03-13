package com.project.aladin.service;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

 @Transactional
  public Page<Book> getSearchList(int page, String keyword){
   Pageable pageable = PageRequest.of(page,10);
   Page<Book> searchList = br.findByBookNameContaining(keyword, pageable);

   return searchList;
 }

 @Transactional
 public Page<Book> getCategorized(Page<Book> searchList, String category, int page){
  Pageable pageable = PageRequest.of(page,10);
  List<Book> categorized= searchList.filter(data->data.getCategory().equals(category)).stream().toList();
  int start= (int) pageable.getOffset();
  int end= Math.min((start+pageable.getPageSize()),categorized.size());

  Page<Book> categorizedList= new PageImpl<>(categorized.subList(start, end),pageable,categorized.size());
  return categorizedList;
 }


}
