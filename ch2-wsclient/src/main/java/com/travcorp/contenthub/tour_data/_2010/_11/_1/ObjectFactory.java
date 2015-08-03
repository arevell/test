
package com.travcorp.contenthub.tour_data._2010._11._1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.travcorp.contenthub.tour_data._2010._11._1 package. 
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

    private final static QName _TourDetailsFullResponse_QNAME = new QName("http://contenthub.travcorp.com/tour_data/2010/11/1.0", "tourDetailsFullResponse");
    private final static QName _TourDetailsFull_QNAME = new QName("http://contenthub.travcorp.com/tour_data/2010/11/1.0", "TourDetailsFull");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.travcorp.contenthub.tour_data._2010._11._1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TourDetailsFull }
     * 
     */
    public TourDetailsFull createTourDetailsFull() {
        return new TourDetailsFull();
    }

    /**
     * Create an instance of {@link TourDetailsFullResponse }
     * 
     */
    public TourDetailsFullResponse createTourDetailsFullResponse() {
        return new TourDetailsFullResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TourDetailsFullResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://contenthub.travcorp.com/tour_data/2010/11/1.0", name = "tourDetailsFullResponse")
    public JAXBElement<TourDetailsFullResponse> createTourDetailsFullResponse(TourDetailsFullResponse value) {
        return new JAXBElement<TourDetailsFullResponse>(_TourDetailsFullResponse_QNAME, TourDetailsFullResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TourDetailsFull }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://contenthub.travcorp.com/tour_data/2010/11/1.0", name = "TourDetailsFull")
    public JAXBElement<TourDetailsFull> createTourDetailsFull(TourDetailsFull value) {
        return new JAXBElement<TourDetailsFull>(_TourDetailsFull_QNAME, TourDetailsFull.class, null, value);
    }

}
