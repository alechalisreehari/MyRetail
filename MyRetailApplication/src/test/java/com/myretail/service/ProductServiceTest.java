package com.myretail.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.myretail.exceptions.ProductServiceException;
import com.myretail.model.Offer;
import com.myretail.model.Price;
import com.myretail.model.Product;
import com.myretail.model.ProductResponse;
import com.myretail.repository.ProductRepositoryTest;

/**
 * Junit test class used to validate the service layer of the my retail
 * application
 * 
 * @author Sreehari
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepositoryTest productRepository;

	/**
	 * This method is used to test the find product valid scenario
	 * 
	 * @throws ProductServiceException
	 */
	@Test
	public void testFindByValidId() throws ProductServiceException {

		Integer id = 78842191;
		// Validate ProductResponse
		ProductResponse productResponse = productService.findProductById(id);
		assertNotNull(productResponse);
		assertNotNull(productResponse.getStatus());
		assertEquals("SUCCESS", productResponse.getStatus());

		// Validate Product object
		Product product = productResponse.getProduct();
		assertNotNull(product);
		assertNotNull(product.getId());
		assertNotNull(product.getName());
		assertEquals(id, product.getId());
		assertEquals("Men's Big & Tall Standard Fit V-Neck Sweater - Goodfellow & Co™", product.getName());

		// Validate price object
		Price price = product.getCurrentPrice();
		assertNotNull(price);
		assertNotNull(price.getCurrencyCode());
		assertNotNull(price.getValue());
		assertEquals("USD", price.getCurrencyCode());
		assertEquals(150.49, price.getValue());

	}

	/**
	 * This method is used to test the find product invalid scenario
	 * 
	 * @throws ProductServiceException
	 */
	@Test
	public void testFindByInValidId() throws ProductServiceException {
		// Validate ProductResponse
		assertThrows(ProductServiceException.class, () -> productService.findProductById(8585858));

	}

	/**
	 * This method is used to test the update price invalid scenario
	 * 
	 * @throws ProductServiceException
	 */
	@Test
	public void updatePriceWithInValidItem() throws ProductServiceException {
		Offer offerTest = new Offer(12312, 10.00, "USD");
		assertThrows(ProductServiceException.class, () -> productService.updatePrice(offerTest));

	}

	/**
	 * This method is used to test the update price valid scenario
	 * 
	 * @throws ProductServiceException
	 */
	@Test
	public void updatePriceWithValidItem() throws ProductServiceException {
		Offer offerTest = new Offer(53184864, 10.00, "USD");

		ProductResponse productResponse = productService.updatePrice(offerTest);
		assertNotNull(productResponse);
		assertNotNull(productResponse.getStatus());
		assertEquals("SUCCESS", productResponse.getStatus());

		// Validate Product object
		Product product = productResponse.getProduct();
		assertNotNull(product);
		assertNotNull(product.getId());
		assertNotNull(product.getName());
		assertEquals(product.getId(), 53184864);
		assertEquals("Nintendo Switch Joy-Con L/R - Neon Pink/Neon Green", product.getName());

		// Validate price object
		Price price = product.getCurrentPrice();
		assertNotNull(price);
		assertNotNull(price.getCurrencyCode());
		assertNotNull(price.getValue());
		assertEquals("USD", price.getCurrencyCode());
		assertEquals(10.00, price.getValue());
	}
}
