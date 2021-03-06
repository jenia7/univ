package by.bsu.chat.action;

import by.bsu.chat.entity.Message;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HistoryAction {
    public void printMessages(Collection<Message> messages) {
        if (messages.isEmpty()) {
            System.out.println("Empty");
            return;
        }

        for (Message message : messages) {
            System.out.println(message);
            System.out.println();
        }
    }

    public boolean removeById(int id, Collection<Message> messages) {
        return messages.removeIf(m -> m.getId() == id);
    }

    public Collection<Message> authorSearch(String author, Collection<Message> messages) {
        Collection<Message> deque = new ArrayDeque<>();
        for (Message message : messages) {
            if (message.getAuthor().equals(author)) {
                deque.add(message);
            }
        }
        return deque;
    }

    public Collection<Message> regexSearch(String regex, Collection<Message> messages) {
        Collection<Message> deque = new ArrayDeque<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        String string;
        for (Message message : messages) {
            string = message.getText();
            if (string != null) {
                matcher = pattern.matcher(string);
                if (matcher.find()) {
                    deque.add(message);
                }
            }
        }
        return deque;
    }

    public Collection<Message> lexemeSearch(String lexeme, Collection<Message> messages) {
        return regexSearch(lexeme, messages);
    }

    public Collection<Message> periodSearch(Collection<Message> messages,long t1, long t2) {
        Collection<Message> deque = new ArrayDeque<>();
        long timestamp;
        for (Message message : messages) {
            timestamp = message.getTimestamp();
            if (timestamp >= t1 && timestamp <= t2) {
                deque.add(message);
            }
        }
        return deque;
    }
}
