package com.biomed.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.sql.DataSource;

import com.biomed.server.dispatch.DispatchServletModule;
import com.biomed.server.handlers.BiomedServiceModule;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import com.jolbox.bonecp.BoneCPDataSource;

public class BiomedModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new DispatchServletModule());
    install(new BiomedServiceModule());

    bind(CalendarApi.class).asEagerSingleton();

    bindProperties();
  }

  private void bindProperties() {
    Properties prop = new Properties();
    try {
    	System.out.println("Current Directory: " + System.getProperty("user.dir"));
      prop.load(new FileInputStream("../server.properties"));
      Names.bindProperties(binder(), prop);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Provides
  @Singleton
  DataSource providesDataSource(
      @Named("database.host") String host,
      @Named("database.name") String name,
      @Named("database.username") String username,
      @Named("database.password") String password) {

    BoneCPDataSource dataSource = new BoneCPDataSource();
    dataSource.setDriverClass("com.mysql.jdbc.Driver");
    dataSource.setJdbcUrl("jdbc:mysql://" + host + "/" + name);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setMinConnectionsPerPartition(5);
    dataSource.setMaxConnectionsPerPartition(50);
    dataSource.setAcquireIncrement(5);

    return dataSource;
  }

  @Provides
  Connection providesConnection(DataSource ds) {
    try {
      return ds.getConnection();
    } catch (SQLException e) {
      System.out.println("Unable to create database connection:");
      e.printStackTrace();
    }

    return null;
  }
}
