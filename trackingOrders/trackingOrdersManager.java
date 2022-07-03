package trackingOrders;

import java.util.ArrayList;

public class trackingOrdersManager {
	ArrayList<Item> itemInfo = new ArrayList<Item>();
	
	// Adds order to arraylist of items
	public void addItem(String name, String store, String trackingNum) throws InterruptedException {
		Item order = new Item(name, store, trackingNum);
		itemInfo.add(order);
	}
	
	// Updates tracking number of a specified order (tracking number was not initially provided)
	public void updateTrackingNum(String name, String store, String trackingNum) throws InterruptedException {
		Item item = new Item();
		
		for (int i = 0; i < itemInfo.size(); i++) {
			item = itemInfo.get(i);
			if (item.getName().equalsIgnoreCase(name) && item.getStore().equalsIgnoreCase(store)) {
				item.updateStatus(trackingNum);
			}
		}
	}
	
	// Returns a string containing each order and its status
	public String getAllOrdersInfo() {
		String infoStr = "***** Orders Information *****" + "\n";
		Item item = new Item();
		
		for (int i = 0; i < itemInfo.size(); i++) {
			item = itemInfo.get(i);
			infoStr += "Name: " + item.getName() + ", ";
			infoStr += "Store: " + item.getStore() + ", ";
			infoStr += "Status: " + item.getStatus() + "\n"; 
		}
		return infoStr;
	}
	
	// Updates list by removing items that have been delivered
	public void removeDeliveredItems() {
		Item item = new Item();
		
		for (int i = 0; i < itemInfo.size(); i++) {
			item = itemInfo.get(i);
			if (item.getStatus().contains("delivered") || item.getStatus().contains("Delivered")) {
				itemInfo.remove(i);
				i--;
			}
		}
	}
	
	// Updates list by removing a specified item
	public void removeItem(String name) {
		Item item = new Item();
		
		for (int i = 0; i < itemInfo.size(); i++) {
			item = itemInfo.get(i);
			if (item.getName().equalsIgnoreCase(name)) {
				itemInfo.remove(i);
				i--;
			}
		}
	}
}
