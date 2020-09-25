package nonageShop.dao;

import java.util.ArrayList;

import nonageShop.dto.Address;

public interface AddressDao {
	ArrayList<Address> selectAddressByDong(String dong);
}
