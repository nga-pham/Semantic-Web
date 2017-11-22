package bll;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFLanguages;
import org.apache.jena.riot.RDFParser;
import org.apache.jena.riot.system.ErrorHandlerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.String;
import java.net.URLEncoder;



public class Load_RDF_Triples {
	
	private static final String FILE_NAME = "res/mayrhofen-at.jsonld";
	private static final String FILE_EXTENSION = "JSON-LD";
	private static final String SUCCESS_LOG_MESSAGE = "Load annotation successfully";
	private static final String ERROR_LOG_MESSAGE = "Cannot load annotation";
	

	public static void main(String[] args) {

		 /*try (InputStream in = new FileInputStream(FILE_NAME)) {
		        RDFParser.create()
		            .source(in)
		            .lang(RDFLanguages.JSONLD)
		            .errorHandler(ErrorHandlerFactory.errorHandlerStrict);
		        System.out.println(SUCCESS_LOG_MESSAGE);
		    } catch (Exception e) {
				System.out.println(ERROR_LOG_MESSAGE + e.getMessage().toString());
			}*/
		
		// Create a dataset and read into it from file 
		try {
			Dataset dataset = RDFDataMgr.loadDataset(FILE_NAME) ;
			System.out.println(SUCCESS_LOG_MESSAGE);
		} catch (Exception e) {
			System.out.println(ERROR_LOG_MESSAGE + e.getMessage().toString());
		}
	}

}
