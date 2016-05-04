package es.uniovi.asw.Parser.parser;

import es.uniovi.asw.UpdateDB.model.Voter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Chamadoira on 17/02/2016.
 * @author UO236953
 */
public class XSSFParser implements Parser{

	@Override
	public List<Voter> processFile(String file) throws IOException{
    	List<Voter> voters = new ArrayList<Voter>();
        XSSFSheet spreadsheet = new XSSFWorkbook(new FileInputStream(new File(file))).getSheetAt(0);
        Iterator<Row> rowIterator = spreadsheet.iterator();
        Row row = rowIterator.next();
        int rowCounter=2;
        while (rowIterator.hasNext()){
            row = rowIterator.next();
            try {
                voters.add(new Voter(
                        row.getCell(0).getStringCellValue(),
                        row.getCell(1).getStringCellValue(),
                        row.getCell(2).getStringCellValue(),
                        (int)row.getCell(3).getNumericCellValue()));
            } catch (java.lang.IllegalStateException e) {
                System.err.println("The voter [row = "+rowCounter+"] doesn't follow the required structure");
            }
            rowCounter++;
        }
        return voters;
    }
	
}
