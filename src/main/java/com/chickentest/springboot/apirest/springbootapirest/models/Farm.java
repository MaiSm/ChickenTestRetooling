package com.chickentest.springboot.apirest.springbootapirest.models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="farms")
public class Farm {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;	
	private String name;
	private double money;
	private int eggs;
	private int chickens;
	private double sellingEggPrice;
	private double sellingChickenPrice;
	private double buyingEggPrice;
	private double buyingChickenPrice;
	private int limitOfEggs;
	private int limitOfChickens;
	
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
	public int getEggs() {
		return eggs;
	}
	public void setEggs(int eggs) {
		this.eggs = eggs;
	}
	public int getChickens() {
		return chickens;
	}
	public void setChickens(int chickens) {
		this.chickens = chickens;
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
		
}