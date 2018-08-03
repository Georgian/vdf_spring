package com.ggrec.vdf_spring.security;

import java.util.Collection;

import com.ggrec.vdf_spring.domain.VDFAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class VDFAccountAuthentication implements Authentication {

	private final VDFAccount user;

	private boolean authenticated = true;

	public VDFAccountAuthentication(VDFAccount user) {
		this.user = user;
	}

	@Override
	public String getName() {
		return user.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public VDFAccount getDetails() {
		return user;
	}

	@Override
	public Object getPrincipal() {
		return user;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}