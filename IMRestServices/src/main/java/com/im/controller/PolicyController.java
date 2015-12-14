package com.im.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/premium", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public long getTotalPremium(@RequestParam(name="userName") String userName)
	{
		return policyService.calculatePremium(userName);
	}
	
	@RequestMapping(value="/policies/{userName}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PolicyDetails> getAllPolicies(@PathVariable(value="userName") String userName)
	{
		return policyService.getRespectivePolicies(userName);
	}

	@RequestMapping(value="/policy", method=RequestMethod.POST)
	public PolicyDetails addPolicy(@RequestBody AddPolicy policy,@RequestHeader(name="_id") String id)
	{
		return policyService.addPolicy(policy);
	}
	
	@RequestMapping(value="/policies",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PolicyDetails> getAllPolicies()
	{
		return policyService.getAllPolicies();		
	}
	
	@RequestMapping(value="/policy", method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public PolicyDetails setStatus(@RequestParam(name="policyNumber") String policyNumber ,@RequestParam(name="status") String status)
	{
		return policyService.setStatus(policyNumber,status);
	}
	
}
