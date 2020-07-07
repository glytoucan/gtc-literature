package org.glycoinfo.rdf.service.impl;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.service.exception.LiteratureException;
import org.glycosmos.client.GlycosmosClient;
import org.springframework.beans.factory.annotation.Autowired;

import jp.bluetree.gov.ncbi.model.Publication;
import jp.bluetree.gov.ncbi.service.NCBIService;

public class LiteratureProcedure {
	private static final Log logger = LogFactory.getLog(LiteratureProcedure.class);
		
	@Autowired
	NCBIService ncbiService;

	@Autowired
	GlycosmosClient client;

	/**
	 * adds a Literature (bibo:Article).
	 * This RDF construct by Accession number and PubMed ID
	 * @throws IOException 
	 
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

		 @throws SparqlException
	 * 
	 */
	public String addLiterature(String accessionNumber, String pubmedId, String contributorId) throws LiteratureException, IOException {
		if (StringUtils.isNotBlank(accessionNumber) && StringUtils.isNotBlank(pubmedId) && StringUtils.isNotBlank(contributorId)) {
		  Publication pub = ncbiService.getSummary(pubmedId);
      if (null==pub || StringUtils.isBlank(pub.getTitle())) {
        throw new LiteratureException("could not retrieve publication title");
      }
			client.insertLiterature(accessionNumber, pubmedId, contributorId);
		} else {
			logger.info("PubMed ID is null");
			throw new LiteratureException("literature id or accession number cannot be null.");
		}
		return pubmedId;
	}

	// Delete
	public String deleteLiterature(String accessionNumber, String pubmedId, String contributorId) throws LiteratureException, IOException {
		if (StringUtils.isNotBlank(accessionNumber) && StringUtils.isNotBlank(pubmedId) && StringUtils.isNotBlank(contributorId)) {
			client.deleteLiterature(accessionNumber, pubmedId, contributorId);
		} else {
			logger.info("PubMed ID is null");
      throw new LiteratureException("literature id cannot be null.");
		}
		return pubmedId;
	}

	public String searchLiterature(String accessionNumber, String graph) throws LiteratureException, IOException {
		if (StringUtils.isNotBlank(accessionNumber)) {
			client.searchLiterature(accessionNumber, graph);
		} else {
			logger.info("PubMed ID is null");
      throw new LiteratureException("literature id cannot be null.");
		}
		return accessionNumber;
	}
}
