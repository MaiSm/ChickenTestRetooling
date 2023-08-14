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
	private double money;
	private int eggs;
	private int chickens;
	private int eggPrice;
	private int chickenPrice;
	private int limitOfEggs;
	private int limitOfChickens;
	
	@OneToMany(mappedBy = "farm")
	private List<Egg> eggsList;
	
	@OneToMany(mappedBy = "farm")
	private List<Chicken> chickenList;
	
	public double getId() {
		return id;
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
	public int getEggPrice() {
		return eggPrice;
	}
	public void setEggPrice(int eggPrice) {
		this.eggPrice = eggPrice;
	}
	public int getChickenPrice() {
		return chickenPrice;
	}
	public void setChickenPrice(int chickenPrice) {
		this.chickenPrice = chickenPrice;
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