package com.ecommerce.app.services;

import com.ecommerce.app.models.Order;
import com.ecommerce.app.models.Product;
import com.ecommerce.app.models.ProductGroup;
import com.ecommerce.app.models.ProductImage;
import com.ecommerce.app.repositories.GroupRepository;
import com.ecommerce.app.repositories.OrderRepository;
import com.ecommerce.app.repositories.ProductRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@Service
public class EcommerceService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private SessionFactory sessionFactory;


    /* PRODUCT */
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public Product getProduct(long id){
        return productRepository.findById(id).get();
    }
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public String addProductImage(final String productId, final String filename){

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        ProductImage image = new ProductImage();
        image.setProductId(Long.parseLong(productId));
        image.setPath(filename);

        try {
            String res = session.save(image).toString();
            session.getTransaction().commit();
            return res;
        } catch (HibernateException e) {
            System.out.print(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return "";
    }

    /* GROUPS */
    public List<ProductGroup> getGroups(){
        return groupRepository.findAll();
    }
    public ProductGroup getGroup(long id){
        return groupRepository.findById(id).get();
    }
    public ProductGroup saveGroup(ProductGroup group){
        return groupRepository.save(group);
    }

    /* ORDERS */
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
    public Order getOrder(long id){
        return orderRepository.findById(id).get();
    }
    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }
}
