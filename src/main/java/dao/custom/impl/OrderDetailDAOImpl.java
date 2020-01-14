package dao.custom.impl;


import dao.CrudDAOImpl;
import dao.custom.OrderDetailDAO;
import entity.OrderDetail;
import entity.OrderDetailPK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail,OrderDetailPK> implements OrderDetailDAO {

    @Override
    public boolean existsByItemCode(String itemCode) throws Exception {
        return (boolean) session.createNativeQuery("SELECT * FROM OrderDetail WHERE itemCode=?1").setParameter(1,itemCode).uniqueResult();
    }
}
