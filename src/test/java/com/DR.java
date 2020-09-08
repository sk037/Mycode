package com;

import org.testng.TestNG;

public class DR extends DeleteUser{
        static TestNG testng;
		public static void main(String[] args) {
			//CreateUser testSuite = new CreateUser();
			//testSuite.setup();
		//	System.out.println("Working Directory = " + System.getProperty("user.dir"));
			testng=new TestNG();
			testng.setTestClasses(new Class[] {DeleteUser.class});
			testng.run();
				
		}
	}
