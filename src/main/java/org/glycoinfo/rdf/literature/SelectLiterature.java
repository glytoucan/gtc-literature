package org.glycoinfo.rdf.literature;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.SelectSparqlBean;
import org.glycoinfo.rdf.SparqlException;
import org.springframework.stereotype.Component;

/*
Select SPARQL 
Input: Accession number
Output: PubMed ID

@author shinmachi
*/ 
@Component
public class SelectLiterature extends SelectSparqlBean implements Literature {
	
	public SelectLiterature(String sparql) {
		super(sparql);
	}
	public SelectLiterature() {
		super();
		this.prefix = "\n\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" 
				+ "PREFIX dcterms: <http://purl.org/dc/terms/>\n"
				+ "PREFIX bibo: <http://purl.org/ontology/bibo/>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n\n";
		this.select = "DISTINCT ?pubmed_id \n";

	}
	@Override
	public String getWhere() throws SparqlException {
		String where = "\n"
				+ " VALUES ?accNum { \"" + getSparqlEntity().getValue(AccessionNumber) + "\" } \n"
				+ " ?glycan glytoucan:has_primary_id ?accNum. \n"
				+ " ?glycan dcterms:references ?literature. \n"
				+ " ?literature dcterms:identifier ?pubmed_id. \n";
		return where;
	}
	protected Log Logger = LogFactory.getLog(getClass());
}

