package com.project.aladin.controller;

import com.project.aladin.entity.Book;
import com.project.aladin.entity.Cart;
import com.project.aladin.entity.Purchase;
import com.project.aladin.entity.Review;
import com.project.aladin.repository.BookRepository;
import com.project.aladin.repository.CartRepository;
import com.project.aladin.repository.CommentRepository;
import com.project.aladin.repository.EventRepository;
import com.project.aladin.repository.MemberRepository;
import com.project.aladin.repository.PurchaseRepository;
import com.project.aladin.repository.ReviewRepository;
import com.project.aladin.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
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
  public String deleteAction(@PathVariable long cartId) {
    cr.deleteById(cartId);
    return "redirect:/page/cartList";
  }

  @GetMapping("/function/quantityManager/{quantity}")
  public String quantityManager(@PathVariable int quantity, long cartSeq) {
    Cart selectedCart = cr.findById(cartSeq).get();
    int currentQuantity = selectedCart.getQuantity();
    selectedCart.setQuantity(currentQuantity + quantity);
    cr.saveAndFlush(selectedCart);
    return "redirect:/page/cartList";
  }

  @GetMapping("/function/insertReview")
  public String insertReview(HttpServletRequest request, double star, String content, Long bookSeq,
      HttpSession session) {
    String referer = request.getHeader("referer");
    Long memberSeq = (Long) session.getAttribute("memberSeq");
    Review review = new Review();
    review.setBook(br.findById(bookSeq).get());
    review.setMember(mr.findById(memberSeq).get());
    review.setStar(star);
    review.setContent(content);
    rr.saveAndFlush(review);
    return "redirect:" + referer;
  }

  @GetMapping("/function/buyAction")
  public String buyAction(long[] cartSeq) {
    log.info(cartSeq);

    for (int i = 0; i < cartSeq.length; i++) {
     log.info(cr.findById(cartSeq[i])) ;
//purchase insert action
      Cart cartInfo = cr.findById(cartSeq[i]).get();
      Purchase purchaseInfo = new Purchase();
      purchaseInfo.setBook(cartInfo.getBook());
      purchaseInfo.setQuantity(cartInfo.getQuantity());
      purchaseInfo.setMember(cartInfo.getMember());
      pr.saveAndFlush(purchaseInfo);

//cart delete action
      cr.deleteById(cartSeq[i]);

    }
    return "page/buyActionConfirmed";
  }

  @GetMapping("/function/insertBook")
  public String insertBook(String bookName, int price, String publishDate, String publisher, String writer, int discountRate, int mileageRate, int shipPrice, String category){
DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
LocalDate date= LocalDate.parse(publishDate, formatter);

    Book bookInfo = new Book();
//필수입력사항
bookInfo.setBookName(bookName);
bookInfo.setPrice(price);
bookInfo.setCategory(category);
bookInfo.setPublishDate(date);
bookInfo.setPublisher(publisher);
bookInfo.setWriter(writer);

//추가입력사항
bookInfo.setDiscountRate(discountRate);
bookInfo.setMileageRate(mileageRate);;
bookInfo.setShipPrice(shipPrice);

br.saveAndFlush(bookInfo);
    return "page/myPage";
  };

}
