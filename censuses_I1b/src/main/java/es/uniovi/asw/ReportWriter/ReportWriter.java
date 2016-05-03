package es.uniovi.asw.ReportWriter;

/**
 * Created by Chamadoria on 24/02/2016.
 * @author UO236953
 */
public class ReportWriter implements IWriteReport {
	
    private FileWriter writer;
    
    public ReportWriter(FileWriter writer){
    	this.writer = writer;
    }

	@Override
	public void writeReport(String fileName) {
		writer.toFile(fileName);
	}

	@Override
	public void append(String message) {
		writer.add(message);
	}

}
