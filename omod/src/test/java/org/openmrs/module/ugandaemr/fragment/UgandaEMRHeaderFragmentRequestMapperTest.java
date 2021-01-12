package org.openmrs.module.ugandaemr.fragment;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openmrs.ui.framework.fragment.FragmentRequest;

/**
 * Created by ssmusoke on 18/02/2016.
 */
public class UgandaEMRHeaderFragmentRequestMapperTest {

	@Test
	public void overrideRequestForHeaderFragment() {
		FragmentRequest request = new FragmentRequest("appui", "header");

		assertEquals(request.getMappedProviderName(), "appui");
		assertEquals(request.getMappedFragmentId(), "header");

		UgandaEMRHeaderFragmentRequestMapper mapper = new UgandaEMRHeaderFragmentRequestMapper();

		assertTrue(mapper.mapRequest(request));
		assertEquals(request.getMappedProviderName(), "ugandaemr");
		assertEquals(request.getMappedFragmentId(), "ugandaEMRHeader");
	}
}
