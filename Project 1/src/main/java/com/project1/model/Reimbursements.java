package com.project1.model;

import java.math.BigDecimal;

public class Reimbursements  {
	/*
	 * RECEIPT NUMBER(8,0),AMOUNT NUMBER(8,2), REASON VARCHAR2(50),
		STATUS NUMBER(1,0), MANAGERID NUMBER(3,0), ACCOUNTNUMBER NUMBER(3,0)
 		CONSTRAINT FK_USERLOGID REFERENCES USERLOG
	 */
	private long receipt;
	private BigDecimal amount;
	private String reason;
	private int status;
	private int managerId;
	private int accountNumber;
	
	public Reimbursements() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Reimbursements(long receipt, BigDecimal amount, String reason, int status, int managerId,
			int accountNumber) {
		super();
		this.receipt = receipt;
		this.amount = amount;
		this.reason = reason;
		this.status = status;
		this.managerId = managerId;
		this.accountNumber = accountNumber; 
	}
	
	public long getReceipt() {
		return receipt;
	}
	public void setReceipt(long receipt) {
		this.receipt = receipt;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + managerId;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + (int) (receipt ^ (receipt >>> 32));
		result = prime * result + status;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursements other = (Reimbursements) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (managerId != other.managerId)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (receipt != other.receipt)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursements [receipt=" + receipt + ", amount=" + amount + ", reason=" + reason + ", status=" + status
				+ ", managerId=" + managerId + ", accountNumber=" + accountNumber + "]";
	}
	
	
}

