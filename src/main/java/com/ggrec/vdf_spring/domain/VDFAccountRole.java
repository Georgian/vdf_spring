package com.ggrec.vdf_spring.domain;

public enum VDFAccountRole {

    USER,

    ADMIN

    ;

    public VDFAccountAuthority asAuthorityFor(final VDFAccount account) {
        final VDFAccountAuthority authority = new VDFAccountAuthority();
        authority.setAuthority("ROLE_" + toString());
        authority.setAccount(account);
        return authority;
    }

    public static VDFAccountRole valueOf(final VDFAccountAuthority authority) {
        switch (authority.getAuthority()) {
            case "ROLE_USER":
                return USER;
            case "ROLE_ADMIN":
                return ADMIN;
        }
        throw new IllegalArgumentException("No role defined for authority: " + authority.getAuthority());
    }
}
