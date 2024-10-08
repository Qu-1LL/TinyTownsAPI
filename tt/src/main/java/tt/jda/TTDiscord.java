package tt.jda;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.interaction.component.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import tt.*;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

public class TTDiscord extends ListenerAdapter implements TT {

    private final JDA jda;
    private Game game;

    private static final String TT = "tt";
    private static final String PLACE = "place";
    private static final String BUILD = "build";
    private static final String DONE = "done";
    private static final String START = "start";
    private static final String JOIN = "join";
    private static final String NOW = "now";
    public final Object fpLock = new Object();
    public final Object crLock = new Object();
    public final Object prLock = new Object();
    public final Object fbLock = new Object();
    private MessageChannel channel;
    private int phase;

    public TTDiscord (JDA jda, Game game) {
        this.jda = jda;
        this.game = game;
        this.channel = null;
        this.phase = 0;
        jda.addEventListener(this);

    }
    // try turning the gameloop into a thread, force it to wait instead of calling functions that wait for it.

    // public class ResourceListener {
    //     Resource resource = null;
    //     boolean complete = false;
    //     private Resource[] options;
    //     private String name;

    //     public ResourceListener (String name, Resource[] options) {
    //         this.name = name;
    //         this.options = options;
            
    //         if (options.length == 5) {
    //             channel.sendMessage(
    //                 name + " please select your resource:"
    //             ).addActionRow(
    //                 Button.secondary("0",Emoji.fromFormatted(options[0].toPrint())),
    //                 Button.secondary("1",Emoji.fromFormatted(options[1].toPrint())),
    //                 Button.secondary("2",Emoji.fromFormatted(options[2].toPrint())),
    //                 Button.secondary("3",Emoji.fromFormatted(options[3].toPrint())),
    //                 Button.secondary("4",Emoji.fromFormatted(options[4].toPrint()))
    //             ).queue();
    //         } else if (options.length == 4) {
    //             channel.sendMessage(
    //                 name + " please select your resource:"
    //             ).addActionRow(
    //                 Button.secondary("0",Emoji.fromFormatted(options[0].toPrint())),
    //                 Button.secondary("1",Emoji.fromFormatted(options[1].toPrint())),
    //                 Button.secondary("2",Emoji.fromFormatted(options[2].toPrint())),
    //                 Button.secondary("3",Emoji.fromFormatted(options[3].toPrint()))
    //             ).queue();
    //         } else if (options.length == 3) {
    //             channel.sendMessage(
    //                 name + " please select your resource:"
    //             ).addActionRow(
    //                 Button.secondary("0",Emoji.fromFormatted(options[0].toPrint())),
    //                 Button.secondary("1",Emoji.fromFormatted(options[1].toPrint())),
    //                 Button.secondary("2",Emoji.fromFormatted(options[2].toPrint()))
    //             ).queue();
    //         } else if (options.length == 2) {
    //             channel.sendMessage(
    //                 name + " please select your resource:"
    //             ).addActionRow(
    //                 Button.secondary("0",Emoji.fromFormatted(options[0].toPrint())),
    //                 Button.secondary("1",Emoji.fromFormatted(options[1].toPrint()))
    //             ).queue();
    //         } else if (options.length == 1) {
    //             resource = options[0];
    //             complete = true;
    //         } else if (options.length == 0) {
    //             complete = true;
    //         }
    //     }
    //     public ResourceListener (String name) {
    //         this(name,Resource.fullValues());
    //     }
    //     public Resource getResource() {
    //         return resource;
    //     }
    //     public boolean complete () {
    //         return complete;
    //     }
    //     @Override
    //     public void onButtonInteraction(ButtonInteractionEvent event) {
    //         if (event.getUser().getName() == name) {
    //             resource = options[Integer.parseInt(event.getComponentId())];
    //             complete = true;

    //             List<ItemComponent> actionRow = event.getMessage().getActionRows().get(0).getComponents();
    //             for (int i = 0; i < actionRow.size(); i++) {
    //                 actionRow.remove(i);
    //             }
    //             event.getMessage().editMessage(event.getMessage().getContentRaw()).setActionRow(actionRow).queue();
    //         }
    //     }
    // }

    // public class PlayerListener {
    //     HashSet<String> usernames;
    //     long messageId;
    //     Thread thread;
    //     boolean complete;

    //     public PlayerListener () {
    //         this.usernames = new HashSet<String>();
    //         this.complete = false;
    //     }
    //     public HashSet<String> start () {
    //         jda.addEventListener(this);
    //         channel.sendMessage("Send \"tt join\" to be added to the game!\nSend \"tt now\" to make the game start!").queue();
            
    //         while (!this.complete) {
    //             // try {
    //             //     wait(1000);
    //             // } catch (InterruptedException e) {}
    //         }
    //         jda.removeEventListener(this);

    //         return usernames;
    //     }
    //     @Override
    //     public void onMessageReceived(MessageReceivedEvent event) {
    //         String[] parsedMessage = event.getMessage().getContentRaw().split(" ");
    //         if (!parsedMessage[0].equalsIgnoreCase(TT)) {
    //             return;
    //         }
    //         if (parsedMessage.length != 2) {
    //             return;
    //         }
    //         if (parsedMessage[1].equalsIgnoreCase(JOIN)) {
    //             usernames.add(event.getAuthor().getName());
    //             String joinMessage = "Players:";
    //             for (String name : getUsernames()) {
    //                 joinMessage += " " + name + ",";
    //             }
    //             channel.sendMessage(joinMessage).queue();
    //         }
    //         if (parsedMessage[1].equalsIgnoreCase(NOW)) {
    //             complete = true;
    //         }
    //     }
    //     public HashSet<String> getUsernames () {
    //         return usernames;
    //     }
    //     public boolean complete () {
    //         return complete;
    //     }
    // }

    // public class PlaceListener {
    //     private boolean complete;
    //     private HashSet<String> placed;
    //     private HashMap<String,Resource> placeableResources;
    //     private HashMap<String,Player> playersMap;

    //     public PlaceListener (HashMap<String,Resource> placeableResources, HashMap<String,Player> playersMap) {
    //         this.complete = false;
    //         this.placed = new HashSet<String>();
    //         this.placeableResources = placeableResources;
    //         this.playersMap = playersMap;
    //     }
    //     @Override
    //     public void onMessageReceived(MessageReceivedEvent event) {
    //         String[] parsedMessage = event.getMessage().getContentRaw().split(" ");
    //         if (!parsedMessage[0].equalsIgnoreCase(TT)) {
    //             return;
    //         }
    //         if (!parsedMessage[1].equalsIgnoreCase(PLACE)) {
    //             return;
    //         }
    //         if (parsedMessage.length != 4) {
    //             return;
    //         }
    //         int x = Integer.parseInt(parsedMessage[2]);
    //         int y = Integer.parseInt(parsedMessage[3]);
    //         if (!(x < 4 && x > 0 && y < 4 && y > 0)) {
    //             return;
    //         } 
    //         String eventsName = event.getAuthor().getName();
    //         if (placeableResources.containsKey(eventsName)) {
    //             playersMap.get(eventsName).place(placeableResources.get(eventsName),x,y);
    //             placed.add(eventsName);
    //             channel.sendMessage(eventsName + "'s town:").queue();
    //             channel.sendMessage(playersMap.get(eventsName).getTown().toString()).queue();
    //         }
    //         if (placed.size() == playersMap.size()) {
    //             complete = true;
    //         }
    //     }
    //     public boolean complete () {
    //         return complete;
    //     }
    // }

    // public class BuildListener {
    //     private boolean complete;
    //     private HashSet<String> done;
    //     private HashMap<String, Player> playersMap;
    //     private boolean built;

    //     public BuildListener (HashMap<String,Player> playersMap) {
    //         this.complete = false;
    //         this.done = new HashSet<String>();
    //         this.playersMap = playersMap;
    //     }
    //     @Override
    //     public void onMessageReceived (MessageReceivedEvent event) {
    //         String[] parsedMessage = event.getMessage().getContentRaw().split(" ");
    //         if (!parsedMessage[0].equalsIgnoreCase(TT)) {
    //             return;
    //         }
    //         if (!parsedMessage[1].equalsIgnoreCase(BUILD) && !parsedMessage[1].equalsIgnoreCase(DONE)) {
    //             return;
    //         }
    //         String author = event.getAuthor().getName();
    //         if (parsedMessage[1].equalsIgnoreCase(DONE)) {
    //             if (playersMap.get(author).getEmptyTiles().size() == 0) {
    //                 channel.sendMessage(author + " must place a building.").queue();
    //             } else {
    //                 done.add(author);
    //                 playersMap.remove(author);
    //                 if (playersMap.size() == 0) {
    //                     complete = true;
    //                 }
    //                 event.getMessage().addReaction(event.getGuild().getEmojiById("<:a_empty:1250660017015619664>")).queue();
    //             }
    //             return;
    //         }
    //         if (parsedMessage.length != 5) {
    //             return;
    //         }
    //         int index = 0;
    //         for (BuildingType bt : BuildingType.values()) {
    //             if (parsedMessage[2] == bt.STRING) {
    //                 break;
    //             }
    //             if (index == 7) {
    //                 return;
    //             }
    //             index++;
    //         }
    //         int x = Integer.parseInt(parsedMessage[3]);
    //         int y = Integer.parseInt(parsedMessage[4]);
    //         if (!(x < 4 && x > 0 && y < 4 && y > 0)) {
    //             return;
    //         } 
    //         if(playersMap.get(author).build(x,y,index)) {
    //             channel.sendMessage(author + " built in their town:").queue();
    //             channel.sendMessage(playersMap.get(author).getTown().toString()).queue();
    //             built = true;
    //         } else {
    //             channel.sendMessage("Could not complete the latest build requested by " + author).queue();
    //         }
    //     }
    //     public boolean complete () {
    //         return complete;
    //     }
    //     public boolean built () {
    //         return built;
    //     }
    // }

    @Override
    public void onMessageReceived (MessageReceivedEvent event) {

        Thread thread = new Thread() {
            @Override
            public void run () {
                String[] parsedMessage = event.getMessage().getContentRaw().split(" ");
                if (phase == 0) {
                    if (!parsedMessage[0].equalsIgnoreCase(TT)) {
                        return;
                    }
                    if (!(parsedMessage[1].equalsIgnoreCase(START) && parsedMessage.length == 2)) {
                        return;
                    }
                    channel = event.getGuild().getTextChannelsByName("da-bot-home", true).get(0);
                    channel.sendMessage("Send \"tt join\" to be added to the game!\nSend \"tt now\" to let the game begin!").queue();
                    game.start();
                }
                if (phase == 1) {
                    synchronized(fpLock) {
                        handleFindPlayers(event);
                        //channel.sendMessage("Made it into synchronized block.").queue();
                        fpLock.notifyAll();
                    }
                    //channel.sendMessage("Exited Synchronized Block.").queue();
                }
                //channel.sendMessage("Exited if (phase == 1) statement").queue();
                if (phase == 3) {
                    synchronized(prLock) {
                        handlePlaceResources(event,myPlaceableResources,myPlayersMap);
                        prLock.notifyAll();
                    }
                }
                if (phase == 4) {
                    synchronized (fbLock) {
                        handleFreeBuild(event,myPlayersMap);
                        fbLock.notifyAll();
                    }
                }
            }
        };
        thread.run();
    }

    @Override
    public void onButtonInteraction (ButtonInteractionEvent event) {
        if (phase == 2) {
            synchronized(crLock) {
                handleChooseResource(event,resourceName,myOptions);
                crLock.notifyAll();
            }
        }
    }

    private static Resource resource;
    private static String resourceName;
    private static boolean resourceDone = false;
    private static Resource[] myOptions;

    @Override
    public Resource getRoundResource(String username) {
        phase = 2;
        channel.sendMessage("set phase = 2").queue();
        sendResourceButtons(username,Resource.fullValues());
        return combineChooseResource(username,Resource.fullValues());
    }

    @Override
    public Resource chooseResource(String name, Resource[] options) {
        sendResourceButtons(name,Resource.fullValues());
        return combineChooseResource(name,options);
    }

    private void sendResourceButtons (String name, Resource[] options) {
        myOptions = options;
        resourceName = name;
        if (options.length == 5) {
            channel.sendMessage(
                name + " please select your resource:"
            ).addActionRow(
                Button.secondary("0",Emoji.fromFormatted(options[0].toPrint())),
                Button.secondary("1",Emoji.fromFormatted(options[1].toPrint())),
                Button.secondary("2",Emoji.fromFormatted(options[2].toPrint())),
                Button.secondary("3",Emoji.fromFormatted(options[3].toPrint())),
                Button.secondary("4",Emoji.fromFormatted(options[4].toPrint()))
            ).queue();
        } else if (options.length == 4) {
            channel.sendMessage(
                name + " please select your resource:"
            ).addActionRow(
                Button.secondary("0",Emoji.fromFormatted(options[0].toPrint())),
                Button.secondary("1",Emoji.fromFormatted(options[1].toPrint())),
                Button.secondary("2",Emoji.fromFormatted(options[2].toPrint())),
                Button.secondary("3",Emoji.fromFormatted(options[3].toPrint()))
            ).queue();
        } else if (options.length == 3) {
            channel.sendMessage(
                name + " please select your resource:"
            ).addActionRow(
                Button.secondary("0",Emoji.fromFormatted(options[0].toPrint())),
                Button.secondary("1",Emoji.fromFormatted(options[1].toPrint())),
                Button.secondary("2",Emoji.fromFormatted(options[2].toPrint()))
            ).queue();
        } else if (options.length == 2) {
            channel.sendMessage(
                name + " please select your resource:"
            ).addActionRow(
                Button.secondary("0",Emoji.fromFormatted(options[0].toPrint())),
                Button.secondary("1",Emoji.fromFormatted(options[1].toPrint()))
            ).queue();
        } else if (options.length == 1) {
            resource = options[0];
            resourceDone = true;
        } else if (options.length == 0) {
            resource = null;
            resourceDone = true;
        }
    }

    private Resource combineChooseResource (String name, Resource[] options) {
        resourceName = name;

        Thread thread = new Thread() {
            @Override
            public void run () {
                channel.sendMessage("chooseResource thread has started").queue();
                synchronized(crLock) {
                    while (!resourceDone) {
                        try {
                            crLock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    resourceDone = false;
                }
            }
        };
        thread.start();
        //channel.sendMessage("chooseResource thread has ended").queue();

        return resource;

        // if (resourceDone) {
        //     return resource;
        // } else {
        //     return combineChooseResource(name,options);
        // }
    } 

    private boolean handleChooseResource (ButtonInteractionEvent event, String name, Resource[] options) {
        channel.sendMessage("chooseResourceHandler entered.").queue();
        if (event.getUser().getName() == name) {
            resource = options[Integer.parseInt(event.getComponentId())];

            List<ItemComponent> actionRow = event.getMessage().getActionRows().get(0).getComponents();
            for (int i = 0; i < actionRow.size(); i++) {
                actionRow.remove(i);
            }
            event.editMessage(event.getMessage().getContentRaw()).setActionRow(actionRow).queue();
            channel.sendMessage("The chosen resource is " + resource.STRING + ".").queue();
            channel.sendMessage(resource.toString()).queue();
            resourceDone = true;
            return true;
        }
        return false;
    }

    @Override
    public void placeResources(HashMap<String, Resource> placeableResources, HashMap<String,Player> playersMap) {
        phase = 3;
        myPlaceableResources = placeableResources;
        myPlayersMap = playersMap;
        placed = new HashSet<String>();
        Thread thread = new Thread() {
            @Override
            public void run () {
                synchronized(prLock) {
                    while (!placedDone) {
                        try {
                            prLock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    placedDone = false;
                }
            }
        };
        thread.start();

        return;
    }

    private static HashMap<String, Resource> myPlaceableResources;
    private static HashMap<String,Player> myPlayersMap;
    private static HashSet<String> placed;
    private static boolean placedDone = false;

    private boolean handlePlaceResources (MessageReceivedEvent event, HashMap<String, Resource> placeableResources, HashMap<String,Player> playersMap) {
        String[] parsedMessage = event.getMessage().getContentRaw().split(" ");
            if (!parsedMessage[0].equalsIgnoreCase(TT)) {
                return false;
            }
            if (!parsedMessage[1].equalsIgnoreCase(PLACE)) {
                return false;
            }
            if (parsedMessage.length != 4) {
                return false;
            }
            int x = Integer.parseInt(parsedMessage[2]);
            int y = Integer.parseInt(parsedMessage[3]);
            if (!(x < 4 && x > 0 && y < 4 && y > 0)) {
                return false;
            } 
            String eventsName = event.getAuthor().getName();
            if (placeableResources.containsKey(eventsName)) {
                playersMap.get(eventsName).place(placeableResources.get(eventsName),x,y);
                placed.add(eventsName);
                channel.sendMessage(eventsName + "'s town:").queue();
                channel.sendMessage(playersMap.get(eventsName).getTown().toString()).queue();
            }
            if (placed.size() == playersMap.size()) {
                return true;
            }
            return false;
    }

    private static boolean buildDone = false;
    private static boolean wasBuilt = false;
    private static HashSet<String> done;

    @Override
    public boolean freeBuild(HashMap<String, Player> playersMap) {
        phase = 4;
        myPlayersMap = playersMap;
        done = new HashSet<String>();
        Thread thread = new Thread() {
            @Override
            public void run () {
                synchronized(fbLock) {
                    while (!buildDone) {
                        try {
                            fbLock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    buildDone = false;
                }
            }
        };
        thread.start();

        return wasBuilt;
    }

    private boolean handleFreeBuild (MessageReceivedEvent event, HashMap<String, Player> playersMap) {
        String[] parsedMessage = event.getMessage().getContentRaw().split(" ");
        if (!parsedMessage[0].equalsIgnoreCase(TT)) {
            return false;
        }
        if (!parsedMessage[1].equalsIgnoreCase(BUILD) && !parsedMessage[1].equalsIgnoreCase(DONE)) {
            return false;
        }
        String author = event.getAuthor().getName();
        if (parsedMessage[1].equalsIgnoreCase(DONE)) {
            if (playersMap.get(author).getEmptyTiles().size() == 0) {
                channel.sendMessage(author + " must place a building.").queue();
            } else {
                done.add(author);
                playersMap.remove(author);
                if (playersMap.size() == 0) {
                    buildDone = true;
                }
                event.getMessage().addReaction(event.getGuild().getEmojiById("<:a_empty:1250660017015619664>")).queue();
                return true;
            }
            return false;
        }
        if (parsedMessage.length != 5) {
            return false;
        }
        int index = 0;
        for (BuildingType bt : BuildingType.values()) {
            if (parsedMessage[2] == bt.STRING) {
                break;
            }
            if (index == 7) {
                return false;
            }
            index++;
        }
        int x = Integer.parseInt(parsedMessage[3]);
        int y = Integer.parseInt(parsedMessage[4]);
        if (!(x < 4 && x > 0 && y < 4 && y > 0)) {
            return false;
        } 
        if (playersMap.get(author).build(x,y,index)) {
            channel.sendMessage(author + " built in their town:").queue();
            channel.sendMessage(playersMap.get(author).getTown().toString()).queue();
            wasBuilt = true;
            return false;
        } else {
            channel.sendMessage("Could not complete the latest build requested by " + author).queue();
            return false;
        }
    }

    public HashSet<String> players = new HashSet<String>();
    public boolean playersDone = false;

    @Override
    public void findPlayers() {
        phase = 1;
        
    }
    
    private boolean handleFindPlayers(MessageReceivedEvent event) {
        channel.sendMessage("Made it to handleFindPlayers()").queue();
        String[] parsedMessage = event.getMessage().getContentRaw().split(" ");
        if (!parsedMessage[0].equalsIgnoreCase(TT)) {
            return false;
        }
        if (parsedMessage.length != 2) {
            return false;
        }
        if (parsedMessage[1].equalsIgnoreCase(JOIN)) {
            players.add(event.getAuthor().getName());
            String joinMessage = "Players:";
            for (String name : players) {
                joinMessage += " " + name + ",";
            }
            channel.sendMessage(joinMessage).queue();
            return false;
        }
        if (parsedMessage[1].equalsIgnoreCase(NOW)) {
            channel.sendMessage("bot successfully read \"tt now\"").queue();
            playersDone = true;
            return true;
        }
        return false;
    }

    @Override
    public void gameEnd() {
        channel.sendMessage("The game is over!\nHere are your scores!").queue();
        for (Player player : game.getPlayersArray()) {
            channel.sendMessage(player.getName() + ":" + player.getTown().score()).queue();
            channel.sendMessage(player.getTown().toString()).queue();
        }
        phase = 0;
    }

    // for debug purposes only v

    public void sendMessage (String contents) {
        channel.sendMessage(contents).queue();
    }
}