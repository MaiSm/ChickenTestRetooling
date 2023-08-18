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
@Table(name="chickens")
public class Chicken {
	
	public Chicken() {		
	}
	
	public Chicken(double price, int days, int daysSinceLastEggs, Farm farm) {
		this.price = price;
		this.days = days;
		this.daysSinceLastEggs = daysSinceLastEggs;
		this.farm = farm;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private double price;
	private int days;
	private int daysSinceLastEggs;
	private static final int DAYS_OF_LIFE = 15;
	private static final int DAYS_TO_PUT_EGGS = 5;
	private static final int EGGS_BY_CHICKEN = 2;
	private static final int DAYS_WHEN_BOUGHT_CHICKEN = 2;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "farm_id")
	private Farm farm;	
	
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
	public int getDaysSinceLastEggs() {
		return daysSinceLastEggs;
	}
	public void setDaysSinceLastEggs(int daysSinceLastEggs) {
		this.daysSinceLastEggs = daysSinceLastEggs;
	}
	public static int getDaysoflife() {
		return DAYS_OF_LIFE;
	}
	public static int getDaysToPutEggs() {
		return DAYS_TO_PUT_EGGS;
	}
	public static int getEggsByChicken() {
		return EGGS_BY_CHICKEN;
	}
	public static int getDaysWhenBoughtChicken() {
		return DAYS_WHEN_BOUGHT_CHICKEN;
	}	
}