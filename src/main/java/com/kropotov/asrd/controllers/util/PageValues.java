package com.kropotov.asrd.controllers.util;

import com.kropotov.asrd.entities.common.PageableEntity;
import org.springframework.ui.Model;

public final class PageValues {
	public static final int[] PAGE_SIZES = {10, 15, 25, 50, 100};

	private PageValues() {
	}

	public static void addContentToModel(Model model, PageWrapper<? extends PageableEntity> page) {
		model.addAttribute("page", page);
		model.addAttribute("pageSizes", PAGE_SIZES);
	}
}
