package com.bms.testng.tests;

public class ExcelDataUpdate {

	
	public static void main(String[] args){
		String lines = ("Set @AsOfDate = (select max(load_date) from v_LoadDates) ;"
						+ "Set @CompareDate = (select max(CompareTo) from v_LoadDates) ;"
						+ " --  Set @AsOfDate = '2017-03-10' ;"
						+ " -- Set @FromDate = (Select Min(activity_date) from V_ActiveProjectsPerDay where load_date In(@AsOfDate,@CompareDate) ) ;"
						+ "Set @FromDate = (select max(DefaultFromDate) from v_LoadDates) ;" );
		
		System.out.println("------------");
		System.out.println(lines);
		System.out.println("------------");
	}
	
	
		
}
