public class Message {
    private String senderName;
    private String messageText;

    public Message(String senderName, String messageText) {
        this.senderName = senderName;
        this.messageText = messageText;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getMessageText() {
        return messageText;
    }

    @Override
    public String toString() {
        return senderName + ": " + messageText;
    }
}
