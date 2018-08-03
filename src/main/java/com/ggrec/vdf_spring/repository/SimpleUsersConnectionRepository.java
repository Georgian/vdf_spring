package com.ggrec.vdf_spring.repository;

import com.ggrec.vdf_spring.domain.VDFAccount;
import com.ggrec.vdf_spring.service.VDFAccountSocialDetailsService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.social.connect.*;

import java.util.*;

/**
 * A Simplified version of the JdbcUsersConnectionRepository that does not persist multiple connections to/from users.
 * This repository works with a one-to-one relation between between User and Connection
 * Note that it uses the JPA based UserService so no custom SQL is used
 */
public class SimpleUsersConnectionRepository implements UsersConnectionRepository {

    public static final String PROVIDER_ID = "facebook";

    private VDFAccountSocialDetailsService accountSocialDetailsService;

    private ConnectionFactoryLocator connectionFactoryLocator;
    private ConnectionSignUp connectionSignUp;

    public SimpleUsersConnectionRepository(VDFAccountSocialDetailsService accountSocialDetailsService, ConnectionFactoryLocator connectionFactoryLocator) {
        this.accountSocialDetailsService = accountSocialDetailsService;
        this.connectionFactoryLocator = connectionFactoryLocator;
    }

    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        try {
            VDFAccount user = accountSocialDetailsService.loadAccountByConnectionKey(connection.getKey());
            user.setAccessToken(connection.createData().getAccessToken());
            accountSocialDetailsService.saveAccount(user);
            return Arrays.asList(user.getUserId());
        } catch (AuthenticationException ae) {
            return Arrays.asList(connectionSignUp.execute(connection));
        }
    }

    @Override
    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
        Set<String> keys = new HashSet<>();
        for (String userId: providerUserIds) {
            ConnectionKey ck = new ConnectionKey(providerId, userId);
            try {
                keys.add(accountSocialDetailsService.loadAccountByConnectionKey(ck).getUserId());
            } catch (AuthenticationException ae) {
                //ignore
            }
        }
        return keys;
    }

    @Override
    public ConnectionRepository createConnectionRepository(String userId) {
        ConnectionRepository connectionRepository = new VDFInMemoryConnectionRepository(connectionFactoryLocator);
        final VDFAccount user = accountSocialDetailsService.loadUserByUserId(userId);
        final ConnectionData connectionData = new ConnectionData(
                PROVIDER_ID,
                user.getUserId(),
                null, null, null,
                user.getAccessToken(),
                null, null, null);

        // TODO
        final Connection connection = connectionFactoryLocator
                .getConnectionFactory(PROVIDER_ID).createConnection(connectionData);
        connectionRepository.addConnection(connection);
        return connectionRepository;
    }

    public void setConnectionSignUp(ConnectionSignUp connectionSignUp) {
        this.connectionSignUp = connectionSignUp;
    }
}