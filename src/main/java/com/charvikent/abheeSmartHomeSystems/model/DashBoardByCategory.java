package com.charvikent.abheeSmartHomeSystems.model;

public class DashBoardByCategory {
	
	
	public String categoryId;
	public String categoryName;
	public String kStatus;
	public String kStatusNameWithId;
	
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getkStatus() {
		return kStatus;
	}
	public void setkStatus(String kStatus) {
		this.kStatus = kStatus;
	}
	public String getkStatusNameWithId() {
		return kStatusNameWithId;
	}
	public void setkStatusNameWithId(String kStatusNameWithId) {
		this.kStatusNameWithId = kStatusNameWithId;
	}
	@Override
	public String toString() {
		return "DashBoardByCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + ", kStatus="
				+ kStatus + ", kStatusNameWithId=" + kStatusNameWithId + "]";
	}
	

}
