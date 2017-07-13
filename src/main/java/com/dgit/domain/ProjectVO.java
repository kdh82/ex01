package com.dgit.domain;

public class ProjectVO {
	private String name;
	private double price;

	public ProjectVO(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

}
