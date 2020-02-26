package com.bid.contract.controller;

import com.bid.contract.dao.ContractDao;
import com.bid.contract.entities.BidDetails;
import com.bid.contract.entities.ProjectDetails;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.List;

/**
 * This is a Controller class contains below REST endpoints
 *
 *      Create                  Project             POST
 *      Retrieve                All ProjectDetails  GET
 *      Create                  A Bid               POST
 *      getLowestBidForProject  Retrieve A Lowest   GET
 *                              Bid of the project
 */

@RestController
public class ContractApiController {

    @Autowired
    ContractDao contractDao;

    @RequestMapping(value = "project", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createProject(@RequestBody ProjectDetails project) {
        System.out.println ( "JSON value " + project);
        contractDao.createProject ( project );
        return "Project Created Successfully "+ project;
    }

    @RequestMapping(value = "project", method = RequestMethod.GET)
    public List<ProjectDetails> getProject(@RequestParam(required = false) String projectName, @RequestParam(required = false) Integer projectId) {
        System.out.println ( "Inside getProjects value ");
        List<ProjectDetails> projects = contractDao.getProjectDetails(projectName, projectId);
        return projects;
    }

    @RequestMapping(value = "bid", method = RequestMethod.POST)
    public BidDetails createABid(@RequestBody BidDetails bid) {
        System.out.println ( "Inside createABid  ");
        contractDao.createABid ( bid );
        return bid;
    }

    @RequestMapping(value = "bid", method = RequestMethod.GET)
    public List<BidDetails> getLowestBidForProject(@RequestParam String projectName) {
        System.out.println ( "Inside getLowestBidForProject  ");
        List<BidDetails> bids = contractDao.getLowestBidForProject (projectName);
        return bids;
    }
}
