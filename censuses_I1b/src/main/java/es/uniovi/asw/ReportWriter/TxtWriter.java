package es.uniovi.asw.ReportWriter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TxtWriter implements FileWriter {
	
	private StringBuffer sb;

    public TxtWriter(){
        sb = new StringBuffer();
    }

    public void add(String register){
        sb.append(register+'\n');
    }

    public void clean(){
        sb = new StringBuffer();
    }
    
    public void print(){
        System.out.println(sb);
    }
    
    public void toFile(String fileName){
        try {
            PrintWriter writer = new PrintWriter(fileName + ".txt");
            writer.print(sb);
            writer.close();
        }catch (FileNotFoundException e1) {
                e1.printStackTrace();
        }
    }
    
    @Override
    public String toString(){
        return sb.toString();
    }
}
