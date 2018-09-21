package com.hkt.cwp.models;

public class BaiThi {
	private Integer BaiThi_id;
	private String BaiThi_name;
	public BaiThi() {}
	public BaiThi(Integer baiThi_id, String baiThi_name) {
		super();
		BaiThi_id = baiThi_id;
		BaiThi_name = baiThi_name;
	}
	public Integer getBaiThi_id() {
		return BaiThi_id;
	}
	public void setBaiThi_id(Integer baiThi_id) {
		BaiThi_id = baiThi_id;
	}
	public String getBaiThi_name() {
		return BaiThi_name;
	}
	public void setBaiThi_name(String baiThi_name) {
		BaiThi_name = baiThi_name;
	}
	
}
