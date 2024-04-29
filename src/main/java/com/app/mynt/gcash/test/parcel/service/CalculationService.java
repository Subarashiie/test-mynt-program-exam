package com.app.mynt.gcash.test.parcel.service;

import com.app.mynt.gcash.test.parcel.model.Parcel;
import com.app.mynt.gcash.test.parcel.model.ParcelCost;

public interface CalculationService {

	public ParcelCost calculateDeliveryCost (Parcel parcel, String voucher);
}
