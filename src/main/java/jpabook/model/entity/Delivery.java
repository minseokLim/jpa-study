package jpabook.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Delivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DELIVERY_ID")
	private Long id;
	
	@OneToOne(mappedBy = "delivery")
	private Order order;
	
	private String city;
	
	private String street;
	
	private String zipcode;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;
	
	public void setOrder(Order order) {
		this.order = order;
		
		if(order.getDelivery() != this) {
			order.setDelivery(this);
		}
	}

	@Builder
	public Delivery(String city, String street, String zipcode, DeliveryStatus status) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
		this.status = status;
	}
}
