package com.mobile.app;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.mobile.app.entity.Category;
import com.mobile.app.entity.Mobile;
import com.mobile.app.repository.CategoryRepository;
import com.mobile.app.repository.MobileRepository;
import com.mobile.app.service.MobileService;

public class DataBaseInit {

	@Autowired
	MobileRepository mobileRepository;

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	MobileService mobileService;
	
	LocalDate date = LocalDate.parse("2000-09-20");

	public void addCategories() {
		categoryRepository.save(new Category(100, "Samsung", new ArrayList<Mobile>()));
		categoryRepository.save(new Category(101, "Nokia", new ArrayList<Mobile>()));
		categoryRepository.save(new Category(102, "Motorola", new ArrayList<Mobile>()));
		categoryRepository.save(new Category(103, "Android", new ArrayList<Mobile>()));
		categoryRepository.save(new Category(104, "Ios", new ArrayList<Mobile>()));
		categoryRepository.save(new Category(104, "I-Phone", new ArrayList<Mobile>()));

	}
	
	

	public void addMobilesToDB() {
		mobileRepository.save(new Mobile(1, "Samsung Galaxy S23 plus", 94900.0, date, "SAMSUNG-123", "SAMSUNG"));
		mobileRepository.save(new Mobile(2, "Samsung Galaxy S23 ", 74900.0, date, "SAMSUNG-123", "SAMSUNG"));
		mobileRepository.save(new Mobile(3, "Samsung Galaxy S23 Ultra", 124900.0, date, "SAMSUNG-123", "SAMSUNG"));
		mobileRepository.save(new Mobile(4, "Galaxy Tab S8 Ultra.", 124900.0, date, "SAMSUNG-123", "SAMSUNG"));
		mobileRepository.save(new Mobile(5, "Galaxy Z Fold4", 124900.0, date, "SAMSUNG-123", "SAMSUNG"));
		mobileRepository.save(new Mobile(6, "Galaxy Z Flip4", 124900.0, date, "SAMSUNG-123", "SAMSUNG"));
		mobileRepository.save(new Mobile(7, "Galaxy Watch5 Pro", 124900.0, date, "SAMSUNG-123", "SAMSUNG"));
		mobileRepository.save(new Mobile(8, "Galaxy Buds2 Pro", 124900.0, date, "SAMSUNG-123", "SAMSUNG"));
		mobileRepository.save(new Mobile(9, "Galaxy A53 5G", 124900.0, date, "SAMSUNG-123", "SAMSUNG"));
		mobileRepository.save(new Mobile(10, "Samsung Galaxy S21 FE", 124900.0, date, "SAMSUNG-123", "SAMSUNG"));
		mobileRepository.save(new Mobile(11, "Nokia 2780 Flip ", 124900.0, date, "Nokia-123", "SAMSUNG"));
		mobileRepository.save(new Mobile(12, "Nokia 110 (2022) ", 124900.0, date, "Nokia-123", "Nokia"));
		mobileRepository.save(new Mobile(13, "Nokia 8210 4G", 124900.0, date, "Nokia-123", "Nokia"));
		mobileRepository.save(new Mobile(14, "Nokia 5710 XpressAudio", 124900.0, date, "Nokia-123", "Nokia"));
		mobileRepository.save(new Mobile(15, "Nokia 2760 Flip", 124900.0, date, "Nokia-123", "Nokia"));
		mobileRepository.save(new Mobile(16, "Nokia 2660 Flip. ", 124900.0, date, "Nokia-123", "Nokia"));
		mobileRepository.save(new Mobile(17, "Nokia 105 (2022)", 124900.0, date, "Nokia-123", "Nokia"));
		mobileRepository.save(new Mobile(18, "Nokia 6310 (2021)", 124900.0, date, "Nokia-123", "Nokia"));
		mobileRepository.save(new Mobile(19, "Nokia X30 5G ", 124900.0, date, "Nokia-123", "Nokia"));
		mobileRepository.save(new Mobile(20, "Nokia 150 2020 ", 124900.0, date, "Nokia-123", "Nokia"));
		mobileRepository.save(new Mobile(21, "Nokia 5.1 plus ", 124900.0, date, "Nokia-123", "Nokia"));
		mobileRepository.save(new Mobile(22, "Nokia 3310 New", 124900.0, date, "Nokia-123", "Nokia"));
	}
	

}
