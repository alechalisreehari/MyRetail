package com.myretail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myretail.model.Offer;

/**
 * Product Repository for getting the offer details
 * 
 * @author Sreehari
 *
 */
public interface ProductRepository extends MongoRepository<Offer, Integer> {
}
