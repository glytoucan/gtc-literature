package org.glycoinfo.rdf.literature;

import org.apache.commons.lang.StringUtils;
import org.glycoinfo.rdf.InsertSparqlBean;

/*
Insert mapped IDs between Accession number and PubMed ID by insert SPARQL.

@prefix rdfs:   <http://www.w3.org/2000/01/rdf-schema#> .
@prefix dcterms: <http://purl.org/dc/terms/> .
@prefix bibo: <http://purl.org/ontology/bibo/>.

# Literature
<http://rdf.glycoinfo.org/glycan/{accession_number}> #Saccharide 
 dcterms:references <http://rdf.glycoinfo.org/references/{pubmed_id}> ; #Article

<http://rdf.glycoinfo.org/references/{pubmed_id}>
 a bibo:Article ;
 dcterms:identifier "{pubmed_id}" ;
 rdfs:seeAlso <http://identifiers.org/pubmed/{pubmed_id}> . #PubMed URL

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
					+ " dcterms:references " + "<http://rdf.glycoinfo.org/references/" + getSparqlEntity().getValue(PubemdId) + ">. \n"
					+ "<http://rdf.glycoinfo.org/references/" + getSparqlEntity().getValue(PubemdId) + "> \n"
					+ " a bibo:Article; \n"
					+ " glytoucan:contributor <http://rdf.glycoinfo.org/glytoucan/contributor/userId/" + getSparqlEntity().getValue(ContributorId) + ">; \n"
					+ " dcterms:identifier \"" + getSparqlEntity().getValue(PubemdId) + "\"; \n"
					+ " rdfs:seeAlso " + "<http://identifiers.org/pubmed/" + getSparqlEntity().getValue(PubemdId) + ">. \n";
		}
		return this.insert;
	}
        
}




