package com.kropotov.asrd.services.springdatajpa.specification;

import com.kropotov.asrd.entities.company.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

public class CompanySpecification {
	private CompanySpecification() {
	}

	public static Specification<Company> hasTitleLike(final String title) {
		return (root, cr, cb) -> cb.like(root.get(Company_.title), "%" + title + "%");
	}

	public static Specification<Company> hasEmailLike(final String email) {
		return (root, cr, cb) -> cb.like(root.get(Company_.email), "%" + email + "%");
	}

	public static Specification<Company> hasFaxLike(final String fax) {
		return (root, cr, cb) -> cb.like(root.get(Company_.fax), "%" + fax + "%");
	}

	public static Specification<Company> hasMilReprLike(final String militaryRepresentation) {
		return (root, cr, cb) -> cb.like(root.get(Company_.militaryRepresentation), "%" + militaryRepresentation + "%");
	}

	public static Specification<Company> hasPhoneLike(final String phone) {
		return (root, cr, cb) -> cb.like(root.join(Company_.companyPhones, JoinType.LEFT)
				.get(CompanyPhone_.phone), "%" + phone + "%");
	}

	public static Specification<Company> hasAddressLike(final String zipCode, final String city, final String street, final String place) {
		return (root, cr, cb) -> {
			Join<Company, Address> addressJoin =  root.join(Company_.address, JoinType.LEFT);
			return cb.and(
					cb.like(addressJoin.get(Address_.zipCode), "%" + zipCode + "%"),
					cb.like(addressJoin.get(Address_.city), "%" + city + "%"),
					cb.like(addressJoin.get(Address_.street), "%" + street + "%"),
					cb.like(addressJoin.get(Address_.place), "%" + place + "%"));
		};
	}
}
