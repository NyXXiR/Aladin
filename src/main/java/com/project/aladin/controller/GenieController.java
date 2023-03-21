package com.project.aladin.controller;

import com.project.aladin.entity.Book;
import com.project.aladin.entity.Cart;
import com.project.aladin.entity.Member;
import com.project.aladin.entity.Review;
import com.project.aladin.repository.BookRepository;
import com.project.aladin.repository.CartRepository;
import com.project.aladin.repository.CommentRepository;
import com.project.aladin.repository.EventRepository;
import com.project.aladin.repository.MemberRepository;
import com.project.aladin.repository.PurchaseRepository;
import com.project.aladin.service.BookService;
import com.project.aladin.repository.ReviewRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Log4j2
public class GenieController {

  final BookRepository br;
  final CommentRepository cr2;
  final EventRepository er;
  final MemberRepository mr;
  final PurchaseRepository pr;
  final BookService bs;
  final CartRepository cr;
  final ReviewRepository rr;

  // 메인페이지
  @GetMapping("/")
  public String index(Model model) {
    int test1 = 1;
    model.addAttribute("test", test1);
    return "page/home";
  }

  @GetMapping("/page/detail/{bookId}")
  public String detail(@PathVariable long bookId, HttpSession session) {
    // ID를 기준으로 객체를 생성, 보내줌
    // 이 때 세션에 객체를 저장해놓고 계속 사용한다.
    Optional<Book> book = br.findById(bookId);
    Book unwrappedBook = book.get();
    session.setAttribute("book", unwrappedBook);

    //리뷰 정보를 세션에 담는다
    List<Review> review = book.get().getReviewList();

    log.info("isempty체크: " + review.isEmpty());
    if (review.isEmpty()) {
      session.setAttribute("starAvg", 0.0);
      session.setAttribute("reviewCnt", 0);
    } else {
      int reviewCnt = review.size();
      double starSum = 0;
      for (int i = 0; i < review.size(); i++) {
        double eachStar = review.get(i).getStar();
        starSum += eachStar;
      }
      double starAvg = starSum / reviewCnt;
      session.setAttribute("starAvg", starAvg);
      session.setAttribute("reviewCnt", reviewCnt);

    }
    return "page/detail";
  }

  @GetMapping("/page/category")
  public String category(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
    Page<Book> pageList = bs.getBookList(page);

    model.addAttribute("pageList", pageList);

    return "page/category";
  }

  @GetMapping("/page/prototype")
  public String prototype() {
    return "page/prototype";
  }

  @GetMapping("/page/login")
  public String toLogin(HttpSession session) {
    if (session.getAttribute("memberSeq") != null) {
      return "page/home";
    }
    return "page/login";
  }

  @PostMapping("/page/login")
  public String login(HttpSession session, HttpServletRequest request, String id, String pw,
      Model model) {

    Optional<Member> loginMember = mr.findByMemberIdAndPw(id, pw);

    if (loginMember.isPresent()) {
      session.setAttribute("memberSeq", loginMember.get().getSeq());
    } else {
      return "redirect:/page/login";
    }

    return "page/home";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }

  @GetMapping("/page/myPage")
  public String myPage() {
    return "page/myPage";
  }

  @GetMapping("/page/insertBook")
  public String insertBook() {
    return "page/insertBook";
  }

  @GetMapping("/page/insertMember")
  public String insertMember() {
    return "page/insertMember";
  }

  // 구매 프로세스 관련 매핑

  @GetMapping("/page/cartList")
  public String cartList(Model model, HttpSession session) {
    Optional<Long> loginSession = Optional.ofNullable((Long) session.getAttribute("memberSeq"));
    if (loginSession.isPresent()) {
      Optional<Member> member = mr.findById(loginSession.get());
      model.addAttribute("member", member.get());

    } else {
      return "page/login";
    }
    return "page/cartList";
  }

  // detail에서 제품을 추가하면서 카트리스트로 갈 경우, addToCart 매핑을 거쳐서 리다이렉트하도록 설계
  //같은 책이 이미 있다면 그 객체를 select해서 quantity를 추가해줘야 함
  @GetMapping("/page/addToCart")
  public String addToCart(HttpSession session, int quantity) {
    Optional<Long> loginSession = Optional.ofNullable((Long) session.getAttribute("memberSeq"));

    if (loginSession.isPresent()) {
      Book book = (Book) session.getAttribute("book");

      Optional<Cart> findCart = cr.findAll().stream()
          .filter(data -> data.getBook().getSeq().equals(book.getSeq()))
          .filter(data -> data.getMember().getSeq().equals(session.getAttribute("memberSeq")))
          .findAny();

      log.info(loginSession.isPresent());
      log.info(findCart.isPresent());
      if (findCart.isPresent()) {
        int currentQuantity = findCart.get().getQuantity();
        findCart.get().setQuantity(currentQuantity + quantity);
        cr.saveAndFlush(findCart.get());
      } else {
        //카트에 똑같은 제품이 담기지 않았을 경우
        Cart cartItem = new Cart();
        cartItem.setQuantity(quantity);
        cartItem.setBook(book);
        long id = loginSession.get();
        Optional<Member> member = mr.findById(id);
        cartItem.setMember(member.get());
        cr.save(cartItem);

      }

    } else {
      return "page/login";
    }
    return "page/addToCart";
  }

  //각 행의 seq를 받아와서 그걸 기준으로 삭제하면 될 것 같음
  @GetMapping("/page/deleteFromCart")
  public String deleteFromCart() {

    return "redirect:page/cartList";
  }


  @GetMapping("/page/directBuy")
  public String directBuy() {
    // long bookSeq랑 int quantity 넘어옴

    return "page/directBuy";
  }


  @GetMapping("/page/search")
  public String search(String keyword, Model model,
      @RequestParam(value = "page", defaultValue = "0") int page, String category) {
    log.info("넘어오는 카테고리값:" + category);

//이건 검색만 적용된 리스트
    List<Book> searchList = bs.getSearchList(keyword);
List<Map> reviewList = new ArrayList<>();
    List<Double> starList = new ArrayList<>();
    List<Double> cntList = new ArrayList<>();
    //검색에 더해 카테고리 검색이 추가된 리스트
    Page<Book> getCategorized = bs.getCategorized(searchList, category, page);

    for (int i = 0; i < getCategorized.getNumberOfElements(); i++) {
      HashMap<String, Double> getReviews= new HashMap<>();

      List<Review> eachReviewList = getCategorized.getContent().get(i).getReviewList();

      log.info("첫번째 생긴꼬라지를 보자"+eachReviewList);
      log.info("중간체크"+getCategorized.getContent().get(i).getSeq());

      double starSum = 0;
      double reviewCnt=eachReviewList.size();
      for (int j = 0; j < reviewCnt; j++) {
        Review eachReview = eachReviewList.get(j);
        double eachStar = eachReview.getStar();
        starSum+=eachStar;
      }
double starAvg =starSum/reviewCnt;
getReviews.put("starAvg",starAvg);
getReviews.put("reviewCnt", reviewCnt);

      log.info(getReviews);
reviewList.add(getReviews);
      starList.add(starAvg);
      cntList.add(reviewCnt);

      log.info(reviewList);


    }

    int totalPage = getCategorized.getTotalPages();
    model.addAttribute("pageList", getCategorized);
    model.addAttribute("totalPage", totalPage);
    //적립/할인 연산을 위해 숫자 추가
    model.addAttribute("100", 100);

    //페이징 기능을 위해 model에 keyword와 category 검색값을 저장
    model.addAttribute("keyword", keyword);
    model.addAttribute("category", category);

model.addAttribute("reviewList",reviewList);
    model.addAttribute("starList",starList);
    log.info("스타리스트 생긴거"+starList);
    model.addAttribute("cntList",cntList);



    return "page/category";
  }

}
