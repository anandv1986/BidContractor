package com.bid.contract.controller;

import com.bid.contract.dao.ContractDao;
import com.bid.contract.entities.ProjectDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

@RunWith (SpringRunner.class)
@WebMvcTest(value = ContractApiController.class)
public class ContractApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContractDao contractDao;

    @MockBean
    private ProjectDetails projectDetails;

    String jsonRequest = "{\"projectName\": \"Amex\",\"projectDesc\": \"This is about Batch processing\",\"maxBudget\": 14300.00}";//"{\"projectName\":\"test\"}"


    @Test
    public void postProjectDetails() throws Exception {
        Mockito.when ( contractDao.createProject ( projectDetails ) ).thenReturn ( projectDetails );

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post ( "/project" ).contentType ( MediaType.APPLICATION_JSON_VALUE ).accept ( MediaType.APPLICATION_JSON ).content ( jsonRequest );

        MvcResult result = mockMvc.perform ( requestBuilder ).andReturn ();

        System.out.println ( "The expected result is "+result);

        Assert.assertEquals (200, result.getResponse ().getStatus ());
    }
    @Test
    public void retrieveProjectDetails() throws Exception {
        Mockito.when (contractDao.getProjectDetails ( Mockito.anyString (), Mockito.anyInt () )).thenReturn ( new ArrayList<> (  ) );

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get ( "/project" ).accept ( MediaType.APPLICATION_JSON );

        MvcResult result = mockMvc.perform ( requestBuilder ).andReturn ();

        System.out.println ( "The expected result is "+result);

        Assert.assertEquals (200, result.getResponse ().getStatus ());
    }
}
