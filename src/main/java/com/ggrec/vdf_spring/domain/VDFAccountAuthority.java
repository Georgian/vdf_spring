package com.ggrec.vdf_spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "vdf_account_authority")
public class VDFAccountAuthority implements GrantedAuthority {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @Id
    private VDFAccount account;

    @NotNull
    @Id
    private String authority;

    public VDFAccount getAccount() {
        return account;
    }

    public void setAccount(VDFAccount account) {
        this.account = account;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VDFAccountAuthority))
            return false;

        VDFAccountAuthority ua = (VDFAccountAuthority) obj;
        return Objects.equals(ua.getAuthority(), this.getAuthority());
    }

    @Override
    public int hashCode() {
        return getAuthority() == null ? 0 : getAuthority().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getAuthority();
    }

}
