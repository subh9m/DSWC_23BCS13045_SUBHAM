//Qn 1.
abstract class SpaceVessel {
    short shipId;
    boolean isOperational;
    char classification;

    SpaceVessel(short shipId, boolean isOperational, char classification) {
        this.shipId = shipId;
        this.isOperational = isOperational;
        this.classification = classification;
    }
}

class MiningShip extends SpaceVessel {

    float[][] cargoHold;  // 2D array: bays × containers

    MiningShip(short shipId, boolean isOperational, char classification, int bays, int containers) {
        super(shipId, isOperational, classification);
        cargoHold = new float[bays][containers];
    }

        public float calculateTotalOreWeight() {
        float total = 0;

        for (int i = 0; i < cargoHold.length; i++) {
            for (int j = 0; j < cargoHold[i].length; j++) {
                total += cargoHold[i][j];
            }
        }

        return total;
    }

        public float findHeaviestContainer() {
        float maxWeight = 0;

        for (int i = 0; i < cargoHold.length; i++) {
            for (int j = 0; j < cargoHold[i].length; j++) {
                if (cargoHold[i][j] > maxWeight) {
                    maxWeight = cargoHold[i][j];
                }
            }
        }

        return maxWeight;
    }
}

public class Main {
    public static void main(String[] args) {

        SpaceVessel[] fleet = new SpaceVessel[3];

        fleet[0] = new MiningShip((short)101, true, 'A', 2, 3);
        fleet[1] = new MiningShip((short)102, false, 'B', 2, 2);
        fleet[2] = new MiningShip((short)103, true, 'C', 3, 3);

        MiningShip ship = (MiningShip) fleet[0];

        ship.cargoHold[0][0] = 120.5f;
        ship.cargoHold[0][1] = 300.0f;
        ship.cargoHold[1][2] = 450.75f;

        System.out.println("Total Ore: " + ship.calculateTotalOreWeight());
        System.out.println("Heaviest Container: " + ship.findHeaviestContainer());
    }
}

//Qn 2.
package Q2;

class PowerNode {

    byte sectorStates = 0; // all OFF initially

    public void turnOnSector(int sectorIndex) {
        sectorStates = (byte)(sectorStates | (1 << sectorIndex));
    }

    public void turnOffSector(int sectorIndex) {
        sectorStates = (byte)(sectorStates & ~(1 << sectorIndex));
    }

    public boolean isSectorOn(int sectorIndex) {
        return (sectorStates & (1 << sectorIndex)) != 0;
    }
}

public class Main {
    public static void main(String[] args) {

        PowerNode node = new PowerNode();

        node.turnOnSector(0);
        node.turnOnSector(3);
        node.turnOnSector(7);

        System.out.println(node.isSectorOn(3)); // true
        System.out.println(node.isSectorOn(2)); // false

        node.turnOffSector(3);

        System.out.println(node.isSectorOn(3)); // false
    }
}

//Qn 3.

package Q3;
class DNASequencer {

    StringBuilder dna;

    DNASequencer(int capacity) {
        dna = new StringBuilder(capacity);
    }

    public void ingestSequence(char[] sensorData) {
        for (int i = 0; i < sensorData.length; i++) {
            dna.append(sensorData[i]);
        }
    }

    public void mutateDNA(String target, String replacement) {
        int index = dna.indexOf(target);

        if (index != -1) {
            dna.replace(index, index + target.length(), replacement);
        }
    }

    public void printSequence() {
        System.out.println(dna.toString());
    }
}

public class Main {
    public static void main(String[] args) {

        DNASequencer seq = new DNASequencer(100000);

        char[] data = {'A','C','G','T','A','C','G','T','A','C','G'};

        seq.ingestSequence(data);

        System.out.print("Before Mutation: ");
        seq.printSequence();

        seq.mutateDNA("ACG", "TTT");

        System.out.print("After Mutation: ");
        seq.printSequence();
    }
}

//Qn 4.
package Q4;

class HardwareLockException extends Exception {
    public HardwareLockException(String message) {
        super(message);
    }
}

class SensorCorruptionException extends RuntimeException {
    public SensorCorruptionException(String message) {
        super(message);
    }
}

class TelemetryStream implements AutoCloseable {

    public void readData() {
        System.out.println("Reading telemetry data...");
    }

    @Override
    public void close() {
        System.out.println("TelemetryStream closed safely.");
    }
}


public class Main {

    public static void parseTelemetry() throws HardwareLockException {

        try (TelemetryStream stream = new TelemetryStream()) {

            stream.readData();

            // Simulating sensor issues
            int temperature = 500;

            if (temperature > 100) {
                throw new SensorCorruptionException("Sensor reading invalid!");
            }

            // Simulating hardware issue
            boolean fileLocked = true;

            if (fileLocked) {
                throw new HardwareLockException("File is locked by OS!");
            }

            System.out.println("Telemetry processed successfully.");
        }
    }

        public static void main(String[] args) {

        try {
            parseTelemetry();
        }
        catch (HardwareLockException e) {
            System.out.println("FATAL ERROR: " + e.getMessage());
        }
        catch (SensorCorruptionException e) {
            System.out.println("WARNING: " + e.getMessage());
        }
    }
}
