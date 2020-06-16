package jpabook.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private int age;
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<Order>();
	
	@Builder
	public Member(String username, int age, Team team) {
		this.username = username;
		this.age = age;
		this.team = team;
		
		if(team != null) {
			team.getMembers().add(this);
		}
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", username=" + username + ", age=" + age + ", team=" + team + "]";
	}
}
