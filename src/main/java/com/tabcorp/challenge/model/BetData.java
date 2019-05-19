package com.tabcorp.challenge.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tabcorp.challenge.util.CustomDateDeserializer;
import com.tabcorp.challenge.util.CustomDateSerializer;

@Entity
@Table
@JsonIgnoreProperties({ "id" })
public class BetData {

	@Id
	@SequenceGenerator(name="betdataseq",initialValue=11)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="betdataseq")
	private Long id;
	private Integer propNumber;
	private Integer customerId;
	@Max(value=20000)
	private Double investmentAmount;
	@Pattern(regexp="WIN|PLACE|TRIFECTA|DOUBLE|QUADDIE")
	private String betType;
	@DateTimeFormat(pattern="yyyy-MM-dd' 'HH:mm")
	@FutureOrPresent(message="Cannot be a past date")
	private Date dateTime;
	
	/**
	 * @return the propNumber
	 */
	@JsonGetter(value="PropNumber")
	public Integer getPropNumber() {
		return propNumber;
	}
	/**
	 * @param propNumber the propNumber to set
	 */
	@JsonSetter(value="PropNumber")
	public void setPropNumber(Integer propNumber) {
		this.propNumber = propNumber;
	}
	/**
	 * @return the customerId
	 */
	@JsonGetter(value="CustomerID")
	public Integer getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	@JsonSetter(value="CustomerID")
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the investmentAmount
	 */
	@JsonGetter("InvestmentAmount")
	public Double getInvestmentAmount() {
		return investmentAmount;
	}
	/**
	 * @param investmentAmount the investmentAmount to set
	 */
	@JsonSetter("InvestmentAmount")
	public void setInvestmentAmount(Double investmentAmount) {
		this.investmentAmount = investmentAmount;
	}
	/**
	 * @return the betType
	 */
	@JsonGetter("BetType")
	public String getBetType() {
		return betType;
	}
	/**
	 * @param betType the betType to set
	 */
	@JsonSetter("BetType")
	public void setBetType(String betType) {
		this.betType = betType;
	}
	/**
	 * @return the dateTime
	 */
	@JsonGetter("DateTime")
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDateTime() {
		return dateTime;
	}
	/**
	 * @param dateTime the dateTime to set
	 */
	@JsonSetter("DateTime")
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BetData [propNumber=" + propNumber + ", customerId=" + customerId + ", investmentAmount="
				+ investmentAmount + ", betType=" + betType + ", dateTime=" + dateTime + "]";
	}
}
