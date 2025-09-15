package com.paging.controller;
import java.util.Map;
import java.net.URI;
import com.paging.entity.Quote;
import com.paging.service.QuoteService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {
	
	private final QuoteService service;
	
	public QuoteController(QuoteService service) {
		this.service = service;
	}
	
	@GetMapping
	public Map<String,Object>getAll(
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "4") int size,
			@RequestParam(defaultValue = "dayNumber") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDir) {
	
		Page<Quote>result = service.list(page, size, sortBy, sortDir);
        return Map.of(
        		"content", result.getContent(),
        		"currentPage", result.getNumber(),
        		"totalItems", result.getTotalElements(),
        		"totalPages", result.getTotalPages(),
        		"pageSize", result.getSize(),
        		"sort", Map.of("by", sortBy, "dir", sortDir)
        		);
	}
	
	@GetMapping("/day/{dayNumber}")
	   public ResponseEntity<Quote> getByDayNumber(@PathVariable Integer dayNumber) {
		return service.getByDayNumber(dayNumber)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
        		
	 @PostMapping
		    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote) {
		        Quote saved = service.create(quote);
		        return ResponseEntity.created(URI.create("/api/quotes/" + saved.getId())).body(saved);
		    }
		}