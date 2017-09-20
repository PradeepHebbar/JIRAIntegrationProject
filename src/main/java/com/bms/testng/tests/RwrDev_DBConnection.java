package com.bms.testng.tests;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class RwrDev_DBConnection {

	public static void main(String[] args) {
		
			String url = "jdbc:mysql://bms-rwr-rds-v208-dev.web-dev.bms.com:3306/bms_obi_reporting";
			String name = "admin";
			String password = "admin123";
			
			
					
					
			Connection con;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				con =(Connection) DriverManager.getConnection(  
						url,"admin","admin123");
				Statement stm = con.createStatement();
				
				ResultSet rs = stm.executeQuery("select * from Activity_Log; " );
				
		
						
				/*ResultSet rs = stm.executeQuery("Set @AsOfDate = (select max(load_date) from v_LoadDates) ;"
						+ "Set @CompareDate = (select max(CompareTo) from v_LoadDates) ;"
						+ " --  Set @AsOfDate = '2017-03-10' ;"
						+ " -- Set @FromDate = (Select Min(activity_date) from V_ActiveProjectsPerDay where load_date In(@AsOfDate,@CompareDate) ) ;"
						+ "Set @FromDate = (select max(DefaultFromDate) from v_LoadDates) ;" 
						+ "Set @ToDate = @AsOfDate ;"
						+ "-- Set @FromDate = '2016-10-13' ;"
						+ "-- Set @ToDate = '2016-11-29' ;"
						+ "Select " 
						+ "vpm.load_date, vpm.project_id, vpm.name, vpm.phase, vpm.start_date, vpm.anticipated_close_date, vpm.completion_date"
						+ ",vpm.completed, vpm.Role_List, vpm.Team_List, vpm.Market_List, vpm.Data_Source_List, vpm.Drug_Asset_List"
						+ ",vpm.Requestor_Dept_List, vpm.TA_List, vpm.focus, vpm.type_ID, vpm.Project_Type, vpm.client_department, vpm.creation_date"
						+ ",vpm.creator, vpm.creation_desc, vpm.comments, vpm.conference_name, vpm.coverage_period, vpm.data_type_used"
						+ ",vpm.high_visibility_flag, vpm.keywords, vpm.last_modified_by, vpm.last_modification_date, vpm.last_modification_desc"
						+ ",vpm.marked_for_deletion, vpm.methods, vpm.outcomes, vpm.visibility, vpm.protocol_number, vpm.public_disclosure_date"
						+ ",vpm.rationale, vpm.requestor, vpm.research_question, vpm.retention_date, vpm.retention_grace_period"
						+ ",vpm.study_design, vpm.study_population, vpm.study_rationale"
						+ ",vpm.title_of_study, vpm.exception_flag, vpm.project_branch, vpm.therapeutic_area"
						+ ",vpm.Activity_Score, vpm.Total_Researchers,vpm.Last_Activity_Date, vpm.DaysSinceLastActivity"
						+ ",vpm.OBI_Status, vpm.DaysInProgress, vpm.DaysToTarget, vpm.PctRemaining, vpm.nMetadataCompl, vpm.nMetadataFields"
						+ ",vpm.PctMetadataCompl, vpm.nMandatoryMetadataCompl, vpm.nMandatoryMetadataFields, vpm.PctMandatoryMetadataCompl"
						+ ",(Select SUM(ac.description_weight) "
						+ " From Activity_Log al Inner Join Action_Catalog ac On ac.catalog_ID = al.action_catalog_ID "
						+ " Where al.project_ID = vpm.project_id AND Date(al.activity_time) Between Date(@FromDate) AND Date(@ToDate)"
						+ " ) As 'Total Activity Score'"
						+ ",(Select count(distinct aal.user_ID) "
						+ " From Activity_Log aal "
						+   " Where aal.project_ID = vpm.project_id AND Date(aal.activity_time) Between Date(@FromDate) AND Date(@ToDate)" 
						+   " ) As 'Total Active Researchers' "
						+  " From "
						+ " v_Projects_ActivityScore_v2 vpm "
						+ " where project_id in "
						+  "  ( Select distinct vappd.project_id from V_ActiveProjectsPerDay vappd "
						+  "  where load_date = vpm.load_date "
						+  "  AND vappd.activity_date >= @FromDate "
						+  "  AND vappd.activity_date <= @ToDate "
						+  " ) "
						+  " AND load_date In (@AsOfDate, @CompareDate) "
						+  " Order By vpm.load_date DESC, vpm.project_id "
						+ ";");*/
				
				System.out.println("---------------");
				while(rs.next()){
					//int count = rs.getInt(1);
					//System.out.println(count);
					String CORDS_Team = rs.getString("user_ID");
					System.out.println(CORDS_Team);
				}
				System.out.println("---------------");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			
			
					
			
	
		
	}

}
