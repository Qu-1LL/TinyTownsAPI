package tt.jda;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.interaction.component.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import tt.*;

public class TTDiscord implements TT {

    private final JDA jda;
    private Game game;

    private static final String TT = "tt";
    private static final String PLACE = "place";
    private static final String BUILD = "build";
    private static final String DONE = "done";
    private static final long CHANNEL_ID = 1214007594151321701L;

    public TTDiscord (JDA jda, Game game) {
        this.jda = jda;
        this.game = game;
    }

    public class ResourceListener extends ListenerAdapter {
        Resource resource = null;
        boolean complete = false;
        private Resource[] options;
        private String name;

        public ResourceListener (String name, Resource[] options) {
            this.name = name;
            this.options = options;
            jda.addEventListener(this);
            
            if (options.length == 5) {
                jda.getTextChannelById(CHANNEL_ID).sendMessage(
                    name + " please select your resource:"
                ).addActionRow(
                    Button.secondary("0",Emoji.fromFormatted(options[0].toPrint())),
                    Button.secondary("1",Emoji.fromFormatted(options[1].toPrint())),
                    Button.secondary("2",Emoji.fromFormatted(options[2].toPrint())),
                    Button.secondary("3",Emoji.fromFormatted(options[3].toPrint())),
                    Button.secondary("4",Emoji.fromFormatted(options[4].toPrint()))
                ).queue();
            } else if (options.length == 4) {
                jda.getTextChannelById(CHANNEL_ID).sendMessage(
                    name + " please select your resource:"
                ).addActionRow(
                    Button.secondary("0",Emoji.fromFormatted(options[0].toPrint())),
                    Button.secondary("1",Emoji.fromFormatted(options[1].toPrint())),
                    Button.secondary("2",Emoji.fromFormatted(options[2].toPrint())),
                    Button.secondary("3",Emoji.fromFormatted(options[3].toPrint()))
                ).queue();
            } else if (options.length == 3) {
                jda.getTextChannelById(CHANNEL_ID).sendMessage(
                    name + " please select your resource:"
                ).addActionRow(
                    Button.secondary("0",Emoji.fromFormatted(options[0].toPrint())),
                    Button.secondary("1",Emoji.fromFormatted(options[1].toPrint())),
                    Button.secondary("2",Emoji.fromFormatted(options[2].toPrint()))
                ).queue();
            } else if (options.length == 2) {
                jda.getTextChannelById(CHANNEL_ID).sendMessage(
                    name + " please select your resource:"
                ).addActionRow(
                    Button.secondary("0",Emoji.fromFormatted(options[0].toPrint())),
                    Button.secondary("1",Emoji.fromFormatted(options[1].toPrint()))
                ).queue();
            } else if (options.length == 1) {
                resource = options[0];
                complete = true;
            } else if (options.length == 0) {
                complete = true;
            }
        }
        public ResourceListener (String name) {
            this(name,Resource.fullValues());
        }
        public Resource getResource() {
            return resource;
        }
        public boolean complete () {
            return complete;
        }
        @Override
        public void onButtonInteraction(ButtonInteractionEvent event) {
            if (event.getUser().getId() == name) {
                resource = options[Integer.parseInt(event.getComponentId())];
                complete = true;

                List<ItemComponent> actionRow = event.getMessage().getActionRows().get(0).getComponents();
                for (int i = 0; i < actionRow.size(); i++) {
                    actionRow.remove(i);
                }
                event.getMessage().editMessage(event.getMessage().getContentRaw()).setActionRow(actionRow).queue();
            }
        }
    }

    public class PlayerListener extends ListenerAdapter {
        HashSet<String> usernames;
        long messageId;
        boolean started;
        Thread thread;
        boolean complete;

        public PlayerListener () {
            this.usernames = new HashSet<String>();
            this.started = false;
            this.complete = false;
            jda.addEventListener(this);

            jda.getTextChannelById(CHANNEL_ID).sendMessage(
                    "Click this button to join the next Tiny Towns game!\n\n"
                    + "The next game starts soon!\n\n"
                    + "Players:"
                ).addActionRow(
                    Button.primary("join", "Join!")
                ).queue();
        }
        @Override
        public void onButtonInteraction(ButtonInteractionEvent event) {
            if (!started) {
                usernames.add(event.getUser().getId());

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run () {
                        started = true;
                        for (int i = 15; i > 0; i--) {
                            try {
                                wait(990);
                            } catch (InterruptedException e) {}
                            String joinMessage = "Click this button to join the next Tiny Towns game!\n\n"
                                            + "The next game starts in" + i + " seconds!\n\n"
                                            + "Players:";
                                            for (String name : usernames) {
                                                joinMessage += " " + name + ",";
                                            }
                            event.getMessage().editMessage(joinMessage
                            ).queue();
                        }
                        // potential to raise errors here v
                        List<ItemComponent> actionRow = null; 
                        event.getMessage().editMessage("The game is starting!").setActionRow(actionRow).queue();
                        complete = true;
                    }
                });

                thread.start();
            }
            if (started) {
                usernames.add(event.getUser().getId());
            }
        }
        public HashSet<String> getUsernames () {
            return usernames;
        }
        public boolean complete () {
            return complete;
        }
    }

    public class PlaceListener extends ListenerAdapter {
        private boolean complete;
        private HashSet<String> placed;
        private HashMap<String,Resource> placeableResources;
        private HashMap<String,Player> playersMap;

        public PlaceListener (HashMap<String,Resource> placeableResources, HashMap<String,Player> playersMap) {
            this.complete = false;
            this.placed = new HashSet<String>();
            this.placeableResources = placeableResources;
            this.playersMap = playersMap;
            jda.addEventListener(this);
        }
        @Override
        public void onMessageReceived(MessageReceivedEvent event) {
            String[] parsedMessage = event.getMessage().getContentRaw().split(" ");
            if (!parsedMessage[0].equalsIgnoreCase(TT)) {
                return;
            }
            if (!parsedMessage[1].equalsIgnoreCase(PLACE)) {
                return;
            }
            if (parsedMessage.length != 4) {
                return;
            }
            int x = Integer.parseInt(parsedMessage[2]);
            int y = Integer.parseInt(parsedMessage[3]);
            if (!(x < 4 && x > 0 && y < 4 && y > 0)) {
                return;
            } 
            String eventsName = event.getAuthor().getId();
            if (placeableResources.containsKey(eventsName)) {
                playersMap.get(eventsName).place(placeableResources.get(eventsName),x,y);
                placed.add(eventsName);
                jda.getTextChannelById(CHANNEL_ID).sendMessage(eventsName + "'s town:").queue();
                jda.getTextChannelById(CHANNEL_ID).sendMessage(playersMap.get(eventsName).getTown().toString()).queue();
            }
            if (placed.size() == playersMap.size()) {
                complete = true;
                jda.removeEventListener(this);
            }
        }
        public boolean complete () {
            return complete;
        }
    }

    public class BuildListener extends ListenerAdapter {
        private boolean complete;
        private HashSet<String> done;
        private HashMap<String, Player> playersMap;
        private boolean built;

        public BuildListener (HashMap<String,Player> playersMap) {
            this.complete = false;
            this.done = new HashSet<String>();
            this.playersMap = playersMap;
            jda.addEventListener(this);
        }
        @Override
        public void onMessageReceived (MessageReceivedEvent event) {
            String[] parsedMessage = event.getMessage().getContentRaw().split(" ");
            if (!parsedMessage[0].equalsIgnoreCase(TT)) {
                return;
            }
            if (!parsedMessage[1].equalsIgnoreCase(BUILD) && !parsedMessage[1].equalsIgnoreCase(DONE)) {
                return;
            }
            String author = event.getAuthor().getId();
            if (parsedMessage[1].equalsIgnoreCase(DONE)) {
                if (playersMap.get(author).getEmptyTiles().size() == 0) {
                    jda.getTextChannelById(CHANNEL_ID).sendMessage(author + " must place a building.").queue();
                } else {
                    done.add(author);
                    playersMap.remove(author);
                    if (playersMap.size() == 0) {
                        complete = true;
                        jda.removeEventListener(this);
                    }
                    event.getMessage().addReaction(event.getGuild().getEmojiById("<:a_empty:1250660017015619664>")).queue();
                }
                return;
            }
            if (parsedMessage.length != 5) {
                return;
            }
            int index = 0;
            for (BuildingType bt : BuildingType.values()) {
                if (parsedMessage[2] == bt.STRING) {
                    break;
                }
                if (index == 7) {
                    return;
                }
                index++;
            }
            int x = Integer.parseInt(parsedMessage[3]);
            int y = Integer.parseInt(parsedMessage[4]);
            if (!(x < 4 && x > 0 && y < 4 && y > 0)) {
                return;
            } 
            if(playersMap.get(author).build(x,y,index)) {
                jda.getTextChannelById(CHANNEL_ID).sendMessage(author + " built in their town:").queue();
                jda.getTextChannelById(CHANNEL_ID).sendMessage(playersMap.get(author).getTown().toString()).queue();
                built = true;
            } else {
                jda.getTextChannelById(CHANNEL_ID).sendMessage("Could not complete the latest build requested by " + author).queue();
            }
        }
        public boolean complete () {
            return complete;
        }
        public boolean built () {
            return built;
        }
    }

    @Override
    public Resource getRoundResource(String username) {
        return useResourceListener(new ResourceListener(username));
    }

    @Override
    public Resource chooseResource(String name, Resource[] options) {
        return useResourceListener(new ResourceListener(name,options));
    }

    private Resource useResourceListener (ResourceListener receiver) {
        Thread thread = new Thread() {
            @Override
            public void run () {
                while (!receiver.complete()) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {}
                }
            }
        };
        
        thread.run();

        jda.removeEventListener(receiver);
        return receiver.getResource();
    } 

    @Override
    public void placeResources(HashMap<String, Resource> placeableResources, HashMap<String,Player> playersMap) {
        PlaceListener receiver = new PlaceListener(placeableResources, playersMap);
        Thread thread = new Thread() {
            @Override
            public void run () {
                while (!receiver.complete()) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {}
                }
            }
        };
        
        thread.run();
    }

    @Override
    public boolean freeBuild(HashMap<String, Player> playersMap) {
        BuildListener receiver = new BuildListener(playersMap);
        Thread thread = new Thread() {
            @Override
            public void run () {
                while (!receiver.complete()) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {}
                }
            }
        };
        
        thread.run();

        return receiver.built();
    }

    @Override
    public HashSet<String> findPlayers() {
        PlayerListener receiver = new PlayerListener();
        Thread thread = new Thread() {
            @Override
            public void run () {
                while (!receiver.complete()) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {}
                }
            }
        };

        thread.run();

        jda.removeEventListener(receiver);
        return receiver.getUsernames();
    }

    @Override
    public void gameEnd() {
        TextChannel tc = jda.getTextChannelById(CHANNEL_ID);
        tc.sendMessage("And the game is over!\nHere are your scores!").queue();
        for (Player player : game.getPlayersArray()) {
            tc.sendMessage(player.getName() + ":" + player.getTown().score()).queue();
            tc.sendMessage(player.getTown().toString()).queue();
        }
    }
}