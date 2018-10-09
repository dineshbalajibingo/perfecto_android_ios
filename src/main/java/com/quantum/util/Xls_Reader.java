package com.quantum.util;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Xls_Reader {
	
	public String getAccNUmber(String accType) throws FilloException
	{
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection("/Users/dineshbalajivenkataraman/Documents/New/workspace/Dinesh/perfecto_quantum-master/src/main/resources/data/testData.xls");
		String accNumber = null;
		System.out.println("Account Type Passed is ------> " + accType);
		Recordset rs = connection.executeQuery("Select * from Accounts").where("AccountType ="+accType+"");
		System.out.println("Record Set is ------> " + rs);														
		
		while(rs.next()) {
			accNumber = rs.getField("Account Number 1");
			System.out.println("Account Number is---------->" + accNumber);
		}
		
		return accNumber;
	}
	
	

}
