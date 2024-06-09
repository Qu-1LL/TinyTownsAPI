package tt.jda;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tt.*;
import tt.buildings.*;
import tt.buildings.green.Tavern;
import tt.buildings.grey.Well;
import tt.buildings.navy.Factory;
import tt.buildings.orange.Chapel;
import tt.buildings.red.Farm;
import tt.buildings.yellow.Theatre;


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
    private final String command6 = "rules";
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
        if (type.equalsIgnoreCase("theatre")) {
            towns[index].build(x-1,y-1,new Theatre());
        }
        if (type.equalsIgnoreCase("chapel")) {
            towns[index].build(x-1,y-1,new Chapel());
        }
        if (type.equalsIgnoreCase("farm")) {
            towns[index].build(x-1,y-1,new Farm());
        }
        if (type.equalsIgnoreCase("factory")) {
            towns[index].build(x-1,y-1,new Factory());
        }
        if (type.equalsIgnoreCase("tavern")) {
            towns[index].build(x-1,y-1,new Tavern());
        }
        if (type.equalsIgnoreCase("well")) {
            towns[index].build(x-1,y-1,new Well());
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
        if (parsedmessage[0].equalsIgnoreCase(caller) && parsedmessage[1].equalsIgnoreCase(command6)) {
            
            if (event.isFromGuild() && event.getGuild().getTextChannelsByName(textChannelName, true).size() > 0) {
                MessageChannel homeChannel = event.getGuild().getTextChannelsByName(textChannelName, true).get(0);

                Building[] b = new Building[7];
                b[0] = new Well();
                b[1] = new Theatre();
                b[2] = new Tavern();
                b[3] = new Farm();
                b[4] = new Cottage();
                b[5] = new Chapel();
                b[6] = new Factory();
                for (Building build : b) {
                    homeChannel.sendMessage(build.toString()).queue();
                }
            } else {
                eChannel.sendMessage("Error").queue();
            }
        }
    }
}