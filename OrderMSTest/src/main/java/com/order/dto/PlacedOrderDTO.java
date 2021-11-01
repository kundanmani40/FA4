package com.order.dto;

//PlacedOrderDTO class that tells about instances(properties) for 'PlacedOrder'
public class PlacedOrderDTO {
	
	//Instances of 'PlacedOrder'
	private String orderId;
	private String buyerId;
	private Integer rewardPoints;

	//Getters and Setters methods for above instances
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	//toString() method to get proper println during testing
	@Override
	public String toString() {
		return "PlacedOrderDTO [orderId=" + orderId + ", buyerId=" + buyerId + ", rewardPoints=" + rewardPoints + "]";
	}

}
