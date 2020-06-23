package com.kropotov.asrd.services.springdatajpa.specification;

import com.kropotov.asrd.entities.User;
import com.kropotov.asrd.entities.User_;
import com.kropotov.asrd.entities.company.Company;
import com.kropotov.asrd.entities.company.Company_;
import com.kropotov.asrd.entities.docs.Invoice;
import com.kropotov.asrd.entities.docs.Invoice_;
import com.kropotov.asrd.entities.items.ControlSystem;
import com.kropotov.asrd.entities.items.ControlSystem_;
import com.kropotov.asrd.entities.items.Device;
import com.kropotov.asrd.entities.items.Device_;
import com.kropotov.asrd.entities.titles.DeviceTitle;
import com.kropotov.asrd.entities.titles.DeviceTitle_;
import com.kropotov.asrd.entities.titles.SystemTitle;
import com.kropotov.asrd.entities.titles.SystemTitle_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class InvoiceSpecification {
	private InvoiceSpecification() {
	}

	public static Specification<Invoice> hasStatus(final Byte entityStatus) {
		return (root, cr, cb) -> cb.equal(root.get(Invoice_.entityStatus), entityStatus);
	}

	public static Specification<Invoice> createdAtAfter(final LocalDateTime createdAtFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDateTime>get(Invoice_.createdAt), cb.literal(createdAtFrom));
	}

	public static Specification<Invoice> createdAtBefore(final LocalDateTime createdAtTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDateTime>get(Invoice_.createdAt), cb.literal(createdAtTo));
	}

	public static Specification<Invoice> updatedAtAfter(final LocalDateTime updatedAtFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDateTime>get(Invoice_.updatedAt), cb.literal(updatedAtFrom));
	}

	public static Specification<Invoice> updatedAtBefore(final LocalDateTime updatedAtTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDateTime>get(Invoice_.updatedAt), cb.literal(updatedAtTo));
	}

	public static Specification<Invoice> invoiceDateAfter(final LocalDate updatedAtFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDate>get(Invoice_.date), cb.literal(updatedAtFrom));
	}

	public static Specification<Invoice> invoiceDateBefore(final LocalDate updatedAtTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDate>get(Invoice_.date), cb.literal(updatedAtTo));
	}

	public static Specification<Invoice> hasNumberLike(final String number) {
		return (root, cr, cb) -> cb.like(root.get(Invoice_.number), "%" + number + "%");
	}

	public static Specification<Invoice> inSystem(final String system) {
		return (root, cr, cb) -> {
			Join<ControlSystem, SystemTitle> systemTitleJoin = root.join(Invoice_.systems, JoinType.LEFT).join(ControlSystem_.title, JoinType.LEFT);
			return cb.like(systemTitleJoin.get(SystemTitle_.title), "%" + system + "%");
		};
	}

	public static Specification<Invoice> inDevice(final String device) {
		return (root, cr, cb) -> {
			Join<Device, DeviceTitle> deviceTitleJoin = root.join(Invoice_.devices, JoinType.LEFT).join(Device_.title, JoinType.LEFT);
			return cb.like(deviceTitleJoin.get(DeviceTitle_.title), "%" + device + "%");
		};
	}

	public static Specification<Invoice> fromCompanyLike(final String from) {
		return (root, cr, cb) -> {
			Join<Invoice, Company> companyJoin = root.join(Invoice_.from, JoinType.LEFT);
			return cb.like(companyJoin.get(Company_.title), "%" + from + "%");
		};
	}

	public static Specification<Invoice> destCompanyLike(final String dest) {
		return (root, cr, cb) -> {
			Join<Invoice, Company> companyJoin = root.join(Invoice_.destination, JoinType.LEFT);
			return cb.like(companyJoin.get(Company_.title), "%" + dest + "%");
		};
	}

	public static Specification<Invoice> hasUserLike(final String name) {
		return (root, cr, cb) -> {
			Join<Invoice, User> userJoin = root.join(Invoice_.user, JoinType.LEFT);
			return cb.or(cb.like(userJoin.get(User_.firstName), "%" + name + "%"),
					cb.like(userJoin.get(User_.lastName), "%" + name + "%"),
					cb.like(userJoin.get(User_.userName), "%" + name + "%"));
		};
	}
}
