//Author
//Kandarp Sharad Parikh
//B00873863

package com.servicegenie.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.servicegenie.daos.ViewServicesByServiceProviderDao;


//Class to view the services 
@Service
public class ViewServicesByServiceProviderService
{
	
	private ViewServicesByServiceProviderDao services;

	public ViewServicesByServiceProviderService() throws SQLException 
	{
		ViewServicesByServiceProviderDao services = new ViewServicesByServiceProviderDao();
		this.services = services;
	}
	
	// Method to get all the service details based on the service provider
	public List<ArrayList<String>> getServiceDetails(String serviceProvider , ModelMap model) throws SQLException 
	{
		ResultSet rs = this.services.getServicesByServiceProvider(serviceProvider);
		List<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();
		
		while(rs.next())
		{
			ArrayList<String> list1 = new ArrayList<String>();
			list1.add(rs.getString("ServiceID"));
			list1.add(rs.getString("ServiceProviderID"));
			list1.add(rs.getString("ServiceName"));
			list1.add(rs.getString("ServiceDescription"));
			list1.add(rs.getString("ServicePrice"));
			listOfLists.add(list1);
		}
		
		return listOfLists;
	}
	
	// Method to get the list of unique Service Providers
	public List<String> getListOfServiceProviders() throws SQLException
	{
		ResultSet rs = this.services.getListOfServiceProviders();
		List<String> serviceProvidersList = new ArrayList<String>();
		while(rs.next()) {
			serviceProvidersList.add(rs.getString("ServiceProviderID"));
		}
		List<String> uniqueServiceProviders = serviceProvidersList.stream().distinct().collect(Collectors.toList());
		
		return uniqueServiceProviders;
	}

}
