package com.mobile.app.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Mobile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mobileId;
	
	@NotBlank(message = "Name is mandatory")
	
	private String mobileName;
	
	@Min(value = 0, message = "Cost cannot be neagtive")
	private Double mobileCost;
	
	@NotNull
	private LocalDate mfd;
	@NotNull
	@NotBlank(message = "Model Number is mandatory")
	private String modelNumber;
	@NotNull
	@NotBlank(message = "Company Name is mandatory")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
	private String companyName;

	@NotNull
	@NotBlank(message = "Description is mandatory")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
	private String Description;
	
	@JsonProperty(access = JsonProperty.Access.AUTO)
	private Availability availability;
	
	 public enum Availability{
		Available,Unavailable,Comming_Soon
	}
	
	 
	public MobileOrderStatus orderStatusOfMobile;
	 
	public enum MobileOrderStatus{
		Placed,Cancelled
	}
	
	public Mobile() {
		super();	
	}

	public Mobile(int mobileId, @NotBlank(message = "Name is mandatory") String mobileName,
			@Min(value = 0, message = "Cost cannot be neagtive") Double mobileCost, @NotNull LocalDate mfd,
			@NotNull @NotBlank(message = "Model Number is mandatory") String modelNumber,
			@NotNull @NotBlank(message = "Company Name is mandatory") @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces") String companyName,
			@NotNull @NotBlank(message = "Description is mandatory") @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces") String description,
			Availability availability) {
		super();
		this.mobileId = mobileId;
		this.mobileName = mobileName;
		this.mobileCost = mobileCost;
		this.mfd = mfd;
		this.modelNumber = modelNumber;
		this.companyName = companyName;
		Description = description;
		this.availability = availability;
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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	public MobileOrderStatus getOrderStatusOfMobile() {
		return orderStatusOfMobile;
	}

	public void setOrderStatusOfMobile(MobileOrderStatus orderStatusOfMobile) {
		this.orderStatusOfMobile = orderStatusOfMobile;
	}
	
	
	
	
}
