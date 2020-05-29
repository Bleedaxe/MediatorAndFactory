package pu.fmi.mediatorandfactory.data;

import pu.fmi.mediatorandfactory.data.exception.InvalidUserException;
import pu.fmi.mediatorandfactory.data.io.Writer;

import java.util.*;

public class ChatRoom {
    private static final String SYSTEM_USER = "System";
    private static final List<String> SYSTEM_USERS = Collections.unmodifiableList(Arrays.asList(
            SYSTEM_USER,
            ChatBot.INSTANCE.getName()
    ));

    private static final String ADD_BOT_COMMAND_TEXT = "addBot";
    private static final String REMOVE_USER_MESSAGE_FORMAT = "User %s is removed from the chat room.";

    private final Writer writer;
    private final Map<String, User> users;
    private boolean isBotActive;

    public ChatRoom(Writer writer) {
        this.writer = writer;
        users = new HashMap<>();

        this.isBotActive = false;
    }

    public Collection<User> getUsers() {
        return new ArrayList<>(this.users.values());
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public void removeUser(String username) {
        validateUser(username);

        writeMessage(SYSTEM_USER, String.format(REMOVE_USER_MESSAGE_FORMAT, username));
        users.remove(username);
    }

    //This should be called from main
    public void writeMessage(String username, String message) {
        validateUser(username);

        if (isAddBotCommand(message)) {
            isBotActive = true;
            writer.writeLine("ChatBot activated!!!");
            return;
        }

        writer.writeLine(String.format(">> [%s]: %s",username, message));
        validateMessage(username, message);
    }

    private void validateMessage(String username, String message) {
        if (isBotActive) {
            ChatBot.INSTANCE.validateMessage(username, message, this);
        }
    }

    private void validateUser(String username) {
        if (!this.users.containsKey(username) && !SYSTEM_USERS.contains(username)) {
            throw new InvalidUserException(username);
        }
    }

    private boolean isAddBotCommand(String messageText) {
        return messageText.equals(ADD_BOT_COMMAND_TEXT);
    }
}
