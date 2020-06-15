package com.butch.apiutils.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 用户表实现UserDetails接口
 */
public class User implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String name;
	private Sex fk_sex;
	private String address;
	private String phone;
	private Double salary;
	private String lastsign;
	private Integer absenteeism;
	private Jurisdiction fk_jurisdiction;
	private Depart fk_depart;
	private List<String> authorities;

	public User() {
	}


	public User(String username) {
		super();
		this.username = username;
	}

	public User(String username, String password, String name, Sex fk_sex, String address, String phone, Double salary,
			String lastsign, Integer absenteeism, Jurisdiction fk_jurisdiction, Depart fk_depart) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.fk_sex = fk_sex;
		this.address = address;
		this.phone = phone;
		this.salary = salary;
		this.lastsign = lastsign;
		this.absenteeism = absenteeism;
		this.fk_jurisdiction = fk_jurisdiction;
		this.fk_depart = fk_depart;
	}

	/**
	 * 权限将会由Security的模块Details生产
	 */
	public List<String> getAuthorities(){
		return this.authorities;
	}

	public void setAuthorities(List<String> authorities){
		this.authorities=authorities;
	}
	
	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sex getFk_sex() {
		return fk_sex;
	}

	public void setFk_sex(Sex fk_sex) {
		this.fk_sex = fk_sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getLastsign() {
		return lastsign;
	}

	public void setLastsign(String lastsign) {
		this.lastsign = lastsign;
	}

	public Integer getAbsenteeism() {
		return absenteeism;
	}

	public void setAbsenteeism(Integer absenteeism) {
		this.absenteeism = absenteeism;
	}

	public Jurisdiction getFk_jurisdiction() {
		return fk_jurisdiction;
	}

	public void setFk_jurisdiction(Jurisdiction fk_jurisdiction) {
		this.fk_jurisdiction = fk_jurisdiction;
	}

	public Depart getFk_depart() {
		return fk_depart;
	}

	public void setFk_depart(Depart fk_depart) {
		this.fk_depart = fk_depart;
	}

	@Override
	public String toString() {
		return "User [absenteeism=" + absenteeism + ", address=" + address + ", fk_depart=" + fk_depart
				+ ", fk_jurisdiction=" + fk_jurisdiction + ", fk_sex=" + fk_sex + ", lastsign=" + lastsign + ", name="
				+ name + ", password=" + password + ", phone=" + phone + ", salary=" + salary + ", username=" + username
				+ "]";
	}

}
