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
import com.bulletproof.ecommerce.rest.model.ApiError;
import com.bulletproof.ecommerce.rest.model.ProductDTO;
import com.bulletproof.ecommerce.rest.model.StoreDTO;
import com.bulletproof.ecommerce.rest.model.mapper.ValueObjectMapper;

/**
 * The Class StoreService.
 *
 * @author sumit REST End Point for the getting the store details
 */
@CrossOrigin(origins = "*")
@RestController
public class StoreService {

	/** Logger Instance. */
	private static final Logger logger = LoggerFactory.getLogger("StoreService.class");

	@Autowired
	private ValueObjectMapper valueObjectMapper;

	@Autowired
	private StoreItemRetailRepository storeItemRetailRepository;

	/**
	 * Get Store Details.
	 *
	 * @param storeId
	 *            Store Id
	 * @return the response
	 * 
	 * @api {get} store/{storeId} Get Store Details
	 * @apiName getStoreDetails
	 * @apiGroup store
	 * @apiParam (String) {String} storeId Store Id
	 * @apiSuccess {Boolean} success success.
	 * @apiError {json} Error-Response: { "status": "error" "message": "" }
	 */
	@RequestMapping(value = "store/{storeId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> getStoreDetails(@PathVariable String storeId) {
		// return the store details
		logger.info("\n**Entry: getStoreDetailsc**" + " storeId" + storeId);

		try {
			StoreDTO storeDTO = valueObjectMapper.map(
					storeItemRetailRepository.getStoreDetails(storeId), StoreDTO.class);
			return new ResponseEntity<Object>(storeDTO, HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}