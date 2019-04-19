package com.mtpt.timework;

import org.springframework.stereotype.Component;

import com.mtpt.config.BaseConfig;
import com.mtpt.extend.HttpRequest;

@Component("ordersTask")
public class OrderResultTimeTask {
	public void runSelectSecondConfirm() {
		HttpRequest.sendGet(BaseConfig.RUN_SELECT_CONFIRM, "");
	}
}
