package nonageShop.service;

import java.sql.Connection;
import java.util.ArrayList;

import nonageShop.dao.impl.ProductDaoImpl;
import nonageShop.ds.JndiDS;
import nonageShop.dto.Product;

public class ProductService {
	private ProductDaoImpl dao = ProductDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public ProductService() {
		dao.setCon(con);
	}
	
	public ArrayList<Product> listNewProduct(){
		return dao.listNewProduct();
	}
	
	public ArrayList<Product> listBestProduct(){
		return dao.listBestProduct();
	}
	
	public Product getProduct(int no) {
		return dao.getProduct(no);
	}
	
	public ArrayList<Product> listKindProduct(String kind){
		return dao.listKindProduct(kind);
	}

}
