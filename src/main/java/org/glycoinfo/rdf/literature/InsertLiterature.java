package org.glycoinfo.rdf.literature;

import org.apache.commons.lang.StringUtils;
import org.glycoinfo.rdf.InsertSparqlBean;

/*
Insert mapped IDs between Accession number and PubMed ID by insert SPARQL.

@prefix rdfs:   <http://www.w3.org/2000/01/rdf-schema#> .
@prefix dcterms: <http://purl.org/dc/terms/> .
@prefix bibo: <http://purl.org/ontology/bibo/>.

<http://rdf.glycoinfo.org/glycan/{accession number}> 
   decterms:references <http://rdf.ncbi.nlm.nih.gov/pubmed/{pubmed id}> .

<http://rdf.ncbi.nlm.nih.gov/pubmed/{pubmed id}>
  a  bibo:Article;
  dcterms:identifier  "{pubmed id}";
  rdfs:seeAlso  <http://identifiers.org/pubmed/{pubmed id}>;
  rdfs:seeAlso  <http://rdf.ncbi.nlm.nih.gov/pubmed/{pubmed id}>. 

 @author shinmachis
*/

public class InsertLiterature extends InsertSparqlBean implements Literature {

	void init(){
		this.prefix = "\n\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" 
				+ "PREFIX dcterms: <http://purl.org/dc/terms/>\n"
				+ "PREFIX bibo: <http://purl.org/ontology/bibo/>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n\n";
	}
        
	public InsertLiterature() {
			init();
	}
	
	public String getInsert() {
		if (StringUtils.isNotBlank(getSparqlEntity().getValue(AccessionNumber)) && StringUtils.isNotBlank(getSparqlEntity().getValue(PubemdId))) {
			this.insert = "<http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(AccessionNumber) +"> \n"
					+ " glytoucan:has_primary_id \"" + getSparqlEntity().getValue(AccessionNumber) + "\"; \n"
					+ " dcterms:references " + "<http://rdf.ncbi.nlm.nih.gov/pubmed/" + getSparqlEntity().getValue(PubemdId) + ">. \n"
					+ "<http://rdf.ncbi.nlm.nih.gov/pubmed/" + getSparqlEntity().getValue(PubemdId) + "> \n"
					+ " a bibo:Article; \n"
					+ " dcterms:identifier \"" + getSparqlEntity().getValue(PubemdId) + "\"; \n"
					+ " rdfs:seeAlso " + "<http://identifiers.org/pubmed/" + getSparqlEntity().getValue(PubemdId) + ">; \n"
					+ " rdfs:seeAlso " + "<http://rdf.ncbi.nlm.nih.gov/pubmed/" + getSparqlEntity().getValue(PubemdId) + "> \n";
		}
		return this.insert;
	}
        
}




