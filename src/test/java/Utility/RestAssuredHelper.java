package Utility;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

public class RestAssuredHelper {
    RequestSpecification requestSpecification;
    Response response;

    public void getRepoList(String repoName){

        requestSpecification = RestAssured.given();
        response = requestSpecification.request(Method.GET, repoName);

    }

    public List<String> getRepoData(String dataName){
        ArrayList<String> list_api = response.then().extract().path(dataName);
        return list_api;

    }



}
