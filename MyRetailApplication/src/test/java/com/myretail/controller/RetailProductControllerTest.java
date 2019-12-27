package com.myretail.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.myretail.model.ProductResponse;

/**
 * Product Controller for product related CRUD actions.
 * 
 * @author Sreehari
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RetailProductControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductResponse productResponse;

	/**
	 * This method validates the find product by id for a valid item.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindByValidId() throws Exception {

		this.mvc.perform(get("/v1/products/51552470").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.product.id").value("51552470"))
				.andExpect(jsonPath("$.product.name").value("2 Year Target + SquareTrade Video Game Hardware Protection Plan ($50-74.99)"))
				.andExpect(jsonPath("$.product.current_price.value").value("120.49"))
				.andExpect(jsonPath("$.product.current_price.currency_code").value("USD"))
				.andExpect(jsonPath("$.status").value("SUCCESS"));

	}
	
	/**
	 * This method validates the find product by id for an invalid item.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindByInvalidId() throws Exception {

		this.mvc.perform(get("/v1/products/878789").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value("FAILURE"))
				.andExpect(jsonPath("$.exceptionCode").value("404"))
				.andExpect(jsonPath("$.exceptionMessage").value("Product Not Found"));
	}

	/**
	 * This method validates the update product price by id for an valid item.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdatePriceValidId() throws Exception {

		this.mvc.perform(put("/v1/products/53494594").content("{\"value\":20.49,\"currency_code\":\"USD\"}")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.product.id").value("53494594")) 
				.andExpect(jsonPath("$.product.name").value("Super Smash Bros. Ultimate - Nintendo Switch")) 
				.andExpect(jsonPath("$.product.current_price.value").value("20.49"))
				.andExpect(jsonPath("$.product.current_price.currency_code").value("USD"))
				.andExpect(jsonPath("$.status").value("SUCCESS"));

	}
	/**
	 * This method validates the update product price by id for an invalid item.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdatePriceInvalidId() throws Exception {

		this.mvc.perform(put("/v1/products/878789").content("{\"value\":120.49,\"currency_code\":\"USD\"}")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value("FAILURE"))
				.andExpect(jsonPath("$.exceptionCode").value("404"))
				.andExpect(jsonPath("$.exceptionMessage").value("Product Not Found"));

	}
}
