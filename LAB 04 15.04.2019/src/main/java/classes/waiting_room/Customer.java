package classes.waiting_room;

import java.util.Random;

public class Customer {
    private int id;
    private int placeInQueue;
    private String name;
    private String surname;
    private String illness;

    public String generatePlaceInQueue() {
        Random placeGenerator = new Random();
        String answer;

        setPlaceInQueue((placeGenerator.nextInt()));

        System.out.println(placeGenerator.nextInt());
        System.out.println(getPlaceInQueue());

        answer = "Place in queue is: " + placeInQueue;
        return answer;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceInQueue() {
        return placeInQueue;
    }

    public void setPlaceInQueue(int placeInQueue) {
        this.placeInQueue = placeInQueue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }
}
