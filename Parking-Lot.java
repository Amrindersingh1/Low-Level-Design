/* Requirements:
*	1. Parking lot: address, multiple floors
*	2. Entry gate : multiple, issue parking ticket, isfull screen, parking attendant
* 3. Exit gate : multiple, process ticket, payment, parking attendant
*	4. Parking Floor : Parking spots, info screen about available spots
*	5. Parking Attendants : issue ticket, process ticket
*	6. Parking spot: MOTORBIKE, SMALL, LARGE, ELECTRIC, HANDICAP
*	7. Vehicles: 2 wheeler, car, van/truck/bus, electric
*	8. Payment: cash/card, hourly based model
*/

/* Actors:
*	1. Admin : login/logout, add/modify gates, add/modify floors, add/modify spots, add/modify attendant , add/modify rates
*	2. Attendant : login/logout, process ticket, process payment
*	3. Customer : take ticket, payment(cash/credit)
*	4. System : Assign a parking spot, remove vehicle from parking spot, show parking status, show available spots , issue ticket
*/

// Constants

public enum ParkingSpotType {
	MOTORBIKE, SMALL, LARGE, ELECTRIC, HANDICAPPED
}

public enum VehicleType {
	MOTORBIKE, CAR, VAN, ELECTRIC, HANDICAPPED
}

public class Address {
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String country;
}

public class Person {
	private String name;
	private Address address;
	private String email;
	private String phone;
}


// Accounts/Admin
public abstract class Account {
	private String userName;
	private String password;
	private AccountStatus status;
	private Person person;

	public boolean Login();
	public boolean Logout();
	public boolean resetPassword();
	
}

public class Admin extends Account{
	public bool addEntrance(Entrance entrance);
	public bool addExit(Exit exit);
	public bool addParkingFloor(ParkingFloor floor);
	public bool addParkingSpot(String floorName, ParkingSpot spot);
	public bool addParkingDisplayBoard(String floorName, ParkingDisplayBoard displayBoard);
	public bool addCustomerInfoPanel(String floorName, CustomerInfoPanel infoPanel);
	
	public bool updateEntrance(Entrance entrance);
	public bool updateExit(Exit exit);
	public bool updateParkingFloor(ParkingFloor floor);
	public bool updateParkingSpot(String floorName, ParkingSpot spot);
	public bool updateParkingDisplayBoard(String floorName, ParkingDisplayBoard displayBoard);
	public bool updateCustomerInfoPanel(String floorName, CustomerInfoPanel infoPanel);
}

public class ParkingAttendant extends Account{
	public boolean processTicket(ParkingTicket ticket);
}


// Vehicles
public abstract class Vehicle {
	private String licenseNumber;
	private final VehicleType type;
	private ParkingTicket ticket;
	
	public Vehicle(VehicleType type) {
		this.type = type;
	}
}

public class Car extends Vehicle {
  public Car() {
    super(VehicleType.CAR);
  }
}

public class Van extends Vehicle {
  public Van() {
    super(VehicleType.VAN);
  }
}

public class Truck extends Vehicle {
  public Truck() {
    super(VehicleType.TRUCK);
  }
}

// Parking Spot
public abstarct class ParkingSpot {
	private String number;
	private boolean free;
	private Vehicle vehicle;
	private final ParkingSpotType type;
	
	public ParkingSpot(ParkingSpotType type) {
		this.type = type;
	}
	
	public boolean assignVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		free = false;
	  }
	
	public boolean removeVehicle(Vehicle vehicle) {
		this.vehicle = null;
		this.free = true;
	}
	
}

public class HandicappedSpot extends ParkingSpot {
  public HandicappedSpot() {
    super(ParkingSpotType.HANDICAPPED);
  }
}

public class SmallSpot extends ParkingSpot {
  public SmallSpot() {
    super(ParkingSpotType.SMALL);
  }
}

public class LargeSpot extends ParkingSpot {
  public LargeSpot() {
    super(ParkingSpotType.LARGE);
  }
}

public class MotorbikeSpot extends ParkingSpot {
  public MotorbikeSpot() {
    super(ParkingSpotType.MOTORBIKE);
  }
}

public class ElectricSpot extends ParkingSpot {
  public ElectricSpot() {
    super(ParkingSpotType.ELECTRIC);
  }
}


// Parking Floor : Parking spots, info screen about available spots

public class ParkingFloor{
	
	private String name;
	private List<HandicappedSpot> handicappedSpots;
	private List<SmallSpot> smallSpots;
	private List<LargeSpot> largeSpots;
	private List<MotorbikeSpot> motorbikeSpots;
	private List<ElectricSpot> electricSpots;
	private ParkingDisplayBoard displayBoard;

	public ParkingFloor(String name) {
		this.name = name;
	}
	
	public void addParkingSpot(ParkingSpot spot) {
		switch (spot.getType()) {
			case ParkingSpotType.HANDICAPPED:
			  handicappedSpots.add(spot);
			  break;
			case ParkingSpotType.SMALL:
			  smallSpots.add(spot);
			  break;
			case ParkingSpotType.LARGE:
			  largeSpots.add(spot);
			  break;
			case ParkingSpotType.MOTORBIKE:
			  motorbikeSpots.add(spot);
			  break;
			case ParkingSpotType.ELECTRIC:
			  electricSpots.add(spot);
			  break;
			default:
			  print("Wrong parking spot type!");
		}
	}
	
	public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
		
		spot.assignVehicle(vehicle);
		displayBoard.updateDisplayBoard(spot);
			  
	}	
	

	public void freeSpot(ParkingSpot spot) {
		spot.removeVehicle();
		displayBoard.updateDisplayBoard(spot);
	}
}

public class ParkingDisplayBoard {
	
	private String id;
	private HandicappedSpot handicappedFreeSpot;
	private SmallSpot smallFreeSpot;
	private LargeSpot largeFreeSpot;
	private MotorbikeSpot motorbikeFreeSpot;
	private ElectricSpot electricFreeSpot;

	private void updateDisplayBoard(ParkingSpot spot) {
		int count = spot.isFree()1:-1;
		switch (spot.getType()) {
			case ParkingSpotType.HANDICAPPED:
			  handicappedFreeSpot+=count;
			  break;
			case ParkingSpotType.SMALL:
			  smallFreeSpot+=count;
			  break;
			case ParkingSpotType.LARGE:
			  LargeSpot+=count;
			  break;
			case ParkingSpotType.MOTORBIKE:
			  MotorbikeSpot+=count;
			  break;
			case ParkingSpotType.ELECTRIC:
			  ElectricSpot+=count;
			  break;
			default:
			  print("Wrong parking spot type!");
		}
	}

}

// Parking Lot
public class ParkingLot {
  private String name;
  private Location address;
  private ParkingRate parkingRate;

  private int compactSpotCount;
  private int largeSpotCount;
  private int motorbikeSpotCount;
  private int electricSpotCount;
  private final int maxCompactCount;
  private final int maxLargeCount;
  private final int maxMotorbikeCount;
  private final int maxElectricCount;

  private HashMap<String, EntrancePanel> entrancePanels;
  private HashMap<String, ExitPanel> exitPanels;
  private HashMap<String, ParkingFloor> parkingFloors;

  // all active parking tickets, identified by their ticketNumber
  private HashMap<String, ParkingTicket> activeTickets;

  // singleton ParkingLot to ensure only one object of ParkingLot in the system,
  // all entrance panels will use this object to create new parking ticket: getNewParkingTicket(),
  // similarly exit panels will also use this object to close parking tickets
  private static ParkingLot parkingLot = null;

  // private constructor to restrict for singleton
  private ParkingLot() {
    // 1. initialize variables: read name, address and parkingRate from database
    // 2. initialize parking floors: read the parking floor map from database,
    //  this map should tell how many parking spots are there on each floor. This
    //  should also initialize max spot counts too.
    // 3. initialize parking spot counts by reading all active tickets from database
    // 4. initialize entrance and exit panels: read from database
  }

  // static method to get the singleton instance of StockExchange
  public static ParkingLot getInstance() {
    if (parkingLot == null) {
      parkingLot = new ParkingLot();
    }
    return parkingLot;
  }

  // note that the following method is 'synchronized' to allow multiple entrances
  // panels to issue a new parking ticket without interfering with each other
  public synchronized ParkingTicket getNewParkingTicket(Vehicle vehicle) throws ParkingFullException {
    if (this.isFull(vehicle.getType())) {
      throw new ParkingFullException();
    }
    ParkingTicket ticket = new ParkingTicket();
    vehicle.assignTicket(ticket);
    ticket.saveInDB();
    // if the ticket is successfully saved in the database, we can increment the parking spot count
    this.incrementSpotCount(vehicle.getType());
    this.activeTickets.put(ticket.getTicketNumber(), ticket);
    return ticket;
  }

  public boolean isFull(VehicleType type) {
    // trucks and vans can only be parked in LargeSpot
    if (type == VehicleType.Truck || type == VehicleType.Van) {
      return largeSpotCount >= maxLargeCount;
    }

    // motorbikes can only be parked at motorbike spots
    if (type == VehicleType.Motorbike) {
      return motorbikeSpotCount >= maxMotorbikeCount;
    }

    // cars can be parked at compact or large spots
    if (type == VehicleType.Car) {
      return (compactSpotCount + largeSpotCount) >= (maxCompactCount + maxLargeCount);
    }

    // electric car can be parked at compact, large or electric spots
    return (compactSpotCount + largeSpotCount + electricSpotCount) >= (maxCompactCount + maxLargeCount
        + maxElectricCount);
  }

  // increment the parking spot count based on the vehicle type
  private boolean incrementSpotCount(VehicleType type) {
    if (type == VehicleType.Truck || type == VehicleType.Van) {
      largeSpotCount++;
    } else if (type == VehicleType.Motorbike) {
      motorbikeSpotCount++;
    } else if (type == VehicleType.Car) {
      if (compactSpotCount < maxCompactCount) {
        compactSpotCount++;
      } else {
        largeSpotCount++;
      }
    } else { // electric car
      if (electricSpotCount < maxElectricCount) {
        electricSpotCount++;
      } else if (compactSpotCount < maxCompactCount) {
        compactSpotCount++;
      } else {
        largeSpotCount++;
      }
    }
  }

  public boolean isFull() {
    for (String key : parkingFloors.keySet()) {
      if (!parkingFloors.get(key).isFull()) {
        return false;
      }
    }
    return true;
  }

  public void addParkingFloor(ParkingFloor floor) {
    /* store in database */ }

  public void addEntrancePanel(EntrancePanel entrancePanel) {
    /* store in database */ }

  public void addExitPanel(ExitPanel exitPanel) {
    /* store in database */ }
}
