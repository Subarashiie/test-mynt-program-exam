package com.app.mynt.gcash.test.parcel.service;

import com.app.mynt.gcash.test.parcel.model.VoucherItem;

public interface VoucherService {
	
	VoucherItem checkVoucherValidity(String voucherCode);

}
