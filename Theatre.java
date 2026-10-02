public class Theatre {

    int id;
    String name;
    String location;

    public Theatre(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public void displayTheatre() {
        System.out.println(
            id + ". " + name + " - " + location
        );
    }
}