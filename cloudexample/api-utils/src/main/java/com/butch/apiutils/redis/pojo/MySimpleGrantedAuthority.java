package com.butch.apiutils.redis.pojo;

import org.springframework.security.core.GrantedAuthority;

public class MySimpleGrantedAuthority implements GrantedAuthority{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String authority;

    public MySimpleGrantedAuthority(){
        
    }

    public MySimpleGrantedAuthority(String authority){
        this.authority=authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return authority;
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
    
}