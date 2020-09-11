package com.example.spring.converter;

import javax.persistence.Converter;

import static org.hibernate.annotations.common.util.StringHelper.isNotEmpty;

@Converter(autoApply = false)
public class StringEncryptDecryptConverter
        extends AbstractEncryptDecryptConverter<String> {

    /**
     * Default constructor initializes with an instance of the
     * {@link CipherMaker} crypto class to get a {@link javax.crypto.Cipher}
     * instance
     */
    public StringEncryptDecryptConverter() {
        this(new CipherMaker());
    }

    /**
     * Constructor
     *
     * @param cipherMaker
     */
    public StringEncryptDecryptConverter(CipherMaker cipherMaker) {
        super(cipherMaker);
    }

    @Override
    boolean isNotNullOrEmpty(String attribute) {
        return isNotEmpty(attribute);
    }

    @Override
    String convertStringToEntityAttribute(String dbData) {
        // the input is a string and output is a string
        return dbData;
    }

    @Override
    String convertEntityAttributeToString(String attribute) {
        // Here too the input is a string and output is a string
        return attribute;
    }
}
