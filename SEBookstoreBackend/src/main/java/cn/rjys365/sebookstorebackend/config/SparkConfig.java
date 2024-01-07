package cn.rjys365.sebookstorebackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

@Configuration
public class SparkConfig {

    @Bean
    public SparkConf sparkConf() {
        return new SparkConf()
                .setAppName("mySparkApp")
                .setMaster("local"); // Use local[*] for development
    }

    @Bean
//    @ConditionalOnMissingBean(JavaSparkContext.class)
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkConf());
    }

//    @Bean
//    public SparkSession sparkSession() {
//        return SparkSession.builder()
//                .sparkContext(javaSparkContext().sc())
//                .appName("mySparkApp")
//                .getOrCreate();
//    }
}