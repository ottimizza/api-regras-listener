package br.com.ottimizza.regraslistener.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;


public class MessageUtils {
    
    public static String CleanMessage(String message) {
		return message.replaceAll("\\\\", "").replace("\"{","{").replace("}\"","}").trim();
	}

    public static List<String> listFromMessage(String message) {
        List<String> gruposRegrasIds = new ArrayList<>();
		String ids = "";
		message = CleanMessage(message);
		System.out.println(message);
		if(message.contains("message")) {
			String s = message.substring(message.indexOf("message"));
            if(s.contains("{") && s.contains("}")) {
                ids = s.substring(s.indexOf("{") + 1, s.lastIndexOf("}")).trim();
            }
            gruposRegrasIds = Arrays.asList(ids.split(","));
		}
		return gruposRegrasIds;
	}
}