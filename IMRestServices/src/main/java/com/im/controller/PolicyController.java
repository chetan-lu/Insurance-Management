package com.im.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.im.collection.PolicyDetails;
import com.im.entity.AddPolicy;
import com.im.service.PolicyService;

@CrossOrigin
@RestController
@RequestMapping(value="/imservices")
public class PolicyController {
	
	@Autowired
	private PolicyService policyService;
	
	@RequestMapping(value=" /getPremium", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public long getTotalPremium(@RequestParam(name="userName") String userName)
	{
		return policyService.calculatePremium(userName);
	}
	
	@RequestMapping(value=" /getAllPolicies", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PolicyDetails> getAllPolicies(@RequestParam(name="userName") String userName)
	{
		return policyService.getRespectivePolicies(userName);
	}

	@RequestMapping(value="/addPolicy", method=RequestMethod.POST)
	public PolicyDetails addPolicy(@RequestBody AddPolicy policy,@RequestHeader(name="_id") String id)
	{
		return policyService.addPolicy(policy);
	}
	
	@RequestMapping(value="/allPolicies",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PolicyDetails> getAllPolicies()
	{
		return policyService.getAllPolicies();		
	}
	
	@RequestMapping(value="/setStatus", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public PolicyDetails setStatus(@RequestParam("name=policyNumber") String policyNumber ,@RequestParam(name="status") String status)
	{
		return policyService.setStatus(policyNumber,status);
	}

}
