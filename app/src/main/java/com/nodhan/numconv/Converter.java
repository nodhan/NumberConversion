package com.nodhan.numconv;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nodhan on 12/11/16.
 */

class Converter {
    private String numberToConvert;

    Converter(String numberToConvert) {
        this.numberToConvert = numberToConvert;
    }

    List<ConvertedNumberInfo> binaryToOther() {
        List<ConvertedNumberInfo> info = new ArrayList<>();
        int decimal = Integer.parseInt(numberToConvert, 2);
        info.add(binaryToDecimal(decimal));
        info.add(binaryToOctal(decimal));
        info.add(binaryToHexa(decimal));
        return info;
    }

    private ConvertedNumberInfo binaryToOctal(int decimal) {
        ConvertedNumberInfo info = new ConvertedNumberInfo();
        info.type = NumberType.OCTAL;
        info.number = Integer.toOctalString(decimal);
        return info;
    }

    private ConvertedNumberInfo binaryToDecimal(int decimal) {
        ConvertedNumberInfo info = new ConvertedNumberInfo();
        info.type = NumberType.DECIMAL;
        info.number = decimal + "";
        return info;
    }

    private ConvertedNumberInfo binaryToHexa(int decimal) {
        ConvertedNumberInfo info = new ConvertedNumberInfo();
        info.type = NumberType.HEXADECIMAL;
        info.number = Integer.toHexString(decimal);
        return info;
    }

    List<ConvertedNumberInfo> octalToOther() {
        List<ConvertedNumberInfo> info = new ArrayList<>();
        int decimal = Integer.parseInt(numberToConvert, 8);
        info.add(octalToBinary(decimal));
        info.add(octalToDecimal(decimal));
        info.add(octalToHexa(decimal));
        return info;
    }

    private ConvertedNumberInfo octalToBinary(int decimal) {
        ConvertedNumberInfo info = new ConvertedNumberInfo();
        info.type = NumberType.BINARY;
        info.number = Integer.toBinaryString(decimal);
        return info;
    }

    private ConvertedNumberInfo octalToDecimal(int decimal) {
        ConvertedNumberInfo info = new ConvertedNumberInfo();
        info.type = NumberType.DECIMAL;
        info.number = decimal + "";
        return info;
    }

    private ConvertedNumberInfo octalToHexa(int decimal) {
        ConvertedNumberInfo info = new ConvertedNumberInfo();
        info.type = NumberType.HEXADECIMAL;
        info.number = Integer.toHexString(decimal);
        return info;
    }


    List<ConvertedNumberInfo> decimalToOther() {
        List<ConvertedNumberInfo> info = new ArrayList<>();
        int decimal = Integer.parseInt(numberToConvert, 10);
        info.add(decimalToBinary(decimal));
        info.add(decimalToOctal(decimal));
        info.add(decimalToHexa(decimal));
        return info;
    }

    private ConvertedNumberInfo decimalToBinary(int decimal) {
        ConvertedNumberInfo info = new ConvertedNumberInfo();
        info.type = NumberType.BINARY;
        info.number = Integer.toBinaryString(decimal);
        return info;
    }

    private ConvertedNumberInfo decimalToOctal(int decimal) {
        ConvertedNumberInfo info = new ConvertedNumberInfo();
        info.type = NumberType.OCTAL;
        info.number = Integer.toOctalString(decimal);
        return info;
    }

    private ConvertedNumberInfo decimalToHexa(int decimal) {
        ConvertedNumberInfo info = new ConvertedNumberInfo();
        info.type = NumberType.HEXADECIMAL;
        info.number = Integer.toHexString(decimal);
        return info;
    }

    List<ConvertedNumberInfo> hexaToOther() {
        List<ConvertedNumberInfo> info = new ArrayList<>();
        int decimal = Integer.parseInt(numberToConvert, 16);
        info.add(hexaToBinary(decimal));
        info.add(hexaToOctal(decimal));
        info.add(hexaToDecimal(decimal));
        return info;
    }

    private ConvertedNumberInfo hexaToBinary(int decimal) {
        ConvertedNumberInfo info = new ConvertedNumberInfo();
        info.type = NumberType.BINARY;
        info.number = Integer.toBinaryString(decimal);
        return info;
    }

    private ConvertedNumberInfo hexaToOctal(int decimal) {
        ConvertedNumberInfo info = new ConvertedNumberInfo();
        info.type = NumberType.OCTAL;
        info.number = Integer.toOctalString(decimal);
        return info;
    }

    private ConvertedNumberInfo hexaToDecimal(int decimal) {
        ConvertedNumberInfo info = new ConvertedNumberInfo();
        info.type = NumberType.DECIMAL;
        info.number = decimal + "";
        return info;
    }
}
