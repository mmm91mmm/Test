package ua.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.filterForm.ManufacturerFilterForm;
import ua.shop_e.Manufacturer;

public class ManufacturerFilterAdapter implements Specification<Manufacturer>{

	private String search = "";

	public  ManufacturerFilterAdapter( ManufacturerFilterForm form) {
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root< Manufacturer> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class && query.getResultType() != long.class) {
//			root.fetch("ingredient");
//			root.fetch("measuringSystem");
//			query.distinct(true);
		}
		Expression<String> exp = root.get("name");
		return cb.like(cb.upper(exp), search.toUpperCase()+"%");
	}
}