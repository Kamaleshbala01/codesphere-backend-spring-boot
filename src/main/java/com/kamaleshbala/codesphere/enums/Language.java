package com.kamaleshbala.codesphere.enums;

public enum Language {
    CPP("code-exec-cpp","cpp","Main.cpp"),
    JAVA("code-exec-java","java","Main.java"),
    PYTHON("code-exec-python","py","Main.py")

    ;

    private final String name;
    private final String extension;
    private final String fileName;

    Language(String name, String extension, String fileName){
        this.name = name;
        this.extension = extension;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getExtension() {
        return extension;
    }

    public String getName() {
        return name;
    }
}
