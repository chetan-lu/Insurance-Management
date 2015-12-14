(function(){
	'use strict';
	
	var policies = angular.module("policy", []);
	
	policies.controller("respectivePoliciesCtrl",function($scope,$http,$stateParams){
		var loadUser=JSON.parse(localStorage.getItem("user"));
	
		var result=$http.get(baseUrl+"/policies?userName="+loadUser.userName).success(function(response)
		{
			$scope.userName=loadUser.userName;
			$scope.listPolicies=response;				
		});				
		
	});
}
)();