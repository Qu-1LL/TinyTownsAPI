package tt.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tt.*;
import tt.buildings.*;

public class Game extends ListenerAdapter {

    private final JDA jda;
    private final String textChannelName;
    private int playerCount;
    private Player[] players;

    public Building cottage;
    public Building yellow;
    public Building orange;
    public Building red;
    public Building green;
    public Building navy;
    public Building grey;


    public Game (JDA jda, String textChannelName) {
        this.jda = jda;
        this.textChannelName = textChannelName;
        jda.addEventListener(this);
    }


}
