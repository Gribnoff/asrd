package com.kropotov.asrd.services.springdatajpa.specification;

import com.kropotov.asrd.entities.User;
import com.kropotov.asrd.entities.items.ControlSystem;
import com.kropotov.asrd.entities.User_;
import com.kropotov.asrd.entities.items.ControlSystem_;
import com.kropotov.asrd.entities.titles.SystemTitle_;
import com.kropotov.asrd.entities.titles.SystemTitle;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SystemSpecification {
	private SystemSpecification() {
	}

	public static Specification<ControlSystem> hasStatus(final Byte entityStatus) {
		return (root, cr, cb) -> cb.equal(root.get(ControlSystem_.entityStatus), entityStatus);
	}

	public static Specification<ControlSystem> createdAtAfter(final LocalDateTime createdAtFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDateTime>get(ControlSystem_.createdAt), cb.literal(createdAtFrom));
	}

	public static Specification<ControlSystem> createdAtBefore(final LocalDateTime createdAtTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDateTime>get(ControlSystem_.createdAt), cb.literal(createdAtTo));
	}

	public static Specification<ControlSystem> updatedAtAfter(final LocalDateTime updatedAtFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDateTime>get(ControlSystem_.updatedAt), cb.literal(updatedAtFrom));
	}

	public static Specification<ControlSystem> updatedAtBefore(final LocalDateTime updatedAtTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDateTime>get(ControlSystem_.updatedAt), cb.literal(updatedAtTo));
	}

	public static Specification<ControlSystem> hasNumberLike(final String number) {
		return (root, cr, cb) -> cb.like(root.get(ControlSystem_.number), "%" + number + "%");
	}

	public static Specification<ControlSystem> hasLocation(final byte location) {
		return (root, cr, cb) -> cb.equal(root.get(ControlSystem_.location), location);
	}

	public static Specification<ControlSystem> hasPurposeLike(final String purpose) {
		return (root, cr, cb) -> cb.like(root.get(ControlSystem_.purpose), "%" + purpose + "%");
	}

	public static Specification<ControlSystem> hasPurposePassportLike(final String purposePassport) {
		return (root, cr, cb) -> cb.like(root.get(ControlSystem_.purposePassport), "%" + purposePassport + "%");
	}

	public static Specification<ControlSystem> vintageAfter(final LocalDate vintageTo) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDate>get(ControlSystem_.vintage), cb.literal(vintageTo));
	}

	public static Specification<ControlSystem> vintageBefore(final LocalDate vintageFrom) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDate>get(ControlSystem_.vintage), cb.literal(vintageFrom));
	}

	public static Specification<ControlSystem> hasVpNumber(final int vpNumber) {
		return (root, cr, cb) -> cb.equal(root.get(ControlSystem_.vpNumber), vpNumber);
	}

	public static Specification<ControlSystem> otkDateAfter(final LocalDate otkDateFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDate>get(ControlSystem_.otkDate), cb.literal(otkDateFrom));
	}

	public static Specification<ControlSystem> otkDateBefore(final LocalDate otkDateTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDate>get(ControlSystem_.otkDate), cb.literal(otkDateTo));
	}

	public static Specification<ControlSystem> vpDateAfter(final LocalDate vpDateFrom) {
		return (root, cr, cb) -> cb.greaterThanOrEqualTo(root.<LocalDate>get(ControlSystem_.vpDate), cb.literal(vpDateFrom));
	}

	public static Specification<ControlSystem> vpDateBefore(final LocalDate vpDateTo) {
		return (root, cr, cb) -> cb.lessThanOrEqualTo(root.<LocalDate>get(ControlSystem_.vpDate), cb.literal(vpDateTo));
	}

	public static Specification<ControlSystem> hasTitleLike(final String title) {
		return (root, cr, cb) -> {
			Join<ControlSystem, SystemTitle> titleJoin = root.join(ControlSystem_.title, JoinType.LEFT);
			return cb.like(titleJoin.get(SystemTitle_.title), "%" + title + "%");
		};
	}

	public static Specification<ControlSystem> hasUserLike(final String name) {
		return (root, cr, cb) -> {
			Join<ControlSystem, User> userJoin = root.join(ControlSystem_.user, JoinType.LEFT);
			return cb.or(cb.like(userJoin.get(User_.firstName), "%" + name + "%"),
					cb.like(userJoin.get(User_.lastName), "%" + name + "%"),
					cb.like(userJoin.get(User_.userName), "%" + name + "%"));
		};
	}
}
