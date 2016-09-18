package ua.shop_e;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (indexes ={@Index(columnList ="name")})
public class Size {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;

	@ManyToMany(mappedBy= "sizes")
	private List <Product> product;

	@OneToMany(mappedBy ="size")
	private List <MyOrder> myOrder = new ArrayList<>();
	
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

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public List<MyOrder> getMyOrder() {
		return myOrder;
	}

	public void setMyOrder(List<MyOrder> myOrder) {
		this.myOrder = myOrder;
	}
	
}
