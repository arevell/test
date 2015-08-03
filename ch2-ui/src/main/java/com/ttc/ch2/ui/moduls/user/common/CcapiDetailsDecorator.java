package com.ttc.ch2.ui.moduls.user.common;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.ttc.ch2.common.ordering.OrderingFunctionByName;
import com.ttc.ch2.domain.Function;
import com.ttc.ch2.domain.auth.CCAPIAuthority;

public class CcapiDetailsDecorator {
private String functionName;
private String companies;

public CcapiDetailsDecorator(String functionName, String companies) {
	super();
	this.functionName = functionName;
	this.companies = companies;
}

public String getFunctionName() {
	return functionName;
}
public void setFunctionName(String functionName) {
	this.functionName = functionName;
}

public String getCompanies() {
	return companies;
}

public void setCompanies(String companies) {
	this.companies = companies;
}

public static List<CcapiDetailsDecorator> buildList(Collection<CCAPIAuthority> auths)
{
	List<CcapiDetailsDecorator> data=Lists.newArrayList();
	Multimap<String,String> authData = ArrayListMultimap.create();
	for (CCAPIAuthority auth : auths) {
		authData.put(auth.getFunction().getName(), auth.getSellingCompany().getCode());			
	}				
		for (String functionName : authData.keySet()) {		
		CcapiDetailsDecorator value=new CcapiDetailsDecorator(functionName, Joiner.on(", ").join(Sets.newTreeSet(authData.get(functionName))));
		data.add(value);
	}		

		Collections.sort(data, new Ordering<CcapiDetailsDecorator> (){
			@Override
			public int compare(CcapiDetailsDecorator o1,
					CcapiDetailsDecorator o2) {
				return o1.getFunctionName().compareTo(o2.getFunctionName());
			}

		});
		
	return data;
}




}
