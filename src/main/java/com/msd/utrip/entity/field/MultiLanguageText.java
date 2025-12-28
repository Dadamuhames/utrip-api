package com.msd.utrip.entity.field;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import jakarta.persistence.Embeddable;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Embeddable
public class MultiLanguageText {
  @Type(JsonBinaryType.class)
  @Column(name = "name", columnDefinition = "jsonb")
  private Map<String, String> texts = new HashMap<>();

  public MultiLanguageText() {}

  public MultiLanguageText(Map<String, String> texts) {
    this.texts = texts;
  }

  public String get(String lang) {
    return texts.getOrDefault(lang, texts.getOrDefault("ru", ""));
  }

}
