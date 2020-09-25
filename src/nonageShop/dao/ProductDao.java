package nonageShop.dao;

import java.util.ArrayList;

import nonageShop.dto.Product;

public interface ProductDao {
	// �Ż�ǰ ����Ʈ ������
	ArrayList<Product> listNewProduct();
	
	// ����Ʈ ��ǰ ����Ʈ ������
	ArrayList<Product> listBestProduct();
	
	// ��ǰ��ȣ�� ��ǰ ���� �Ѱ� ������
	Product getProduct(int no);
	
	// ��ǰ������ ��ǰ ����Ʈ ������
	ArrayList<Product> listKindProduct(String kind);
	
	// �����ڿ� - ��ǰ���� ����¡
	int totalRecord(String productName);
	public String pageNumber(int tpage, String name);
	ArrayList<Product> listProduct(int tpage, String productName);
	
	// �����ڿ� - ��ǰ ��� �� ����
	int insertProduct(Product producdt);
	int updateProduct(Product product);
}
