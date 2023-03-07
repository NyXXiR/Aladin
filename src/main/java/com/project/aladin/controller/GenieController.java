package com.project.aladin.controller;

import com.project.aladin.entity.Book;
import com.project.aladin.entity.Cart;
import com.project.aladin.entity.Member;
import com.project.aladin.repository.BookRepository;
import com.project.aladin.repository.CartRepository;
import com.project.aladin.repository.CommentRepository;
import com.project.aladin.repository.EventRepository;
import com.project.aladin.repository.MemberRepository;
import com.project.aladin.repository.PurchaseRepository;
import com.project.aladin.repository.ReviewRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class GenieController {

  final BookRepository br;
  final CommentRepository cr2;
  final EventRepository er;
  final MemberRepository mr;
  final PurchaseRepository pr;

  final CartRepository cr;

  final ReviewRepository rr;

  //메인페이지
  @GetMapping("/")
  public String index(Model model) {
    int test1 = 1;
    model.addAttribute("test", test1);
    return "page/home";
  }


  @GetMapping("/page/detail/{bookId}")
  public String detail(@PathVariable long bookId, HttpSession session) {
    // ID를 기준으로 객체를 생성, 보내줌
    //이 때 세션에 객체를 저장해놓고 계속 사용한다.
    Optional<Book> book = br.findById(bookId);
    Book unwrappedBook = book.get();
    session.setAttribute("book", unwrappedBook);

    return "page/detail";
  }

  @GetMapping("/page/category")
  public String category() {
    return "page/category";
  }

  @GetMapping("/page/prototype")
  public String prototype() {
    return "page/prototype";
  }


  @GetMapping("/page/login")
  public String toLogin() {
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


  //구매 프로세스 관련 매핑



  @GetMapping("/page/cartList")
  public String cartList(int quantity){
    //long bookSeq랑 int quantity 넘어옴
    return "page/cartList";
  }

  //detail에서 제품을 추가하면서 카트리스트로 갈 경우, addToCart 매핑을 거쳐서 리다이렉트하도록 설계
@GetMapping("/function/addToCart")
  public String addToCart(HttpSession session, int quantity){
    Optional<Long> loginSession= (Optional<Long>) session.getAttribute("memberSeq");
if(loginSession.isPresent()){
Book book= (Book) session.getAttribute("book");
Cart cartItem =new Cart();
cartItem.setQuantity(quantity);
cartItem.setBook(book);
long id= loginSession.get();
Optional<Member> member= mr.findById(id);
cartItem.setMember(member.get());
cr.save(cartItem);

}else{
  return "page/login";
}

return "redirect:/page/cartList";
  }

  @GetMapping("/page/directBuy")
  public String directBuy(){
    //long bookSeq랑 int quantity 넘어옴

    return "page/directBuy";
  }



}
