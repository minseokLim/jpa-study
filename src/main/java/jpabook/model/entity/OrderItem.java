package jpabook.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jpabook.model.entity.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ORDER_ITEM")
@NoArgsConstructor
public class OrderItem extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ITEM_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private Order order;
	
	private int orderPrice;
	
	private int count;
	
	public void setOrder(Order order) {
		
		if(this.order != null) {
			this.order.getOrderItems().remove(this);
		}
		
		this.order = order;
		order.getOrderItems().add(this);
	}

	@Builder
	public OrderItem(Item item, Order order, int orderPrice, int count) {
		this.item = item;
		this.order = order;
		this.orderPrice = orderPrice;
		this.count = count;
	}
}
