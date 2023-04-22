package javaProjecs_oops;

import java.util.*;

class Stock
{
	public Stock(String name, double price) {
		this.name=name;
		this.price=price;
		
		// TODO Auto-generated constructor stub
	}
	public Stock(String name, double price, int quantity, double totalPrice) {
		
		// TODO Auto-generated constructor stub
		this.name=name;
		this.price=price;
		this.quantity=quantity;
		this.totalPrice=totalPrice;
	}
	String name;
	int quantity;
	double price;
	double totalPrice;
	
	
}
class OriginalStock
{
	public OriginalStock(int orgCapacity, int orgSize) {
		// TODO Auto-generated constructor stub
		capacity=orgCapacity;
		size=orgSize;
		stock=new Stock[capacity];
	}
	int capacity;
	static int size;
     static Stock[] stock;
	public void addStock(String name, double price) {
		// TODO Auto-generated method stub
		if(capacity==size);
		{
			System.out.println("Stock cant be added");
		}
		
		stock[size]=new Stock(name , price);
		size++;
		
		
	}
	public void displayStocks() {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++)
		{
			System.out.println("Stock Name :"+stock[i].name);
			System.out.println("Stock Price :"+stock[i].price);
		}
		
	}
	public static   Stock getStock(String name) {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++)
		{
			if(stock[i].name.equals(name))
				return stock[i];
		}
		return null;
	}
	
}
class PortfolioStock
{
	private static final OriginalStock OriginalStock = null;
	public PortfolioStock(int portCapacity, int portSize,OriginalStock orgStock) {
		// TODO Auto-generated constructor stub
		capacity=portCapacity;
		size=portSize;
		stock=new Stock[capacity];
		this.orgStock=OriginalStock ;
	}
	int capacity;
	int size;
	Stock[] stock;
	OriginalStock orgStock; 
	public void addStocks(String name, int quantity) {
		// TODO Auto-generated method stub
		Stock exist=orgStock.getStock(name);
		if(exist==null)
		{
			System.out.println("Invalid stock. Add operation can't be performed!");
		}
		else if (capacity == size)
			System.out.println("Stock can't be purchased");
		else
		{
			stock[size]=new Stock(name , exist.price,quantity,exist.price);
		size++;
		}
		
	}
	public void displayStocks() {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++)
		{
			System.out.println("Stock Name :"+stock[i].name);
			System.out.println("Stock Price :"+stock[i].price);
			System.out.println("Stock Quantity :"+stock[i].quantity);
			System.out.println("Stock Total Price :"+stock[i].totalPrice);
		}
	}
	public void purchaseStocks(String name, int quantity) {
		// TODO Auto-generated method stub
		Stock temp=orgStock.getStock(name);
		if(temp==null)
		{
			System.out.println("Invalid operation");
		}
		else if(capacity==size)
		{
			System.out.println("Stock can't be purchased");
		}
		else {
		Stock curr=portStockIsExist(name);
		if(curr!=null)
		{
			curr.quantity +=quantity;
			curr.totalPrice =curr.quantity*curr.price;
		}
		}
		
			stock[size]=new Stock(name , temp.price, quantity , temp.totalPrice);
			size++;
		
		
	}
	private Stock portStockIsExist(String name) {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++)
		{
			if(stock[i].name.equals(name))
				return stock[i];
		}
		
		return null;
	}
	public void sellStocks(String name, int quantity) {
		// TODO Auto-generated method stub
		Stock temp=orgStock.getStock(name);
		if(temp==null)
		{
			System.out.println("Invalid stock. Sell operation can't be performed!");
		}
		Stock temp1=portStockIsExist(name);
		if(temp1==null)
		{
		
			System.out.println("Stock doesn't exist");
		}
		if(temp1.quantity<quantity)
		{
			System.out.println("Invalid stock quantity.");
		}
		else
		{
			temp1.quantity -=quantity;
			temp1.totalPrice=temp1.quantity*temp1.price;
		}
	}
}
public class Main {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	int orgCapacity=sc.nextInt();
	int orgSize=sc.nextInt();
	
	OriginalStock orgStocks=new OriginalStock(orgCapacity ,orgSize);
	for(int i=0;i<orgSize;i++)
	{
		String name=sc.next();
		double price=sc.nextDouble();
		
		Stock temp=new Stock(name , price);
		
		orgStocks.stock[i]=temp;
	}
	int portCapacity=sc.nextInt();
	int portSize=sc.nextInt();
	
	
	PortfolioStock portStocks=new PortfolioStock(portCapacity ,portSize, orgStocks);
	for(int i=0;i<portSize;i++)
	{
		String name=sc.next();
		//double price=sc.nextDouble();
		Stock curr=OriginalStock.getStock(name); 
		double price=curr.price;
		int quantity=sc.nextInt();
		double totalPrice=price*quantity; 
		
		Stock temp=new Stock(name , price,quantity,totalPrice);
		
		portStocks.stock[i]=temp;
	}
	
	
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:orgStocks.addStock(sc.next(),sc.nextDouble());
			       orgStocks.displayStocks();
			       break;
			case 2:portStocks.addStocks(sc.next(),sc.nextInt());
			       portStocks.displayStocks();
			       break;
			case 3: orgStocks.displayStocks();
			       break;
			case 4:portStocks.displayStocks();
			       break;
			case 5:portStocks.purchaseStocks(sc.next(),sc.nextInt());
			       portStocks.displayStocks();
		           break;
			case 6:portStocks.sellStocks(sc.next(),sc.nextInt());
			       portStocks.displayStocks();
		}
	
}
}
