package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException {
        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
        req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();
        return req;
    }
    public String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fi = new FileInputStream("D:\\Project\\RestAssuredApi\\src\\test\\java\\config\\global.properties");
        prop.load(fi);
        return prop.getProperty(key);
    }
}
