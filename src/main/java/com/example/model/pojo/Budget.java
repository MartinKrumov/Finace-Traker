package com.example.model.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Budget {
	private long budgedId;
//	private String name;
	private BigDecimal amount;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private long category;

    public Budget(BigDecimal amount, LocalDateTime fromDate, LocalDateTime toDate, long category) {
        this.amount = amount;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.category = category;
    }

    public long getBudgedId() {
        return budgedId;
    }

    public void setBudgedId(long budgedId) {
        this.budgedId = budgedId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }
}
