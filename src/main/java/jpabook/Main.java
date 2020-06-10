package jpabook;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import jpabook.model.Address;
import jpabook.model.Member;
import jpabook.model.Order;
import jpabook.model.Product;
import jpabook.model.Team;

public class Main {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			init(em);
			
			TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
			
			List<Member> resultList = query.getResultList();
			
			for(Member member : resultList) {
				System.out.println("member = " + member);
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
	}
	
	private static void init(EntityManager em) {
		
		Team team1 = Team.builder().name("1팀").build();
		Team team2 = Team.builder().name("2팀").build();
		em.persist(team1);
		em.persist(team2);
		
		Member member1 = Member.builder().username("임민석").age(34).team(team2).build();
		Member member2 = Member.builder().username("홍길동").age(20).build();
		Member member3 = Member.builder().username("우수아").age(35).team(team2).build();
		Member member4 = Member.builder().username("원빈").age(39).build();
		Member member5 = Member.builder().username("박지성").age(40).team(team1).build();
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);
		em.persist(member5);
		
		Product product1 = Product.builder().name("선풍기").price(30000).stockAmount(5).build();
		Product product2 = Product.builder().name("모니터").price(100000).stockAmount(15).build();
		Product product3 = Product.builder().name("에어컨").price(3000000).stockAmount(100).build();
		em.persist(product1);
		em.persist(product2);
		em.persist(product3);
		
		Address address1 = Address.builder().city("화성").street("병점로").zipcode("14207").build();
		Address address2 = Address.builder().city("인천").street("만수로").zipcode("43252").build();
		Address address3 = Address.builder().city("서울").street("봉은사로").zipcode("53234").build();
		
		Order order1 = Order.builder().orderAmount(1).address(address1).member(member1).product(product1).build();
		Order order2 = Order.builder().orderAmount(1).address(address2).member(member3).product(product2).build();
		Order order3 = Order.builder().orderAmount(5).address(address3).member(member5).product(product3).build();
		em.persist(order1);
		em.persist(order2);
		em.persist(order3);
	}
}
