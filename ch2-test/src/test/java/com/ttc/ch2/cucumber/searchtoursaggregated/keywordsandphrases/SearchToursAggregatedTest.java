package com.ttc.ch2.cucumber.searchtoursaggregated.keywordsandphrases;

import java.io.IOException;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.ttc.ch2.cucumber.SearchLib;
import com.ttc.ch2.search.export.IndexSynchronizerService;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/SearchToursAggregatedKap", "json:target/SearchToursAggregatedKap.json"})
public class SearchToursAggregatedTest {
	
	private static String documentID = "66633SPECIAL";
    private static String aggregatedID = "66633SPECIALagg";
	private static Node node;
	
	@BeforeClass
    public static void setUp() throws IOException{
		Assume.assumeTrue(SearchLib.isElasticSearchTurnedOn());
		node = SearchLib.getACforSearch().getBean(Node.class);
	    
	    String tiandtdsc = IOUtils.toString(SearchToursAggregated.class.getResourceAsStream("tiandtdsc_row.json"),"UTF-8");
	    Client client = node.client();
	    IndexRequestBuilder indexRequest = client
				.prepareIndex(IndexSynchronizerService.ES_INDEX_NAME, IndexSynchronizerService.ES_TOURS_ANS_SC_TYPE, documentID)
				.setSource(tiandtdsc);
	    
	    IndexResponse resp1 = indexRequest.execute().actionGet();
	    
	    Client client2 = node.client();
	    String aggtour = IOUtils.toString(SearchToursAggregated.class.getResourceAsStream("aggtours_row.json"),"UTF-8");
	    IndexRequestBuilder indexRequest2 = client2
				.prepareIndex(IndexSynchronizerService.ES_INDEX_NAME, IndexSynchronizerService.ES_AGGREGATED_TOURS_TYPE, aggregatedID)
				.setSource(aggtour.toString());
		IndexResponse resp2 = indexRequest2.execute().actionGet();
    }

    @AfterClass
    public static void tearDown(){
    	Client client = node.client();
		DeleteRequestBuilder deleteRequest = client.prepareDelete(IndexSynchronizerService.ES_INDEX_NAME, IndexSynchronizerService.ES_TOURS_ANS_SC_TYPE, documentID);
		DeleteResponse deleteResponse = deleteRequest.execute().actionGet();

		Client client2 = node.client();
		DeleteRequestBuilder deleteRequest2 = client2.prepareDelete(IndexSynchronizerService.ES_INDEX_NAME, IndexSynchronizerService.ES_AGGREGATED_TOURS_TYPE, aggregatedID);
		DeleteResponse deleteResponse2 = deleteRequest2.execute().actionGet();
    }
}