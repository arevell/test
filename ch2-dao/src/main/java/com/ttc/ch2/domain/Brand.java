package com.ttc.ch2.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.ttc.ch2.domain.common.EntityBase;


@NamedQueries({
	@NamedQuery( name = "Brand.findByBrandCode", query = "select b from Brand b where b.code=:code")
})
@Entity
@Table(name="BRAND")
public class Brand extends EntityBase{

	private static final long serialVersionUID = -124774390441289351L;
	
	public static enum Status{UploadTourInfo,ImportTourDeparture,InActive};
	
	
	@Column(name="BRAND_NAME", length=30, nullable=false)
	private String brandName;
	

	@Column(name="CODE", unique=true,length=2, nullable=false)
	private String code;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="brand")
	private Set<SellingCompany> sellingCompanies;
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	@Override
	public String toString() {	
		String txt=super.toString();
		return brandName+"["+txt+"]";
	}
	
	public Set<SellingCompany> getSellingCompanies() {
		if(sellingCompanies==null)
			sellingCompanies = new HashSet<SellingCompany>();
		return sellingCompanies;
	}
	
	public void setSellingCompanies(Set<SellingCompany> sellingCompanies) {
		this.sellingCompanies = sellingCompanies;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
