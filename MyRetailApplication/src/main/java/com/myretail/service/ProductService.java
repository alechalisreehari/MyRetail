package com.myretail.service;

import com.myretail.exceptions.ProductServiceException;
import com.myretail.model.Offer;
import com.myretail.model.ProductResponse;

/**
 * Interface for the product service
 * 
 * @author Sreehari
 *
 */
public interface ProductService {

	public ProductResponse findProductById(Integer id) throws ProductServiceException;

	public ProductResponse updatePrice(Offer offer) throws ProductServiceException;
}
