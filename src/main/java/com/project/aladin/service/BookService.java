package com.project.aladin.service;

import com.project.aladin.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  final BookRepository br;

}
