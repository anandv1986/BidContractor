package com.bid.contract.dao;

import com.bid.contract.entities.BidDetails;
import com.bid.contract.entities.ProjectDetails;

import java.util.List;

public interface ContractDao {

    List<ProjectDetails> getProjectDetails(String projectName, Integer projectId);

    ProjectDetails createProject(ProjectDetails project);

    void createABid(BidDetails bidDetails);

    List<BidDetails> getLowestBidForProject(String projectName);
}
