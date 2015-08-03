package com.ttc.prodserv.mongo.domain.xmlrepo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.springframework.data.annotation.Transient;

@XmlType(name = "discount", propOrder = { "discountId", "paymentDate", "amount", "percentage", "periodFlag" })
@XmlAccessorType(XmlAccessType.FIELD)
public class Discount implements Serializable {

	private static final long serialVersionUID = 7314704450827750704L;

	private long discountId;
	private Date paymentDate;
	private BigDecimal amount;
	private boolean percentage;
	private char periodFlag;

	@Transient
	@XmlTransient
	private long policyId;
	@Transient
	@XmlTransient
	private Date travelPeriodFromDate;
	@Transient
	@XmlTransient
	private Date travelPeriodToDate;
	@Transient
	@XmlTransient
	private int periodFrom;
	@Transient
	@XmlTransient
	private String nonFitAirData;

	public long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(long discountId) {
		this.discountId = discountId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public boolean isPercentage() {
		return percentage;
	}

	public void setPercentage(boolean percentage) {
		this.percentage = percentage;
	}

	public char getPeriodFlag() {
		return periodFlag;
	}

	public void setPeriodFlag(char periodFlag) {
		this.periodFlag = periodFlag;
	}

	public long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}

	public Date getTravelPeriodFromDate() {
		return travelPeriodFromDate;
	}

	public void setTravelPeriodFromDate(Date travelPeriodFromDate) {
		this.travelPeriodFromDate = travelPeriodFromDate;
	}

	public Date getTravelPeriodToDate() {
		return travelPeriodToDate;
	}

	public void setTravelPeriodToDate(Date travelPeriodToDate) {
		this.travelPeriodToDate = travelPeriodToDate;
	}

	public int getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(int periodFrom) {
		this.periodFrom = periodFrom;
	}

	public String getNonFitAirData() {
		return nonFitAirData;
	}

	public void setNonFitAirData(String nonFitAirData) {
		this.nonFitAirData = nonFitAirData;
	}
}
