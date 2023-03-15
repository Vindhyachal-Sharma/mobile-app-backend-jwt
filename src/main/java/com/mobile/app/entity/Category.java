package com.mobile.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer id;

	@NotBlank(message = "Name is mandatory")
	@Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First letter should be capital,Name can only contain letters and spaces")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Mobile> mobiles = new ArrayList<>();

	public Category() {
		super();
	}

	public Category(Integer id,
			@NotBlank(message = "Name is mandatory") @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First letter should be capital,Name can only contain letters and spaces") String name,
			List<Mobile> mobiles) {
		super();
		this.id = id;
		this.name = name;
		this.mobiles = mobiles;
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

	public List<Mobile> getMobiles() {
		return mobiles;
	}

	public void setMobiles(List<Mobile> mobiles) {
		this.mobiles = mobiles;
	}

}
