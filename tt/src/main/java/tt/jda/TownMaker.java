package tt.jda;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tt.*;
import tt.buildings.*;


public class TownMaker extends ListenerAdapter {

    private final JDA jda;
    private final String textChannelName;
    private int players;
    private final String caller = "tt";
    private final String command1 = "make";
    private final String command2 = "build";
    private final String command3 = "resource";
    private final String command4 = "towns";
    private final String command5 = "score";
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
    private void buildTown(String type, int x, int y, int index) {
        if (type.equalsIgnoreCase("cottage")) {
            towns[index].build(x-1,y-1,new Cottage());
        }
        if (type.equalsIgnoreCase("monument")) {
            towns[index].build(x-1,y-1,new UniqueBuilding(BuildingType.MONUMENT));
        }
        if (type.equalsIgnoreCase("yellow")) {
            towns[index].build(x-1,y-1,new UniqueBuilding(BuildingType.YELLOW));
        }
        if (type.equalsIgnoreCase("orange")) {
            towns[index].build(x-1,y-1,new Farm());
        }
        if (type.equalsIgnoreCase("red")) {
            towns[index].build(x-1,y-1,new UniqueBuilding(BuildingType.RED));
        }
        if (type.equalsIgnoreCase("navy")) {
            towns[index].build(x-1,y-1,new UniqueBuilding(BuildingType.NAVY));
        }
        if (type.equalsIgnoreCase("green")) {
            towns[index].build(x-1,y-1,new UniqueBuilding(BuildingType.GREEN));
        }
        if (type.equalsIgnoreCase("grey")) {
            towns[index].build(x-1,y-1,new UniqueBuilding(BuildingType.GREY));
        }
        
    }
    private void addResource(String resource, int x, int y, int index) {
        if (resource.equalsIgnoreCase("brick")) {
            towns[index].getTile(x-1,y-1).setResource(Resource.BRICK);
        }
        if (resource.equalsIgnoreCase("glass")) {
            towns[index].getTile(x-1,y-1).setResource(Resource.GLASS);
        }
        if (resource.equalsIgnoreCase("stone")) {
            towns[index].getTile(x-1,y-1).setResource(Resource.STONE);
        }
        if (resource.equalsIgnoreCase("wheat")) {
            towns[index].getTile(x-1,y-1).setResource(Resource.WHEAT);
        }
        if (resource.equalsIgnoreCase("wood")) {
            towns[index].getTile(x-1,y-1).setResource(Resource.WOOD);
        }

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        MessageChannel eChannel = event.getChannel();
        String[] parsedmessage = message.split(" ");

        if (parsedmessage[0].equalsIgnoreCase(caller) && parsedmessage[1].equalsIgnoreCase(command1)) {
            
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
        if (parsedmessage[0].equalsIgnoreCase(caller) && parsedmessage[1].equalsIgnoreCase(command2)) {
            
            if (event.isFromGuild() && event.getGuild().getTextChannelsByName(textChannelName, true).size() > 0) {
                MessageChannel homeChannel = event.getGuild().getTextChannelsByName(textChannelName, true).get(0);
                
                int index = Integer.parseInt(parsedmessage[5])-1;
                buildTown((String)parsedmessage[2],Integer.parseInt(parsedmessage[3]),Integer.parseInt(parsedmessage[4]),index);
                homeChannel.sendMessage("Town: " + index);
                homeChannel.sendMessage(towns[index].toString()).queue();
            } else {
                eChannel.sendMessage("Error").queue();
            }
        }
        if (parsedmessage[0].equalsIgnoreCase(caller) && parsedmessage[1].equalsIgnoreCase(command3)) {
            
            if (event.isFromGuild() && event.getGuild().getTextChannelsByName(textChannelName, true).size() > 0) {
                MessageChannel homeChannel = event.getGuild().getTextChannelsByName(textChannelName, true).get(0);
                
                int index = Integer.parseInt(parsedmessage[5])-1;
                addResource((String)parsedmessage[2],Integer.parseInt(parsedmessage[3]),Integer.parseInt(parsedmessage[4]),index);
                homeChannel.sendMessage("Town: " + index);
                homeChannel.sendMessage(towns[index].toString()).queue();
            } else {
                eChannel.sendMessage("Error").queue();
            }
        }
        if (parsedmessage[0].equalsIgnoreCase(caller) && parsedmessage[1].equalsIgnoreCase(command4)) {
            
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
        if (parsedmessage[0].equalsIgnoreCase(caller) && parsedmessage[1].equalsIgnoreCase(command5)) {
            
            if (event.isFromGuild() && event.getGuild().getTextChannelsByName(textChannelName, true).size() > 0) {
                MessageChannel homeChannel = event.getGuild().getTextChannelsByName(textChannelName, true).get(0);

                for (int i = 0;i < players;i++) {
                    homeChannel.sendMessage("Town: " + (i + 1) + " Score: " + towns[i].score()).queue();
                    homeChannel.sendMessage(towns[i].toString()).queue();
                }
            } else {
                eChannel.sendMessage("Error").queue();
            }
        }
    }
}