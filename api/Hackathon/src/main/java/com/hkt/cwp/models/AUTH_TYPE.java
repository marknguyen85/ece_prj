package com.hkt.cwp.models;

import com.hkt.cwp.Utils.Constants;

public enum AUTH_TYPE {
	 MANAGE(Constants.AUTH_MANAGE),EMP(Constants.AUTH_EMP), ALL(Constants.ALL);

    private String value;

    private AUTH_TYPE(String value) {
        this.value = value;
    }

    public String get() {
        return this.value;
    }
}
