package com.mobile.app.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Mobile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mobileId;
//	@Pattern(regexp = "[a-zA-Z0-9]{3,}",message = "name must be min 3 chars, special chars not allowed.")
	private String mobileName;
	private Double mobileCost;
	private LocalDate mfd;
	private String modelNumber;
	private String companyName;
//	@ManyToOne
//	private Category category;
	@JsonIgnore
	@OneToOne
	private Orders order;

	public Mobile() {
		super();
	}

//	public Category getCategory() {
//		return category;
//	}
//
//	public void setCategory(Category category) {
//		this.category = category;
//	}

	public Mobile(int mobileId, String mobileName, Double mobileCost, LocalDate mfd, String modelNumber,
			String companyName, Category category) {
		super();
		this.mobileId = mobileId;
		this.mobileName = mobileName;
		this.mobileCost = mobileCost;
		this.mfd = mfd;
		this.modelNumber = modelNumber;
		this.companyName = companyName;
//		this.category = category;

	}

	public int getMobileId() {
		return mobileId;
	}

	public void setMobileId(int mobileId) {
		this.mobileId = mobileId;
	}

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	public Double getMobileCost() {
		return mobileCost;
	}

	public void setMobileCost(Double mobileCost) {
		this.mobileCost = mobileCost;
	}

	public LocalDate getMfd() {
		return mfd;
	}

	public void setMfd(LocalDate mfd) {
		this.mfd = mfd;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}
	

}
