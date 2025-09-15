package com.paging.repository;
import java.util.Optional;
import com.paging.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository  extends JpaRepository<Quote, Long> {
  Optional<Quote> findByDayNumber(Integer dayNumber);
}
