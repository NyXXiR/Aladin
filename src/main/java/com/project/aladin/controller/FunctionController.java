package com.project.aladin.controller;

import com.project.aladin.entity.Cart;
import com.project.aladin.repository.BookRepository;
import com.project.aladin.repository.CartRepository;
import com.project.aladin.repository.CommentRepository;
import com.project.aladin.repository.EventRepository;
import com.project.aladin.repository.MemberRepository;
import com.project.aladin.repository.PurchaseRepository;
import com.project.aladin.repository.ReviewRepository;
import com.project.aladin.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Log4j2
public class FunctionController {
  final BookRepository br;
  final CommentRepository cr2;
  final EventRepository er;
  final MemberRepository mr;
  final PurchaseRepository pr;
  final BookService bs;
  final CartRepository cr;
  final ReviewRepository rr;

  @GetMapping("/function/deleteFromCart/{cartId}")
  public String deleteAction(@PathVariable long cartId){
    cr.deleteById(cartId);
    return "redirect:/page/cartList";
  }

  @GetMapping("/function/quantityManager/{quantity}")
  public String quantityManager(@PathVariable int quantity, long cartSeq){
    Cart selectedCart= cr.findById(cartSeq).get();
    int currentQuantity= selectedCart.getQuantity();
    selectedCart.setQuantity(currentQuantity+quantity);
    cr.saveAndFlush(selectedCart);
    return "redirect:/page/cartList";
  }




}
