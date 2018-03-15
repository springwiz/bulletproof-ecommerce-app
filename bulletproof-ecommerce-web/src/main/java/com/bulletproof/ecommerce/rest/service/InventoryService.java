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
import com.bulletproof.ecommerce.core.model.InventorySupply;
import com.bulletproof.ecommerce.rest.model.ApiError;
import com.bulletproof.ecommerce.rest.model.ApiResponse.ResponseStatus;
import com.bulletproof.ecommerce.rest.model.ApiSuccess;
import com.bulletproof.ecommerce.rest.model.InventorySupplyDTO;
import com.bulletproof.ecommerce.rest.model.mapper.ValueObjectMapper;

/**
 * The Class InventoryService.
 *
 * @author sumit REST End Point for the Inventory
 */
@CrossOrigin(origins = "*")
@RestController
public class InventoryService {

	/** Logger Instance. */
	private static final Logger logger = LoggerFactory.getLogger("InventoryService.class");

	@Autowired
	private ValueObjectMapper valueObjectMapper;

	@Autowired
	private StoreItemRetailRepository storeItemRetailRepository;

	/**
	 * Adjust Inventory
	 *
	 * @param invSupply InventorySupply
	 * 
	 * @api {post} inventory/adjust
	 * @apiName adjustInventory
	 * @apiGroup inventory
	 * @apiParam (Object) {Object} invSupply InventorySupply
	 * @apiSuccess {Boolean} success success.
	 * @apiError {json} Error-Response: { "status": "error" "message": "" }
	 */
	@RequestMapping(value = "inventory/adjust", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Object> adjustInventory(@RequestBody InventorySupply invSupply) {
		// adjust the inventory for item and store
		logger.info("\n**Entry: adjustInventory**" + " invSupply: " + invSupply.getId());

		try {
			storeItemRetailRepository.adjustInventory(invSupply);
			return new ResponseEntity<Object>(new ApiSuccess("SUCCESS", ResponseStatus.SUCCESS, "SUCCESS"), HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Find Inventory.
	 *
	 * @param storeId
	 *            Store Id
	 * @param productId
	 *            Product Id
	 * @return the response
	 * 
	 * @api {get} inventory/store/{storeId}/product/{productId} Find Inventory
	 * @apiName findInventory
	 * @apiGroup inventory
	 * @apiParam (String) {String} storeId Store Id
	 * @apiParam (String) {String} productId Product Id
	 * @apiSuccess {Boolean} success success.
	 * @apiError {json} Error-Response: { "status": "error" "message": "" }
	 */
	@RequestMapping(value = "inventory/store/{storeId}/product/{productId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> findInventory(@PathVariable String storeId, @PathVariable String productId) {
		// return the inventory for item and store
		logger.info("\n**Entry: findInventory**" + " storeId: " + storeId + " productId" + productId);

		try {
			InventorySupplyDTO invSupplyDTO = valueObjectMapper.map(
				storeItemRetailRepository.findInventory(storeId, productId), InventorySupplyDTO.class);
			return new ResponseEntity<Object>(invSupplyDTO, HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<Object>(new ApiError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}