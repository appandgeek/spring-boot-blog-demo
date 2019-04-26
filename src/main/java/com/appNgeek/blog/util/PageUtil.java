package com.appNgeek.blog.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtil {

	private static String defaultSortCloumn = "creationDate";

	private static boolean defaultDesc = true;

	private static int defaultSize = 25;

	public static Pageable getPageable(Integer page, Integer size, String sortColumn, Boolean desc) {

		if (page == null)
			page = 0;

		if (size == null || size == 0)
			size = defaultSize;

		if (sortColumn == null || sortColumn.isEmpty())
			sortColumn = defaultSortCloumn;

		if (desc == null)
			desc = defaultDesc;

		Pageable pageable = null;
		if (desc)
			pageable = PageRequest.of(page, size, Sort.by(sortColumn).descending());
		else {
			pageable = PageRequest.of(page, size, Sort.by(sortColumn).ascending());
		}
		return pageable;
	}

	public static Pageable getPageable(Integer page, Integer size, String sortColumn) {
		return getPageable(page, size, sortColumn, defaultDesc);
	}

	public static Pageable getPageable(Integer page, Integer size) {
		return getPageable(page, size, defaultSortCloumn, defaultDesc);
	}

	public static Pageable getPageable(Integer page) {
		return getPageable(page, defaultSize, defaultSortCloumn, defaultDesc);
	}
}
