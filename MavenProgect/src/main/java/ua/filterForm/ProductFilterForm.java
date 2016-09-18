package ua.filterForm;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ProductFilterForm {
	
	private String min = "";
	
	private String max = "";
	
	private int minInt = 0;
	
	private int maxInt = 0;
	
	private static final Pattern p = Pattern.compile("^[0-9]{1,9}$");
	
	private List<Integer> colourIds = new ArrayList<>();
	
	private List<Integer> manufacturerIds = new ArrayList<>();
	
	private List<Integer> subcategoryIds = new ArrayList<>();
	
	private List<Integer> countryIds = new ArrayList<>();

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		if(p.matcher(min).matches()) minInt = Integer.valueOf(min);
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		if(p.matcher(max).matches()) maxInt = Integer.valueOf(max);
		this.max = max;
	}

	public int getMinInt() {
		return minInt;
	}

	public void setMinInt(int minInt) {
		this.minInt = minInt;
	}

	public int getMaxInt() {
		return maxInt;
	}

	public void setMaxInt(int maxInt) {
		this.maxInt = maxInt;
	}

	public List<Integer> getColourIds() {
		return colourIds;
	}

	public void setColourIds(List<Integer> colourIds) {
		this.colourIds = colourIds;
	}

	public List<Integer> getManufacturerIds() {
		return manufacturerIds;
	}

	public void setManufacturerIds(List<Integer> manufacturerIds) {
		this.manufacturerIds = manufacturerIds;
	}

	public List<Integer> getSubcategoryIds() {
		return subcategoryIds;
	}

	public void setSubcategoryIds(List<Integer> subcategoryIds) {
		this.subcategoryIds = subcategoryIds;
	}

	public List<Integer> getCountryIds() {
		return countryIds;
	}

	public void setCountryIds(List<Integer> countryIds) {
		this.countryIds = countryIds;
	}
	
	
}
