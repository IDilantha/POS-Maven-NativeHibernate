package dao.custom.impl;


import dao.custom.QueryDAO;
import entity.CustomEntity;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    protected Session session;


    @Override
    public CustomEntity getOrderInfo(int orderId) throws Exception {
        return (CustomEntity) session.createQuery("SELECT NEW entity.CustomEntity(O.id,C.id,C.name,O.date) FROM Customer C " +
                "INNER JOIN C.orders O WHERE O.id=?1")
                .setParameter(1, orderId)
                .uniqueResult();

    }


/*
    @Override
    public CustomEntity getOrderInfo2(int orderId) throws Exception {
        NativeQuery nativeQuery = session.createNativeQuery("SELECT O.id, C.customerId, C.name, O.date, SUM(OD.qty * OD.unitPrice) AS Total  FROM Customer C INNER JOIN `Order` O ON C.customerId=O.customerId\" +\n" +
                "                \" INNER JOIN OrderDetail OD on O.id = OD.orderId WHERE O.id=? GROUP BY orderId");
        nativeQuery.setParameter(1,orderId);

        if (rst.next()){
            return new CustomEntity(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getDouble(5));
        }else{
            return null;
        }
    }

    @Override
    public List<CustomEntity> getOrdersInfo(String query) throws Exception {
        NativeQuery nativeQuery = session.createNativeQuery("SELECT O.id, C.customerId, C.name, O.date, SUM(OD.qty * OD.unitPrice) AS Total  FROM Customer C INNER JOIN `Order` O ON C.customerId=O.customerId " +
                "INNER JOIN OrderDetail OD on O.id = OD.orderId WHERE O.id LIKE ? OR C.customerId LIKE ? OR C.name LIKE ? OR O.date LIKE ? GROUP BY O.id");
        nativeQuery.setParameter(1,query);
        nativeQuery.setParameter(2,query);
        nativeQuery.setParameter(3,query);
        nativeQuery.setParameter(4,query);

        List<CustomEntity> al = new ArrayList<>();
        while (rst.next()){
            al.add(new CustomEntity(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getDouble(5)));
        }
        return al;
    }
*/

    @Override
    public void setSession(Session session) {
        this.session=session;
    }



    @Override
    public CustomEntity getOrderInfo2(int orderId) throws Exception {
        return null;
    }

    @Override
    public List<CustomEntity> getOrdersInfo(String query) throws Exception {
        return null;
    }
}
