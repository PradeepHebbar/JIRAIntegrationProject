package com.bms.rwr.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

public class ReportMerger2 {
    private static String reportImageExtension = "png";
    public static void main(String[] args) throws InterruptedException, IOException  {
    	File reportDirectory;
    	reportDirectory = new File("target/cucumber-parallel");
    	if (reportDirectory.exists()) {        	
        	ReportMerger2 munger = new ReportMerger2();
            munger.mergeReports(reportDirectory);
        }
    }

    /**
     * Merge all reports together into master report in given reportDirectory
     * @param reportDirectory
     * @throws InterruptedException 
     * @throws IOException 
     * @throws Exception
     */
    public void mergeReports(File reportDirectory) throws InterruptedException, IOException  {
    	ReportMerger2 merger2 = new ReportMerger2();
    	merger2.restoringFiles(new File("files"), new File("src/test/resources/featurefiles"));    	
    	Collection<File> existingReports = FileUtils.listFiles(reportDirectory, new String[]{"json"}, true);
    	File reportOutputDirectory = new File("report");
    List<String> jsonFiles = new ArrayList<>();
    for (File report : existingReports) {
        //only address report files
    	jsonFiles.add(report.getPath());
        
    }

    String jenkinsBasePath = "";
    String buildNumber = "1";
    String projectName = "cucumberProject";
    boolean runWithJenkins = false;
    boolean parallelTesting = false;

    Configuration configuration = new Configuration(reportOutputDirectory, projectName);
    // optional configuration
    configuration.setParallelTesting(parallelTesting);
    configuration.setJenkinsBasePath(jenkinsBasePath);
    configuration.setRunWithJenkins(runWithJenkins);
    configuration.setBuildNumber(buildNumber);

    ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
    Reportable result = reportBuilder.generateReports();
    }

    /**
     * merge source file into target
     *
     * @param target
     * @param source
     */
    public void mergeFiles(File target, File source) throws Throwable {
        //copy embeded images
        Collection<File> embeddedImages = FileUtils.listFiles(source.getParentFile(), new String[]{reportImageExtension}, true);
        for (File image : embeddedImages) {
            FileUtils.copyFileToDirectory(image, target.getParentFile());
        }

        //merge report files
        String targetReport = FileUtils.readFileToString(target);
        String sourceReport = FileUtils.readFileToString(source);

        FileUtils.writeStringToFile(target, targetReport + sourceReport);
    }

    /**
     * Give unique names to embedded images to ensure they aren't lost during merge
     * Update report file to reflect new image names
     *
     * @param reportFile
     */
    public void renameEmbededImages(File reportFile) throws Throwable {
        File reportDirectory = reportFile.getParentFile();
        Collection<File> embeddedImages = FileUtils.listFiles(reportDirectory, new String[]{reportImageExtension}, true);

        String fileAsString = FileUtils.readFileToString(reportFile);

        for (File image : embeddedImages) {
            String curImageName = image.getName();
            String uniqueImageName = UUID.randomUUID().toString() + "." + reportImageExtension;

            image.renameTo(new File(reportDirectory, uniqueImageName));
            fileAsString = fileAsString.replace(curImageName, uniqueImageName);
        }

        FileUtils.writeStringToFile(reportFile, fileAsString);
    }
    
    public void restoringFiles(File sourceFolder,File destinationFolder) throws IOException {
		if (!destinationFolder.exists()) {
			destinationFolder.mkdir();
		} 
		try {
			if(sourceFolder.list().length>=destinationFolder.list().length){
				FileUtils.copyDirectory(sourceFolder, destinationFolder);
				FileUtils.cleanDirectory(sourceFolder);
			}			
		} catch (IOException e) {
			throw e;
		}

	}
    
}
