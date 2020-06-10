package jpabook.model.entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("B")
@NoArgsConstructor
public class Book extends Item {

	private String author;
	
	private String isbn;

	@Builder
	public Book(String name, int price, int stockQuantity, String author, String isbn) {
		super(name, price, stockQuantity);
		this.author = author;
		this.isbn = isbn;
	}
}
