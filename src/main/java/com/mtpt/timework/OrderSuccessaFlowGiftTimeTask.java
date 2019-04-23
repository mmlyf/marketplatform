package com.mtpt.timework;

import org.springframework.stereotype.Component;

import com.mtpt.config.BaseConfig;
import com.mtpt.extend.HttpRequest;

@Component("ordersucFlowGift")
public class OrderSuccessaFlowGiftTimeTask {
	public void runOrderSucceccFlowGift() {
		HttpRequest.sendGet(BaseConfig.RUN_ORDERSUC_FLOWGIFT, "");
	}
}
