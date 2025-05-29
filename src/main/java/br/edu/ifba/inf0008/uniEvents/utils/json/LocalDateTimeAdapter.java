package br.edu.ifba.inf0008.uniEvents.utils.json;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
  public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

  @Override
  public JsonElement serialize(LocalDateTime localDateTime, Type typeOfSrc, JsonSerializationContext context){
    return new JsonPrimitive(DATE_TIME_FORMATTER.format(localDateTime));
  }

  @Override
  public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    return LocalDateTime.parse(json.getAsString(), DATE_TIME_FORMATTER);
  } 
}
