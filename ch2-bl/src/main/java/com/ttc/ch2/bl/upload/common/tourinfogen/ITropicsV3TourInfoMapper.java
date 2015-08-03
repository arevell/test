package com.ttc.ch2.bl.upload.common.tourinfogen;

import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang.StringUtils;

import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.A;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Airport;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.AirportTransfers;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Airports;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Assets;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Br;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Brochure;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.CataloguedTour;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.City;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Continent;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ContinentCode;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ContinentsVisited;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.CountriesVisited;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Country;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Highlights;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ISO3166CountryCode;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Image;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Images;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Itinerary;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ItinerarySegment;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Li;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Location;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.LocationsVisited;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Notes;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ObjectFactory;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.OperatingProduct;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.RoomType;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.RoomTypes;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Section;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.SellingCompanies;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.SellingCompany;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Strong;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Text;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourCategories;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourCategory;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourVariationDefiners;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Ul;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.WhatsIncluded;

public class ITropicsV3TourInfoMapper {

	private static String DUMMY_TEXT = "empty_text";
	private static String DUMMY_URL = "http://empty.url";

	private static BigInteger DUMMY_WIDTH = new BigInteger("320");
	private static BigInteger DUMMY_HEIGHT = new BigInteger("200");

	private static ObjectFactory objectFactoryV3 = new ObjectFactory();

	public static TourInfo map(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfo) {

		if (tourInfo == null) {
			return null;
		}

		TourInfo tourInfoDst = new TourInfo();

		copyProductData(tourInfo, tourInfoDst);
		copyProductSellingCompaniesData(tourInfo, tourInfoDst);
		copyContinentsVisitedData(tourInfo, tourInfoDst);
		copyCountriesVisitedData(tourInfo, tourInfoDst);
		copyLocationsVisitedData(tourInfo, tourInfoDst);
		copyAssetsData(tourInfo, tourInfoDst);
		copyItineraryData(tourInfo, tourInfoDst);
		copyWhatsIncludedData(tourInfo, tourInfoDst);
		copyHighlightsData(tourInfo, tourInfoDst);
		copyAirportTransfersData(tourInfo, tourInfoDst);
		copyNotesData(tourInfo, tourInfoDst);
		mockTourVariationDefinersData(tourInfo, tourInfoDst);

		return tourInfoDst;
	}

	private static void copyProductData(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfoSrc, TourInfo tourInfoDst) {

		CataloguedTour cataloguedTour = new CataloguedTour();
		cataloguedTour.setCode(tourInfoSrc.getCatalogueCode());
		cataloguedTour.setName(DUMMY_TEXT);

		tourInfoDst.setBrandCode(tourInfoSrc.getBrandCode());
		tourInfoDst.setCataloguedTour(cataloguedTour);
		tourInfoDst.setCMSId(tourInfoSrc.getCMSId());
		tourInfoDst.setCMSTourId(tourInfoSrc.getCMSTourId());
		tourInfoDst.setDescription(tourInfoSrc.getDescription());
		tourInfoDst.setDuration(tourInfoSrc.getDuration());
		tourInfoDst.setTourCode(tourInfoSrc.getMVCode());
		tourInfoDst.setTourName(tourInfoSrc.getTourName());
	}

	private static void copyProductSellingCompaniesData(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfoSrc, TourInfo tourInfoDst) {

		if (tourInfoSrc.getMetadata() == null || tourInfoSrc.getMetadata().getSellingCompanies() == null) {
			return;
		}

		SellingCompanies sellingCompanies = new SellingCompanies();

		for (com.ttsl.tourinfo._2010._08._2.SellingCompany sellingCompanySrc : tourInfoSrc.getMetadata().getSellingCompanies().getSellingCompany()) {

			SellingCompany sellingCompanyDst = new SellingCompany();
			sellingCompanyDst.setCode(sellingCompanySrc.getCode());
			sellingCompanyDst.setCurrencyCode(sellingCompanySrc.getCurrency());

			if (sellingCompanySrc.getBrochure() != null) {

				for (com.ttsl.tourinfo._2010._08._2.Brochure brochureSrc : sellingCompanySrc.getBrochure()) {

					Brochure brochureDst = new Brochure();
					brochureDst.setCode(brochureSrc.getCode());
					brochureDst.setName(brochureSrc.getName());

					sellingCompanyDst.getBrochure().add(brochureDst);
				}
			}

			if (tourInfoSrc.getMetadata().getTourCategories() != null && tourInfoSrc.getMetadata().getTourCategories().getTourCategory().size() > 0) {

				TourCategories tourCategoriesDst = new TourCategories();

				for (com.ttsl.tourinfo._2010._08._2.TourCategory tourCategorySrc : tourInfoSrc.getMetadata().getTourCategories().getTourCategory()) {

					TourCategory tourCategoryDst = new TourCategory();
					tourCategoryDst.setName(tourCategorySrc.getName());

					for (com.ttsl.tourinfo._2010._08._2.CategoryValue categoryValueSrc : tourCategorySrc.getCategoryValue()) {
						tourCategoryDst.getCategoryValue().add(categoryValueSrc.getName());
					}

					tourCategoriesDst.getTourCategory().add(tourCategoryDst);
				}

				sellingCompanyDst.setTourCategories(tourCategoriesDst);

			} else {

				mockTourCategories(sellingCompanyDst);
			}

			sellingCompanies.getSellingCompany().add(sellingCompanyDst);
		}

		tourInfoDst.setSellingCompanies(sellingCompanies);
	}

	private static void copyContinentsVisitedData(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfoSrc, TourInfo tourInfoDst) {

		if (tourInfoSrc.getContinentsVisited() == null) {
			return;
		}

		ContinentsVisited continentsVisitedDst = new ContinentsVisited();

		for (com.ttsl.tourinfo._2010._08._2.Continent continentSrc : tourInfoSrc.getContinentsVisited().getContinent()) {

			Continent continentDst = new Continent();

			continentDst.setCode(ContinentCode.fromValue(continentSrc.getCode().value()));
			continentDst.setName(continentSrc.getName());

			continentsVisitedDst.getContinent().add(continentDst);
		}

		tourInfoDst.setContinentsVisited(continentsVisitedDst);
	}

	private static void copyCountriesVisitedData(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfoSrc, TourInfo tourInfoDst) {

		if (tourInfoSrc.getCountriesVisited() == null) {
			return;
		}

		CountriesVisited countriesVisitedDst = new CountriesVisited();

		for (com.ttsl.tourinfo._2010._08._2.Country countrySrc : tourInfoSrc.getCountriesVisited().getCountry()) {

			Country countryDst = new Country();

			countryDst.setCode(ISO3166CountryCode.fromValue(countrySrc.getCode().value()));
			countryDst.setContinentCode(ContinentCode.fromValue(countrySrc.getContinent().value()));
			countryDst.setName(countrySrc.getName());

			countriesVisitedDst.getCountry().add(countryDst);
		}

		tourInfoDst.setCountriesVisited(countriesVisitedDst);
	}

	private static void copyLocationsVisitedData(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfoSrc, TourInfo tourInfoDst) {

		if (tourInfoSrc.getLocationsVisited() == null) {
			return;
		}

		LocationsVisited locationsVisitedDst = new LocationsVisited();

		for (com.ttsl.tourinfo._2010._08._2.Location locationSrc : tourInfoSrc.getLocationsVisited().getLocation()) {

			Location locationdDst = new Location();

			locationdDst.setCountryCode(ISO3166CountryCode.fromValue(locationSrc.getCountry().value()));
			locationdDst.setName(locationSrc.getName());

			locationsVisitedDst.getLocation().add(locationdDst);
		}

		tourInfoDst.setLocationsVisited(locationsVisitedDst);
	}

	private static void copyAssetsData(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfoSrc, TourInfo tourInfoDst) {

		if (tourInfoSrc.getAssets() == null) {
			return;
		}

		Images imagesDst = new Images();

		if (tourInfoSrc.getAssets().getImage().size() > 0) {

			for (com.ttsl.tourinfo._2010._08._2.Image imageSrc : tourInfoSrc.getAssets().getImage()) {

				Image imageDst = new Image();

				imageDst.setCaption(imageSrc.getCaption());
				imageDst.setHeight(imageSrc.getHeight());
				imageDst.setName(imageSrc.getName());
				imageDst.setType(imageSrc.getType());
				imageDst.setUrl(imageSrc.getUrl());
				imageDst.setWidth(imageSrc.getWidth());

				imagesDst.getImage().add(imageDst);
			}

		} else {

			mockAssetsImages(imagesDst);
		}

		Assets assetsDst = new Assets();
		assetsDst.setImages(imagesDst);

		tourInfoDst.setAssets(assetsDst);
	}

	private static void copyItineraryData(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfoSrc, TourInfo tourInfoDst) {

		if (tourInfoSrc.getItinerary() == null) {
			return;
		}

		Itinerary itineraryDst = new Itinerary();

		for (com.ttsl.tourinfo._2010._08._2.ItinerarySegment itinerarySegmentSrc : tourInfoSrc.getItinerary().getItinerarySegment()) {

			ItinerarySegment itinerarySegmentDst = new ItinerarySegment();

			itinerarySegmentDst.setAccommodation(itinerarySegmentSrc.getAccommodation());
			itinerarySegmentDst.setDuration(itinerarySegmentSrc.getDuration());
			itinerarySegmentDst.setStartDay(itinerarySegmentSrc.getStartDay());
			itinerarySegmentDst.setText(new Text());
			itinerarySegmentDst.setTitle(itinerarySegmentSrc.getTitle());

			copySectionTextData(itinerarySegmentSrc.getText(), itinerarySegmentDst.getText());

			itineraryDst.getItinerarySegment().add(itinerarySegmentDst);
		}

		tourInfoDst.setItinerary(itineraryDst);
	}

	private static void copyWhatsIncludedData(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfoSrc, TourInfo tourInfoDst) {

		if (tourInfoSrc.getWhatsIncluded() == null) {
			return;
		}

		tourInfoDst.setWhatsIncluded(new WhatsIncluded());

		copySectionData(tourInfoSrc.getWhatsIncluded().getSection(), tourInfoDst.getWhatsIncluded().getSection());
	}

	private static void copyHighlightsData(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfoSrc, TourInfo tourInfoDst) {

		if (tourInfoSrc.getHighlights() == null) {
			return;
		}

		tourInfoDst.setHighlights(new Highlights());

		copySectionData(tourInfoSrc.getHighlights().getSection(), tourInfoDst.getHighlights().getSection());
	}

	private static void copyAirportTransfersData(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfoSrc, TourInfo tourInfoDst) {

		if (tourInfoSrc.getAirportTransfers() == null) {
			return;
		}

		tourInfoDst.setAirportTransfers(new AirportTransfers());

		copySectionData(tourInfoSrc.getAirportTransfers().getSection(), tourInfoDst.getAirportTransfers().getSection());
	}

	private static void copyNotesData(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfoSrc, TourInfo tourInfoDst) {

		if (tourInfoSrc.getNotes() == null) {
			return;
		}

		tourInfoDst.setNotes(new Notes());

		copySectionData(tourInfoSrc.getNotes().getSection(), tourInfoDst.getNotes().getSection());
	}

	private static void copySectionData(List<com.ttsl.tourinfo._2010._08._2.Section> sectionListSrc, List<Section> sectionListDst) {

		for (com.ttsl.tourinfo._2010._08._2.Section sectionSrc : sectionListSrc) {

			Section sectionDst = new Section();

			sectionDst.setText(new Text());
			sectionDst.setTitle(sectionSrc.getTitle());

			for (com.ttsl.tourinfo._2010._08._2.Text textSrc : sectionSrc.getText()) {
				copySectionTextData(textSrc, sectionDst.getText());
			}

			sectionListDst.add(sectionDst);
		}
	}

	@SuppressWarnings("unchecked")
	private static void copySectionTextData(com.ttsl.tourinfo._2010._08._2.Text textSrc, Text textDst) {

		if (textSrc == null) {
			return;
		}

		for (Object contentSrc : textSrc.getContent()) {

			Object contentDst = null;

			if (contentSrc instanceof String) {

				contentDst = contentSrc;

			} else if (contentSrc instanceof com.ttsl.tourinfo._2010._08._2.Br) {

				contentDst = new Br();

			} else if (contentSrc instanceof com.ttsl.tourinfo._2010._08._2.A) {

				contentDst = new A();

				((A) contentDst).setHref(((com.ttsl.tourinfo._2010._08._2.A) contentSrc).getHref());
				((A) contentDst).setValue(((com.ttsl.tourinfo._2010._08._2.A) contentSrc).getValue());

			} else if (contentSrc instanceof com.ttsl.tourinfo._2010._08._2.Ul) {

				contentDst = new Ul();

				for (com.ttsl.tourinfo._2010._08._2.Li liSrc : ((com.ttsl.tourinfo._2010._08._2.Ul) contentSrc).getLi()) {

					Li liDst = new Li();

					for (Object contentLiSrc : liSrc.getContent()) {

						if (contentLiSrc instanceof String) {

							liDst.getContent().add((String) contentLiSrc);

						} else if (contentLiSrc instanceof com.ttsl.tourinfo._2010._08._2.A) {

							A contentLiADst = new A();

//							contentLiADst.setHref(((com.ttsl.tourinfo._2010._08._2.A) contentSrc).getHref());
//							contentLiADst.setValue(((com.ttsl.tourinfo._2010._08._2.A) contentSrc).getValue());
							
							contentLiADst.setHref(((com.ttsl.tourinfo._2010._08._2.A) contentLiSrc).getHref());
							contentLiADst.setValue(((com.ttsl.tourinfo._2010._08._2.A) contentLiSrc).getValue());

							liDst.getContent().add(objectFactoryV3.createLiA(contentLiADst));

						} else if (contentLiSrc instanceof JAXBElement) {

							Strong contentLiStrongDst = new Strong();
							contentLiStrongDst.setValue(((JAXBElement<String>) contentLiSrc).getValue());

							liDst.getContent().add(objectFactoryV3.createLiStrong(contentLiStrongDst));
						}
					}

					((Ul) contentDst).getLi().add(liDst);
				}

			} else if (contentSrc instanceof JAXBElement) {

				contentDst = new Strong();

				((Strong) contentDst).setValue(((JAXBElement<String>) contentSrc).getValue());
			}

			textDst.getContent().add(contentDst);
		}
	}

	private static void mockTourCategories(SellingCompany sellingCompanyDst) {

		TourCategory tourCategoryDst = new TourCategory();
		tourCategoryDst.setName(DUMMY_TEXT);
		tourCategoryDst.getCategoryValue().add(DUMMY_TEXT);

		TourCategories tourCategoriesDst = new TourCategories();
		tourCategoriesDst.getTourCategory().add(tourCategoryDst);

		sellingCompanyDst.setTourCategories(tourCategoriesDst);
	}

	private static void mockAssetsImages(Images imagesDst) {

		Image imageDst = new Image();

		imageDst.setCaption(DUMMY_TEXT);
		imageDst.setName(DUMMY_TEXT);
		imageDst.setType(DUMMY_TEXT);
		imageDst.setUrl(DUMMY_URL);
		imageDst.setWidth(DUMMY_WIDTH);
		imageDst.setHeight(DUMMY_HEIGHT);

		imagesDst.getImage().add(imageDst);
	}

	private static void mockTourVariationDefinersData(com.ttsl.tourinfo._2010._08._2.TourInfo tourInfoSrc, TourInfo tourInfoDst) {

		OperatingProduct operatingProductDst = new OperatingProduct();
		operatingProductDst.setCategory(DUMMY_TEXT);
		operatingProductDst.setClassification(DUMMY_TEXT);
		operatingProductDst.setCode(StringUtils.defaultIfEmpty(tourInfoSrc.getOPCode(), DUMMY_TEXT));
		operatingProductDst.setContractingSeason(DUMMY_TEXT);
		operatingProductDst.setStandardName(DUMMY_TEXT);

		RoomType roomTypeDst = new RoomType();
		roomTypeDst.setSellable(true);
		roomTypeDst.setType(DUMMY_TEXT);

		RoomTypes roomTypesDst = new RoomTypes();
		roomTypesDst.getRoomType().add(roomTypeDst);

		Airport airportDst = new Airport();
		airportDst.setCity(DUMMY_TEXT);
		airportDst.setCountry(DUMMY_TEXT);
		airportDst.setDefaultForCity(false);
		airportDst.setIATACode(DUMMY_TEXT);
		airportDst.setName(DUMMY_TEXT);
		airportDst.setRegion(DUMMY_TEXT);

		Airports airportsDst = new Airports();
		airportsDst.getAirport().add(airportDst);

		City cityDst = new City();
		cityDst.setAirports(airportsDst);
		cityDst.setName(DUMMY_TEXT);

		TourVariationDefiners tourVariationDefinersDst = new TourVariationDefiners();
		tourVariationDefinersDst.setOperatingProduct(operatingProductDst);
		tourVariationDefinersDst.setRoomTypes(roomTypesDst);
		tourVariationDefinersDst.setStartCity(cityDst);
		tourVariationDefinersDst.setEndCity(cityDst);
		tourVariationDefinersDst.setIsTourPackage(false);

		tourInfoDst.setTourVariationDefiners(tourVariationDefinersDst);
	}
}
