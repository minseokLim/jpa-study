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
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_ID")
	private Long id;
	
	private String name;
	
	private String city;
	
	private String street;
	
	private String zipcode;
	
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<Order>();

	@Builder
	public Member(String name, String city, String street, String zipcode) {
		this.name = name;
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", city=" + city + ", street=" + street + ", zipcode=" + zipcode + "]";
	}
}
