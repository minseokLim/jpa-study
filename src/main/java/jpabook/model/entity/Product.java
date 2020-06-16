package jpabook.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private int price;
	
	@Column(name = "STOCK_AMOUNT")
	private int stockAmount;

	@OneToMany(mappedBy = "product")
	private List<Order> orders = new ArrayList<Order>();
	
	@Builder
	public Product(String name, int price, int stockAmount) {
		this.name = name;
		this.price = price;
		this.stockAmount = stockAmount;
	}
}
