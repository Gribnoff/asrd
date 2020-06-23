package com.kropotov.asrd.services.springdatajpa.specification;

import com.kropotov.asrd.entities.User;
import com.kropotov.asrd.entities.User_;
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

public class DeviceSpecification {
	private DeviceSpecification() {
	}

	public static Specification<Device> hasStatus(final Byte entityStatus) {
		return (root, cr, cb) -> cb.equal(root.get(Device_.entityStatus), entityStatus);
	}

	public static Specification<Device> inSystem(final String title) {
		return (root, cr, cb) -> {
			Join<ControlSystem, SystemTitle> systemTitleJoin = root.join(Device_.system, JoinType.LEFT).join(ControlSystem_.title, JoinType.LEFT);
			return cb.like(systemTitleJoin.get(SystemTitle_.title), "%" + title + "%");
		};
	}

	public static Specification<Device> createdAtAfter(final LocalDateTime createdAtFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDateTime>get(Device_.createdAt), cb.literal(createdAtFrom));
	}

	public static Specification<Device> createdAtBefore(final LocalDateTime createdAtTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDateTime>get(Device_.createdAt), cb.literal(createdAtTo));
	}

	public static Specification<Device> updatedAtAfter(final LocalDateTime updatedAtFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDateTime>get(Device_.updatedAt), cb.literal(updatedAtFrom));
	}

	public static Specification<Device> updatedAtBefore(final LocalDateTime updatedAtTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDateTime>get(Device_.updatedAt), cb.literal(updatedAtTo));
	}

	public static Specification<Device> hasNumberLike(final String number) {
		return (root, cr, cb) -> cb.like(root.get(Device_.number), "%" + number + "%");
	}

	public static Specification<Device> hasLocation(final byte location) {
		return (root, cr, cb) -> cb.equal(root.get(Device_.location), location);
	}

	public static Specification<Device> hasPurposeLike(final String purpose) {
		return (root, cr, cb) -> cb.like(root.get(Device_.purpose), "%" + purpose + "%");
	}

	public static Specification<Device> hasPurposePassportLike(final String purposePassport) {
		return (root, cr, cb) -> cb.like(root.get(Device_.purposePassport), "%" + purposePassport + "%");
	}

	public static Specification<Device> vintageAfter(final LocalDate vintageTo) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDate>get(Device_.vintage), cb.literal(vintageTo));
	}

	public static Specification<Device> vintageBefore(final LocalDate vintageFrom) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDate>get(Device_.vintage), cb.literal(vintageFrom));
	}

	public static Specification<Device> hasVpNumber(final int vpNumber) {
		return (root, cr, cb) -> cb.equal(root.get(Device_.vpNumber), vpNumber);
	}

	public static Specification<Device> otkDateAfter(final LocalDate otkDateFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDate>get(Device_.otkDate), cb.literal(otkDateFrom));
	}

	public static Specification<Device> otkDateBefore(final LocalDate otkDateTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDate>get(Device_.otkDate), cb.literal(otkDateTo));
	}

	public static Specification<Device> vpDateAfter(final LocalDate vpDateFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDate>get(Device_.vpDate), cb.literal(vpDateFrom));
	}

	public static Specification<Device> vpDateBefore(final LocalDate vpDateTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDate>get(Device_.vpDate), cb.literal(vpDateTo));
	}

	public static Specification<Device> hasTitleLike(final String title) {
		return (root, cr, cb) -> {
			Join<Device, DeviceTitle> titleJoin = root.join(Device_.title, JoinType.LEFT);
			return cb.like(titleJoin.get(DeviceTitle_.title), "%" + title + "%");
		};
	}

	public static Specification<Device> hasUserLike(final String name) {
		return (root, cr, cb) -> {
			Join<Device, User> userJoin = root.join(Device_.user, JoinType.LEFT);
			return cb.or(cb.like(userJoin.get(User_.firstName), "%" + name + "%"),
					cb.like(userJoin.get(User_.lastName), "%" + name + "%"),
					cb.like(userJoin.get(User_.userName), "%" + name + "%"));
		};
	}
}
