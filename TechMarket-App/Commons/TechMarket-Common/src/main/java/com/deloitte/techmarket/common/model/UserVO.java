package com.deloitte.techmarket.common.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

/**
 * UserVO class.
 *
 */
@Component
public class UserVO {

	private Integer userId;

	@NotNull
	private String userName;

	@NotNull
	private String password;

	private String firstname;

	private String lastname;

	private String email;

	private String mobile;

	private String role;

	private String jwt;

	@NotNull
	@Min(11)
	private Integer age;

	private String category;

	private Boolean isUserActive;


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public Boolean getIsUserActive() {
		return isUserActive;
	}

	public void setIsUserActive(Boolean isUserActive) {
		this.isUserActive = isUserActive;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userName=" + userName + ", password=" + password + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email + ", mobile=" + mobile + ", role=" + role
				+ ", jwt=" + jwt + ", age=" + age + ", category=" + category + ", isUserActive=" + isUserActive + "]";
	}

}
