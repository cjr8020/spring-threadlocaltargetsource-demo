package com.asimio.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asimio.demo.tenant.TenantStore;

@RestController
@RequestMapping(value = "/demo")
public class DemoResource {

	@Autowired
	private TenantStore tenantStore;

	@RequestMapping(method = RequestMethod.GET)
	public String getDemo() {
		return String.format("Tenant: %s", this.tenantStore.getTenantId());
	}
}