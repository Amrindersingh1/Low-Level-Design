
/* 
* Requirements:
*	1. user can login/register
*	2. Guest shopping - but register to buy
*	3. search catalog - name or category
*	4. shopping cart - add/remove/update
*	5. sell new product
*	6. checkout and buy from shopping cart
*	7. shipping to address
*	8. cancel if not shipped
*	9. track shipment - notification, status of order 
*	10. payment usind credit/debit card
*   11. Add/modify product review
*/

/* 
* Actors:
*	1. customer - 	login,signup,reset password, manage user details, 
*				search , cart operations, buy, checkout, payment, add/modify product,
*				track, cancel
*	2. Guest - resister, search, shopping cart operations
*	3. Admin - user account management, add/modify product category
*	4. Stysem - notifications 
*/


// constants

public class Address {
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String country;
}

public class user {
	private String name;
	private Address shippingAddress;
	private String email;
	private String phone;
}

public enum OrderStatus {
	PENDING, SHIPPED, COMPLETED, CANCELED, REFUND
}

public enum AccountStatus {
	ACTIVE, BLOCKED, BANNED, INACTIVE`
}

public enum ShipmentStatus {
	PENDING, SHIPPED, DELIVERED, ON_HOLD,
}

public enum PaymentStatus {
	UNPAID, PENDING, COMPLETED, DECLINED, CANCELLED, REFUNDED
}


// Account
public class Account {
	private String userName;
	private String password;
	private AccountStatus status;
  

	private List<CreditCard> creditCards;
	private List<ElectronicBankTransfer> bankAccounts;

	public boolean addProduct(Product product);
	public boolean addProductReview(ProductReview review);
	
	public boolean login();
	public boolean signUp();
	public boolean resetPassword();
}

public abstract class Customer {
	private ShoppingCart cart;
	private Order order;

	public ShoppingCart getShoppingCart();
	public bool addItemToCart(Item item);
	public bool removeItemFromCart(Item item);
}

public class Guest extends Customer {
	public bool registerAccount();
}

public class Member extends Customer {
	private Account account;
	public OrderStatus placeOrder(Order order);
}


// Catalog
public interface Search {
	public List<Product> searchProductsByName(String name);
	public List<Product> searchProductsByCategory(String category);
}

public class Catalog implements Search {
	HashMap<String, List<Product>> productNames;
	HashMap<String, List<Product>> productCategories;

	public List<Product> searchProductsByName(String name) {
		return productNames.get(name);
	}

	public List<Product> searchProductsByCategory(String category) {
		return productCategories.get(category);
	  }
}

// Products
public class ProductCategory {
	private String name;
	private String description;
}

public class ProductReview {
	private int rating;
	private String review;

	private Member reviewer;
}

public class Product {
	private String productID;
	private String name;
	private String description;
	private double price;
	private ProductCategory category;
	private int availableItemCount;

	private Account seller;

	public int getAvailableCount();
	public boolean updatePrice(double newPrice);
}


// Shopping cart
public class Item {
	private String productID;
	private int quantity;
	private double price;

	public boolean updateQuantity(int quantity);
}

public class ShoppingCart {
	private List<Items> items;

	public boolean addItem(Item item);
	public boolean removeItem(Item item);
	public boolean updateItemQuantity(Item item, int quantity);
	public List<Item> getItems();
	public boolean checkout();
}

// Order
public class Order {
	private String orderNumber;
	private OrderStatus status;
	private Date orderDate;

	public boolean sendForShipment();
	public boolean makePayment(Payment payment);
}

// payment
public abstract class Payment {
	private int notificationId;
	private Date createdOn;
	private String content;

	public boolean sendNotification(Account account);
}

// shipment
public class ShipmentLog {
	private String shipmentNumber;
	private ShipmentStatus status;
	private Date creationDate;
}

public class Shipment {
	private String shipmentNumber;
	private Date shipmentDate;
	private Date estimatedArrival;
	private String shipmentMethod;
	private List<ShipmentLog> shipmentLogs;

	public boolean addShipmentLog(ShipmentLog shipmentLog);
}

// notifications
public abstract class Notification {
	private int notificationId;
	private Date createdOn;
	private String content;

	public boolean sendNotification(Account account);
}

