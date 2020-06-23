package com.kropotov.asrd.services.springdatajpa.specification;

import com.kropotov.asrd.entities.User;
import com.kropotov.asrd.entities.User_;
import com.kropotov.asrd.entities.docs.ActInputControl;
import com.kropotov.asrd.entities.docs.ActInputControl_;
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

public class ActInputControlSpecification {
	private ActInputControlSpecification() {
	}

	public static Specification<ActInputControl> hasStatus(final Byte entityStatus) {
		return (root, cr, cb) -> cb.equal(root.get(ActInputControl_.entityStatus), entityStatus);
	}

	public static Specification<ActInputControl> createdAtAfter(final LocalDateTime createdAtFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDateTime>get(ActInputControl_.createdAt), cb.literal(createdAtFrom));
	}

	public static Specification<ActInputControl> createdAtBefore(final LocalDateTime createdAtTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDateTime>get(ActInputControl_.createdAt), cb.literal(createdAtTo));
	}

	public static Specification<ActInputControl> updatedAtAfter(final LocalDateTime updatedAtFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDateTime>get(ActInputControl_.updatedAt), cb.literal(updatedAtFrom));
	}

	public static Specification<ActInputControl> updatedAtBefore(final LocalDateTime updatedAtTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDateTime>get(ActInputControl_.updatedAt), cb.literal(updatedAtTo));
	}

	public static Specification<ActInputControl> actDateAfter(final LocalDate updatedAtFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDate>get(ActInputControl_.date), cb.literal(updatedAtFrom));
	}

	public static Specification<ActInputControl> actDateBefore(final LocalDate updatedAtTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDate>get(ActInputControl_.date), cb.literal(updatedAtTo));
	}

	public static Specification<ActInputControl> hasNumberLike(final String number) {
		return (root, cr, cb) -> cb.like(root.get(ActInputControl_.number), "%" + number + "%");
	}

	public static Specification<ActInputControl> inSystem(final String system) {
		return (root, cr, cb) -> {
			Join<ControlSystem, SystemTitle> systemTitleJoin = root.join(ActInputControl_.systems, JoinType.LEFT).join(ControlSystem_.title, JoinType.LEFT);
			return cb.like(systemTitleJoin.get(SystemTitle_.title), "%" + system + "%");
		};
	}

	public static Specification<ActInputControl> inDevice(final String device) {
		return (root, cr, cb) -> {
			Join<Device, DeviceTitle> deviceTitleJoin = root.join(ActInputControl_.devices, JoinType.LEFT).join(Device_.title, JoinType.LEFT);
			return cb.like(deviceTitleJoin.get(DeviceTitle_.title), "%" + device + "%");
		};
	}

	public static Specification<ActInputControl> hasInvoiceNumberLike(final String invoiceNumber) {
		return (root, cr, cb) -> {
			Join<ActInputControl, Invoice> invoiceJoin = root.join(ActInputControl_.invoice, JoinType.LEFT);
			return cb.like(invoiceJoin.get(Invoice_.number), "%" + invoiceNumber + "%");
		};
	}

	public static Specification<ActInputControl> hasResult(final byte result) {
		return (root, cr, cb) -> cb.equal(root.get(ActInputControl_.result), result);
	}


	public static Specification<ActInputControl> hasUserLike(final String name) {
		return (root, cr, cb) -> {
			Join<ActInputControl, User> userJoin = root.join(ActInputControl_.user, JoinType.LEFT);
			return cb.or(cb.like(userJoin.get(User_.firstName), "%" + name + "%"),
					cb.like(userJoin.get(User_.lastName), "%" + name + "%"),
					cb.like(userJoin.get(User_.userName), "%" + name + "%"));
		};
	}
}
