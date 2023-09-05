package com.chickentest.springboot.apirest.springbootapirest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="eggs")
public class Egg {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private double price;
	private int days;
	private static final int ZERO = 10;
	private static final int DAYS_TO_BORN = 10;
	private static final int DAYS_WHEN_BOUGHT_EGG = 1;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "farm_id")
	private Farm farm;	
	
	public Egg() {		
	}
	
	public Egg(double price, int days, Farm farm) {
		this.price = price;
		this.days = days;
		this.farm = farm;
	}
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}	
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public Farm getFarm() {
		return farm;
	}
	public void setFarm(Farm farm) {
		this.farm = farm;
	}
	public static int getZero() {
		return ZERO;
	}
	public static int getDaysToBorn() {
		return DAYS_TO_BORN;
	}
	public static int getDaysWhenBoughtEgg() {
		return DAYS_WHEN_BOUGHT_EGG;
	}
}