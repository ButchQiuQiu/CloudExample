package com.butch.apiutils.redis.pojo;

import java.io.Serializable;

public class MySimpleAuthority implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String authority;

    public MySimpleAuthority(){
        
    }

    public MySimpleAuthority(String authority){
        this.authority=authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String toString() {
        return authority;
    }

    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }

}