package CRUDOperations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "car_table")
public class Car 
{
	@Id//Car id is the Primary Key
	@Column(name = "car_id")
	private int id;
	@Column(name = "car_name")
	private String name;
	@Column(name = "car_seats")
	private int seats;//It indicates the number of seats
	@Column(name = "car_color")
	private String color;//It indicates the color of the car
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
