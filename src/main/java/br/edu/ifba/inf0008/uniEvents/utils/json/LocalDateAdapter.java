package br.edu.ifba.inf0008.uniEvents.utils.json;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  @Override
  public JsonElement serialize(LocalDate localDate, Type typeOfSrc, JsonSerializationContext context){
    return new JsonPrimitive(DATE_FORMATTER.format(localDate));
  }
  
  @Override
  public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    return LocalDate.parse(json.getAsString(), DATE_FORMATTER);
  } 
}
