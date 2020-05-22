package jpabook.model;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.model.entity.Category;
import jpabook.model.entity.Delivery;
import jpabook.model.entity.DeliveryStatus;
import jpabook.model.entity.Item;
import jpabook.model.entity.Member;
import jpabook.model.entity.Order;
import jpabook.model.entity.OrderItem;
import jpabook.model.entity.OrderStatus;

public class Main {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Category category1 = Category.builder().name("악기").build();
			Category category2 = Category.builder().name("운동").build();
			Category category3 = Category.builder().name("취미").build();
			category1.setParent(category3);
			category2.setParent(category3);
			
			em.persist(category1);
			em.persist(category2);
			em.persist(category3);
			
			Item item = Item.builder().name("Bass Guitar").price(3000).stockQuantity(5).build();
			item.addCategory(category1);
			em.persist(item);
			
			Member member = Member.builder().name("Lim Min Seok").city("Toronto").street("Finch").zipcode("12345").build();
			em.persist(member);
			
			Delivery delivery = Delivery.builder().city("서울").status(DeliveryStatus.READY).street("봉은사로").zipcode("Z1231").build();
			em.persist(delivery);
			
			Order order = Order.builder().orderDate(new Date()).status(OrderStatus.ORDER).build();
			order.setMember(member);
			order.setDelivery(delivery);
			em.persist(order);
			
			OrderItem orderItem = OrderItem.builder().item(item).orderPrice(6000).count(2).build();
			orderItem.setItem(item);
			orderItem.setOrder(order);
			em.persist(orderItem);
			
			Order o = em.find(Order.class, 1L);
			Member m = o.getMember();
			
			print(m);
			print("OrderDate : " + m.getOrders().get(0).getDelivery().getCity());
			print(o.getOrderItems().get(0).getItem().getCategories().get(0).getParent().getName());
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
	}
	
	private static void print(Object value) {
		System.out.println("****** " + value.toString());
	}
}
