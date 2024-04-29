package com.app.mynt.gcash.test.parcel.service.impl;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.mynt.gcash.test.parcel.model.Parcel;
import com.app.mynt.gcash.test.parcel.model.ParcelCost;
import com.app.mynt.gcash.test.parcel.model.PriceRateRule;
import com.app.mynt.gcash.test.parcel.model.VoucherItem;
import com.app.mynt.gcash.test.parcel.service.CalculationService;
import com.app.mynt.gcash.test.parcel.service.VoucherService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CalculationServiceImpl implements CalculationService {
	
	@Autowired
	private PriceRateRule rateRule;
	
	@Autowired
	private VoucherService voucherSvc;

	@Override
	public ParcelCost calculateDeliveryCost(Parcel parcel, String voucher) {	
		log.info("executing calculateDeliveryCost...");
		double cost = 0;
		double parcelWeight = parcel.getWeight();
		double parcelHeight = parcel.getHeight();
		double parcelLength = parcel.getLength();
		double parcelWidth = parcel.getWidth();

		double volume =  parcelHeight * parcelLength * parcelWidth;

		double discount = 0;
		if(voucher != null) {
			VoucherItem voucherItem = voucherSvc.checkVoucherValidity(voucher);
			if(voucherItem != null) {
				discount = voucherItem.getDiscount();
			}
		}
		
		
		if(parcelWeight > rateRule.getWeighLimitReject()) {
			return ParcelCost.builder().cost("N/A").ruleApplied("Reject").parcelTotalWeight(parcelWeight).build();
		} else if(parcelWeight > rateRule.getHeavyParcelBaseWeight()) {
			return ParcelCost.builder().cost(calculatePrice(parcelWeight, rateRule.getHeavyParcelRate(), discount)).ruleApplied("Heavy Parcel").parcelTotalWeight(parcelWeight).build();
		} else if(volume < rateRule.getSmallParcelLimit()) {
			return ParcelCost.builder().cost(calculatePrice(volume, rateRule.getSmallParcelRate(), discount)).ruleApplied("Small Parcel").parcelTotalVolume(volume).build();
		} else if(volume < rateRule.getMediumParcelLimit()) {
			return ParcelCost.builder().cost(calculatePrice(volume, rateRule.getMediumParcelRate(), discount)).ruleApplied("Medium Parcel").parcelTotalVolume(volume).build();
		} else {
			return ParcelCost.builder().cost(calculatePrice(volume, rateRule.getLargeParcelRate(), discount)).ruleApplied("Large Parcel").parcelTotalVolume(volume).build();
		}
	}
	
	private String calculatePrice(double weight, double multiplier, double voucherDiscount) {
        DecimalFormat df = new DecimalFormat("#.##");
        double price = weight * multiplier;

        if(voucherDiscount > 0) {
        	double discount = price * voucherDiscount;
        	price = price - discount;
            return df.format(price);
        } else {
    		return df.format(price);
        }
		
	}
	

}
