package com.asimio.demo.web;

import com.asimio.demo.tenant.TenantStore;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TenantFilter implements Filter {

  private static final String TENANT_HEADER_NAME = "X-TENANT-ID";

  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(TenantFilter.class);

  @Autowired
  private TenantStore tenantStore;

  @Override
  public void init(FilterConfig arg0) throws ServletException {
    // NOOP
  }

  @Override
  public void doFilter(
        ServletRequest servletRequest,
        ServletResponse servletResponse,
        FilterChain chain)
      throws IOException, ServletException {

     logger.info("Thread had tenant data: {} from an old request", this.tenantStore.getTenantId());

    HttpServletRequest request = (HttpServletRequest) servletRequest;
    String tenantId = request.getHeader(TENANT_HEADER_NAME);
    try {
      this.tenantStore.setTenantId(tenantId);
      chain.doFilter(servletRequest, servletResponse);
    } finally {
      // Otherwise when a previously used container thread is used, it will have the old tenant id set and
      // if for some reason this filter is skipped, tenantStore will hold an unreliable value
      this.tenantStore.clear();
    }
  }

  @Override
  public void destroy() {
    // NOOP
  }

}