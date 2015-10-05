package com.mkyong.rest;

public class Order {
String customer;
String address;
String amount;
public String getCustomer() {
	return customer;
}

public String getAmount() {
	return amount;
}
public void setCustomer(String customer) {
	this.customer = customer;
}

public void setAmount(String amount) {
	this.amount = amount;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

}
