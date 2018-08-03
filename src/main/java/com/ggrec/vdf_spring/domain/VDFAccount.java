package com.ggrec.vdf_spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.User;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.*;

@Entity(name = "vdf_account")
public class VDFAccount implements SocialUserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String name;

    // private String encryptedPassword;

    @JsonIgnore
    private String facebookId;

    @JsonIgnore
    private String accessToken;

    @Transient
    private long expires;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<VDFAccountAuthority> authorities;

    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean accountEnabled;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getEncryptedPassword() {
//        return encryptedPassword;
//    }
//
//    public void setEncryptedPassword(String encryptedPassword) {
//        this.encryptedPassword = encryptedPassword;
//    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return getUserId() + ":" + getEmail();
    }

    public static VDFAccount fromSocialUserProfile(UserProfile userProfile) {
        VDFAccount newAccount = new VDFAccount();
        newAccount.setEmail(userProfile.getEmail());
        newAccount.setFacebookId(userProfile.getId());
        newAccount.grantRole(VDFAccountRole.USER);
        return newAccount;
    }

    public static VDFAccount fromSocialConnection(Connection<?> connection) {
        VDFAccount newAccount = new VDFAccount();
        newAccount.setEmail(connection.fetchUserProfile().getEmail());
        newAccount.setFacebookId(connection.getKey().getProviderUserId());
        newAccount.setAccessToken(connection.createData().getAccessToken());
        newAccount.grantRole(VDFAccountRole.USER);
        return newAccount;
    }

    @JsonIgnore
    @Override
    public String getUserId() {
        return Objects.toString(id);
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // Use Roles as external API
    public Set<VDFAccountRole> getRoles() {
        Set<VDFAccountRole> roles = EnumSet.noneOf(VDFAccountRole.class);
        if (authorities != null) {
            for (VDFAccountAuthority authority : authorities) {
                roles.add(VDFAccountRole.valueOf(authority));
            }
        }
        return roles;
    }

    public void setRoles(Set<VDFAccountRole> roles) {
        for (VDFAccountRole role : roles) {
            grantRole(role);
        }
    }

    public void grantRole(VDFAccountRole role) {
        if (authorities == null) {
            authorities = new HashSet<>();
        }
        authorities.add(role.asAuthorityFor(this));
    }

    public void revokeRole(VDFAccountRole role) {
        if (authorities != null) {
            authorities.remove(role.asAuthorityFor(this));
        }
    }

    public boolean hasRole(VDFAccountRole role) {
        return authorities.contains(role.asAuthorityFor(this));
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        throw new IllegalStateException("Password should never be used");
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return !accountEnabled;
    }

}
