package com.app.mynt.gcash.test.parcel.model;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class PriceRateRule {
	
	@Value("${parcel.rule.reject.weight.limit.kg}")
	private double WeighLimitReject;
	
	@Value("${parcel.rule.heavy.parcel.weight.base.kg}")
	private double HeavyParcelBaseWeight;

	@Value("${parcel.rule.small.parcel.volume.limit}")
	private double SmallParcelLimit;

	@Value("${parcel.rule.medium.parcel.volume.limit}")
	private double MediumParcelLimit;
	
	@Value("${parcel.rule.heavy.parcel.rate}")
	private double HeavyParcelRate;

	@Value("${parcel.rule.large.parcel.rate}")
	private double LargeParcelRate;

	@Value("${parcel.rule.medium.parcel.rate}")
	private double MediumParcelRate;

	@Value("${parcel.rule.small.parcel.rate}")
	private double SmallParcelRate;
	
	
	

}
