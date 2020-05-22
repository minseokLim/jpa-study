package jpabook.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private Long id;
	
	private String name;
	
	@ManyToMany
	@JoinTable(name = "CATEGORY_ITEM", joinColumns = @JoinColumn(name = "CATEGORY_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
	private List<Item> items = new ArrayList<Item>();
	
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	private Category parent;
	
	@OneToMany(mappedBy = "parent")
	private List<Category> children = new ArrayList<Category>();
	
	public void setParent(Category parent) {
		this.parent = parent;
		
		if(!parent.children.contains(this)) {
			parent.addChildCategory(this);
		}
	}
	
	public void addChildCategory(Category child) {
		children.add(child);
		
		if(child.getParent() != this) {
			child.setParent(this);
		}
	}
	
	public void addItem(Item item) {
		items.add(item);
		
		if(!item.getCategories().contains(this)) {
			item.addCategory(this);
		}
	}

	@Builder
	public Category(String name) {
		this.name = name;
	}
}
