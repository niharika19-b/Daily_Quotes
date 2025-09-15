package com.paging.service;
import java.util.Optional;
import com.paging.entity.Quote;
import com.paging.repository.QuoteRepository;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {
       
	private final QuoteRepository repo;
	
	public QuoteService(QuoteRepository repo) {
		this.repo = repo;
	}
	
	public Page<Quote> list(int page, int size, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		return repo.findAll(pageable);
	}

	public Optional<Quote> getByDayNumber(Integer dayNumber) {
		return repo.findByDayNumber(dayNumber);
	}
	
	public Quote create(Quote quote) {
		return repo.save(quote);
	}
}
