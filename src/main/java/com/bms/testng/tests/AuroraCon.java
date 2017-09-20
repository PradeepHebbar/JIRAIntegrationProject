package com.bms.testng.tests;
/*package com.brillio.testng.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.CallableStatement;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class AuroraCon {
	ResultSet rs = null;

	public Connection getDBConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String jdbcurl = "jdbc:mysql://bms-rwr-rds-v208-dev.web-dev.bms.com:3306/bms_obi_reporting";
		String username = "admin";
		String password = "admin123";

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(jdbcurl, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	
	 * public ResultSet getResultsetFromSql(String sql) { Connection conn =
	 * getDBConnection(); // ResultSet rs = null; try { Statement stmt =
	 * conn.createStatement(); rs = stmt.executeQuery(sql); } catch
	 * (SQLException e) { e.printStackTrace(); } return rs; }
	 

	public ResultSet getResultsetFromSql() throws SQLException {
		Connection con = getDBConnection();
		Statement stm = con.createStatement();
		String createProcedure = null;

		String queryDrop = "DROP PROCEDURE IF EXISTS Fetch_Data";

		createProcedure = "create procedure Fetch_Data() " + "begin "
				+ "Set @AsOfDate = (select max(load_date) from bms_obi_reporting.v_LoadDates) ;"
				+ "Set @CompareDate = (select max(CompareTo) from bms_obi_reporting.v_LoadDates) ;"
				+ "Set @FromDate = (select max(DefaultFromDate) from bms_obi_reporting.v_LoadDates) ;"
				+ "Set @ToDate = @AsOfDate ;" + "Select "
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
				+ " From bms_obi_reporting.Activity_Log al Inner Join Action_Catalog ac On ac.catalog_ID = al.action_catalog_ID "
				+ " Where al.project_ID = vpm.project_id AND Date(al.activity_time) Between Date(@FromDate) AND Date(@ToDate)"
				+ " ) As 'Total Activity Score'" + ",(Select count(distinct aal.user_ID) "
				+ " From bms_obi_reporting.Activity_Log aal "
				+ " Where aal.project_ID = vpm.project_id AND Date(aal.activity_time) Between Date(@FromDate) AND Date(@ToDate)"
				+ " ) As 'Total Active Researchers' " + " From " + " bms_obi_reporting.v_Projects_ActivityScore_v2 vpm "
				+ " where project_id in "
				+ "  ( Select distinct vappd.project_id from bms_obi_reporting.V_ActiveProjectsPerDay vappd "
				+ "  where load_date = vpm.load_date " + "  AND vappd.activity_date >= @FromDate "
				+ "  AND vappd.activity_date <= @ToDate " + " ) " + " AND load_date In (@AsOfDate, @CompareDate) "
				+ " Order By vpm.load_date DESC, vpm.project_id " + ";" + "end";
		Statement stmt = null;
		Statement stmtDrop = null;

		try {
			System.out.println("Calling DROP PROCEDURE");
			stmtDrop = con.createStatement();
			stmtDrop.execute(queryDrop);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmtDrop != null) {
				stmtDrop.close();
			}
		}

		try {
			stmt = con.createStatement();
			stmt.executeUpdate(createProcedure);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

		CallableStatement cs = null;
		cs = (CallableStatement) con.prepareCall("{call Fetch_Data}");
		rs = cs.executeQuery();

		return rs;
	}

	public int getColumnCount() {
		ResultSetMetaData rsmd;
		int columnsNumber = 0;
		try {
			rsmd = (ResultSetMetaData) rs.getMetaData();
			columnsNumber = rsmd.getColumnCount();
			System.out.println(columnsNumber);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return columnsNumber;
	}

	public int getRowCount() {
		int row = 0;
		try {
			if (rs.last()) {

				row = rs.getRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Row Number" + row);
		return row;
	}
}
*/