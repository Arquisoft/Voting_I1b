package es.uniovi.asw.ReportWriter.writer;

public interface FileWriter {
	 public void toFile(String fileName);
	 public void add(String register);
	 public void clean();
	 public void print();
}
