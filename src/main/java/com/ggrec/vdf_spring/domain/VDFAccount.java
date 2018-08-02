package com.ggrec.vdf_spring.domain;

import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.User;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "vdf_athlete")
public class VDFAccount {

    @Id
    @GeneratedValue
    private long id;
    private String email;
    private String name;
    private String encryptedPassword;
    private String facebookId;

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

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public static VDFAccount fromSocialUserProfile(UserProfile userProfile) {
        VDFAccount userCreateRequestVO = new VDFAccount();
        userCreateRequestVO.setFacebookId(userProfile.getId());
        userCreateRequestVO.setEmail(StringUtils.isEmpty(userProfile.getEmail()) ? "" : userProfile.getEmail());
        // TODO Fb id
        return userCreateRequestVO;
    }


}
