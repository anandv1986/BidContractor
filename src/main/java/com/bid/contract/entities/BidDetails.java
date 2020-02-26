package com.bid.contract.entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *  Entity Class for representing BID_DETAILS table
 *
 */

@Entity
@Table(name = "BID_DETAILS")
public class BidDetails {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String projectName;

    @Column
    private BigDecimal bidAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    @Override
    public String toString() {
        return "BidDetails{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", bidAmount=" + bidAmount +
                '}';
    }
}
