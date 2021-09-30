package com.returnordermanagement.authorizationService.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "myuser")

public class MyUser implements Serializable{

	@Id
	@Column(name = "userid")
	private String userid;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
    @Transient
	private String token;

	public MyUser(String userid, String username, String password) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
	}

}
