package com.ssafy.sunbis.model.dto;

public class HouseInfoDto {

	// houseinfo테이블에 있는 정보
	// 추 후에 아파트 정보를 받기위해 필요한 pk니 꼭 필요함
	private long aptCode;

	// 아파트 거래정보를 보여주기 위한 기본정보
	private String apartmentName;

	// 주소
	private String sidoName;
	private String gugunName;
	private String dong;
	// 얘가 법정동코드 시+구+동 다 가지고있는 10자리임
	// 앞에 5자리가 시군구 코드
	// 뒤에 5자리가 읍면동 코드
	private String dongCode;
	// 동 + 지번으로 주소표현할때 필요함
	private String jibun;
	// 도로명 주소
	private String roadName;

	// 아파트 정보
	private int buildYear;
	// 아파트 위치
	private String lng;
	private String lat;

	// 아파트 거래 정보
	private long no;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String floor;
	public long getAptCode() {
		return aptCode;
	}
	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public int getDealDay() {
		return dealDay;
	}
	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	@Override
	public String toString() {
		return "HouseInfoDto [aptCode=" + aptCode + ", apartmentName=" + apartmentName + ", sidoName=" + sidoName
				+ ", gugunName=" + gugunName + ", dong=" + dong + ", dongCode=" + dongCode + ", jibun=" + jibun
				+ ", roadName=" + roadName + ", buildYear=" + buildYear + ", lng=" + lng + ", lat=" + lat + ", no=" + no
				+ ", dealAmount=" + dealAmount + ", dealYear=" + dealYear + ", dealMonth=" + dealMonth + ", dealDay="
				+ dealDay + ", area=" + area + ", floor=" + floor + "]";
	}


}
