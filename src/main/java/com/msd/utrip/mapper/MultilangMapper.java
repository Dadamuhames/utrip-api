package com.msd.utrip.mapper;

import com.msd.utrip.entity.field.MultiLanguageText;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MultilangMapper {

    MultiLanguageText mapMultiLangField(Map<String, String> value) {
        return new MultiLanguageText(value);
    }

    Map<String, String> mapMultiLangField(MultiLanguageText value) {
        if (value == null) return null;

        return value.getTexts();
    }
}
