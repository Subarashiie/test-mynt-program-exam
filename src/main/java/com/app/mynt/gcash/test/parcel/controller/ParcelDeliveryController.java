package com.app.mynt.gcash.test.parcel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.mynt.gcash.test.parcel.model.Parcel;
import com.app.mynt.gcash.test.parcel.model.ParcelCost;
import com.app.mynt.gcash.test.parcel.service.CalculationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ParcelDeliveryController {
	
	@Autowired
	private CalculationService calculationService;
	
	@PostMapping("/calculateCost")
    public ParcelCost calculateCostWithVoucher(@RequestBody Parcel parcel, @RequestParam(required = false) String voucher) {
		if(voucher != null) {
			log.info("executing calculateCostWithVoucher... [" + voucher +"]");
		} else {
			log.info("executing calculateCost...");	
		}
		return calculationService.calculateDeliveryCost(parcel, voucher);
		
    }

}
