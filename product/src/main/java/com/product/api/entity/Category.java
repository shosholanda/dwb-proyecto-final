package com.product.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category {

		@Id
		@Column(name = "category_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int category_id;
		
		@Column(name = "category")
		@NotNull
		private String category;
		
		@Column(name = "status")
		@Min(value = 0, message = "El status debe ser 0 o 1")
		@Max(value = 1, message = "El status debe ser 0 o 1")
		@JsonIgnore
		private int status;
		
		public Category() {
			
		}

		public int getCategory_id() {
			return category_id;
		}

		public void setCategory_id(int category_id) {
			this.category_id = category_id;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return "Category [category_id=" + category_id + ", category=" + category + ", status=" + status + "]";
		}
}
