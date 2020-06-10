package jpabook.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_ID")
	private Long id;
	
	private String name;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<Order>();

	@Builder
	public Member(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
}
