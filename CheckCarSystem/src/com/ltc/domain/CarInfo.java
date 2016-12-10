package com.ltc.domain;

import java.util.Date;

/**
 * 此类用于封装车辆的检测信息
 * 
 * @author ltc 2016-5-20 14:45:03
 *
 */
public class CarInfo {
	private String carNumber;
	private String carStyle;
	private String userName;
	private Date carYearCheck;
	private Date technologyLevel;
	private Date towLevel;
	private Date compulsoryInsurance;
	private String phoneNum;
	private String notice;
	private long rownum;

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarStyle() {
		return carStyle;
	}

	public void setCarStyle(String carStyle) {
		this.carStyle = carStyle;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCarYearCheck() {
		return carYearCheck;
	}

	public void setCarYearCheck(Date carYearCheck) {
		this.carYearCheck = carYearCheck;
	}

	public Date getTechnologyLevel() {
		return technologyLevel;
	}

	public void setTechnologyLevel(Date technologyLevel) {
		this.technologyLevel = technologyLevel;
	}

	public Date getTowLevel() {
		return towLevel;
	}

	public void setTowLevel(Date towLevel) {
		this.towLevel = towLevel;
	}

	public Date getCompulsoryInsurance() {
		return compulsoryInsurance;
	}

	public void setCompulsoryInsurance(Date compulsoryInsurance) {
		this.compulsoryInsurance = compulsoryInsurance;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public long getRownum() {
		return rownum;
	}

	public void setRownum(long rownum) {
		this.rownum = rownum;
	}

	@Override
	public String toString() {
		return "CarInfo [carNumber=" + carNumber + ", carStyle=" + carStyle + ", userName=" + userName
				+ ", carYearCheck=" + carYearCheck + ", technologyLevel=" + technologyLevel + ", towLevel=" + towLevel
				+ ", compulsoryInsurance=" + compulsoryInsurance + ", phoneNum=" + phoneNum + ", notice=" + notice
				+ "]";
	}

}
