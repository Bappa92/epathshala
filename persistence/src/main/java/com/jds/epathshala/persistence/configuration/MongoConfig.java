//package com.jds.epathshala.persistence.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
//
//import com.mongodb.MongoClient;
//
//@Configuration
//public class MongoConfig extends AbstractMongoConfiguration {
//	@Override
//	public String getDatabaseName() {
//		return "epathshaladb";
//	}
//
//
//	@Override
//	@Bean
//	public MongoClient mongoClient() {
//		MongoClient mclient = new MongoClient("localhost", 27017);
//		return mclient;
//	}
//}
