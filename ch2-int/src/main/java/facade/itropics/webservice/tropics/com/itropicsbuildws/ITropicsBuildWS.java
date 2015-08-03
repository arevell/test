
package facade.itropics.webservice.tropics.com.itropicsbuildws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ITropicsBuildWS", targetNamespace = "http://com.tropics.webservice.iTropics.facade/ITropicsBuildWS.xsd")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ITropicsBuildWS {


    /**
     * 
     * @param in
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsSearchMVVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsSearchMVVO searchMV(
        @WebParam(name = "in", partName = "in")
        WsInSearchMVVO in);

    /**
     * 
     * @param param
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsmVorTPDetailsVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsmVorTPDetailsVO getMVorTPDetails(
        @WebParam(name = "param", partName = "param")
        WsInGetMvorTpDetails param);

    /**
     * 
     * @param request
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsGetDatesAndRatesExVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsGetDatesAndRatesExVO getDatesAndRatesEx(
        @WebParam(name = "request", partName = "request")
        WsInGetDatesAndRatesExVO request);

    /**
     * 
     * @param param
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsftAdminCodesVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsftAdminCodesVO getFTAdminCodes(
        @WebParam(name = "param", partName = "param")
        WsInGetFTAdminCodes param);

    /**
     * 
     * @param in
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsTourDepartureInfoPagingVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsTourDepartureInfoPagingVO getTourDepartureInfoPaging(
        @WebParam(name = "in", partName = "in")
        WsInGetTourDepartureInfoPagingVO in);

    /**
     * 
     * @param in
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsDepartureMandatoryMiscVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsDepartureMandatoryMiscVO getDepartureMandatoryMisc(
        @WebParam(name = "in", partName = "in")
        WsInGetDepartureMandatoryMiscVO in);

    /**
     * 
     * @param tourDirectorId
     * @param includeClosedDepartures
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsTourSummariesVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsTourSummariesVO getToursOfTourDirector(
        @WebParam(name = "tourDirectorId", partName = "tourDirectorId")
        long tourDirectorId,
        @WebParam(name = "includeClosedDepartures", partName = "includeClosedDepartures")
        boolean includeClosedDepartures);

    /**
     * 
     * @param in
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsRoomOccupancyResponseVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsRoomOccupancyResponseVO validateRoomOccupancy(
        @WebParam(name = "in", partName = "in")
        ComTropicsWebserviceITropicsVoCurrentProductsAccomWSInValidateRoomOccupancyVO in);

    /**
     * 
     * @param in
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.ComTropicsWebserviceITropicsVoCurrentCommonWSTransferProductResponseVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public ComTropicsWebserviceITropicsVoCurrentCommonWSTransferProductResponseVO getTransferProductDetails(
        @WebParam(name = "in", partName = "in")
        ComTropicsWebserviceITropicsVoCurrentProductsMisctransferWSInFlightTransfersVO in);

    /**
     * 
     * @param request
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsToursVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsToursVO getTours(
        @WebParam(name = "request", partName = "request")
        WsInGetToursVO request);

    /**
     * 
     * @param request
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsToursOfBrandsVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsToursOfBrandsVO getToursOfBrands(
        @WebParam(name = "request", partName = "request")
        WsInGetToursOfBrandsVO request);

    /**
     * 
     * @param request
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsToursWithSCListVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsToursWithSCListVO getToursWithSCList(
        @WebParam(name = "request", partName = "request")
        WsInGetToursWithSCListVO request);

    /**
     * 
     * @param request
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsDeparturesVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsDeparturesVO getTourDatesAndRates(
        @WebParam(name = "request", partName = "request")
        WsInGetTourDatesAndRatesVO request);

    /**
     * 
     * @param request
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.ComTropicsWebserviceITropicsVoCurrentCommonWSGetAssociatedMiscProductsResponse
     */
    @WebMethod
    @WebResult(partName = "return")
    public ComTropicsWebserviceITropicsVoCurrentCommonWSGetAssociatedMiscProductsResponse getAssociatedMiscProducts(
        @WebParam(name = "request", partName = "request")
        ComTropicsWebserviceITropicsVoLegacyInputWSInGetAssociatedMiscProductsAPIVO request);

    /**
     * 
     * @param request
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsiProductNotesVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsiProductNotesVO getIProductNotes(
        @WebParam(name = "request", partName = "request")
        WsInGetIProductNotesVO request);

    /**
     * 
     * @param in
     * @return
     *     returns facade.itropics.webservice.tropics.com.itropicsbuildws.WsBrochuresVO
     */
    @WebMethod
    @WebResult(partName = "return")
    public WsBrochuresVO getBrochures(
        @WebParam(name = "in", partName = "in")
        WsBrochuresIn in);

}
