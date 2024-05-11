package com.s207.cloudy.domain.members.entity;

import com.s207.cloudy.domain.members.MemberDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@Getter
@ToString

public class Member implements UserDetails {



    @Getter
    private String id;


    private String password;
    private List<GrantedAuthority> authorities;


    @Getter
    private String name;

    @Builder
    public Member(String userId, String userName, String password, List<GrantedAuthority> authorities){
        this.name = userName;
        this.id = userId;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setString(String name){
        this.name = name;
    }

    @Override
    public String getUsername() {
        return id;
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



    public MemberDto toDto(){
        return MemberDto
                .builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
