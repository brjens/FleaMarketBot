package org.example;

import javax.print.DocFlavor.STRING;
import javax.security.auth.login.LoginException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class App extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        // ssave Token.java as a varibale callen token
        Token token = new Token();
        JDABuilder builder = JDABuilder.createDefault(token.getBotToken())
        .addEventListeners(new App())
        .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT);    
        builder.build();
    }

   @Override
public void onMessageReceived(MessageReceivedEvent event) {
    if (event.getAuthor().isBot()) return;

    String message = event.getMessage().getContentRaw();
    System.out.println("Received message: " + message);

    if (message.equalsIgnoreCase("!hello")) {
        try {
            event.getChannel().sendMessage("Hello, " + event.getAuthor().getName() + "!").queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("hello")) {
            String usrTag = event.getUser().getEffectiveName();
            event.reply("Hello, " + usrTag + "!").setEphemeral(true).queue();
        }

        if (command.equals("price")) {
            String itemName = event.getOption("item").getAsString();
            String price = getPrice(itemName);
            event.reply("The price of " + itemName + " is " + price + " RUB").setEphemeral(true).queue();
        }
    }

    private String getPrice(String itemName) {
        TarkovAPI tarkovAPI = new TarkovAPI();
        String price="";
        try {
            price = tarkovAPI.getJsonString(itemName);
            price = JsonParser.getAvg24hPrice(price);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return price;
    }

    // this build the list of commands
    @Override
    public void onGuildReady(GuildReadyEvent event) {
       List<CommandData> commandData = new ArrayList<>(); 
       commandData.add(Commands.slash("hello", "get welcome message"));
      
       commandData.add(Commands.slash("price", "get price of an item")
       .addOptions(new OptionData(OptionType.STRING, "item", "item name", true)));
      
       event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    
