package com.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dto.ProductDTO;
import com.product.entity.Product;
import com.product.entity.SubscribedProduct;
import com.product.repository.ProductRepository;
import com.product.repository.SubscribedProductRepository;
import com.product.utility.MyPrimaryKey;
import com.product.validator.ProductValidator;

//Implementation of ProductService interface
@Service(value = "productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SubscribedProductRepository subscribedProductRepository;
	
	//Creating unique static Pid
	private static int pId;
	
	static {
		pId=1000;
	}
	
	//Add product service
	@Override
	public String addProduct(ProductDTO productDTO) throws Exception
	{
		ProductValidator.validateProduct(productDTO);
		Product product = productRepository.findByProductName(productDTO.getProductName());
		if(product != null)
		{
			updateStockOfProdAdd(product.getProdId(), productDTO.getStock());
			return product.getProdId()+". Just added stock as product already exists";
		}
		else
		{
			product = new Product();
			String id = "P"+pId++;
			product.setProdId(id);
			product.setProductName(productDTO.getProductName());
			product.setPrice(productDTO.getPrice());
			product.setCategory(productDTO.getCategory());
			product.setDescription(productDTO.getDescription());
			product.setImage(productDTO.getImage());
			product.setSubCategory(productDTO.getSubCategory());
			product.setSellerId(productDTO.getSellerId());
			product.setProductRating(productDTO.getProductRating());
			product.setStock(productDTO.getStock());
			productRepository.save(product);
			return product.getProdId();
		}
	}
		
	//Getting all products service
	@Override
	public List<ProductDTO> viewAllProducts() throws Exception
	{
		List<Product> list = productRepository.findAll();
		if(list.isEmpty())
		{
			throw new Exception("There are no products");
		}
		List<ProductDTO> list1 = new ArrayList<>();
		for(Product i:list)
		{
			ProductDTO prod = new ProductDTO();
			prod.setCategory(i.getCategory());
			prod.setDescription(i.getDescription());
			prod.setImage(i.getImage());
			prod.setPrice(i.getPrice());
			prod.setProdId(i.getProdId());
			prod.setProductName(i.getProductName());
			prod.setProductRating(i.getProductRating());
			prod.setSellerId(i.getSellerId());
			prod.setStock(i.getStock());
			prod.setSubCategory(i.getSubCategory());
			list1.add(prod);
		}
		return list1;
	}

	//Getting product by name service
	@Override
	public ProductDTO getProductByName(String name) throws Exception
	{
		Product product = productRepository.findByProductName(name);
		if(product == null)
		{
			throw new Exception("Service.PRODUCT_NOT_FOUND");
		}
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProdId(product.getProdId());
		productDTO.setCategory(product.getCategory());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage(product.getImage());
		productDTO.setPrice(product.getPrice());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductRating(product.getProductRating());
		productDTO.setSellerId(product.getSellerId());
		productDTO.setStock(product.getStock());
		productDTO.setSubCategory(product.getCategory());
		return productDTO;
	}
	
	//Get product by Id service
	@Override
	public ProductDTO getProductById(String prodId) throws Exception
	{
		Product product=productRepository.findByProdId(prodId);
		if(product==null)
		{
			throw new Exception("Products do not exist");
		}
		ProductDTO productDto=new ProductDTO();
		productDto.setProdId(product.getProdId());
		productDto.setProductName(product.getProductName());
		productDto.setPrice(product.getPrice());
		productDto.setStock(product.getStock());
		productDto.setDescription(product.getDescription());
		productDto.setImage(product.getImage());
		productDto.setSellerId(product.getSellerId());
		productDto.setCategory(product.getCategory());
		productDto.setSubCategory(product.getSubCategory());
		productDto.setProductRating(product.getProductRating());
		return productDto;
	}
		
		
	//Get product by Category service
	@Override
	public List<ProductDTO> getProductByCategory(String category) throws Exception
	{
		List<Product> list = productRepository.findByCategory(category);
		if(list.isEmpty())
		{
			throw new Exception("Service.CATEGORY_ERROR");
		}
		List<ProductDTO> list1 = new ArrayList<>();
		for(Product product : list)
		{
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProdId(product.getProdId());
			productDTO.setCategory(product.getCategory());
			productDTO.setDescription(product.getDescription());
			productDTO.setImage(product.getImage());
			productDTO.setPrice(product.getPrice());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductRating(product.getProductRating());
			productDTO.setSellerId(product.getSellerId());
			productDTO.setStock(product.getStock());
			productDTO.setSubCategory(product.getCategory());
			list1.add(productDTO);
		}
		return list1;
	}
	
	//Delete product service
	@Override
	public String deleteProduct(String id) throws Exception
	{
		Product product = productRepository.findByProdId(id);
		if(product == null)
		{
			throw new Exception("Service.PRODUCT_NOT_AVAILABLE");
		}
		productRepository.delete(product);
		return "Product successfully deleted";
	}

	//Updating product stock by delete service
	@Override
	public Boolean updateStockOfProdDelete(String prodId, Integer quantity) throws Exception
	{
		Optional<Product> optional = productRepository.findById(prodId);
		Product product = optional.orElseThrow(()-> new Exception("Product does not exist"));
		if(product.getStock()>=quantity) {
			product.setStock(product.getStock()-quantity);
			return true;
		}
		return false;		
	}
	
	//Updating product stock by add service
	@Override
	public Boolean updateStockOfProdAdd(String prodId, Integer quantity) throws Exception
	{
		Optional<Product> optional = productRepository.findById(prodId);
		Product product = optional.orElseThrow(()-> new Exception("Product does not exist"));
		if(product.getProdId()!=null) {
			product.setStock(product.getStock()+quantity);
			return true;
		}
		return false;		
	}
	
	@Override
	public String subscribeProduct(String buyerId, String prodId, Integer quantity) throws Exception
	{
		Product product=productRepository.findByProdId(prodId);
		if(product == null)
		{
			throw new Exception("Product does not exist");
		}
		if(product.getStock()>=quantity)
		{
			SubscribedProduct subscribedProduct=new SubscribedProduct();
			subscribedProduct.setCompositepk(new MyPrimaryKey(buyerId, prodId));
			subscribedProduct.setQuantity(quantity);
			subscribedProductRepository.save(subscribedProduct);
			return "Subsribed complete for buyer with id: "+buyerId+" to product with id: "+prodId;
		}
		else
		{
			return "Not enough stock";
		}
	}

}
