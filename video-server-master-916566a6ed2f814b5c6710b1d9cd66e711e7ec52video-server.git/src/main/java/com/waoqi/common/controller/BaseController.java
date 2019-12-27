package com.waoqi.common.controller;

import com.waoqi.system.domain.UserToken;
import org.springframework.stereotype.Controller;
import com.waoqi.common.utils.ShiroUtils;
import com.waoqi.system.domain.UserDO;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}
