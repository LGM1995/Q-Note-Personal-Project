package com.example.security.config.auth;

import com.example.security.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

// 시큐리티가 /login 주소 요청이 오면낚아채서 로그인을 진행
// 로그인 진행이 완료되면 시큐리티 session을 만들어줌 (Security ContextHolder에 세션값 저장)
// 단 저장될 객체는 Authentication 객체
// 그 안에 User 오브젝트 타입을 UserDetails 타입으로 저장
public class PrincipalDetails implements UserDetails, OAuth2User {

    private static final long serialVersionUID = 1L;
    private User user;
    private Map<String, Object> attributes;


    // 일반 로그인시 사용할 principalDetails
    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 소셜 로그인시 사용
    public PrincipalDetails(User user,Map<String,Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 유저 권환을 리턴받는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
        collet.add(()->{ return user.getRole();});
        return collet;
    }

    // 리소스 서버로 부터 받는 회원정보
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    // User의 PrimaryKey
    @Override
    public String getName() {
        return user.getId()+"";
    }

}
