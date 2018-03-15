package com.bulletproof.ecommerce.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bulletproof.ecommerce.core.dao.StoreItemRetailRepository;
import com.bulletproof.ecommerce.core.model.Product;
import com.bulletproof.ecommerce.rest.model.ApiError;
import com.bulletproof.ecommerce.rest.model.ApiResponse.ResponseStatus;
import com.bulletproof.ecommerce.rest.model.ApiSuccess;
import com.bulletproof.ecommerce.rest.model.ProductDTO;
import com.bulletproof.ecommerce.rest.model.mapper.ValueObjectMapper;

/**
 * The Class ProductService.
 *
 * @author sumit REST End Point for the finding products in the catalog
 */
@CrossOrigin(origins = "*")
@RestController
public class ProductService {

	/** Logger Instance. */
	private static final Logger logger = LoggerFactory.getLogger("ProductService.class");

	@Autowired
	private ValueObjectMapper valueObjectMapper;

	@Autowired
	private StoreItemRetailRepository storeItemRetailRepository;

	/**
	 * Get Product.
	 *
	 * @param productId
	 *            Product Id
	 * @return the response
	 * 
	 * @api {get} product/{productId} Get Product
	 * @apiName getProduct
	 * @apiGroup product
	 * @apiParam (String) {String} productId Product Id
	 * @apiSuccess {Boolean} success success.
	 * @apiError {json} Error-Response: { "status": "error" "message": "" }
	 */
	@RequestMapping(value = "product/{productId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> getProduct(@PathVariable String productId) {
		// return the item details
		logger.info("\n**Entry: getProduct**" + " productId" + productId);

		try {
			ProductDTO productDTO = valueObjectMapper.map(
					storeItemRetailRepository.getProduct(productId), ProductDTO.class);
			return new ResponseEntity<Object>(productDTO, HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Change Product
	 *
	 * @param product Product
	 * 
	 * @api {post} product/change
	 * @apiName changeProduct
	 * @apiGroup product
	 * @apiParam (Object) {Object} product Product
	 * @apiSuccess {Boolean} success success.
	 * @apiError {json} Error-Response: { "status": "error" "message": "" }
	 */
	@RequestMapping(value = "product/change", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Object> changeProduct(@RequestBody Product product) {
		// change the product
		logger.info("\n**Entry: changeProduct**" + " product: " + product.getId());

		try {
			storeItemRetailRepository.changeProduct(product);
			return new ResponseEntity<Object>(new ApiSuccess("SUCCESS", ResponseStatus.SUCCESS, "SUCCESS"), HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}