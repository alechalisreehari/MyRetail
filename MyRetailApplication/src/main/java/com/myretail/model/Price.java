/**
 * 
 */
package com.myretail.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Price object for response
 * 
 * @author Sreehari
 *
 */
@ApiModel(description="Details about the price")
@Component
@JsonInclude(Include.NON_NULL)
public class Price {

	@ApiModelProperty(notes="The value  of product")
	private Double value;

	@ApiModelProperty(notes="The currency of price")
	@JsonProperty("currency_code")
	private String currencyCode;

	/**
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param bigDecimal the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * @return the currency_code
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param currency_code the currency_code to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

}
