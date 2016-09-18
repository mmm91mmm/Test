package ua.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.transaction.annotation.Transactional;

import ua.filterForm.ProductFilterForm;
import ua.shop_e.Product;

public class ProductFilterAdapter implements Specification<Product>{

	private final ProductFilterForm form;
	
	private final List<Specification<Product>> filters = new ArrayList<>();

	public ProductFilterAdapter(ProductFilterForm form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new ProductFilterForm();
		}
	}

	private void findByCost(){
		if(form.getMinInt()!=0&&form.getMaxInt()!=0){
//			filters.add((root, query, cb)->cb.between(root.get("amount"), form.getMinInt(), form.getMaxInt()));
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("cost");
				return cb.between(exp, form.getMinInt(), form.getMaxInt());
			});
		}else if(form.getMinInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("cost");
				return cb.ge(exp, form.getMinInt());
			});
		}else if(form.getMaxInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("cost");
				return cb.le(exp, form.getMaxInt());
			});
		}
	}
	
	private void findBySubcategory(){
		if(!form.getSubcategoryIds().isEmpty()){
			filters.add((root, query, cb)->root.get("subcategory").in(form.getSubcategoryIds()));
		}
	}
	
	private void findByColour(){
		if(!form.getColourIds().isEmpty()){
			filters.add((root, query, cb)->root.get("colour").in(form.getColourIds()));
		}
	}
	
	private void findByManufacturer(){
		if(!form.getManufacturerIds().isEmpty()){
			filters.add((root, query, cb)->root.get("manufacturer").in(form.getManufacturerIds()));
		}
	}
	
	private void findByCountry(){
		if(!form.getCountryIds().isEmpty()){
			filters.add((root, query, cb)->root.get("country").in(form.getCountryIds()));
		}
	}
	
	
	@Override
	@Transactional
	public Predicate toPredicate(Root< Product> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class && query.getResultType() != long.class) {
			root.fetch("subcategory", JoinType.LEFT);
			root.fetch("country", JoinType.LEFT);
			root.fetch("manufacturer", JoinType.LEFT);
			root.fetch("colour", JoinType.LEFT);
			root.fetch("sizes", JoinType.LEFT);
			query.distinct(true);
		}
		findByCost();
		findBySubcategory();
		findByColour();
		findByManufacturer();
		findByCountry();
		if(!filters.isEmpty()){
			Specifications<Product> spec = Specifications.where(filters.get(0));
			for(Specification<Product> s : filters.subList(1, filters.size())){
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}
}