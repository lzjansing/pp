package com.ms.jansing.pp.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ms.jansing.common.entity.BaseEntity;
import com.ms.jansing.common.serialize.LocalDateSerializer;

import java.time.LocalDate;

/**
 * #	Fund Name	Ticker	Currency	Portfolio Date	Holdings    Manager
 * Created by jansing on 17-7-22.
 */
public class Fund extends BaseEntity {
    private String name;
    private String ticker;
    private String currency;
    private LocalDate portfolioDate;
    private String numberOfHoldings;
    private String manager;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate getPortfolioDate() {
        return portfolioDate;
    }

    public void setPortfolioDate(LocalDate portfolioDate) {
        this.portfolioDate = portfolioDate;
    }

    public String getNumberOfHoldings() {
        return numberOfHoldings;
    }

    public void setNumberOfHoldings(String numberOfHoldings) {
        this.numberOfHoldings = numberOfHoldings;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "name='" + name + '\'' +
                ", ticker='" + ticker + '\'' +
                ", currency='" + currency + '\'' +
                ", portfolioDate=" + portfolioDate +
                ", numberOfHoldings='" + numberOfHoldings + '\'' +
                '}';
    }
}
