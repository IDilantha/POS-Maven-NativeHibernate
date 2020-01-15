package db;

import entity.Customer;
import entity.Item;
import entity.Order;
import entity.OrderDetail;
import javafx.scene.control.Alert;
import lk.ijse.dep.crypto.DEPCrypt;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class HibernateUtil {
    private static String username;
    private static String password;

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        File file = new File("src/main/resources/application.properties");
        System.out.println(file.exists());
        Properties properties = new Properties();

        try(FileInputStream fis = new FileInputStream(file)){
            properties.load(fis);
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,"FIS EKE AULA");
        }
        username = DEPCrypt.decode(properties.getProperty("hibernate.connection.username"),"123");
        password = DEPCrypt.decode(properties.getProperty("hibernate.connection.password"),"123");

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .loadProperties(file)
                .applySetting("hibernate.connection.username", username)
                .applySetting("hibernate.connection.password", password)
                .build();

        Metadata metadata = new MetadataSources( standardRegistry )
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderDetail.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
                .build();

        return metadata.getSessionFactoryBuilder()
                .build();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
