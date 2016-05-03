package es.uniovi.asw;

import es.uniovi.asw.DBUpdate.InsertDBTest;
import es.uniovi.asw.Parser.XSSFReaderTest;
import es.uniovi.asw.ReportWriter.TxtWriterTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Chamadoira on 24/02/2016.
 * 
 * @author UO236953
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ XSSFReaderTest.class, TxtWriterTest.class, InsertDBTest.class })
public final class AllTestSuite {
}