package jpabook.model;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.model.entity.Address;
import jpabook.model.entity.Category;
import jpabook.model.entity.Delivery;
import jpabook.model.entity.Member;
import jpabook.model.entity.Order;
import jpabook.model.entity.OrderItem;
import jpabook.model.entity.item.Movie;
import jpabook.model.enums.DeliveryStatus;
import jpabook.model.enums.OrderStatus;

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
			
			Movie item = Movie.builder().name("타이타닉").price(10000).stockQuantity(100).director("제임스 카메룬").actor("디카프리오").build();
			item.addCategory(category1);
			em.persist(item);
			
			Member member = Member.builder().name("Lim Min Seok").address(Address.builder().city("Toronto").street("Finch").zipcode("12345").build()).build();
			em.persist(member);
			
			Delivery delivery = Delivery.builder().address(Address.builder().city("서울").street("봉은사로").zipcode("Z1231").build()).status(DeliveryStatus.READY).build();
			
			OrderItem orderItem = OrderItem.builder().item(item).orderPrice(6000).count(2).build();
			orderItem.setItem(item);
			
			Order order = Order.builder().orderDate(new Date()).status(OrderStatus.ORDER).build();
			order.setMember(member);
			order.setDelivery(delivery);
			order.addOrderItem(orderItem);
			em.persist(order);
			
			Order o = em.find(Order.class, 1L);
			Member m = o.getMember();
			
			print(m);
			print("OrderDate : " + m.getOrders().get(0).getDelivery().getAddress().getCity());
			print(o.getOrderItems().get(0).getItem().getCategories().get(0).getParent().getName());
			print(member == m);
			print(((Movie) m.getOrders().get(0).getOrderItems().get(0).getItem()).getActor());
			
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
