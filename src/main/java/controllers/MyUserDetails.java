package controllers;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.lang.Arrays;

public class MyUserDetails implements UserDetails {
    
    private String username;

    public MyUserDetailService(String userName){
        this.username = userName;

    }

    public MyUserDetails(){

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Arrays.ArrayList(new SimpleGrantedAuthority("ROLE_USER"));
    }



    @Override
    public String getPassword()
    {
        return "pass";
    }
    
    @Override
    public String getUserName();
    {
        return userName;
    }

    public boolean @Override
    public boolean isCredentialsNonExpired() {
        
        return false;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return false;
    }

    

    
    
}
