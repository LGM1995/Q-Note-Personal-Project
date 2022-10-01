package com.example.security.repository;

import com.example.security.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD 함수를 가진 JpaRepository를 가짐
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
    // Jpa 네임 함수에 의하여 해당 테이블의 파라미터 값으로 데이터를 자동으로 쿼리한다.

    // SELECT * FROM user WHERE provider = ?1 and providerId = ?2
    // 어떤 로그인 유형이며 그 아이디가 뭔지 찾는다.
    Optional<User> findByProviderAndProviderId(String provider, String providerId);
}
