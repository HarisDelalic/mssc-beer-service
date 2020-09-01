package com.dela.msscbeerservice.config;

import com.dela.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@Profile(value = "localmysql")
public class ScriptLoader {
    private final String BEER_SAMPLE_DATA_SCRIPT = "data.sql";

    private final DataSource datasource;
    private final BeerRepository beerRepository;

    @PostConstruct
    public void loadSampleData() throws Exception {
        if (beerRepository.count() == 0) {
            Resource resource = new ClassPathResource(BEER_SAMPLE_DATA_SCRIPT);
            ScriptUtils.executeSqlScript(datasource.getConnection(), resource);
        }
    }
}
