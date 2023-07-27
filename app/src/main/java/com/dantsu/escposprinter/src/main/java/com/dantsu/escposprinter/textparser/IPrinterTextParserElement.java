package com.dantsu.escposprinter.src.main.java.com.dantsu.escposprinter.textparser;

import com.dantsu.escposprinter.src.main.java.com.dantsu.escposprinter. EscPosPrinterCommands;
import com.dantsu.escposprinter.exceptions.EscPosConnectionException;
import com.dantsu.escposprinter.exceptions.EscPosEncodingException;

public interface IPrinterTextParserElement {
    int length() throws EscPosEncodingException;
    IPrinterTextParserElement print(EscPosPrinterCommands printerSocket) throws EscPosEncodingException, EscPosConnectionException;
}
