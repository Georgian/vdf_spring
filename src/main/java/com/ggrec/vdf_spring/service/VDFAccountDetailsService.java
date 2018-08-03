package com.ggrec.vdf_spring.service;

import com.ggrec.vdf_spring.domain.VDFAccount;
import com.ggrec.vdf_spring.repository.VDFAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

@Service
@Transactional
public class VDFAccountDetailsService implements VDFAccountSocialDetailsService {

    private VDFAccountRepository accountRepository;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Autowired
    public VDFAccountDetailsService(VDFAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<VDFAccount> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public VDFAccount loadAccountByUserId(Long userId) {
        return checkAccount(accountRepository.getOne(userId), userId);
    }

    @Override
    public VDFAccount loadAccountByEmail(String email) {
        return checkAccount(accountRepository.findOneByEmail(email), email);
    }

    @Override
    public VDFAccount loadAccountByConnectionKey(ConnectionKey connectionKey) {
        final VDFAccount account = accountRepository.findOneByFacebookId(connectionKey.getProviderUserId());
        return checkAccount(account, connectionKey);
    }

    @Override
    public VDFAccount saveAccount(VDFAccount account) {
        return accountRepository.save(account);
    }


    /**
     * @param account
     * @param identifier For debug purposes
     * @return
     */
    private VDFAccount checkAccount(VDFAccount account, Object identifier) {
        if (account == null)
            throw new UsernameNotFoundException("Account not found: " + identifier);

        detailsChecker.check(account);
        return account;
    }

    /**
     * https://stackoverflow.com/a/44872810/1774643
     */
    @PostConstruct
    private void init() {
        try {
            String[] fieldsToMap = {
                    "id", "email", "first_name"
            };

            Field field = Class.forName("org.springframework.social.facebook.api.UserOperations").
                    getDeclaredField("PROFILE_FIELDS");
            field.setAccessible(true);

            Field modifiers = field.getClass().getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, fieldsToMap);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
