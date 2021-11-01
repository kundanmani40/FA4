package com.product.validator;

import com.product.dto.ProductDTO;

public class ProductValidator {
	
	public static void validateProduct(ProductDTO product) throws Exception {
		
		if(!validateName(product.getProductName()))
			throw new Exception("Validator.VALIDATE_NAME");
		
		if(!validateDescription(product.getDescription()))
			throw new Exception("Validator.VALIDATE_DESCRIPTION");
			
		if(!validatePrice(product.getPrice()))
			throw new Exception("Validator.VALIDATE_PRICE");
		
		if(!validateStock(product.getStock()))
			throw new Exception("Validator.VALIDATE_STOCK");
		
		if(!validateImage(product.getImage()))
			throw new Exception("Validator.VALIDATE_IMAGE");
		
	}
	
	public static boolean validateName(String name)
	{
		String regex = "([A-Za-z]+([ ][A-Za-z]+)*){1,100}";
		
		if(name.matches(regex))
		{
			return true;
		}
		else
		return false;
	}
	
	public static boolean validateDescription(String desc)
	{
		String regex = "([A-Za-z]+([ ][A-Za-z]+)*){1,500}";
		
		if(desc.matches(regex))
		{
			return true;
		}
		else
		return false;
	}
	
	public static boolean validatePrice(Float price)
	{
		if(price >=200)
		{
			return true;
		}
		else
		return false;
	}
	
	public static boolean validateStock(Integer stock)
	{
		if(stock>=10)
		{
			return true;
		}
		else
		return false;
	}
	
	public static boolean validateImage(String image)
	{
		String regex = "[A-Za-z]+[\\.](png|jpeg)";
		
		if(image.matches(regex)) {
			return true;
		}
		else
		return false;
	}

}
