package com.myretail.service;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.myretail.constants.MyRetailConstants;
import com.myretail.exceptions.ProductServiceException;
import com.myretail.model.Offer;
import com.myretail.model.Price;
import com.myretail.model.Product;
import com.myretail.model.ProductResponse;
import com.myretail.repository.ProductRepository;

/**
 * This service class is used for different activities related to the product
 * 
 * @author Sreehari
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = LogManager.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductResponse productResponse;

	@Autowired
	private ProductRepository productRepository;

	@Value("${external.api.url}")
	private String apiURL;

	@Value("${external.api.exclusions}")
	private String exlusions;

	@Override
	public ProductResponse findProductById(Integer id) throws ProductServiceException {

		productResponse = new ProductResponse();
		// Get the Details from Database
		try {
			log.info("Redsky ID " + id);
			Product prod = new Product();
			prod.setId(id);
			// Get name from API
			prod.setName(getProductName(id));

			// Get price from database
			Offer offer = productRepository.findById(id).get();
			if (null != offer) {
				prod.setCurrentPrice(buildPrice(offer));
			}

			productResponse.setStatus(MyRetailConstants.SUCCESS);
			productResponse.setProduct(prod);

		} catch (Exception ex) {
			throw new ProductServiceException();
		}

		return productResponse;
	}

	/**
	 * This method builds the price object based on the details from the data base.
	 * 
	 * @param offer
	 * @return
	 */
	private Price buildPrice(Offer offer) {

		Price price = new Price();
		price.setValue(offer.getPrice());
		price.setCurrencyCode(offer.getCurrencyCode());
		log.info("offer.getPrice() " + offer.getPrice());

		return price;

	}

	/**
	 * This method is used to get the product name from the external API based on
	 * the id passed.
	 * 
	 * @param id
	 * @return
	 */
	private String getProductName(Integer id) {

		log.info("start method getProductName with ID" + id);
		// Building the API url
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiURL);
		builder.path(id.toString());
		builder.queryParam(MyRetailConstants.EXCLUDES, exlusions);
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		// Invoking the API
		String response = restTemplate.exchange(builder.build().toUriString(), HttpMethod.GET, entity, String.class)
				.getBody();

		log.info("Response from the redsky API " + response);

		return getName(response);
	}

	/**
	 * Get the name of the product from the external API response JSON.
	 * 
	 * @param response
	 * @return
	 */
	private String getName(String response) {
		log.info("start method getName");
		String name = null;
		if (null != response && !response.isEmpty()) {
			JSONObject object = new JSONObject(response);
			JSONObject productResponse = (null != object && object.has(MyRetailConstants.PRODUCT))
					? object.getJSONObject(MyRetailConstants.PRODUCT)
					: null;
			if (null != productResponse && productResponse.has(MyRetailConstants.ITEM)) {
				JSONObject item = productResponse.getJSONObject(MyRetailConstants.ITEM);
				if (null != item && item.has(MyRetailConstants.PRODUCT_DESC)) {
					JSONObject productDescription = item.getJSONObject(MyRetailConstants.PRODUCT_DESC);
					if (null != productDescription && productDescription.has(MyRetailConstants.TITLE)) {
						
						name = productDescription.get(MyRetailConstants.TITLE).toString();
						log.info("Title redsky API -->" + name);
					}
				}
			}
		}
		log.info("end method getName");
		return name;
	}

	/**
	 * This method is used to update the price for a particular item.
	 * 
	 */
	@Override
	public ProductResponse updatePrice(Offer offer) throws ProductServiceException {
		productResponse = new ProductResponse();
		try {
			log.info("start method updatePrice");
			log.info("before method findById");
			productRepository.findById(offer.getId()).get();
			log.info("after method findById");
			log.info("before method save");
			productRepository.save(offer);
			log.info("after method save");
			log.info("before method findProductById");
			productResponse = findProductById(offer.getId());
			log.info("after method findProductById");
			log.info("start method updatePrice");
			productResponse.setStatus(MyRetailConstants.SUCCESS);
			log.info("end method updatePrice");
		} catch (Exception ex) {
			throw new ProductServiceException();
		}

		return productResponse;
	}

}
