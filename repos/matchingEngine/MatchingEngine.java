import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MatchingEngine {
	
	public int findMatchForOrder (ArrayList<Order> ordersList, Order newOrder) {
		
		int bestMatchOrderID = searchForBestMatchFromOrdersList(ordersList, newOrder);
		return bestMatchOrderID;
		
	}
	
	private int searchForBestMatchFromOrdersList (ArrayList<Order> orderList, Order orderToFindWith) {
		
		ArrayList<Order> selectedOrdersForSpecificOrder = new ArrayList<Order>();
		
		for (Order oneOrder : orderList) {
			if ( (!oneOrder.getBuyOrSell().equals(orderToFindWith.getBuyOrSell())) && (oneOrder.getStock().equals(orderToFindWith.getStock())) &&
					!( oneOrder.getQuantity() == 0 ) ) {
				if ( ((oneOrder.getBuyOrSell().equals("SELL")) && (orderToFindWith.getPrice() >= oneOrder.getPrice())) ||
					((oneOrder.getBuyOrSell().equals("BUY")) && (orderToFindWith.getPrice() <= oneOrder.getPrice())) ) {
				selectedOrdersForSpecificOrder.add(oneOrder);
				}
			}
		}
		
		Collections.sort(selectedOrdersForSpecificOrder, new Comparator<Order>() {
			 public int compare (Order a1, Order a2) {
			 		if (orderToFindWith.getBuyOrSell().equals("BUY")) {
			 		return Float.valueOf(a1.getPrice()).compareTo(a2.getPrice());
					}
		 			else return Float.valueOf(a2.getPrice()).compareTo(a1.getPrice());
			 		}
			});
		 
		 
		 if (selectedOrdersForSpecificOrder.isEmpty()) {
			 return 0;
		 }
		 else {
			 return selectedOrdersForSpecificOrder.get(0).getOrderID();
		 }
		
	}
	
	

}
