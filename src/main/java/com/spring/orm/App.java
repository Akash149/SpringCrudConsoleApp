package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
    	StudentDao studentDao = context.getBean("studentDao",StudentDao.class);
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	boolean go = true;
    	while(go) {
    		System.out.println("************ welcome to spring orm project ************");
        	System.out.println("Press 1 for add new Student");
        	System.out.println("Press 2 for display all student");
        	System.out.println("Press 3 for get detail of single student");
        	System.out.println("Press 4 for delete students");
        	System.out.println("Press 5 for update student");
        	System.out.println("Press 0 for exit");
        	System.out.println("******************************************************");
        	
        	try {
        		
        		int input = Integer.parseInt(br.readLine());
        		
        		switch (input) {
				case 1:
					//add new Student
					System.out.println("Enter user id: ");
					int uId = Integer.parseInt(br.readLine());
					
					System.out.println("Enter user name: ");
					String uName = br.readLine();
					
					System.out.println("Enter city name: ");
					String uCity = br.readLine();
					
					Student student = new Student(uId,uName,uCity);
					int r = studentDao.insert(student);
					System.out.println(r + " Student added");
					break;
				case 2:
					//Display all student
					List<Student> allStudents = studentDao.getAllStudents();
					for(Student s: allStudents) {
						System.out.println("-----------------------------");
						System.out.println("Id: " + s.getId());
						System.out.println("Name: " + s.getName());
						System.out.println("City: " + s.getCity());
						System.out.println("-----------------------------");
					}
					break;
					
				case 3:
					//Get single student details
					System.out.println("Enter user id: ");
					int usId = Integer.parseInt(br.readLine());
					Student st = studentDao.getStudent(usId);
					System.out.println("-----------------------------");
					System.out.println("Id: " + st.getId());
					System.out.println("Name: " + st.getName());
					System.out.println("City: " + st.getCity());
					System.out.println("-----------------------------");
					break;
				case 4:
					//Delete student
					System.out.println("Enter user id: ");
					int userId = Integer.parseInt(br.readLine());
					studentDao.deleteStudent(userId);
					System.out.println("Student deleted...");
					break;
					
				case 5:
					//update student details
					System.out.println("Enter user id: ");
					int Id = Integer.parseInt(br.readLine());
					
					System.out.println("Enter user name: ");
					String Name = br.readLine();
					
					System.out.println("Enter city name: ");
					String City = br.readLine();
					Student students = new Student(Id,Name,City);
					studentDao.updateStudent(students);
					System.out.println(Id + " Data update");
					break;
				case 0:
					//exit
					go = false;
					break;

				default:
					System.out.println("Invalid Input, Try with another option");
					break;
				}
        		
        	} catch(Exception e) {
        		System.out.println("Invalid Input, Try with anoter option");
        		System.out.println(e);
        	}
    	}
    }
}
