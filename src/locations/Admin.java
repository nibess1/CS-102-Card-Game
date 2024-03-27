package locations;


public class Admin extends Location {
    public Admin() {
        super.setName("Admin Building");
        super.setDescription("You can only place one card here.");
        super.setCardLimit(1);
    }
}
