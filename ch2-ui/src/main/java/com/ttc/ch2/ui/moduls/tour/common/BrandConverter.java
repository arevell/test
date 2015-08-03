package com.ttc.ch2.ui.moduls.tour.common;

import java.util.Iterator;
import java.util.List;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Combobox;

import com.ttc.ch2.domain.Brand;

public class BrandConverter implements Converter{
		
	@Override
	public Object coerceToUi(Object val, Component comp, BindContext ctx) {
		 Combobox box;
		    box = (Combobox) comp;
		    if (box.getSelectedItem() == null) {
		        String locationName = null;
		        if (val != null) {
		            List<Brand> locationList = (List<Brand>) box.getModel();
				for (Iterator<Brand> i = locationList.iterator(); i.hasNext();) {
		                Brand tmp = i.next();
		                if (tmp.getId() == (Long) val) {
		                    locationName = tmp.getBrandName();
		                }
		            }
		        }
		        return locationName;
		    } else
		        return box.getSelectedItem().getLabel();
	}

	@Override
	public Object coerceToBean(Object val, Component component, BindContext ctx) {
		 Combobox box;
		    box = (Combobox) component;
		    if (box.getSelectedItem() == null)
		        return val;
		    else
		        return box.getSelectedItem().getValue();
	}

}
