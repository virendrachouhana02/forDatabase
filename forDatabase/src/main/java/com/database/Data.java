package com.database;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Data {
	private String name;
	public void readData() throws SQLException{
		//try(Scanner input = new Scanner(new File("src/com/database/data.txt"))){
		try(Scanner input = new Scanner(new File("E:/abc.txt"))){
			while(input.hasNextLine()){
				name = "";
				String line;
				line = input.nextLine();
				try(Scanner data = new Scanner(line)){
					while(!data.hasNextInt()){
						name += data.next()+" ";
						name = name.trim();
					}
				}
				saveData();
			}
		}catch(IOException e){
			System.out.println(e);
		}
	}
	private void saveData()throws SQLException{
				ApplicationContext context = new ClassPathXmlApplicationContext("SpringJdbc.xml");
		        // get bean declared with name "dataSource" in the configuration file
			DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("dataSource");
		        // get connection
			Connection connection = dataSource.getConnection();
			PreparedStatement pstat = connection.prepareStatement("INSERT INTO info VALUES(?)");
			pstat.setString(1, name);
			pstat.executeUpdate();
		}
		}

class Demo{
	public static void main(String[] args){
		Data info = new Data();
		try{
			info.readData();
		}catch(Exception e){
			System.out.println(e);
		}
		}
	}


