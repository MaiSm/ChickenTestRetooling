package com.chickentest.springboot.apirest.springbootapirest.models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="farms")
public class Farm {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;	
	private String name;
	private double money;
	@Min(value = 1, message = "Invalid price. You must enter only numbers above 1")
	private double sellingEggPrice;
	@Min(value = 1, message = "Invalid price. You must enter only numbers above 1")
	private double sellingChickenPrice;
	@Min(value = 1, message = "Invalid price. You must enter only numbers above 1")
	private double buyingEggPrice;
	@Min(value = 1, message = "Invalid price. You must enter only numbers above 1")
	private double buyingChickenPrice;
	private int limitOfEggs;
	private int limitOfChickens;
	private static final int ZERO = 0;
	private static final int MIN_DAYS_TO_MOVE = 1;
	private static final int MAX_DAYS_TO_MOVE = 5;
	private static final double PERCENT_OF_DISCOUNT = 0.5;
	
	
	@OneToMany(mappedBy = "farm")
	private List<Egg> eggsList;
	
	@OneToMany(mappedBy = "farm")
	private List<Chicken> chickenList;
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}	
	public double getSellingEggPrice() {
		return sellingEggPrice;
	}
	public void setSellingEggPrice(double sellingEggPrice) {
		this.sellingEggPrice = sellingEggPrice;
	}
	public double getSellingChickenPrice() {
		return sellingChickenPrice;
	}
	public void setSellingChickenPrice(double sellingChickenPrice) {
		this.sellingChickenPrice = sellingChickenPrice;
	}
	public double getBuyingEggPrice() {
		return buyingEggPrice;
	}
	public void setBuyingEggPrice(double buyingEggPrice) {
		this.buyingEggPrice = buyingEggPrice;
	}
	public double getBuyingChickenPrice() {
		return buyingChickenPrice;
	}
	public void setBuyingChickenPrice(double buyingChickenPrice) {
		this.buyingChickenPrice = buyingChickenPrice;
	}
	public int getLimitOfEggs() {
		return limitOfEggs;
	}
	public void setLimitOfEggs(int limitOfEggs) {
		this.limitOfEggs = limitOfEggs;
	}
	public int getLimitOfChickens() {
		return limitOfChickens;
	}
	public void setLimitOfChickens(int limitOfChickens) {
		this.limitOfChickens = limitOfChickens;
	}
	public static int getZero() {
		return ZERO;
	}
	public static double getPercentOfDiscount() {
		return PERCENT_OF_DISCOUNT;
	}
	public static int getMinDaysToMove() {
		return MIN_DAYS_TO_MOVE;
	}
	public static int getMaxDaysToMove() {
		return MAX_DAYS_TO_MOVE;
	}
		
}