package com.dantsu.escposprinter.src.main.java.com.dantsu.escposprinter.barcode;

import com.dantsu.escposprinter.src.main.java.com.dantsu.escposprinter.  EscPosPrinterCommands;
import com.dantsu.escposprinter.src.main.java.com.dantsu.escposprinter. EscPosPrinterSize;
import com.dantsu.escposprinter.exceptions.EscPosBarcodeException;

public class BarcodeEAN8 extends com.dantsu.escposprinter.src.main.java.com.dantsu.escposprinter. barcode.BarcodeNumber {
    public BarcodeEAN8(EscPosPrinterSize printerSize, String code, float widthMM, float heightMM, int textPosition) throws EscPosBarcodeException {
        super(printerSize, EscPosPrinterCommands.BARCODE_TYPE_EAN8, code, widthMM, heightMM, textPosition);
    }

    @Override
    public int getCodeLength() {
        return 8;
    }
}
