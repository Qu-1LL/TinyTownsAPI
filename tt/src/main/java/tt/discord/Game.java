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

    public Game (JDA jda, String textChannelName) {
        this.jda = jda;
        this.textChannelName = textChannelName;
        jda.addEventListener(this);
    }

}
