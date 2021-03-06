package com.butch.apiutils.pojo;

import java.io.Serializable;

public class Jurisdiction implements Serializable{
	/**
	 *	二级缓存序列化
	 */
	private static final long serialVersionUID = 1L;
	public static String tablename="jurisdiction";
	private Integer id;
	private String name;
	public Jurisdiction() {
		super();
	}
	public Jurisdiction(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Jurisdiction [id=" + id + ", name=" + name + ", hashCode()=" + hashCode() + "]";
	}
	
}
