package com.buy5.marketplace.repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.buy5.marketplace.model.Order;
import com.buy5.marketplace.model.Person;
import com.buy5.marketplace.model.Product;

import static com.buy5.marketplace.Constants.*;

@Repository
@CrossOrigin(origins = CROSS_ORIGIN_URL)
public class CartRepository {
	private static final String LOG_FORMAT_SUCCESS = "{4,date} {4,time} User : {0} has bought {2,number} {1}(s) for Total Price: {3, number}";
	private static final String LOG_FORMAT_FAILURE = "{4,date} {4,time} User : {0} could not buy {2,number} {1}(s)";
	
	@Autowired
	private ProductRepository prodRepo;
	
	@Autowired
	private UserRepository usersRepo;
	
	private List<Order> orders = new ArrayList<Order>();
	
	public List<Order> findAllOrders() {
		return orders;
	}
	
	public Optional<Order> findOrder(Integer orderId) {
		return orders.parallelStream()
					 .filter(ord -> ord.getId() == orderId)
					 .findFirst();
	}
	
	public synchronized boolean updateOrder(String userName, String productName, int quantity) {
		final Order order = new Order(userName, productName, quantity);
		
		boolean successFlag = false;
		
		Optional<Person> optPers = usersRepo.findByUsermame(userName);
		// Proceed only if User is present in system
		if (optPers.isPresent()) {
			Optional<Product> optProd = prodRepo.findByName(productName);
			if (optProd.isPresent()) {
				Product prod = optProd.get();
				
				// try to adjust inventory
				if (prodRepo.adjustInvetory(prod.getId(), (-quantity)))
				{
					final int totalPrice = (quantity * prod.getPrice());
					order.setTotalPrice(totalPrice);
					
					String logMsg = MessageFormat.format(LOG_FORMAT_SUCCESS 
														,userName	//0
														,productName //1
														,quantity //2
														,totalPrice // 3
														,order.getOrderDate() // 4
														);
					System.out.println(logMsg);
					order.setSuccess(true);
					
					orders.add(order);
					
					successFlag = true;
				} 
				else
				{
					String logMsg = MessageFormat.format(LOG_FORMAT_FAILURE 
														,userName	//0
														,productName //1
														,quantity //2
														,0 // 3
														,order.getOrderDate() // 4
														);
					System.err.println(logMsg);
					order.setSuccess(false);
					
					orders.add(order);
					
					successFlag = false;
				}
			}
		}
		
		order.setSuccess(successFlag);
		return successFlag;
	}
}
