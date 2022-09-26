package com.example.security.config.auth;

import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 등록된 loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService타입으로 IoCehldjdlTsms loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    // 시큐리티 session(Authentication(UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }else {
            return new PrincipalDetails(user);
        }
    }
    // 파라미터에 설정된 변수명과 로그인 페이지의 form 태그안에 input name이 같아야 인식함
    // 설정을 바꾸고 싶다면 config에서 loginpage밑에 .usernameParameter("OO")으로 바꿔야 함
}
