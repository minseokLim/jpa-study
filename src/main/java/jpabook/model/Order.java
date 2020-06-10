package jpabook.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ORDER_AMOUNT")
	private int orderAmount;

	@Embedded
	private Address address;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	@Builder
	public Order(int orderAmount, Address address, Member member, Product product) {
		this.orderAmount = orderAmount;
		this.address = address;
		this.member = member;
		this.product = product;
		
		if(member != null) {
			member.getOrders().add(this);
		}
		
		if(product != null) {
			product.getOrders().add(this);
		}
	}
}
