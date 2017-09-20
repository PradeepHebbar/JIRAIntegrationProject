package com.bms.rwr.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class TestCsv {

	public static void main(String[] args) throws IOException {
		

		 	String csvFile = "objectrepository\\test.csv";
		 	 //String csvFile = "/Users/mkyong/csv/country.csv";
		        BufferedReader br = null;
		        String line = "";
		        String cvsSplitBy = ",";
		        
		        try {

		            br = new BufferedReader(new FileReader(csvFile));
		            while ((line = br.readLine()) != null) {
		                // use comma as separator
		                String[] value = line.split(cvsSplitBy);
		                System.out.println("==========");
	                
		                
		                if(value.length > 5){
			                String s = value[4];
			                int counter = 0;
			                for( int i=0; i<s.length(); i++ ) {
			                    if( s.charAt(i) == '[' ) {
			                        counter++;
			                    } 
			                }

			                if (counter!=0){
			                	if ( counter % 2 != 0 )
			                		value[4] = value[4] +","+ value[5];
			                }
			        			
			                System.out.println("value1= " + value[1] + "value2= " + value[2] + "value3= " + value[3] + "value4= " + value[4] );

		                }

		            }

		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		            if (br != null) {
		                try {
		                    br.close();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
		        }
	}
}
