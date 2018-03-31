import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Trade {
	
	public void writeToFileBestFoundMatchForOrder (Order bestMatch, Order order, int quantity) {
		
		try{
	          File file =new File("Trades.txt");
	    	  if(!file.exists()){
	    	 	file.createNewFile();
	    	  }
	    	  FileWriter fw = new FileWriter(file,true);
	    	  BufferedWriter bw = new BufferedWriter(fw);
	    	  PrintWriter pw = new PrintWriter(bw);
	          
	    	  if (order.getBuyOrSell().equals("BUY")) {
	    		  pw.print(bestMatch.getClientSide() + " ");
	    		  pw.print("-> ");
	    		  pw.print(order.getClientSide() + " ");
	  			}
	    	  else {
	    		  pw.print(order.getClientSide() + " ");
	    		  pw.print("-> ");
	    		  pw.print(bestMatch.getClientSide() + " ");
	  			}
	    	  pw.print(order.getStock() + " ");
	    	  pw.print(quantity + "@");
	    	  pw.println(bestMatch.getPrice());
	    	  
	    	  
	    	  pw.close();

	       }catch(IOException ioe) {
	    	   System.out.println("Exception occurred:");
	    	   ioe.printStackTrace();
	       	}
	   }
	
	public int findBougthStockQuantity(Order order,Order matchForOrder) {
		if (order.getQuantity() >= matchForOrder.getQuantity()) {
			return matchForOrder.getQuantity();
		}
		else return order.getQuantity();
	}
	
	
	public int findLeftOrderQuantityAfterMatch (Order order, Order orderMatch) {
		
		if (order.getQuantity() >= orderMatch.getQuantity()) {
			return order.getQuantity() - orderMatch.getQuantity();
		}
		else return 0;
		
	}

}
