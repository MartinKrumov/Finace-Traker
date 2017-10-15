package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
	private long transactionId;
	private TransactionType type;
	private BigDecimal amount;
	private LocalDateTime date;
	private long wallet;
	private long category;
	private long ownCategory;
	
	public Transaction(TransactionType type, BigDecimal amount, LocalDateTime date, long wallet, long category,
			long ownCategory) {
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.wallet = wallet;
		this.category = category;
		this.ownCategory = ownCategory;
	}
	
}
