package bll;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;



public class Load_RDF_Triples {

	private static final String DES_DIR = "res/mayrhofen-at";
	private static final String FILE_NAME = "res/mayrhofen-at.json";
	//	private static final String FILE_NAME = "ex-3.jsonld";
	private static final String FILE_EXTENSION = "JSON-LD";
	private static final String SUCCESS_LOG_MESSAGE = "Load annotation successfully";
	private static final String ERROR_LOG_MESSAGE = "Cannot load annotation";
	private static final String ERROR_QUERY_MESSAGE = "Cannot perfom query";

	private static void executeQuery(Dataset dataset, String query) {
		try(QueryExecution qExec = QueryExecutionFactory.create(query, dataset)) {
			ResultSet rs = qExec.execSelect();
			ResultSetFormatter.out(rs) ;
			dataset.commit();
		} catch (Exception e) {
			System.out.println(ERROR_QUERY_MESSAGE + e.getMessage().toString());
		} 
	}

	public static void main(String[] args) {

		try {
			/* 
			 * Create a dataset and load file into it
			 * Load mayrhofen.at annotations
			 */
			//			Dataset dataset = RDFDataMgr.loadDataset(FILE_NAME, Lang.JSONLD) ;
			Dataset dataset = TDBFactory.createDataset(DES_DIR);

			Model tdb = dataset.getNamedModel(FILE_NAME + "-rdf");

			// read the input file
			FileManager.get().readModel( tdb, FILE_NAME);

			tdb.close();
			System.out.println(SUCCESS_LOG_MESSAGE);

		} catch (Exception e) {
			System.out.println(ERROR_LOG_MESSAGE + e.getMessage().toString());
		}
		/*
		try {
			
			 * Begin transaction
			 * 

			dataset.begin(ReadWrite.READ);

			// 1. Return all distinct types
			String query = "SELECT DISTINCT ?type\n" + 
					"WHERE\n" + 
					"{ ?s rdf:type ?type\n" + 
					"  ?s a   ?type\n" + 
					"}\n" + 
					"";
			executeQuery(dataset, query);

			// 2. Return all distinct properties used on instances of event type.
			query = "PREFIX sc: <http://schema.org/>\n" + 
					"SELECT DISTINCT ?p\n" + 
					"WHERE\n" + 
					"{ ?s a \"Event\"^^xsd:string\n" + 
					"  ?s ?p ?o\n" + 
					"}\n" + 
					"";
			executeQuery(dataset, query);

			// 3. Return the count of all events that start after 01.12.2017.
			query = "SELECT (COUNT(DISTINCT ?event) AS ?count)\n" + 
					"WHERE\n" + 
					"{ ?event sc:type \"Event\"^^xsd:string\n" + 
					"  ?event sc:startDate ?date\n" + 
					"  FILTER (\n" + 
					"	?date > \"2017-12-1\"^^xsd:date\n" + 
					"	)\n" + 
					"}";
			executeQuery(dataset, query);

			// 4. Return the name and optionally the description of all events all events that start after
			// 01.12.2017.
			query = "PREFIX sc: <http://schema.org/>\n" + 
					"SELECT ?name, ?desc\n" + 
					"WHERE\n" + 
					"{ ?event a \"Event\"^^xsd:string\n" + 
					"  ?name sc:name ?event\n" + 
					"  ?event sc:startDate ?date\n" + 
					"  FILTER (\n" + 
					"	?date > \"2017-12-1\"^^xsd:date\n" + 
					"	)\n" + 
					"}\n" + 
					"OPTIONAL\n" + 
					"{ ?event sc:description ?desc\n" + 
					"}";
			executeQuery(dataset, query);

			// 5. Return the names of all LocalBusinesses that are open on Sunday and optionally their
			// opening and closing hours
			query = "PREFIX sc: <http://schema.org/>\n" + 
					"SELECT ?name, ?openingDate, ?openingDate, ?openinghour, ?closinghour\n" + 
					"WHERE\n" + 
					"{ ?localbusiness a 	 \"LocalBusiness\"^^xsd:string\n" + 
					"  ?name 	 sc:name ?localbusiness\n" + 
					"  ?localbusiness sc:openingHours ?datetime\n" + 
					"  BIND(strbefore(?datetime, \" \") AS ?openingDate)\n" + 
					"  BIND(strafter(?datetime, \" \") AS ?hour)\n" + 
					"  FILTER (\n" + 
					"	?openingDate = \"Su\"@en\n" + 
					"	)\n" + 
					"}\n" + 
					"OPTIONAL\n" + 
					"{ strbefore(?hour, \":\") AS ?openinghour\n" + 
					"  strafter(?hour, \":\") AS ?closinghour\n" + 
					"}";
			executeQuery(dataset, query);

			// 6.
			query = "";
			executeQuery(dataset, query);

			// finish
			dataset.close();
			dataset.end();

		} catch (Exception e) {
			System.out.println(ERROR_QUERY_MESSAGE + e.getMessage().toString());
		}*/
	}

}
