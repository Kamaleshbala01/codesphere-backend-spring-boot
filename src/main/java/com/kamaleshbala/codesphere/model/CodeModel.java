package com.kamaleshbala.codesphere.model;

import com.kamaleshbala.codesphere.enums.Language;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CodeModel {
    private String code;
    private List<String> inputs;
    private List<String> output = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Language language;



    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getInputs() {
        return inputs;
    }

    public void setInputs(List<String> inputs) {
        this.inputs = inputs;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }
}
