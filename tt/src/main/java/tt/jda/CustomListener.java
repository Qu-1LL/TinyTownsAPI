package tt.jda;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class CustomListener extends ListenerAdapter {

    private final JDA jda;
    private final String textChannelName;
    private final String messageToSend;
    private final String waitForMessage;

    public CustomListener(JDA jda, String textChannelName, String messageToSend, String waitForMessage) {
        this.jda = jda;
        this.textChannelName = textChannelName;
        this.messageToSend = messageToSend;
        this.waitForMessage = waitForMessage;
        jda.addEventListener(this);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        MessageChannel eChannel = event.getChannel();

        if (message.equalsIgnoreCase(waitForMessage)) {
            
            if (event.isFromGuild() && event.getGuild().getTextChannelsByName(textChannelName, true).size() > 0) {
                MessageChannel homeChannel = event.getGuild().getTextChannelsByName(textChannelName, true).get(0);
                homeChannel.sendMessage(messageToSend).queue();
            } else {
                eChannel.sendMessage("Error").queue();
            }
        }
    }
}