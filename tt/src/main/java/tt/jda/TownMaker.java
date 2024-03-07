package tt.jda;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tt.Town;


public class TownMaker extends ListenerAdapter {

    private final JDA jda;
    private final String textChannelName;
    private int players;
    private final String[] waitForMessage = {"tt","make"};
    private Town[] towns;

    public TownMaker(JDA jda, String textChannelName) {
        this.jda = jda;
        this.textChannelName = textChannelName;
        jda.addEventListener(this);
    }

    private void fillTowns(Integer player) {
        this.players = (int)player;
        this.towns = new Town[players];
        for (int i = 0; i < players; i++) {
            towns[i] = new Town(4);
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        MessageChannel eChannel = event.getChannel();
        String[] parsedmessage = message.split(" ");

        if (parsedmessage[0].equalsIgnoreCase(waitForMessage[0]) && parsedmessage[1].equalsIgnoreCase(waitForMessage[1])) {
            
            if (event.isFromGuild() && event.getGuild().getTextChannelsByName(textChannelName, true).size() > 0) {
                MessageChannel homeChannel = event.getGuild().getTextChannelsByName(textChannelName, true).get(0);
                fillTowns(Integer.parseInt(parsedmessage[2]));
                for (int i = 0;i < players;i++) {
                    homeChannel.sendMessage("Town: " + (i + 1)).queue();
                    homeChannel.sendMessage(towns[i].toString()).queue();
                }
            } else {
                eChannel.sendMessage("Error").queue();
            }
        }
    }
}