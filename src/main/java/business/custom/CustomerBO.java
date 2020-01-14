package business.custom;

import business.SuperBO;
import dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {

    boolean saveCustomer(CustomerDTO customer)throws Exception;

    boolean updateCustomer(CustomerDTO customer)throws Exception;

    boolean deleteCustomer(String customerId) throws Exception;

    List<CustomerDTO> findAllCustomers() throws Exception;

    String getLastCustomerId()throws Exception;

    CustomerDTO findCustomer(String customerId) throws Exception;

    List<String> getAllCustomerIDs() throws Exception;

}
