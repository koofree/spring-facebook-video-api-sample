package kr.ac.korea.dilab.playing.fvs.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.FeedOperations;

/**
 * Created by okcomputer on 3/30/2015.
 */
@Configuration
public class SocialConfiguration {

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator locator, ConnectionRepository repository) {
        CostomizedConnectController connectController = new CostomizedConnectController(locator, repository);

        return connectController;
    }

    private static class CostomizedConnectController extends ConnectController {
        public CostomizedConnectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
            super(connectionFactoryLocator, connectionRepository);
        }

        @Override
        protected String connectedView(String providerId) {
            return "redirect:/";
        }
    }

}
