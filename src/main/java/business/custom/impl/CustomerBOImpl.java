package business.custom.impl;

import business.custom.CustomerBO;
import business.exception.AlreadyExistsInOrderException;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.CustomerDAO;
import dao.custom.OrderDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    private CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);
    private OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER);

    @Override
    public boolean saveCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.save(new Customer(customer.getId(), customer.getName(), customer.getAddress()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.update(new Customer(customer.getId(), customer.getName(), customer.getAddress()));
    }

    @Override
    public boolean deleteCustomer(String customerId) throws Exception {
        if (orderDAO.existsByCustomerId(customerId)){
            throw new AlreadyExistsInOrderException("Customer already exists in an order, hence unable to delete");
        }
        return customerDAO.delete(customerId);
    }

    @Override
    public List<CustomerDTO> findAllCustomers() throws Exception {
        List<Customer> alCustomers = customerDAO.findAll();
        List<CustomerDTO> dtos = new ArrayList<>();
        for (Customer customer : alCustomers) {
            dtos.add(new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getAddress()));
        }
        return dtos;
    }

    @Override
    public String getLastCustomerId() throws Exception {
        return customerDAO.getLastCustomerId();
    }

    @Override
    public CustomerDTO findCustomer(String customerId) throws Exception {
        Customer customer = customerDAO.find(customerId);
        return new CustomerDTO(customer.getCustomerId(),
                customer.getName(), customer.getAddress());
    }

    @Override
    public List<String> getAllCustomerIDs() throws Exception {
        List<Customer> customers = customerDAO.findAll();
        List<String> ids = new ArrayList<>();
        for (Customer customer : customers) {
            ids.add(customer.getCustomerId());
        }
        return ids;
    }
}
