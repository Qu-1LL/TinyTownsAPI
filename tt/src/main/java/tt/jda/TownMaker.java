package tt.jda;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tt.*;


public class TownMaker extends ListenerAdapter {

    private final JDA jda;
    private final String textChannelName;
    private int players;
    private final String[] waitForMessage1 = {"tt","make"};
    private final String[] waitForMessage2 = {"tt","build"};
    private final String[] waitForMessage3 = {"tt","resource"};
    private final String[] waitForMessage4 = {"tt","towns"};
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
    private void buildTown(int x, int y, int index) {
        towns[index].build(x-1,y-1);
    }
    private void addResource(int x, int y, int index) {
        towns[index].getTile(x-1,y-1).setResource(Resource.BRICK);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        MessageChannel eChannel = event.getChannel();
        String[] parsedmessage = message.split(" ");

        if (parsedmessage[0].equalsIgnoreCase(waitForMessage1[0]) && parsedmessage[1].equalsIgnoreCase(waitForMessage1[1])) {
            
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
        if (parsedmessage[0].equalsIgnoreCase(waitForMessage2[0]) && parsedmessage[1].equalsIgnoreCase(waitForMessage2[1])) {
            
            if (event.isFromGuild() && event.getGuild().getTextChannelsByName(textChannelName, true).size() > 0) {
                MessageChannel homeChannel = event.getGuild().getTextChannelsByName(textChannelName, true).get(0);
                
                int index = Integer.parseInt(parsedmessage[4])-1;
                buildTown(Integer.parseInt(parsedmessage[2]),Integer.parseInt(parsedmessage[3]),index);
                homeChannel.sendMessage("Town: " + index);
                homeChannel.sendMessage(towns[index].toString()).queue();
            } else {
                eChannel.sendMessage("Error").queue();
            }
        }
        if (parsedmessage[0].equalsIgnoreCase(waitForMessage3[0]) && parsedmessage[1].equalsIgnoreCase(waitForMessage3[1])) {
            
            if (event.isFromGuild() && event.getGuild().getTextChannelsByName(textChannelName, true).size() > 0) {
                MessageChannel homeChannel = event.getGuild().getTextChannelsByName(textChannelName, true).get(0);
                
                int index = Integer.parseInt(parsedmessage[4])-1;
                addResource(Integer.parseInt(parsedmessage[2]),Integer.parseInt(parsedmessage[3]),index);
                homeChannel.sendMessage("Town: " + index);
                homeChannel.sendMessage(towns[index].toString()).queue();
            } else {
                eChannel.sendMessage("Error").queue();
            }
        }
        if (parsedmessage[0].equalsIgnoreCase(waitForMessage4[0]) && parsedmessage[1].equalsIgnoreCase(waitForMessage4[1])) {
            
            if (event.isFromGuild() && event.getGuild().getTextChannelsByName(textChannelName, true).size() > 0) {
                MessageChannel homeChannel = event.getGuild().getTextChannelsByName(textChannelName, true).get(0);

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