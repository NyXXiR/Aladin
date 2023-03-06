package com.project.aladin.repository;


import com.project.aladin.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
Optional<Member> findByMemberIdAndPw(String id, String pw);


}
