package com.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dto.BuyerDTO;
import com.user.dto.CartDTO;
import com.user.dto.SellerDTO;
import com.user.entity.Buyer;
import com.user.entity.Cart;
import com.user.entity.Seller;
import com.user.entity.Wishlist;
import com.user.repository.BuyerRepository;
import com.user.repository.CartRepository;
import com.user.repository.SellerRepository;
import com.user.repository.WishlistRepository;
import com.user.utility.MyPrimaryKey;
import com.user.validator.Validate;

//Implementation of UserService interface
@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	private static int b;
	private static int s;
	
	static {
		b=100;
		s=100;
	}
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private WishlistRepository wishlistRepository;

	
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public String buyerRegistration(BuyerDTO buyerDTO) throws Exception
	{
		// TODO Auto-generated method stub
		Validate.validateBuyer(buyerDTO);
		
		Buyer buyer = buyerRepository.findByPhoneNumber(buyerDTO.getPhoneNumber());
		if(buyer != null)
		{
			throw new Exception("Buyer already present");
		}
		String id = "B" + b++;
		buyer = new Buyer();
		buyer.setBuyerId(id);
		buyer.setEmail(buyerDTO.getEmail());
		buyer.setName(buyerDTO.getName());
		buyer.setPhoneNumber(buyerDTO.getPhoneNumber());
		buyer.setPassword(buyerDTO.getPassword());
		buyer.setIsActive("True");
		buyer.setIsPrivileged("False");
		buyer.setRewardPoints("0");
		buyerRepository.save(buyer);
		return buyer.getBuyerId();
	}

	@Override
	public String sellerRegistration(SellerDTO sellerDTO) throws Exception {
		// TODO Auto-generated method stub
		Validate.validateSeller(sellerDTO);
		
		Seller seller = sellerRepository.findByPhoneNumber(sellerDTO.getPhoneNumber());
		if(seller != null)
			throw new Exception("Seller Already present");
		
		String id = "S" + s++;
		seller = new Seller();
		seller.setEmail(sellerDTO.getEmail());
		seller.setSellerId(id);
		seller.setName(sellerDTO.getName());
		seller.setPassword(sellerDTO.getPassword());
		seller.setIsActive("True");
		seller.setPhoneNumber(sellerDTO.getPhoneNumber());
		sellerRepository.save(seller);
		return seller.getSellerId();
	}

	@Override
	public String buyerLogin(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		if(!Validate.validateEmail(email))
			throw new Exception("Enter valid email id");
		Buyer buyer = buyerRepository.findByEmail(email);
		if(buyer == null)
			throw new Exception("Wrong credentials");
		if(!buyer.getPassword().equals(password))
			throw new Exception("Wrong credentials");
		buyer.setIsActive("True");
		buyerRepository.save(buyer);
		return "Successfully Login";
	}

	@Override
	public String sellerLogin(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		if(!Validate.validateEmail(email))
			throw new Exception("Enter valid email id");
		Seller seller = sellerRepository.findByEmail(email);
		if(seller == null)
			throw new Exception("Wrong credentials");
		if(!seller.getPassword().equals(password))
			throw new Exception("Wrong credentials");
		seller.setIsActive("True");
		sellerRepository.save(seller);
		return "Successfully Login";
	}

	@Override
	public String deleteBuyer(String id) throws Exception{
		// TODO Auto-generated method stub
		Buyer buyer = buyerRepository.findByBuyerId(id);
		buyerRepository.delete(buyer);
		return "Account successfully deleted";
	}

	@Override
	public String deleteSeller(String id) throws Exception{
		// TODO Auto-generated method stub
		Seller seller = sellerRepository.findBySellerId(id);
		sellerRepository.delete(seller);
		return "Account successfully deleted";
	}

	@Override
	public String AddToWishlist(String prodId, String buyerId) throws Exception {
		// TODO Auto-generated method stub
		Buyer buyer=buyerRepository.findByBuyerId(buyerId);
		if(buyer == null)
		{
			throw new Exception("Buyer Id is invalid");
		}
		MyPrimaryKey cust = new MyPrimaryKey(prodId,buyerId);
		Wishlist w = new Wishlist();
		w.setMyPrimaryKey(cust);
		wishlistRepository.save(w);
		return "Added Successfully to Wishlist";
	}
	
	@Override
	public String removeFromWishlist(String buyerId, String prodId) throws Exception {
		// TODO Auto-generated method stub
		Wishlist wishlist = wishlistRepository.findByMyPrimaryKeyBuyerIdAndMyPrimaryKeyProdId(buyerId, prodId);
		if(wishlist==null)
			throw new Exception("No Such Item In Cart");
		wishlistRepository.deleteByMyPrimaryKeyBuyerIdAndMyPrimaryKeyProdId(buyerId, prodId);
		return "Product remove from wishlist successfully";
	}

	@Override
	public List<SellerDTO> findAllSeller() {
		// TODO Auto-generated method stub
		List<Seller> sellers = (List<Seller>) sellerRepository.findAll();
		List<SellerDTO> list = new ArrayList<SellerDTO>();
		for(Seller s: sellers)
		{
			SellerDTO sellerDTO = new SellerDTO();
			sellerDTO.setEmail(s.getEmail());
			sellerDTO.setIsActive(s.getIsActive());
			sellerDTO.setName(s.getName());
			sellerDTO.setPassword("Hidden");
			sellerDTO.setPhoneNumber(s.getPhoneNumber());
			list.add(sellerDTO);
		}
		return list;
	}

	@Override
	public String userMode(String buyerId) throws Exception {
		// TODO Auto-generated method stub
		Buyer buyer = buyerRepository.findByBuyerId(buyerId);
		if(buyer==null)
			throw new Exception("Not eligible for privilege mode");
		buyer.setIsPrivileged("True");
		buyerRepository.save(buyer);
		return "Successfully changed to privilege mode";
	}

	//Add to cart service
	@Override
	public String addToCart(String buyerId, String prodId, Integer quantity) throws Exception {
		MyPrimaryKey pk=new MyPrimaryKey(prodId, buyerId);
		Cart cart=new Cart();
		cart.setMyPrimaryKey(pk);
		cart.setQuantity(quantity);
		cartRepository.save(cart);
		return "Added successfully";
	}

	//Get cart service
	@Override
	public List<CartDTO> getCart(String buyerId) throws Exception {
		List<Cart> carts=cartRepository.findByMyPrimaryKeyBuyerId(buyerId);
		if(carts.isEmpty())
		{
			throw new Exception("Cart is empty!!");
		}
		
		List<CartDTO> cartList=new ArrayList<>();
		for(Cart cart:carts)
		{
			CartDTO cartDto=new CartDTO();
			cartDto.setBuyerId(cart.getMyPrimaryKey().getBuyerId());
			cartDto.setProdId(cart.getMyPrimaryKey().getProdId());
			cartDto.setQuantity(cart.getQuantity());
			cartList.add(cartDto);
		}
		return cartList;
	}
	
	//Empty cart service
	@Override
	public String emptyMyCart(String buyerId) throws Exception {
		List<Cart> carts=cartRepository.findByMyPrimaryKeyBuyerId(buyerId);
		if(carts.isEmpty())
		{
			throw new Exception("Cart is empty!!");
		}
		cartRepository.deleteByMyPrimaryKeyBuyerId(buyerId);
		return "Successfully deleted";
	}

	//Remove item from cart service
	@Override
	public String removeItemFromCart(String buyerId, String prodId) throws Exception {
		Cart cart=cartRepository.findByMyPrimaryKeyBuyerIdAndMyPrimaryKeyProdId(buyerId, prodId);
		if(cart==null)
		{
			throw new Exception("No such item in the cart");
		}
		cartRepository.deleteByMyPrimaryKeyBuyerIdAndMyPrimaryKeyProdId(buyerId, prodId);
		return "Successfylly deleted";
	}

	//Update reward points by deletion service
	@Override
	public String updateRewardPointsDelete(String buyerId, Integer rewardPoints) throws Exception {
		Buyer buyer=buyerRepository.findByBuyerId(buyerId);
		if(buyer == null)
		{
			throw new Exception("Buyer with buyer id "+ buyerId +" does not exists");
		}
		buyer.setRewardPoints(String.valueOf((Integer.parseInt(buyer.getRewardPoints())-rewardPoints)));
		buyerRepository.save(buyer);
		return "Reward points deleted for buyer with id : "+buyer.getBuyerId();
	}

	//Update reward points by addition service
	@Override
	public String updateRewardPointsAdd(String buyerId, Integer rewardPoints) throws Exception {
		Buyer buyer=buyerRepository.findByBuyerId(buyerId);
		if(buyer == null)
		{
			throw new Exception("Buyer with buyer id "+ buyerId +" does not exists");
		}
		buyer.setRewardPoints(String.valueOf((Integer.parseInt(buyer.getRewardPoints())+rewardPoints)));
		buyerRepository.save(buyer);
		return "Reward points added for buyer with id : "+buyer.getBuyerId();
	}

}
