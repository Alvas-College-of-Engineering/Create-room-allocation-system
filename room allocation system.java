import java.util.ArrayList;
import java.util.List;

public class Hospital {

    // -------- Room Class --------
    static class Room {
        private int roomNumber;
        private String roomType;
        private boolean isAvailable;

        public Room(int roomNumber, String roomType) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.isAvailable = true;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public String getRoomType() {
            return roomType;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void allocateRoom() {
            isAvailable = false;
        }
    }

    // -------- Patient Class --------
    static class Patient {
        private int patientId;
        private String patientName;
        private String requiredRoomType;

        public Patient(int patientId, String patientName, String requiredRoomType) {
            this.patientId = patientId;
            this.patientName = patientName;
            this.requiredRoomType = requiredRoomType;
        }

        public int getPatientId() {
            return patientId;
        }

        public String getPatientName() {
            return patientName;
        }

        public String getRequiredRoomType() {
            return requiredRoomType;
        }
    }

    // -------- Allocation Class --------
    static class Allocation {
        private Patient patient;
        private Room room;

        public Allocation(Patient patient, Room room) {
            this.patient = patient;
            this.room = room;
        }

        public Patient getPatient() {
            return patient;
        }

        public Room getRoom() {
            return room;
        }
    }

    // -------- Hospital Data --------
    private List<Room> rooms = new ArrayList<>();
    private List<Allocation> allocations = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void allocateRoomToPatient(Patient patient) {
        for (Room room : rooms) {
            if (room.isAvailable() &&
                room.getRoomType().equalsIgnoreCase(patient.getRequiredRoomType())) {

                room.allocateRoom();
                allocations.add(new Allocation(patient, room));
                System.out.println("Room allocated to " + patient.getPatientName());
                return;
            }
        }
        System.out.println("No available room for " + patient.getPatientName());
    }

    public void displayAllAllocations() {
        if (allocations.isEmpty()) {
            System.out.println("No allocations found.");
            return;
        }

        System.out.println("\n--- All Room Allocations ---");
        for (Allocation allocation : allocations) {
            System.out.println("Patient ID: " + allocation.getPatient().getPatientId());
            System.out.println("Patient Name: " + allocation.getPatient().getPatientName());
            System.out.println("Room Number: " + allocation.getRoom().getRoomNumber());
            System.out.println("Room Type: " + allocation.getRoom().getRoomType());
            System.out.println("-----------------------------");
        }
    }

    // -------- MAIN METHOD --------
    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        // Add rooms
        hospital.addRoom(new Room(101, "General"));
        hospital.addRoom(new Room(102, "General"));
        hospital.addRoom(new Room(201, "Private"));
        hospital.addRoom(new Room(202, "Semi-Private"));

        // Create patients
        Patient p1 = new Patient(1, "John", "General");
        Patient p2 = new Patient(2, "Alice", "Private");
        Patient p3 = new Patient(3, "Bob", "General");

        // Allocate rooms
        hospital.allocateRoomToPatient(p1);
        hospital.allocateRoomToPatient(p2);
        hospital.allocateRoomToPatient(p3);

        // Display allocations
        hospital.displayAllAllocations();
    }
}