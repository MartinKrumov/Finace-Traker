package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Budget {
	private long budgedId;
	private String name;
	private BigDecimal amount;
	private LocalDateTime date;
	
	private long wallet;
	private long category;
	private long ownCategory;
	
	public Budget(String name, BigDecimal amount, LocalDateTime date, long wallet, long category, long ownCategory) {
		this.name = name;
		this.amount = amount;
		this.date = date;
		this.wallet = wallet;
		this.category = category;
		this.ownCategory = ownCategory;
	}
	
	
}
