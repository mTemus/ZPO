package Operations;

class TimeOperations {

    void showActionTime(double now, double previous) {

        double timeTimer = (now - previous) / 1000000000;

        System.out.println("Threads took time: " + timeTimer + "s.");
    }



}
