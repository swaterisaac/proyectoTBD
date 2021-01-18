package grupo2.proyectoTBD.repositories;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.Collection;
import java.util.Collections;


    @Configuration
    public class MongoDBConfig extends AbstractMongoClientConfiguration {
        @Value("${spring.data.mongodb.uri}")
        private String mongoDBConn;
        @Override
        protected String getDatabaseName() {
            return "tbd";
        }

        @Override
        public MongoClient mongoClient() {
            ConnectionString connectionString = new ConnectionString(mongoDBConn);
            MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();

            return MongoClients.create(mongoClientSettings);
        }

        @Override
        public Collection<String> getMappingBasePackages() {
            return Collections.singleton("grupo2.proyectoTBD.repositories");
        }
    }