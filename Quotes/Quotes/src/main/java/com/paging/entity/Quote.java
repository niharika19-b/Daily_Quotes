package com.paging.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "quotes" ,indexes = @Index(name = "idx_day_number", columnList = "dayNumber", unique = true))
public class Quote {
      
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "day_number", nullable = false, unique = true)
	    private Integer dayNumber;

	    @Column(name = "quote_text", nullable = false, length = 500)
	    private String quoteText;

	    @Column(name = "created_at", nullable = false)
	    private LocalDateTime createdAt;
	    
	    @PrePersist
	    public void prePersist() {
	        if (createdAt == null) {
	            createdAt = LocalDateTime.now();

	        }}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Integer getDayNumber() {
			return dayNumber;
		}

		public void setDayNumber(Integer dayNumber) {
			this.dayNumber = dayNumber;
		}

		public String getQuoteText() {
			return quoteText;
		}

		public void setQuoteText(String quoteText) {
			this.quoteText = quoteText;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
	   
			
		}
