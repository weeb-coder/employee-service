package com.microservices.employeeservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseCnf extends AbstractCouchbaseConfiguration {

    @Autowired
    private ApplicationContext context;

//    @Value("{app.couchbase.connection-string}")
//    private String connectionString;
//
//    @Value("{app.couchbase.user-name}")
//    private String username;
//
//    @Value("{app.couchbase.password}")
//    private String password;
//
//    @Value("{app.couchbase.bucket-primary}")
//    private String employeeBucket;
//
//    @Value("{app.couchbase.bucket-user}")
//    private String employeeLeaveBucket;

    @Override
    public String getConnectionString() {
        return "couchbase://127.0.0.1";
    }

    @Override
    public String getUserName() {
        return "admin";
    }

    @Override
    public String getPassword() {
        return "admin123";
    }

    @Override
    public String getBucketName() {
        return "Employee";
    }

//    @Override
//    protected void configureRepositoryOperationsMapping(RepositoryOperationsMapping mapping) {
//        mapping.mapEntity(EmployeeLeaveEntity.class, getCouchbaseTemplate("EmployeeLeave"));
////        mapping.mapEntity(EmployeeSalaryEntity.class, getCouchbaseTemplate("EmployeeSalary"));
//    }
//
//    private CouchbaseTemplate getCouchbaseTemplate(String bucketName) {
//        CouchbaseTemplate template = null;
//        try {
//            template = new CouchbaseTemplate(
//                    couchbaseClientFactory(bucketName),
//                    mappingCouchbaseConverter(
//                            couchbaseMappingContext(customConversions()),
//                            new CouchbaseCustomConversions(Collections.emptyList())
//                    )
//            );
//            template.setApplicationContext(context);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return template;
//    }
//
//    private CouchbaseClientFactory couchbaseClientFactory(String bucketName) {
//        return new SimpleCouchbaseClientFactory(
//                couchbaseCluster(
//                        couchbaseClusterEnvironment()),
//                        bucketName,
//                        this.getScopeName()
//        );
//    }
}
