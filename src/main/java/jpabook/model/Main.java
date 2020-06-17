package jpabook.model;

import static jpabook.model.entity.QMember.member;
import static jpabook.model.entity.QTeam.team;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.querydsl.jpa.impl.JPAQuery;

import jpabook.model.dto.QUserDTO;
import jpabook.model.entity.Address;
import jpabook.model.entity.Member;
import jpabook.model.entity.Order;
import jpabook.model.entity.Product;
import jpabook.model.entity.Team;
import jpabook.model.entity.item.Album;
import jpabook.model.entity.item.Book;
import jpabook.model.entity.item.Item;
import jpabook.model.entity.item.Movie;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			init(em);
			
			JPAQuery<Member> query = new JPAQuery<Member>(em);
			
			query.select(team, member.age.sum()).from(member).leftJoin(member.team, team).groupBy(member.team).fetch().stream().forEach(System.out::println);;
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
	}
	
	private static void printArr(Object[] arr) {
		System.out.println(String.join(", ", Arrays.stream(arr).map(String::valueOf).collect(Collectors.toList())));
	}
	
	private static void init(EntityManager em) {
		
		Team team1 = Team.builder().name("1팀").build();
		Team team2 = Team.builder().name("2팀").build();
		em.persist(team1);
		em.persist(team2);
		
		Member member1 = Member.builder().username("임민석").age(34).team(team2).build();
		Member member2 = Member.builder().username("홍길동").age(25).build();
		Member member3 = Member.builder().username("우수아").age(34).team(team2).build();
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
		Order order3 = Order.builder().orderAmount(10).address(address3).member(member5).product(product3).build();
		em.persist(order1);
		em.persist(order2);
		em.persist(order3);
		
		Item item1 = Movie.builder().name("영화").price(50000).stockQuantity(10).director("감독").actor("배우").build();
		Item item2 = Book.builder().name("책").price(4000).stockQuantity(15).author("저자").isbn("isbn").build();
		Item item3 = Album.builder().name("앨범").price(10000).stockQuantity(200).artist("아티스트").etc("기타사항").build();
		em.persist(item1);
		em.persist(item2);
		em.persist(item3);
	}
}
