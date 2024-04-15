package com.library.util.enum_helper;


import jakarta.persistence.Convert;

public class ExampleEntity {

    @Convert(converter = ExampleEnum.Converter.class)
    private ExampleEnum exampleEnum;
}
