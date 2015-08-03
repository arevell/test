package com.ttc.ch2.bl.upload.common.tourinfogen;

import java.util.List;

import javax.xml.bind.JAXBElement;

import com.google.common.collect.Lists;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Strong;
import com.ttsl.tourinfo._2010._08._2.ContinentCode;
import com.ttsl.tourinfo._2010._08._2.ISO3166CountyCode;
import com.ttsl.tourinfo._2010._08._2.A;
import com.ttsl.tourinfo._2010._08._2.AirportTransfers;
import com.ttsl.tourinfo._2010._08._2.Assets;
import com.ttsl.tourinfo._2010._08._2.Br;
import com.ttsl.tourinfo._2010._08._2.Brochure;
import com.ttsl.tourinfo._2010._08._2.CategoryValue;
import com.ttsl.tourinfo._2010._08._2.Continent;
import com.ttsl.tourinfo._2010._08._2.ContinentsVisited;
import com.ttsl.tourinfo._2010._08._2.CountriesVisited;
import com.ttsl.tourinfo._2010._08._2.Country;
import com.ttsl.tourinfo._2010._08._2.Highlights;
import com.ttsl.tourinfo._2010._08._2.Image;
import com.ttsl.tourinfo._2010._08._2.Itinerary;
import com.ttsl.tourinfo._2010._08._2.ItinerarySegment;
import com.ttsl.tourinfo._2010._08._2.Li;
import com.ttsl.tourinfo._2010._08._2.Location;
import com.ttsl.tourinfo._2010._08._2.LocationsVisited;
import com.ttsl.tourinfo._2010._08._2.Metadata;
import com.ttsl.tourinfo._2010._08._2.Notes;
import com.ttsl.tourinfo._2010._08._2.ObjectFactory;
import com.ttsl.tourinfo._2010._08._2.Section;
import com.ttsl.tourinfo._2010._08._2.SellingCompanies;
import com.ttsl.tourinfo._2010._08._2.SellingCompany;
import com.ttsl.tourinfo._2010._08._2.Text;
import com.ttsl.tourinfo._2010._08._2.TourCategories;
import com.ttsl.tourinfo._2010._08._2.TourCategory;
import com.ttsl.tourinfo._2010._08._2.TourInfo;
import com.ttsl.tourinfo._2010._08._2.Ul;
import com.ttsl.tourinfo._2010._08._2.WhatsIncluded;

public class ITropicsV1TourInfoMapper {

	private JAXBElement<Strong> strongLi = new com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ObjectFactory().createLiStrong(null);
	private JAXBElement<Strong> strongText = new com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ObjectFactory().createTextStrong(null);

	public static TourInfo map(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo tourInfo) {

		ITropicsV1TourInfoMapper mapper = new ITropicsV1TourInfoMapper();

		return mapper.doMaping(tourInfo);
	}

	private TourInfo doMaping(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo srcTourInfo) {

		TourInfo destTourInfo = new TourInfo();

		// attributes
		destTourInfo.setBrandCode(srcTourInfo.getBrandCode());
		destTourInfo.setCMSId(srcTourInfo.getCMSId());
		destTourInfo.setCMSTourId(srcTourInfo.getCMSTourId());
		destTourInfo.setCatalogueCode(srcTourInfo.getCataloguedTour().getCode());
		destTourInfo.setDuration(srcTourInfo.getDuration());
		destTourInfo.setMVCode(srcTourInfo.getTourCode());
		destTourInfo.setOPCode(srcTourInfo.getTourVariationDefiners().getOperatingProduct().getCode());

		// elements
		destTourInfo.setTourName(srcTourInfo.getTourName());
		destTourInfo.setMetadata(generateMetadata(srcTourInfo));
		destTourInfo.setContinentsVisited(generateContinentsVisited(srcTourInfo));
		destTourInfo.setCountriesVisited(generateCountriesVisited(srcTourInfo));
		destTourInfo.setLocationsVisited(generateLocationsVisited(srcTourInfo));
		destTourInfo.setDescription(srcTourInfo.getDescription());
		destTourInfo.setAssets(generateAssets(srcTourInfo));
		destTourInfo.setItinerary(generateItinerary(srcTourInfo));

		if (srcTourInfo.getWhatsIncluded() != null) {
			destTourInfo.setWhatsIncluded(generteWhatsIncluded(srcTourInfo));
		}
		if (srcTourInfo.getHighlights() != null) {
			destTourInfo.setHighlights(generateHighlights(srcTourInfo));
		}
		if (srcTourInfo.getAirportTransfers() != null) {
			destTourInfo.setAirportTransfers(generateAirportTransfers(srcTourInfo));
		}
		if (srcTourInfo.getNotes() != null) {
			destTourInfo.setNotes(generateNotes(srcTourInfo));
		}

		return destTourInfo;
	}

	private Metadata generateMetadata(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo srcTourInfo) {

		Metadata metadata = new Metadata();
		SellingCompanies sellingCompanies = new SellingCompanies();
		TourCategories tourCategories = new TourCategories();

		metadata.setSellingCompanies(sellingCompanies);
		metadata.setTourCategories(tourCategories); // req

		List<TourCategory> tourCategoriesList = Lists.newArrayList();

		for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.SellingCompany srcSellingCompany : srcTourInfo.getSellingCompanies().getSellingCompany()) {

			SellingCompany dstSellingCompany = new SellingCompany();

			dstSellingCompany.setCode(srcSellingCompany.getCode());
			dstSellingCompany.setCurrency(srcSellingCompany.getCurrencyCode());

			sellingCompanies.getSellingCompany().add(dstSellingCompany);

			for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Brochure srcBrochure : srcSellingCompany.getBrochure()) {

				Brochure dstBrochure = new Brochure();

				dstBrochure.setCode(srcBrochure.getCode());
				dstBrochure.setName(srcBrochure.getName());

				dstSellingCompany.getBrochure().add(dstBrochure);
			}

			for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourCategory srcTourCategory : srcSellingCompany.getTourCategories().getTourCategory()) {

				if (srcTourCategory.getCategoryValue().size() > 0) {

					TourCategory dstTourCategory = null;

					for (TourCategory tourCategory : tourCategoriesList) {

						if (tourCategory.getName().equals(srcTourCategory.getName())) {

							dstTourCategory = tourCategory;
							break;
						}
					}

					if (dstTourCategory == null) {

						dstTourCategory = new TourCategory();
						dstTourCategory.setName(srcTourCategory.getName());

						tourCategoriesList.add(dstTourCategory);
					}

					for (String srcCategoryValue : srcTourCategory.getCategoryValue()) {

						boolean categoryValueExists = false;

						for (CategoryValue categoryValue : dstTourCategory.getCategoryValue()) {

							if (categoryValue.getName().equals(srcCategoryValue)) {

								categoryValueExists = true;
								break;
							}
						}

						if (!categoryValueExists) {

							CategoryValue categoryValue = new CategoryValue();
							categoryValue.setName(srcCategoryValue);

							dstTourCategory.getCategoryValue().add(categoryValue);
						}
					}
				}
			}
		}

		tourCategories.getTourCategory().addAll(tourCategoriesList);

		return metadata;
	}
	
	private ContinentsVisited generateContinentsVisited(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo srcTourInfo) {

		ContinentsVisited continentsVisited = new ContinentsVisited();

		for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Continent srcContinent : srcTourInfo.getContinentsVisited().getContinent()) {

			Continent dstContinent = new Continent();

			dstContinent.setCode(ContinentCode.valueOf(srcContinent.getCode().value()));
			dstContinent.setName(srcContinent.getName());

			continentsVisited.getContinent().add(dstContinent);
		}

		return continentsVisited;
	}

	private CountriesVisited generateCountriesVisited(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo srcTourInfo) {

		CountriesVisited dstCountriesVisited = new CountriesVisited();

		for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Country srcCountry : srcTourInfo.getCountriesVisited().getCountry()) {

			Country dstCountry = new Country();

			dstCountry.setName(srcCountry.getName());
			dstCountry.setContinent(ContinentCode.valueOf(srcCountry.getContinentCode().value()));
			dstCountry.setCode(ISO3166CountyCode.valueOf(srcCountry.getCode().value()));

			dstCountriesVisited.getCountry().add(dstCountry);
		}

		return dstCountriesVisited;
	}

	private LocationsVisited generateLocationsVisited(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo srcTourInfo) {

		LocationsVisited dstLocationsVisited = new LocationsVisited();

		for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Location srcLocation : srcTourInfo.getLocationsVisited().getLocation()) {

			Location dstLocation = new Location();

			dstLocation.setName(srcLocation.getName());
			dstLocation.setCountry(ISO3166CountyCode.valueOf(srcLocation.getCountryCode().value()));

			dstLocationsVisited.getLocation().add(dstLocation);
		}

		return dstLocationsVisited;
	}

	private Assets generateAssets(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo srcTourInfo) {

		Assets dstAssets = new Assets();

		for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Image srcImage : srcTourInfo.getAssets().getImages().getImage()) {

			Image dstImage = new Image();

			dstImage.setCaption(srcImage.getCaption());
			dstImage.setHeight(srcImage.getHeight());
			dstImage.setName(srcImage.getName());
			dstImage.setType(srcImage.getType());
			dstImage.setUrl(srcImage.getUrl());
			dstImage.setWidth(srcImage.getWidth());

			dstAssets.getImage().add(dstImage);
		}

		return dstAssets;
	}

	private Itinerary generateItinerary(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo srcTourInfo) {

		Itinerary dstItinerary = new Itinerary();

		for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.ItinerarySegment srcItinerarySegment : srcTourInfo.getItinerary().getItinerarySegment()) {
			
			ItinerarySegment dstItinerarySegment = new ItinerarySegment();
			
			// attributes
			dstItinerarySegment.setDuration(srcItinerarySegment.getDuration());
			dstItinerarySegment.setStartDay(srcItinerarySegment.getStartDay());
			dstItinerarySegment.setTitle(srcItinerarySegment.getTitle());
			dstItinerarySegment.setAccommodation(srcItinerarySegment.getAccommodation());

			if (srcItinerarySegment.getText() != null) {
				dstItinerarySegment.setText(generateText(srcItinerarySegment.getText()));
			}

			dstItinerary.getItinerarySegment().add(dstItinerarySegment);
		}

		return dstItinerary;
	}

	private WhatsIncluded generteWhatsIncluded(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo srcTourInfo) {

		WhatsIncluded dstWhatsIncluded = new WhatsIncluded();

		for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Section srcSection : srcTourInfo.getWhatsIncluded().getSection()) {
			dstWhatsIncluded.getSection().add(generateSection(srcSection));
		}

		return dstWhatsIncluded;
	}

	private Highlights generateHighlights(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo srcTourInfo) {

		Highlights dstHighlights = new Highlights();

		for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Section srcSection : srcTourInfo.getHighlights().getSection()) {
			dstHighlights.getSection().add(generateSection(srcSection));
		}

		return dstHighlights;
	}

	private AirportTransfers generateAirportTransfers(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo srcTourInfo) {

		AirportTransfers dstAirportTransfers = new AirportTransfers();

		for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Section srcSection : srcTourInfo.getAirportTransfers().getSection()) {
			dstAirportTransfers.getSection().add(generateSection(srcSection));
		}

		return dstAirportTransfers;
	}

	private Notes generateNotes(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo srcTourInfo) {

		Notes dstNotes = new Notes();

		for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Section srcSection : srcTourInfo.getNotes().getSection()) {
			dstNotes.getSection().add(generateSection(srcSection));
		}

		return dstNotes;
	}

	private Section generateSection(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Section srcSection) {

		Section dstSection = new Section();

		dstSection.setTitle(srcSection.getTitle());
		dstSection.getText().add(generateText(srcSection.getText()));

		return dstSection;
	}

	private Text generateText(com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Text srcText) {

		Text dstTxt = new Text();

		for (Object srcContent : srcText.getContent()) {
			dstTxt.getContent().add(mapingContent(srcContent));
		}

		return dstTxt;
	}

	// this method uses recursion
	private Object mapingContent(Object srcContent) {

		if (srcContent instanceof com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.A) {

			com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.A srcA = (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.A) srcContent;

			A dstA = new A();

			dstA.setHref(srcA.getHref());
			dstA.setValue(srcA.getValue());

			return dstA;

		} else if (srcContent instanceof com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Br) {

			return new Br();

		} else if (srcContent instanceof com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Ul) {

			com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Ul srcUl = (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Ul) srcContent;

			Ul dstUl = new Ul();

			for (com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.Li srcLi : srcUl.getLi()) {

				Li dstLi = new Li();

				for (Object srcLiContent : srcLi.getContent()) {
					Object dstLiContent = mapingContent(srcLiContent);
					dstLi.getContent().add(dstLiContent);
				}

				dstUl.getLi().add(dstLi);
			}

			return dstUl;

		} else if (srcContent instanceof String) {

			return srcContent;

		} else if (srcContent instanceof JAXBElement) {

			@SuppressWarnings("rawtypes")
			JAXBElement element = (JAXBElement) srcContent;

			if ((strongLi.getName().equals(element.getName()) && strongLi.getScope().equals(element.getScope())) ||
					(strongText.getName().equals(element.getName()) && strongText.getScope().equals(element.getScope()))) {

				return new ObjectFactory().createStrong(((Strong) element.getValue()).getValue());
			}

			return mapingContent(element.getValue());

		} else {

			throw new UnsupportedOperationException("Unsupported Text->Content element");
		}
	}
}
