package com.project.aladin.controller;

import com.project.aladin.entity.Member;
import com.project.aladin.repository.BookRepository;
import com.project.aladin.repository.CommentRepository;
import com.project.aladin.repository.EventRepository;
import com.project.aladin.repository.MemberRepository;
import com.project.aladin.repository.PurchaseRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class GenieController {

    final BookRepository br;
    final CommentRepository cr;
    final EventRepository er;
    final MemberRepository mr;
    final PurchaseRepository pr;

    //메인페이지
@GetMapping ("/")
public String index(Model model){
    int test1= 1;
    model.addAttribute("test", test1);
    return "page/home";
}
//나중에 pathvariable로 각 detail로 연결되도록 해야 함
@GetMapping("/page/detail")
    public String detail(){
    return "page/detail";
}

@GetMapping("/page/category")
    public String category(){
    return "page/category";
}

    @GetMapping("/page/prototype")
    public String prototype(){
        return "page/prototype";
    }



@GetMapping("/page/login")
public String toLogin(){
    return "page/login";
}
    @PostMapping("/page/login")
    public String login(HttpSession session, HttpServletRequest request, String id, String pw,
        Model model){

Optional<Member> loginMember= mr.findByMemberIdAndPw(id,pw);

if(loginMember.isPresent()){
    session.setAttribute("memberSeq", loginMember.get().getSeq());
}else{
    return "redirect:/page/login";
}


    return "page/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
session.invalidate();
return "redirect:/";
    }
@GetMapping("/page/myPage")
public String myPage(){
    return "page/myPage";
    }


    @GetMapping("/page/insertBook")
    public String insertBook(){
        return "page/insertBook";
    }
    @GetMapping("/page/insertMember")
    public String insertMember(){
        return "page/insertMember";
    }


}
//만들어야 할 페이지: 책별 상세보기 페이지, 카테고리 페이지 하나