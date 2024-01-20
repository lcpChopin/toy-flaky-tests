package in.natelev.toyflakytests;

// see Figure 5 of RootFinder paper https://cs.gmu.edu/~winglam/publications/2019/LamETAL19RootFinder.pdf
public class SystemTime {
    public static class Message {
        private long time;
        private String message;

        Message(String message) {
            this.time = System.currentTimeMillis();
            this.message = message;
        }

        public boolean equals(Message otherMessage) {
            return this.time == otherMessage.time && this.message.equals(otherMessage);
        }
    }

    public static Message getMessage(String message) {
        return new SystemTime.Message(message);
    }

    public static Message getReplicaMessage(String message) {
        return new SystemTime.Message(message);
    }
}
