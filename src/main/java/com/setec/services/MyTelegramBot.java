package com.setec.services;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.response.SendResponse;
import com.pengrad.telegrambot.request.SendMessage;


@Service
public class MyTelegramBot {
	
	
	@Value("${token}")
	private String token;
	@Value("${chat_id}")
	private long chat_id;
	
	private TelegramBot bot;
	
	public SendResponse sendMessage(String text) {
		
		if (bot == null) {
			bot = new TelegramBot(token);
		
			
		}
		SendResponse message = bot.execute(new SendMessage(chat_id,text));
		return message;
	
	}
	

}
