package com.asimio.demo.web;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.asimio.demo.tenant.TenantStore;

@RunWith(MockitoJUnitRunner.class)
public class DemoResourceTest {

	@Mock
	private TenantStore tenantStore;

	@InjectMocks
	private DemoResource controller;

	@Test
	public void shouldReturnBlahTenantId() {
		// Given
		BDDMockito.when(this.tenantStore.getTenantId()).thenReturn("blahTenantId");
		// When
		String result = this.controller.getDemo();
		// Then
		Assert.assertThat(result, Matchers.equalTo("Tenant: blahTenantId"));
	}

}