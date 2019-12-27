package com.waoqi.common.exception;


import com.waoqi.common.utils.CodeMsg;
import com.waoqi.common.utils.StringUtils;

public class GlobalException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private CodeMsg cm;

	public GlobalException(CodeMsg cm) {
		super(cm.toString());
		this.cm = cm;
	}


	public GlobalException(CodeMsg cm,String msg) {
		super(StringUtils.isEmpty(msg)?cm.toString():msg);
		if (StringUtils.isNotBlank(msg)){
			cm.setMsg(msg);
		}
		this.cm = cm;
	}

	public CodeMsg getCm() {
		return cm;
	}

}
