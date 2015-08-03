
package com.travelcorp.ccapi;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.travelcorp.ccapi package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SearchToursResponse_QNAME = new QName("http://CCAPI.TravelCorp.com/", "SearchToursResponse");
    private final static QName _SearchTours_QNAME = new QName("http://CCAPI.TravelCorp.com/", "SearchTours");
    private final static QName _SearchToursEnhanced_QNAME = new QName("http://CCAPI.TravelCorp.com/", "SearchToursEnhanced");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.travelcorp.ccapi
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchTourResultsItem }
     * 
     */
    public SearchTourResultsItem createSearchTourResultsItem() {
        return new SearchTourResultsItem();
    }

    /**
     * Create an instance of {@link Header }
     * 
     */
    public Header createHeader() {
        return new Header();
    }

    /**
     * Create an instance of {@link SearchToursEnhanced }
     * 
     */
    public SearchToursEnhanced createSearchToursEnhanced() {
        return new SearchToursEnhanced();
    }

    /**
     * Create an instance of {@link SearchToursResponse }
     * 
     */
    public SearchToursResponse createSearchToursResponse() {
        return new SearchToursResponse();
    }

    /**
     * Create an instance of {@link ArrayOfSearchTourResultsItem }
     * 
     */
    public ArrayOfSearchTourResultsItem createArrayOfSearchTourResultsItem() {
        return new ArrayOfSearchTourResultsItem();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link SearchTourResults }
     * 
     */
    public SearchTourResults createSearchTourResults() {
        return new SearchTourResults();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchToursResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CCAPI.TravelCorp.com/", name = "SearchToursResponse")
    public JAXBElement<SearchToursResponse> createSearchToursResponse(SearchToursResponse value) {
        return new JAXBElement<SearchToursResponse>(_SearchToursResponse_QNAME, SearchToursResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchToursEnhanced }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CCAPI.TravelCorp.com/", name = "SearchTours")
    public JAXBElement<SearchToursEnhanced> createSearchTours(SearchToursEnhanced value) {
        return new JAXBElement<SearchToursEnhanced>(_SearchTours_QNAME, SearchToursEnhanced.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchToursEnhanced }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CCAPI.TravelCorp.com/", name = "SearchToursEnhanced")
    public JAXBElement<SearchToursEnhanced> createSearchToursEnhanced(SearchToursEnhanced value) {
        return new JAXBElement<SearchToursEnhanced>(_SearchToursEnhanced_QNAME, SearchToursEnhanced.class, null, value);
    }

}
