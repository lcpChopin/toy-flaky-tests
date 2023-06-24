package in.natelev.toyflakytests;

import java.util.concurrent.ThreadLocalRandom;

public class Random {
    public static boolean randomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
