package io.github.bluething.playground.spring.data.updatewithoutselect;

import net.ttddyy.dsproxy.listener.ChainListener;
import net.ttddyy.dsproxy.listener.DataSourceQueryCountListener;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootConfiguration
@ComponentScan("io.github.bluething.playground.spring.data.updatewithoutselect")
@EnableAutoConfiguration
public class UpdateWithoutSelectConfigurationTests {
    @Bean
    public DataSource dataSource(DataSource originalDataSource) {
        ChainListener listener = new ChainListener();
        SLF4JQueryLoggingListener loggingListener = new SLF4JQueryLoggingListener();
        loggingListener.setQueryLogEntryCreator(new InlineQueryLogEntryCreator());
        listener.addListener(loggingListener);
        listener.addListener(new DataSourceQueryCountListener());
        return ProxyDataSourceBuilder
                .create(originalDataSource)
                .name("DS-Proxy")
                .listener(listener)
                .build();
    }
}
