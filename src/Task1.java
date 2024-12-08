import java.util.*;

class Passenger {
    private final int arrivalTime;

    public Passenger(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
}

class Boat {
    private final int arrivalTime;
    private final boolean isTerminal;
    private final int freeSeats;

    public Boat(int arrivalTime, boolean isTerminal, int freeSeats) {
        this.arrivalTime = arrivalTime;
        this.isTerminal = isTerminal;
        this.freeSeats = freeSeats;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public int getFreeSeats() {
        return freeSeats;
    }
}

class DockSimulation {
    private final Queue<Passenger> passengerQueue;
    private final Queue<Boat> boatQueue;
    private final Random random;

    public DockSimulation() {
        passengerQueue = new LinkedList<>();
        boatQueue = new LinkedList<>();
        random = new Random();
    }

    public void generatePassengers(int simulationTime, int[] passengerArrivalRates) {
        for (int time = 0; time < simulationTime; time++) {
            int rate = passengerArrivalRates[time % passengerArrivalRates.length];
            if (random.nextInt(rate) == 0) {
                passengerQueue.offer(new Passenger(time));
            }
        }
    }

    public void generateBoats(int simulationTime, int[] boatArrivalRates, boolean[] terminalTypes) {
        for (int time = 0; time < simulationTime; time++) {
            int rate = boatArrivalRates[time % boatArrivalRates.length];
            if (random.nextInt(rate) == 0) {
                boolean isTerminal = terminalTypes[time % terminalTypes.length];
                int freeSeats = random.nextInt(50) + 1; // Random seats between 1 and 50
                boatQueue.offer(new Boat(time, isTerminal, freeSeats));
            }
        }
    }

    public double calculateAverageWaitingTime() {
        int totalWaitingTime = 0;
        int servedPassengers = 0;

        while (!boatQueue.isEmpty()) {
            Boat boat = boatQueue.poll();

            int boardingPassengers = Math.min(boat.getFreeSeats(), passengerQueue.size());
            for (int i = 0; i < boardingPassengers; i++) {
                Passenger passenger = passengerQueue.poll();
                totalWaitingTime += Math.max(0, boat.getArrivalTime() - passenger.getArrivalTime());
                servedPassengers++;
            }
        }

        return servedPassengers == 0 ? 0 : (double) totalWaitingTime / servedPassengers;
    }

    public int calculateOptimalBoatInterval(int maxPassengers, int[] boatArrivalRates) {
        int interval = 1;

        while (true) {
            Queue<Passenger> tempQueue = new LinkedList<>(passengerQueue);
            int maxQueueSize = 0;

            for (int time = 0; time < 1440; time += interval) { // Simulate for a day
                if (!tempQueue.isEmpty()) {
                    maxQueueSize = Math.max(maxQueueSize, tempQueue.size());
                }
                int freeSeats = random.nextInt(50) + 1;
                for (int i = 0; i < freeSeats && !tempQueue.isEmpty(); i++) {
                    tempQueue.poll();
                }
            }

            if (maxQueueSize <= maxPassengers) {
                break;
            }
            interval++;
        }

        return interval;
    }
}
