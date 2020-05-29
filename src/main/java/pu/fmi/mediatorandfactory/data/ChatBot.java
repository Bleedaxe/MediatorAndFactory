package pu.fmi.mediatorandfactory.data;

public enum ChatBot {
    INSTANCE;

    private static final String FORBIDDEN_WORD = "cat";
    private static final String FORBIDDEN_WORD_MESSAGE =
            String.format("Word \"%s\" is forbidden for this chat room!", FORBIDDEN_WORD);

    public String getName() {
        return "ChatBot";
    }

    public void validateMessage(String username, String message, ChatRoom chatRoom) {
        boolean isValid = !message.equals(FORBIDDEN_WORD);
        if (isValid) {
            return;
        }

        chatRoom.removeUser(username);
        chatRoom.writeMessage(this.getName(), FORBIDDEN_WORD_MESSAGE);
    }
}
