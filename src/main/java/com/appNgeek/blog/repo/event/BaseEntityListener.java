package com.appNgeek.blog.repo.event;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.appNgeek.blog.domain.BaseEntity;

public class BaseEntityListener {

	@PrePersist
	public void prePersist(BaseEntity abstractEntity) {
		Date currentDate = new Date();

		abstractEntity.setCreationDate(currentDate);
		abstractEntity.setModificationDate(currentDate);
	}

	@PreUpdate
	public void preUpdate(BaseEntity abstractEntity) {
		Date currentDate = new Date();
		
		abstractEntity.setModificationDate(currentDate);
	}

}