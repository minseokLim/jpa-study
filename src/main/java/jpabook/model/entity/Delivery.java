package jpabook.model.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import jpabook.model.enums.DeliveryStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Delivery extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DELIVERY_ID")
	private Long id;
	
	@OneToOne(mappedBy = "delivery")
	private Order order;
	
	@Embedded
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;
	
	public void setOrder(Order order) {
		this.order = order;
		
		if(order.getDelivery() != this) {
			order.setDelivery(this);
		}
	}

	@Builder
	public Delivery(Address address, DeliveryStatus status) {
		this.address = address;
		this.status = status;
	}
}
