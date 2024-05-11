package com.s207.cloudy.domain.members.entity;

import com.s207.cloudy.domain.members.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "member")
@NoArgsConstructor
@Getter
@ToString
@Builder
@AllArgsConstructor
public class Member implements UserDetails {

    @Id
    @Column(name="id")
    @Getter
    private String id;

    @Transient
    private String password;


    @Getter
    @Column(name="name")
    private String name;

    @Builder
    public Member(String userId, String userName){
        this.name = userName;
        this.id = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
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
