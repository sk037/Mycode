package com;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utils.XLUtility;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.io.IOException;

public class DeleteUser {
	//static String payload;
	
	@BeforeClass
	public void setup() {
		RestAssured.useRelaxedHTTPSValidation();
	}
	@Test(dataProvider="getData")
	void Test01(String updateUserName,String userid)
	{
		RestAssured.baseURI="https://pegasus-qa.aka.amazon.com";
		given().
		       log().all().
		       header("Content-Type","application/json").
		       body(AddPayload1.setAttribute(updateUserName, userid)).
		when().
		       log().all().
		       post("/maintenance/deleteUsers").
		then().
		       log().all().
		       assertThat().statusCode(200).and().
		       contentType(ContentType.TEXT);
	
	}
			
	@DataProvider
	public Object [][] getData() throws IOException
	{
//		Object data[][]= {{"kumarivk","veena","kumarivk@amazon.com","false","stiku@amazon.com","2","stiku","2","stiku"},{"bhissi","Abhishek","bhissi@amazon.com","false","stiku@amazon.com","2","stiku","2","stiku"}};
//		return data;
		String xlFilePath = null;
		try {
			xlFilePath = System.getProperty("user.dir") + "\\DeleteUserList.xlsx";
		}catch(Exception e) { 
			   System.out.println("File is not present");  
			  } 
        
        int rowCount = XLUtility.getRowCount(xlFilePath, "Sheet1");
        System.out.println(rowCount);
        int columnCount = XLUtility.getCellCount(xlFilePath, "Sheet1", 1);
        System.out.println(columnCount);

        String data[][] = new String [rowCount][columnCount];
        for (int currentRow = 1; currentRow <= rowCount; currentRow++){
            for(int currentColumn = 0; currentColumn < columnCount; currentColumn++){
            	data[currentRow-1][currentColumn] = XLUtility.getCellData(xlFilePath, "Sheet1", currentRow, currentColumn);
            }
        }
        return data;
    }
	
}
		
class AddPayload1
{
	public static String setAttribute(String data1,String data2)
	{
		String payload="{\r\n" + 
				"	\"updateUserName\":\""+data1+"\",\r\n" + 
				"	\"users\":[\r\n" + 
				"		{\r\n" + 
				"			\"userId\":\""+data2+"\"\r\n" + 
				"		}\r\n" + 
				"	]\r\n" + 
				"}";
	return payload;
	 
}
}
