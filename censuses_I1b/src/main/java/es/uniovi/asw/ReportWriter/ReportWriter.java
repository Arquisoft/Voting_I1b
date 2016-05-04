package es.uniovi.asw.ReportWriter;

import es.uniovi.asw.ReportWriter.writer.FileWriter;

/**
 * Created by Chamadoria on 24/02/2016.
 * @author UO236953
 */
public class ReportWriter implements IReportWriter {
	
    private FileWriter writer;
    
    public ReportWriter(FileWriter writer){
    	this.writer = writer;
    }

	@Override
	public void writeReport(String fileName) {
		writer.toFile(fileName);
	}

	@Override
	public void WReportP(String message) {
		writer.add(message);
	}

}
