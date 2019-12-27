package com.myretail.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Offer document for saving offer details to data source
 * 
 * @author Sreehari
 *
 */
@Document(collection = "Offer")
@Component
public class Offer {

	@Id
	private Integer id;

	@JsonProperty("value")
	private Double price;

	@JsonProperty("currency_code")
	private String currencycode;

	public Offer() {
	}

	public Offer(Integer id, Double price, String currencycode) {
		this.id = id;
		this.price = price;
		this.currencycode = currencycode;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the currency
	 */
	public String getCurrencyCode() {
		return currencycode;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrencyCode(String currencycode) {
		this.currencycode = currencycode;
	}

}
