
public class Order {
	
	private String clientSide;
	private String buyOrSell;
	private String stock;
	private int quantity;
	private float price;
	private int orderID;
	
	public int getOrderID () {
		return this.orderID;
	}
	
	public String getClientSide (){
		return this.clientSide;
	}
	
	public String getBuyOrSell(){
		return this.buyOrSell;
	}
	
	public String getStock() {
		return this.stock;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public void setOrderID (int orderID) {
		this.orderID = orderID;
	}
	
	public void setClientSide (String clientSide){
		this.clientSide = clientSide;
	}
	
	public void setBuyOrSell(String buyOrSell){
		this.buyOrSell = buyOrSell;
	}
	
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
}
