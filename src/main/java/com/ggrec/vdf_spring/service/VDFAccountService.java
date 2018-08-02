package com.ggrec.vdf_spring.service;

import com.ggrec.vdf_spring.domain.VDFAccount;
import com.ggrec.vdf_spring.repository.VDFAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VDFAccountService {

    private VDFAccountRepository vdfAccountRepository;

    @Autowired
    public VDFAccountService(VDFAccountRepository vdfAccountRepository) {
        this.vdfAccountRepository = vdfAccountRepository;
    }

    public VDFAccount saveAccount(VDFAccount vdfAccount) {
        return vdfAccountRepository.save(vdfAccount);
    }

    public VDFAccount loadAccountByEmail(String email) {
        return vdfAccountRepository.findOneByEmail(email);
    }

    public VDFAccount loadAccountByFacebookId(String facebookId) {
        return vdfAccountRepository.findOneByFacebookId(facebookId);
    }

}
