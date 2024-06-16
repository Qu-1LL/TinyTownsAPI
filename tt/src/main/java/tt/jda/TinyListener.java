package tt.jda;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import tt.*;

public class TinyListener extends ListenerAdapter {

    private final JDA jda;
    private final String textChannelName;
    private Game game;
    private int playerCount;

    private static String TT = "tt";
    private static String PLACE = "place";
    private static String BUILD = "build";
    private static String SPACE = " ";

}
