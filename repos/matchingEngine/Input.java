
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Input {
	
	public static void main (String[] args) throws IOException {
		
		ArrayList<Order> orders = new ArrayList<Order>();
		
		Input input = new Input();
		Order newOrder = new Order();
		int orderNumber = 1;
		
		FileInputStream fileName = new FileInputStream("Orders.txt");
		
		try {	
			BufferedReader br = new BufferedReader(new InputStreamReader(fileName));
		
			newOrder = input.inputOrderFromFile(orderNumber,br);
		
			while (newOrder != null) {
				Order bestFoundMatchForOrder = new Order();
				Trade trade = new Trade();
				MatchingEngine useMatchingEngine = new MatchingEngine();
				
				int bestFoundMatchForOrderID = useMatchingEngine.findMatchForOrder(orders, newOrder);
		
				while ((bestFoundMatchForOrderID != 0) && (newOrder.getQuantity() != 0)) {
					
					if (bestFoundMatchForOrderID != 0  ) {
						bestFoundMatchForOrder = orders.get(bestFoundMatchForOrderID - 1);
			
						int quantity = trade.findBougthStockQuantity(newOrder, bestFoundMatchForOrder);
			
						int leftQuantityForOrder = trade.findLeftOrderQuantityAfterMatch(newOrder, bestFoundMatchForOrder);
						int leftQuantityForMatchOrder = trade.findLeftOrderQuantityAfterMatch(bestFoundMatchForOrder, newOrder);
			
						newOrder.setQuantity(leftQuantityForOrder);
						orders.get(bestFoundMatchForOrderID - 1).setQuantity(leftQuantityForMatchOrder);
						
						trade.writeToFileBestFoundMatchForOrder(bestFoundMatchForOrder, newOrder, quantity);
				}	
				
				bestFoundMatchForOrderID = useMatchingEngine.findMatchForOrder(orders, newOrder);
			}
			
			if (newOrder.getQuantity() != 0) {
				orders.add(newOrder);
				orderNumber++;
			}
			
			newOrder = input.inputOrderFromFile(orderNumber,br);
			}
		} finally {
			fileName.close();
			}

	}
	
	
	private Order inputOrderFromFile (int orderID, final BufferedReader br) throws IOException {
		
		String wholeLine = br.readLine();
		if (wholeLine != null) {
			Order newOrder = new Order();
			String[] input = wholeLine.split(" ");
			newOrder.setClientSide(input[0]);
			
			if (input[1].equals("BUY") || input[1].equals("SELL")) newOrder.setBuyOrSell(input[1]);
			else return null;
			
			newOrder.setStock(input[2]);
			String bothQuantityAndPriceSeparatedWithEta = input[3];
			String [] quantityAndPrice = bothQuantityAndPriceSeparatedWithEta.split("@");
			newOrder.setQuantity(Integer.parseInt(quantityAndPrice[0]));
			newOrder.setPrice(Float.parseFloat(quantityAndPrice[1]));
			newOrder.setOrderID(orderID);
			
			return newOrder;
		}
		else {
			br.close();
			return null;
		}
		
	}

		
}
