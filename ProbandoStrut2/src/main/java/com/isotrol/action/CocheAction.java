package com.isotrol.action;

import com.opensymphony.xwork2.ActionSupport;

public class CocheAction extends ActionSupport{

	public String indexCoche() {
		int aleatorio = (int)(Math.random()*2);
		String result = SUCCESS;
		if(aleatorio == 0) {
			result = ERROR;
		}
		return result;
	}
}
