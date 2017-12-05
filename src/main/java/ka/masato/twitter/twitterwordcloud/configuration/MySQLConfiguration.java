package ka.masato.twitter.twitterwordcloud.configuration;

import com.sun.corba.se.impl.naming.cosnaming.NamingContextDataStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Profile("production")
@Configuration
public class MySQLConfiguration {

    private final DataSourceProperties dataSourceProperties;

    public MySQLConfiguration(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @ConfigurationProperties(prefix="spring.datasource.tomcat")
    @Bean
    DataSource realDataSource() throws URISyntaxException{
        DataSource dataSource = null;
        String uri = null;
        String username = null;
        String password = null;

        String dataBaseUrl = System.getenv("CLEARDB_DATABASE_URL");

        if (dataBaseUrl != null) {
            URI dbUri = new URI(dataBaseUrl);
            uri = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];

        }else{
            uri = dataSourceProperties.getUrl();
            username = dataSourceProperties.getUsername();
            password = dataSourceProperties.getPassword();
        }
        log.info(uri);
        DataSourceBuilder factory = DataSourceBuilder.create(dataSourceProperties.getClassLoader())
                    .url(uri)
                    .username(username)
                    .password(password);
        dataSource = factory.build();

        return dataSource;
    }
}
