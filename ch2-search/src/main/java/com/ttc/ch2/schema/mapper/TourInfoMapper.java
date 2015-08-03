package com.ttc.ch2.schema.mapper;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.AdditionalDefiners;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.AdditionalInfo;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Airport;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.AirportTransfers;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Airports;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Assets;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Brochure;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.CataloguedTour;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.City;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Continent;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.ContinentCode;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.ContinentsVisited;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.CountriesVisited;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Country;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Extra;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Highlights;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.ISO3166CountryCode;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Image;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Images;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.IncludedSubProduct;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.IncludedSubProducts;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Itinerary;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.ItinerarySegment;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.KeywordsPhrases;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Location;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.LocationsVisited;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.MarketingFlags;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Meal;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Meals;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Notes;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.OperatingProduct;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.OptionalExtras;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Price;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.RoomType;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.RoomTypes;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Section;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.SellingCompanies;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.SellingCompany;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.TourCategories;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.TourCategory;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.TourInfoSimp;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.TourVariationDefiners;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Video;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.Videos;
import com.ttc.ch2.schema.tourinfosimp._2014._01._3.WhatsIncluded;

public class TourInfoMapper {

	private static final Logger logger = LoggerFactory.getLogger(TourInfoMapper.class);
	private static JAXBContext jaxbTextContext;
	private static JAXBContext jcADws;
	private static JAXBContext jcCVws;
	private static JAXBContext jcCOVws;
	private static JAXBContext jcHws;
	private static JAXBContext jcISws;
	private static JAXBContext jcMFws;
	private static JAXBContext jcASws;
	private static JAXBContext jcRTws;
	
	static {
		try {
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
			properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
			
			jaxbTextContext = JAXBContext.newInstance(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Text.class);
			jcADws = JAXBContext.newInstance(new Class[] { AdditionalDefiners.class }, properties);
	        jcCVws = JAXBContext.newInstance(new Class[] { com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ContinentsVisited.class }, properties);
	        jcCOVws = JAXBContext.newInstance(new Class[] {com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.CountriesVisited.class }, properties);
	        jcHws = JAXBContext.newInstance(new Class[] { Highlights.class }, properties);
	        jcISws = JAXBContext.newInstance(new Class[] { com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.IncludedSubProducts.class }, properties);
	        jcMFws = JAXBContext.newInstance(new Class[] { MarketingFlags.class }, properties);
	        jcASws = JAXBContext.newInstance( new Class[] { com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Assets.class}, properties );
	        jcRTws = JAXBContext.newInstance( new Class[] { com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.RoomTypes.class}, properties );
		} catch (Exception e) {
			logger.error("Cannot initialize TourInfoMapper static block", e);
		}
	}
	
	private Marshaller jaxbTextMarshaller;
	private Unmarshaller jaxbTextUnmarshaller;
	private Unmarshaller unmarshallerAD;
	private Unmarshaller unmarshallerCV;
	private Unmarshaller unmarshallerCOV;
	private Unmarshaller unmarshallerH;
	private Unmarshaller unmarshallerIS;
	private Unmarshaller unmarshallerMF;
	private Unmarshaller unmarshallerAS; 
	private Unmarshaller unmarshallerRT;

	public TourInfoMapper() throws JAXBException {
		
		jaxbTextMarshaller = jaxbTextContext.createMarshaller();
		jaxbTextUnmarshaller = jaxbTextContext.createUnmarshaller();
		unmarshallerAD = jcADws.createUnmarshaller();
		unmarshallerCV = jcCVws.createUnmarshaller();
		unmarshallerCOV = jcCOVws.createUnmarshaller();
		unmarshallerH = jcHws.createUnmarshaller();
		unmarshallerIS = jcISws.createUnmarshaller();
		unmarshallerMF = jcMFws.createUnmarshaller();
		unmarshallerAS = jcASws.createUnmarshaller(); 
		unmarshallerRT = jcRTws.createUnmarshaller();
	}
	
	public com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Highlights mapHighlights(String highlightsJson) throws JAXBException, UnsupportedEncodingException{
		com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Highlights wsHiglights = new com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Highlights();
		Highlights highlights = unmarshallerH.unmarshal(
				new StreamSource(new ByteArrayInputStream(highlightsJson.getBytes())),Highlights.class).getValue();
		
		for(int i=0; i<highlights.getSection().size(); i++) {
				wsHiglights.getSection().add(mapSection(highlights.getSection().get(i)));
		}			
		
		return wsHiglights;
	}
	
	public com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.AdditionalDefiners mapAdditionalDefiners(String additionalDefinersJson) throws UnsupportedEncodingException, JAXBException {
		com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.AdditionalDefiners wsAdditionalDefiners = new com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.AdditionalDefiners();
		AdditionalDefiners additionalDefiners = unmarshallerAD.unmarshal(new StreamSource(new ByteArrayInputStream(additionalDefinersJson.getBytes("UTF-8"))),AdditionalDefiners.class).getValue();
		
		for(int i=0; i<additionalDefiners.getSection().size(); i++) {
			wsAdditionalDefiners.getSection().add(mapSection(additionalDefiners.getSection().get(i)));
		}
		return wsAdditionalDefiners;
	}
	
	
	public com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ContinentsVisited mapContinentsVisited(String continentsVisitedJson) throws UnsupportedEncodingException, JAXBException {
		com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ContinentsVisited wsContinentsVisited =
				unmarshallerCV.unmarshal(new StreamSource(
						new ByteArrayInputStream(continentsVisitedJson.getBytes("UTF-8"))), 
						com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ContinentsVisited.class).getValue();		
		
		return wsContinentsVisited;
	}
	
	public com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.CountriesVisited mapCountriesVisited(String countriesVisitedJson) throws UnsupportedEncodingException, JAXBException {
		com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.CountriesVisited wsCountriesVisited = 
				unmarshallerCOV.unmarshal(new StreamSource
						(new ByteArrayInputStream(countriesVisitedJson.getBytes("UTF-8"))),
						com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.CountriesVisited.class).getValue();
			
		return wsCountriesVisited;
	}
	
	public com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.IncludedSubProducts mapIncludedSubProducts(String includedSubProductsJson) throws UnsupportedEncodingException, JAXBException{
		com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.IncludedSubProducts wsIncludedSubProducts =
				unmarshallerIS.unmarshal(new StreamSource( 
						new ByteArrayInputStream(includedSubProductsJson.getBytes("UTF-8"))), 
						com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.IncludedSubProducts.class).getValue(); 			 
			
		return wsIncludedSubProducts;
	}
	
	public com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.MarketingFlags mapMarketingFlags(String marketingFlagsJson) throws UnsupportedEncodingException, JAXBException {
		com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.MarketingFlags wsMarketingFlags = new com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.MarketingFlags();
		
		MarketingFlags marketingFlags = unmarshallerMF.unmarshal(new StreamSource(
				new ByteArrayInputStream(marketingFlagsJson.getBytes("UTF-8"))),
				MarketingFlags.class).getValue(); 
		
		wsMarketingFlags.setMarketingPriority(marketingFlags.getMarketingPriority());
		wsMarketingFlags.setMostPopular(marketingFlags.isMostPopular());
		if(marketingFlags.getKeywordsPhrases() != null) {
			com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.KeywordsPhrases keywordsPhrases = new com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.KeywordsPhrases();
			wsMarketingFlags.setKeywordsPhrases(keywordsPhrases);
			for(int j=0; j<marketingFlags.getKeywordsPhrases().getText().size(); j++) {
				keywordsPhrases.getText().add(mapText(marketingFlags.getKeywordsPhrases().getText().get(j)));
				
			}
		}
		return wsMarketingFlags;
	}
	
	public com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Assets mapAssets(String assetsJson) throws UnsupportedEncodingException, JAXBException {
		com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Assets wsAssets = 
				unmarshallerAS.unmarshal(new StreamSource(
						new ByteArrayInputStream(assetsJson.getBytes("UTF-8"))), 
						com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Assets.class).getValue();
		
		return wsAssets;
	}
	
	public com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.RoomTypes mapRoomTypes(String roomTypeJson) throws UnsupportedEncodingException, JAXBException {
		com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.RoomTypes wsRoomTypes = 
				unmarshallerRT.unmarshal(new StreamSource(
						new ByteArrayInputStream(roomTypeJson.getBytes("UTF-8"))),
						com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.RoomTypes.class).getValue();
		
		return wsRoomTypes;
	}
	
	public TourInfoSimp mapToTourInfoSimp(TourInfo tourInfo) throws JAXBException {
		Marshaller jaxbTextMarshaller = jaxbTextContext.createMarshaller();
		TourInfoSimp tourInfoSimp = new TourInfoSimp();
		if( tourInfo.getAdditionalInfo() != null ) {
			AdditionalInfo additionalInfo = new AdditionalInfo();
			tourInfoSimp.setAdditionalInfo(additionalInfo);
			for(int i=0; i<tourInfo.getAdditionalInfo().getSection().size(); i++) {
				additionalInfo.getSection().add(mapSection(tourInfo.getAdditionalInfo().getSection().get(i)));
			}
		}
		if(tourInfo.getAirportTransfers() != null) {
			AirportTransfers airportTransfers = new AirportTransfers();
			tourInfoSimp.setAirportTransfers(airportTransfers);
			for(int i=0; i<tourInfo.getAirportTransfers().getSection().size(); i++) {
				airportTransfers.getSection().add(mapSection(tourInfo.getAirportTransfers().getSection().get(i)));
			}
			
		}
		if(tourInfo.getAssets() != null) {
			Assets assets=new Assets();
			tourInfoSimp.setAssets(assets);
			if( tourInfo.getAssets().getImages() != null ) {
				Images images = new Images();
				assets.setImages(images);
				for(int i=0; i<tourInfo.getAssets().getImages().getImage().size(); i++) {
					Image image = new Image();
					images.getImage().add(image);
					image.setCaption(tourInfo.getAssets().getImages().getImage().get(i).getCaption());
					image.setHeight(tourInfo.getAssets().getImages().getImage().get(i).getHeight());
					image.setName(tourInfo.getAssets().getImages().getImage().get(i).getName());
					image.setType(tourInfo.getAssets().getImages().getImage().get(i).getType());
					image.setUrl(tourInfo.getAssets().getImages().getImage().get(i).getUrl());
					image.setWidth(tourInfo.getAssets().getImages().getImage().get(i).getWidth());
				}
			}
			if(tourInfo.getAssets().getVideos() != null) {
				Videos videos = new Videos();
				assets.setVideos(videos);
				for(int i=0; i<videos.getVideo().size(); i++) {
					Video video = new Video();
					videos.getVideo().add(video);
					video.setAspectRatio(tourInfo.getAssets().getVideos().getVideo().get(i).getAspectRatio());
					video.setCaption(tourInfo.getAssets().getVideos().getVideo().get(i).getCaption());
					video.setName(tourInfo.getAssets().getVideos().getVideo().get(i).getName());
					video.setType(tourInfo.getAssets().getVideos().getVideo().get(i).getType());
					video.setUrl(tourInfo.getAssets().getVideos().getVideo().get(i).getUrl());
				}
			}
		}
		tourInfoSimp.setBrandCode(tourInfo.getBrandCode());
		if(tourInfo.getCataloguedTour() != null) {
			CataloguedTour cataloguedTour = new CataloguedTour();
			tourInfoSimp.setCataloguedTour(cataloguedTour);
			cataloguedTour.setCode(tourInfo.getCataloguedTour().getCode());
			cataloguedTour.setName(tourInfo.getCataloguedTour().getName());
		}
		tourInfoSimp.setCMSId(tourInfo.getCMSId());
		tourInfoSimp.setCMSTourId(tourInfo.getCMSTourId());
		if(tourInfo.getContinentsVisited() != null) {
			ContinentsVisited continentsVisited = new ContinentsVisited(); 
			tourInfoSimp.setContinentsVisited(continentsVisited);
			for(int i=0; i<tourInfo.getContinentsVisited().getContinent().size(); i++) {
				Continent continent = new Continent();
				continentsVisited.getContinent().add(continent);
				continent.setName(tourInfo.getContinentsVisited().getContinent().get(i).getName());
				continent.setCode(ContinentCode.fromValue(tourInfo.getContinentsVisited().getContinent().get(i).getCode().value()));
			}
		}
		if(tourInfo.getCountriesVisited() != null) {
			CountriesVisited countriesVisited = new CountriesVisited();
			tourInfoSimp.setCountriesVisited(countriesVisited);
			for(int i=0; i<tourInfo.getCountriesVisited().getCountry().size(); i++) {
				Country country = new Country();
				countriesVisited.getCountry().add(country);
				country.setCode(ISO3166CountryCode.fromValue(tourInfo.getCountriesVisited().getCountry().get(i).getCode().value()));
				country.setContinentCode(ContinentCode.fromValue(tourInfo.getCountriesVisited().getCountry().get(i).getContinentCode().value()));
				country.setName(tourInfo.getCountriesVisited().getCountry().get(i).getName());
			}
		}
		tourInfoSimp.setDescription(tourInfo.getDescription());
		tourInfoSimp.setDuration(tourInfo.getDuration());
		if(tourInfo.getHighlights() != null) {
			Highlights highlights = new Highlights();
			tourInfoSimp.setHighlights(highlights);
			for(int i=0; i<tourInfo.getHighlights().getSection().size(); i++) {
				highlights.getSection().add(mapSection(tourInfo.getHighlights().getSection().get(i)));
			}			
		}
		if(tourInfo.getItinerary() != null) {
			Itinerary itinerary = new Itinerary();
			tourInfoSimp.setItinerary(itinerary);
			for(int i=0; i<tourInfo.getItinerary().getItinerarySegment().size(); i++) {
				ItinerarySegment itinerarySegment = new ItinerarySegment();
				itinerary.getItinerarySegment().add(itinerarySegment);
				itinerarySegment.setAccommodation(tourInfo.getItinerary().getItinerarySegment().get(i).getAccommodation());
				itinerarySegment.setDuration(tourInfo.getItinerary().getItinerarySegment().get(i).getDuration());
				if(tourInfo.getItinerary().getItinerarySegment().get(i).getLocationsVisited() != null) {
					LocationsVisited locationsVisited = new LocationsVisited();
					itinerarySegment.setLocationsVisited(locationsVisited);
					for(int j=0; j<tourInfo.getItinerary().getItinerarySegment().get(i).getLocationsVisited().getLocation().size(); j++) {
						Location location = new Location();
						locationsVisited.getLocation().add(location);
						location.setName(tourInfo.getItinerary().getItinerarySegment().get(i).getLocationsVisited().getLocation().get(j).getName());
						location.setCountryCode(ISO3166CountryCode.fromValue(
								tourInfo.getItinerary().getItinerarySegment().get(i).getLocationsVisited().getLocation().get(j).getCountryCode().value()
								));
					}
				}
				if(tourInfo.getItinerary().getItinerarySegment().get(i).getMeals() != null) {
					Meals meals = new Meals();
					itinerarySegment.setMeals(meals);
					for(int j=0; j<tourInfo.getItinerary().getItinerarySegment().get(i).getMeals().getMeal().size(); j++) {
						Meal meal = new Meal();
						meals.getMeal().add(meal);
						meal.setType(tourInfo.getItinerary().getItinerarySegment().get(i).getMeals().getMeal().get(j).getType());
						meal.setNumber(tourInfo.getItinerary().getItinerarySegment().get(i).getMeals().getMeal().get(j).getNumber());
					}
				}
				if(tourInfo.getItinerary().getItinerarySegment().get(i).getOptionalExtras() != null) {
					OptionalExtras optionalExtras = new OptionalExtras();
					itinerarySegment.setOptionalExtras(optionalExtras);
					for(int j=0; j<tourInfo.getItinerary().getItinerarySegment().get(i).getOptionalExtras().getExtra().size(); j++) {
						Extra extra = new Extra();
						optionalExtras.getExtra().add(extra);
						extra.setCode(tourInfo.getItinerary().getItinerarySegment().get(i).getOptionalExtras().getExtra().get(j).getCode());
						for(int k=0; k< tourInfo.getItinerary().getItinerarySegment().get(i).getOptionalExtras().getExtra().get(j).getPrice().size(); k++) {
							Price price = new Price();
							extra.getPrice().add(price);
							price.setAmountFrom(tourInfo.getItinerary().getItinerarySegment().get(i).getOptionalExtras().getExtra().get(j).getPrice().get(k).getAmountFrom());
							price.setAmountTo(tourInfo.getItinerary().getItinerarySegment().get(i).getOptionalExtras().getExtra().get(j).getPrice().get(k).getAmountTo());
							price.setCurrencyCode(tourInfo.getItinerary().getItinerarySegment().get(i).getOptionalExtras().getExtra().get(j).getPrice().get(k).getCurrencyCode());
							if(tourInfo.getItinerary().getItinerarySegment().get(i).getOptionalExtras().getExtra().get(j).getPrice().get(k).getNotes() != null) {
								Notes notes = new Notes();
								price.setNotes(notes);
								for(int l=0; l<tourInfo.getItinerary().getItinerarySegment().get(i).getOptionalExtras().getExtra().get(j).getPrice().get(k).getNotes().getSection().size(); l++) {
									notes.getSection().add(mapSection(tourInfo.getItinerary().getItinerarySegment().get(i).getOptionalExtras().getExtra().get(j).getPrice().get(k).getNotes().getSection().get(l)));
								}
							}
							price.setPassengerType(tourInfo.getItinerary().getItinerarySegment().get(i).getOptionalExtras().getExtra().get(j).getPrice().get(k).getPassengerType());
						}
						for(int k=0; k<tourInfo.getItinerary().getItinerarySegment().get(i).getOptionalExtras().getExtra().get(j).getSection().size(); k++) {
							extra.getSection().add(mapSection(tourInfo.getItinerary().getItinerarySegment().get(i).getOptionalExtras().getExtra().get(j).getSection().get(k)));
						}
					}
				}
				itinerarySegment.setStartDay(tourInfo.getItinerary().getItinerarySegment().get(i).getStartDay());
				
				itinerarySegment.setText(mapText(tourInfo.getItinerary().getItinerarySegment().get(i).getText()));
				itinerarySegment.setTitle(tourInfo.getItinerary().getItinerarySegment().get(i).getTitle());
			}
			
		}
		if(tourInfo.getLocationsVisited() != null) {
			LocationsVisited locationsVisited = new LocationsVisited();
			tourInfoSimp.setLocationsVisited(locationsVisited);
			for(int i=0; i<tourInfo.getLocationsVisited().getLocation().size(); i++) {
				Location location = new Location();
				locationsVisited.getLocation().add(location);
				location.setCountryCode(ISO3166CountryCode.fromValue(tourInfo.getLocationsVisited().getLocation().get(i).getCountryCode().value()));
				location.setName(tourInfo.getLocationsVisited().getLocation().get(i).getName());
			}
		}
		if(tourInfo.getNotes() != null) {
			Notes notes = new Notes();
			tourInfoSimp.setNotes(notes);
			for(int i=0; i<tourInfo.getNotes().getSection().size(); i++) {
				notes.getSection().add(mapSection(tourInfo.getNotes().getSection().get(i)));
			}
		}
		if(tourInfo.getSellingCompanies() != null) {
			SellingCompanies sellingCompanies = new SellingCompanies(); 
			tourInfoSimp.setSellingCompanies(sellingCompanies);
			for(int i=0; i<tourInfo.getSellingCompanies().getSellingCompany().size(); i++) {
				SellingCompany sellingCompany = new SellingCompany(); 
				sellingCompanies.getSellingCompany().add(sellingCompany);
				sellingCompany.setCode(tourInfo.getSellingCompanies().getSellingCompany().get(i).getCode());
				sellingCompany.setCurrencyCode(tourInfo.getSellingCompanies().getSellingCompany().get(i).getCurrencyCode());
				if(tourInfo.getSellingCompanies().getSellingCompany().get(i).getMarketingFlags() != null) {
					MarketingFlags marketingFlags = new MarketingFlags(); 
					sellingCompany.setMarketingFlags(marketingFlags);
					marketingFlags.setMarketingPriority(tourInfo.getSellingCompanies().getSellingCompany().get(i).getMarketingFlags().getMarketingPriority());
					marketingFlags.setMostPopular(tourInfo.getSellingCompanies().getSellingCompany().get(i).getMarketingFlags().isMostPopular());
					if(tourInfo.getSellingCompanies().getSellingCompany().get(i).getMarketingFlags().getKeywordsPhrases() != null) {
						KeywordsPhrases keywordsPhrases = new KeywordsPhrases();
						marketingFlags.setKeywordsPhrases(keywordsPhrases);
						for(int j=0; j<tourInfo.getSellingCompanies().getSellingCompany().get(i).getMarketingFlags().getKeywordsPhrases().getText().size(); j++) {
							keywordsPhrases.getText().add(mapText(tourInfo.getSellingCompanies().getSellingCompany().get(i).getMarketingFlags().getKeywordsPhrases().getText().get(j)));
						}
					}
				}
				sellingCompany.setTermsAndConditionsLinkURL(tourInfo.getSellingCompanies().getSellingCompany().get(i).getTermsAndConditionsLinkURL());
				if(tourInfo.getSellingCompanies().getSellingCompany().get(i).getTourCategories() != null) {
					TourCategories tourCategories = new TourCategories();
					sellingCompany.setTourCategories(tourCategories);
					for(int j=0; j<tourInfo.getSellingCompanies().getSellingCompany().get(i).getTourCategories().getTourCategory().size(); j++ ) {
						TourCategory tourCategory = new TourCategory();
						tourCategories.getTourCategory().add(tourCategory);
						tourCategory.setName(tourInfo.getSellingCompanies().getSellingCompany().get(i).getTourCategories().getTourCategory().get(j).getName());
						for(int k=0; k<tourInfo.getSellingCompanies().getSellingCompany().get(i).getTourCategories().getTourCategory().get(j).getCategoryValue().size(); k++) {
							tourCategory.getCategoryValue().add(tourInfo.getSellingCompanies().getSellingCompany().get(i).getTourCategories().getTourCategory().get(j).getCategoryValue().get(k));
						}
					}
				}
				for(int j=0; j<tourInfo.getSellingCompanies().getSellingCompany().get(i).getBrochure().size(); j++) {
					Brochure brochure = new Brochure();
					sellingCompany.getBrochure().add(brochure);
					brochure.setCode(tourInfo.getSellingCompanies().getSellingCompany().get(i).getBrochure().get(j).getCode());
					brochure.setName(tourInfo.getSellingCompanies().getSellingCompany().get(i).getBrochure().get(j).getName());
					
				}
			}
		}
		tourInfoSimp.setTourCode(tourInfo.getTourCode());
		tourInfoSimp.setTourName(tourInfo.getTourName());
		if(tourInfo.getWhatsIncluded() !=null) {
			WhatsIncluded whatsIncluded = new WhatsIncluded();
			tourInfoSimp.setWhatsIncluded(whatsIncluded);
			for(int i=0; i<tourInfo.getWhatsIncluded().getSection().size(); i++) {
				whatsIncluded.getSection().add(mapSection(tourInfo.getWhatsIncluded().getSection().get(i)));
			}
		}
		if(tourInfo.getTourVariationDefiners() != null) {
			TourVariationDefiners tourVariationDefiners = new TourVariationDefiners(); 
			tourInfoSimp.setTourVariationDefiners(tourVariationDefiners);
			if(tourInfo.getTourVariationDefiners().getAdditionalDefiners() != null) {
				AdditionalDefiners additionalDefiners = new AdditionalDefiners();
				tourVariationDefiners.setAdditionalDefiners(additionalDefiners);
				for(int i=0; i<tourInfo.getTourVariationDefiners().getAdditionalDefiners().getSection().size(); i++) {
					additionalDefiners.getSection().add(mapSection(tourInfo.getTourVariationDefiners().getAdditionalDefiners().getSection().get(i)));
				}
			}
			if(tourInfo.getTourVariationDefiners().getEndCity() != null) {
				tourVariationDefiners.setEndCity(mapCity(tourInfo.getTourVariationDefiners().getEndCity()));
			}
			if(tourInfo.getTourVariationDefiners().getStartCity() != null){
				tourVariationDefiners.setStartCity(mapCity(tourInfo.getTourVariationDefiners().getStartCity()));
			}
			tourVariationDefiners.setIncludedCruiseCabinType(tourInfo.getTourVariationDefiners().getIncludedCruiseCabinType());
			if(tourInfo.getTourVariationDefiners().getIncludedSubProducts() != null ) {
				IncludedSubProducts includedSubProducts = new IncludedSubProducts(); 
				tourVariationDefiners.setIncludedSubProducts(includedSubProducts);
				for(int i=0; i<tourInfo.getTourVariationDefiners().getIncludedSubProducts().getIncludedSubProduct().size(); i++) {
					IncludedSubProduct includedSubProduct = new IncludedSubProduct(); 
					includedSubProducts.getIncludedSubProduct().add(includedSubProduct);
					includedSubProduct.setCategory(tourInfo.getTourVariationDefiners().getIncludedSubProducts().getIncludedSubProduct().get(i).getCategory());
					includedSubProduct.setCode(tourInfo.getTourVariationDefiners().getIncludedSubProducts().getIncludedSubProduct().get(i).getCode());
					includedSubProduct.setName(tourInfo.getTourVariationDefiners().getIncludedSubProducts().getIncludedSubProduct().get(i).getName());
					includedSubProduct.setServiceType(tourInfo.getTourVariationDefiners().getIncludedSubProducts().getIncludedSubProduct().get(i).getServiceType());
				}
				
			}
			tourVariationDefiners.setIsTourPackage(tourInfo.getTourVariationDefiners().isIsTourPackage());
			if(tourInfo.getTourVariationDefiners().getOperatingProduct() != null) {
				OperatingProduct operatingProduct = new OperatingProduct();
				tourVariationDefiners.setOperatingProduct(operatingProduct);
				operatingProduct.setCategory(tourInfo.getTourVariationDefiners().getOperatingProduct().getCategory());
				operatingProduct.setClassification(tourInfo.getTourVariationDefiners().getOperatingProduct().getClassification());
				operatingProduct.setCode(tourInfo.getTourVariationDefiners().getOperatingProduct().getCode());
				operatingProduct.setContractingSeason(tourInfo.getTourVariationDefiners().getOperatingProduct().getContractingSeason());
				operatingProduct.setStandardName(tourInfo.getTourVariationDefiners().getOperatingProduct().getStandardName());
			}
			if(tourInfo.getTourVariationDefiners().getRoomTypes() != null) {
				RoomTypes roomTypes = new RoomTypes();
				tourVariationDefiners.setRoomTypes(roomTypes);
				for(int i=0; i<tourInfo.getTourVariationDefiners().getRoomTypes().getRoomType().size(); i++ ) {
					RoomType roomType = new RoomType();
					roomTypes.getRoomType().add(roomType);
					roomType.setSellable(tourInfo.getTourVariationDefiners().getRoomTypes().getRoomType().get(i).isSellable());
					roomType.setType(tourInfo.getTourVariationDefiners().getRoomTypes().getRoomType().get(i).getType());
				}
				
			}
		}
		return tourInfoSimp;
	}
	
	private City mapCity(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.City fromCity){
		City city = new City();
		city.setName(fromCity.getName());
		if(fromCity.getAirports() != null) {
			Airports airports = new Airports();
			city.setAirports(airports);
			for(int i=0; i<fromCity.getAirports().getAirport().size(); i++) {
				Airport airport = new Airport();
				airports.getAirport().add(airport);
				airport.setCity(fromCity.getAirports().getAirport().get(i).getCity());
				airport.setCountry(fromCity.getAirports().getAirport().get(i).getCountry());
				airport.setDefaultForCity(fromCity.getAirports().getAirport().get(i).isDefaultForCity());
				airport.setIATACode(fromCity.getAirports().getAirport().get(i).getIATACode());
				airport.setName(fromCity.getAirports().getAirport().get(i).getName());
				airport.setRegion(fromCity.getAirports().getAirport().get(i).getRegion());
			}
		}
		return city;	
	}
	private String mapText(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Text fromText) throws JAXBException {
		StringWriter sw = new StringWriter();
		JAXBElement<com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Text> jbText = 
				new JAXBElement<com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Text>(new QName("http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0", "text"),
						com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Text.class, fromText); 
		jaxbTextMarshaller.marshal(jbText, sw);
		return sw.toString();
	}
	
	private com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Text mapText(String fromText) throws JAXBException, UnsupportedEncodingException {
		com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Text text;
		text = jaxbTextUnmarshaller.unmarshal(new StreamSource(
				new ByteArrayInputStream(fromText.getBytes("UTF-8"))),
				com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Text.class).getValue();
		return text;
	}
	
	private Section mapSection(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Section fromSection) throws JAXBException{
		Section section = new Section();
		section.setTitle(fromSection.getTitle());
		section.setText(mapText(fromSection.getText()));
		return section;
	}
	
	private com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Section mapSection(Section fromSection) throws JAXBException, UnsupportedEncodingException{
		com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Section section = new com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Section();
		section.setTitle(fromSection.getTitle());
		section.setText(mapText(fromSection.getText()));
		return section;
	}
	
}
