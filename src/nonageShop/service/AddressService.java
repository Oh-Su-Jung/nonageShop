package nonageShop.service;

import java.sql.Connection;
import java.util.ArrayList;

import nonageShop.dao.impl.AddressDaoImpl;
import nonageShop.ds.JndiDS;
import nonageShop.dto.Address;

public class AddressService {
	private AddressDaoImpl dao = AddressDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public AddressService() {
		dao.setCon(con);
	}
	
	public ArrayList<Address> selectAddressByDong(String dong){
		return dao.selectAddressByDong(dong);
	}

}
