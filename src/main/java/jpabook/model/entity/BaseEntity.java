package jpabook.model.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
	
	private Date createdDate;
	private Date lastModifiedDate;
}
