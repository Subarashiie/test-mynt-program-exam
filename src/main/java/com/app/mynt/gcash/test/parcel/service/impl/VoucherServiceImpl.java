package com.app.mynt.gcash.test.parcel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.mynt.gcash.test.parcel.model.VoucherItem;
import com.app.mynt.gcash.test.parcel.service.VoucherService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class VoucherServiceImpl implements VoucherService {
	
	@Value("${voucher.api.base.url}")
    private String voucherApiBaseURL;
	
	@Value("${voucher.api.resource.url}")
    private String voucherApiResourceURL;
	
    @Value("${voucher.api.timeout.limit}")
    private int voucherConnectionTimeout;

    private final RestTemplate restTemplate;

    public VoucherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public VoucherItem checkVoucherValidity(String voucherCode) {
		log.info("executing checkVoucherValidity...");
		
		VoucherItem voucherItem = new VoucherItem();
  
        String url = voucherApiBaseURL + voucherApiResourceURL + "/" + voucherCode;
        try {
            voucherItem = restTemplate.getForObject(url, VoucherItem.class);
        } catch (Exception ex) {
        	log.error(ex.toString());
        }
        return voucherItem;
    }

}
