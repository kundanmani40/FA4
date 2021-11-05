package com.user.service;

import java.util.List;

import com.user.dto.BuyerDTO;
import com.user.dto.CartDTO;
import com.user.dto.SellerDTO;

//Service interface which enables abstraction
public interface UserService {
	
	public String buyerRegistration(BuyerDTO buyerDTO) throws Exception;
	
	public String sellerRegistration(SellerDTO sellerDTO) throws Exception;
	
	public String buyerLogin(String email, String password) throws Exception;
	
	public String sellerLogin(String email, String password) throws Exception;
	
	public String deactivateBuyer(String id) throws Exception;
	
	public String deactivateSeller(String id) throws Exception;
	
	public String AddToWishlist(String prodId,String buyerId) throws Exception;
	
	public String removeFromWishlist(String buyerId, String prodId) throws Exception; 

	public List<SellerDTO> findAllSeller() throws Exception;
	
	public String userMode(String buyerId) throws Exception;
	
	public String getBuyerMode(String id) throws Exception;
	
	public String subscribeProduct(String buyerId) throws Exception;
	
	public String addToCart(String buyerId, String prodId, Integer quantity) throws Exception;
	
	public List<CartDTO> getCart(String buyerId) throws Exception;
	
	public String emptyMyCart(String buyerId) throws Exception;
	
	public String removeItemFromCart(String buyerId, String prodId) throws Exception;
	
	public String visitorRegisterAsBuyer(BuyerDTO buyerDto) throws Exception;
	
	public String visitorRegisterAsSeller(SellerDTO sellerDto) throws Exception;
	
	public String updateRewardPointsDelete(String buyerId, Integer rewardPoints) throws Exception;
	
	public String updateRewardPointsAdd(String buyerId, Integer rewardPoints) throws Exception;

}
