package CRUDOperations;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



public class Controller {
	public static void main(String[] args) {
		Controller controller = new Controller();
		Scanner sc = new Scanner(System.in);
		
		boolean result = true;
		int mychoice= 0;
		
		while(result) {
			
			System.out.println("Choose the  Choice");
			System.out.println("1 - Insert A Car");
			System.out.println("2 - Display Car by ID");
			System.out.println("3 - Update the Car Details");
			System.out.println("4 - Delete the Car");
			System.out.println("5 - Display the Car List");
			System.out.println("6 - Exit");
			System.out.println("Enter the Choice:");
			
			mychoice=sc.nextInt();
			switch(mychoice)
			{
			case 1:{
				controller.insertCar();
				break;
			}
			case 2:{
				controller.displayCarbyID();
				break;
			}
			case 3: {
				controller.updateCar();
				break;
			}
			case 4:{
				controller.deleteCar();
				break;
			}
			case 5:{
				controller.displayAllCars();
				break;
			}
			case 6:{
				result = false;
				System.out.println("Exited Operations");
				break;
			}
			default:
				System.out.println("Invalid Choice");
			
			}
			
		}
		sc.close();
		
	}
	
	public void insertCar()//Create Operation in CRUD
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		
		Scanner sc = new Scanner(System.in);
		//Create an object for the POJO Class
		Car car = new Car();
		System.out.println("Enter the Car ID:");
		car.setId(sc.nextInt());
		System.out.println("Enter the Car Name:");
		car.setName(sc.next());
		System.out.println("Enter the Number of Seats in the Car:");
		car.setSeats(sc.nextInt());
		System.out.println("Enter the Car Color:");
		car.setColor(sc.next());
		
		session.persist(car);
		t.commit();
		System.out.println("Car Added Successfully");
		
		session.close();
		sf.close();
		
 	}
	
	public void displayCarbyID()//Retrieve Operation in CRUD
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Car ID:");
		int car_id = sc.nextInt();
		
		Car car = session.find(Car.class, car_id);
		
		if(car!= null)
		{
			System.out.println("Car ID:"+car.getId());
			System.out.println("Car Name:"+car.getName());
			System.out.println("Number of Seats in Car:"+car.getSeats());
			System.out.println("Car Color:"+car.getColor());
		}
		else {
			System.out.println("Car Not Found!");
		}
		session.close();
		sf.close();
		
		
		
	}
	
	public void updateCar()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction  t = session.beginTransaction();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Car ID:");
		int car_id = sc.nextInt();
		
		Car car = session.find(Car.class, car_id);
		
		if(car!=null)
		{
			
			int choice;
			do {
			System.out.println("Choose the Choices");
			System.out.println("Choose 1 for Name Update:");
			System.out.println("Choose 2 for Seats Update:");
			System.out.println("Choose 3 for Color Update:");
			System.out.println("Choose 4 for Exiting Update Operation");
			System.out.println("Enter the choice:");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Enter Car Name to Update:");
				car.setName(sc.next());
				t.commit();
				break;
				
			case 2:
				System.out.println("Enter Number of Seats to Update:");
				car.setSeats(sc.nextInt());
				t.commit();
				System.out.println("Car Updated Successfully");
				break;
			case 3:
				System.out.println("Enter the Color of the Car to Update:");
				car.setColor(sc.next());
				t.commit();
				System.out.println("Car Updated Successfully");
				break;
			case 4:
				System.out.println("Exiting the Update Operation");
				break;
				
			default:
				System.out.println("Invalid Input");
				break;
			}
			}while(choice!=4);
			
			
			
				
		}
		else {
			System.out.println("Car Not Found!");
		}
	
		session.close();
		sf.close();
	}
	
	public void deleteCar()
	{
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction  t = session.beginTransaction();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Car ID to Delete:");
		int car_id = sc.nextInt();
		
		Car car = session.find(Car.class, car_id);
		
		if(car != null)
		{
			session.remove(car);
			t.commit();
			System.out.println("Car Deleted Successfully");
		}
		else {
			System.out.println("Car ID Not Found");
		}
	}
	
	public void displayAllCars()
	{

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		
		SessionFactory sf = configuration.buildSessionFactory();//based on database
		Session session = sf.openSession();//based on operation
		
		String hql = "from Car";//select * from product_table;
		
		Query<Car> qry = session.createQuery(hql, Car.class);//here Product is the result class
		List<Car> carlist = qry.getResultList();
		System.out.println("Total Cars in the Showroom = "+carlist.size());
		
		for(Car  c : carlist)
		{
			System.out.println("----Car Details----");
			System.out.println("Car ID:"+c.getId());
			System.out.println("Car Name:"+c.getName());
			System.out.println("Car Color:"+c.getColor());
			System.out.println("Car Seats:"+c.getSeats());
		}
		session.close();
		sf.close();
	}
}
