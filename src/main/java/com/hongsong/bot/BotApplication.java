package com.hongsong.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class BotApplication {

    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault("token").build();
    }
}
