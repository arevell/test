package com.ttc.ch2.hibernate.tour;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.ttc.ch2.common.BaseDao;
import com.ttc.ch2.domain.tour.ContentRepository;


@FixMethodOrder(MethodSorters.JVM)
public class ContentRepositoryDAOImplTest {

	@Test
	public void testFindByTourCodes_none() {
		ContentRepositoryDAOImpl dao = mock( ContentRepositoryDAOImpl.class);
		when(dao.findByTourCodes(anyList(), anyString())).thenCallRealMethod();
		when(dao.search(any(Search.class))).thenReturn(new ArrayList<>());
		
		
		List<ContentRepository> result = dao.findByTourCodes(Collections.<String>emptyList(), "TT");
		assertThat(result, empty());
		verify(dao, never()).search(any(Search.class));
	}

	@Test
	public void testFindByTourCodes_once() {
		List<String> tours = new ArrayList<>();
		for (int i = 0; i < BaseDao.MAX_ELEMENTS_INSIDE_SQL_IN_CLAUSE / 2; i++) {
			tours.add(MessageFormat.format("{0,number,0000}", i));
		}

		ContentRepositoryDAOImpl dao = mock( ContentRepositoryDAOImpl.class);

		when(dao.findByTourCodes(anyList(), anyString())).thenCallRealMethod();
		when(dao.search(any(Search.class))).thenReturn(new ArrayList<>());


		List<ContentRepository> result = dao.findByTourCodes(tours, "TT");
		assertThat(result, empty());

		ArgumentCaptor<ISearch> captor = ArgumentCaptor.forClass(ISearch.class);
		verify(dao, times(1)).search(captor.capture());
		List<String> usedTourCodes = extractTourCodeParameter(captor);
		assertThat(usedTourCodes, contains(tours.toArray()));
	}
	
	@Test
	public void testFindByTourCodes_twice() {
		List<String> tours = new ArrayList<>();
		for (int i = 0; i < BaseDao.MAX_ELEMENTS_INSIDE_SQL_IN_CLAUSE *1.5 ; i++) {
			tours.add(MessageFormat.format("{0,number,0000}", i));
		}
		
		ContentRepositoryDAOImpl dao = mock( ContentRepositoryDAOImpl.class);
		when(dao.findByTourCodes(anyList(), anyString())).thenCallRealMethod();
		when(dao.search(any(Search.class))).thenReturn(new ArrayList<>());
		
		
		List<ContentRepository> result = dao.findByTourCodes(tours, "TT");
		assertThat(result, empty());
		
		ArgumentCaptor<ISearch> captor = ArgumentCaptor.forClass(ISearch.class);
		verify(dao, times(2)).search(captor.capture());
		

		List<String> usedTourCodes = extractTourCodeParameter(captor);
		assertThat(usedTourCodes, contains(tours.toArray()));
	}

	@Test
	public void testFindByTourCodes_thrice() {
		List<String> tours = new ArrayList<>();
		for (int i = 0; i < BaseDao.MAX_ELEMENTS_INSIDE_SQL_IN_CLAUSE *2.5 ; i++) {
			tours.add(MessageFormat.format("{0,number,0000}", i));
		}
		
		ContentRepositoryDAOImpl dao = mock( ContentRepositoryDAOImpl.class);
		when(dao.findByTourCodes(anyList(), anyString())).thenCallRealMethod();
		when(dao.search(any(Search.class))).thenReturn(new ArrayList<>());
		
		
		List<ContentRepository> result = dao.findByTourCodes(tours, "TT");
		assertThat(result, empty());
		
		ArgumentCaptor<ISearch> captor = ArgumentCaptor.forClass(ISearch.class);
		verify(dao, times(3)).search(captor.capture());

		List<String> usedTourCodes = extractTourCodeParameter(captor);
		assertThat(usedTourCodes, contains(tours.toArray()));
	}
	
	private List<String> extractTourCodeParameter(ArgumentCaptor<ISearch> captor) {
		
		List<ISearch> list = captor.getAllValues();
		
		List<String> usedTourCodes = new ArrayList<>();
		for (ISearch search : list) {
			for (Filter filter : search.getFilters()) {
				if ("tourCode".equals(filter.getProperty())) {
					@SuppressWarnings("unchecked")
					List<String> sub = (List<String>) filter.getValuesAsList();
					usedTourCodes.addAll(sub);
				}
			}
		}
		return usedTourCodes;
	}
}
