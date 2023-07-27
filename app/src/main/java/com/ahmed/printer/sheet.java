package com.ahmed.printer;



public class sheet
{

    //  1px solid black;
    public static final String HEADER_SHEET_1 ="<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            "@page{\n" +
            "margin: 0mm ; \n" +
            "}\n" +
            "table, th, td{\n" +
            " border: none;\n" +
            " border-collapse: collapse;\n" +
            " \n" +
            "}\n" +
            "\n" +
            ".totals {\n" +
            "\n" +
            " border: 3px solid white;\n" +
            " border-collapse: collapse;\n" +
            "}\n" +
            "\n" +
            "\n" +
            ".nameTD{\n" +
            "border: 3px solid white;\n" +
            " border-collapse: collapse;\n" +
            " text-align: right;\n" +
            " font-weight: bold;\n" +
            " font-size: 20px;\n" +
            "}\n" +
            "\n" +
            ".totalTD{\n" +
            "border: 3px solid white;\n" +
            "border-collapse: collapse;\n" +
            " text-align: left;\n" +
            " font-weight: bold;\n" +
            " font-size: 20px;\n" +
            "}\n" +
            "\n" +
            "td {\n" +
            " padding 1px;\n" +
            " text-align: center;\n" +
            " font-size: large;\n" +
            //" font-weight: bold;\n" +
            "}\n" +
            "th {\n" +
            " padding 1px;\n" +
            " text-align: center;\n" +
            " font-size: 20px;\n" +
            " font-weight: bold;\n" +
            "}\n" +
            "h1 ,h2 , span{\n" +
            "margin:1px;\n" +
            "text-align: center;\n" +
            "}\n" +
            "p{\n" +
            "margin:1px;\n" +
            // "font-size: large;\n"+
            "text-align: center;\n" +
            "}\n"+"img{\n" +
            "margin:1px;\n" +
            "text-align: center;\n" +
            "}\n"+
            "</style>\n" +
            "</head>\n" +
            "<body>" +
            "<div style=\"display:flex; justify-content:center;\">"+
            "<img style=\"width:150x; height:150px;\" src=\"logoblack.png\" alt=\"logo\" border=\"0\"></a>"+
            "<h1 style=\"margin-top:20%; text-align:center;\">"+Common.ITEM_COMMENT+"</h1>\n" +
            "</div>\n"+
            //"<h2 style=\"text-align:center;\">"+Common.ITEM_USERNAME+"</h2>\n" +
            "<p style=\"text-align:center;\">"+Common.PHONE_NUMBER+"</p>\n"+
            "<p style=\"text-align:center;\">"+Common.ADDRESS+"</p>\n"+
            //"<h3 style=\"border: 1px solid black; text-align:center; border-radius: 3px; margin-left:20%; width:60%; margin-top :8px;\">"+Common.ITEM_IHCODE+"</h3>\n" +
            "<p style=\"text-align:center;\">"+Common.ITEM_CURRENT_TIME+"</p>\n";

//    public static final String END_HEAD = "<hr color=\"black\">\n" +
//            "<table style=\" width:100% ;\" >\n" +
//            "<tr>\n" +
//            "<th>\n" +
//            "السعر\n" +
//            "</th>\n" +
//            "<th>\n" +
//            "الكمية\n" +
//            "</th>\n" +
//            "<th>\n" +
//            "اسم الصنف\n" +
//            "</th>";

    public static final String END_HEAD = "<hr color=\"black\">\n" +
            "<table style=\" width:100% ;\" >\n" +
            "<tr>\n" +
            "<th>\n"+
            "القيمة\n"+
            "</th>\n"+
            "<th>\n" +
            "السعر\n" +
            "</th>\n" +
            "<th>\n" +
            "الكمية\n" +
            "</th>\n" +
            "<th>\n" +
            "اسم الصنف\n" +
            "</th>";

    public static final String ITEM_SHEET_1 = "</tr>\n" +
            "<tr>\n" +
            "<td>\n" +
            Common.ITEM_PRICE+"\n" +
            "</td>\n" +
            "<td>\n" +
            Common.ITEM_VALUE_ALL+"\n" +
            "</td>\n" +
            "<td>\n"
            +Common.ITEM_QUANTITY+"\n" +
            "</td>\n" +
            "<td>\n" +
            Common.ITEM_NAME+"\n" +
            "</td>\n" +
            "</tr>\n";

//    public static final String ITEM_SHEET_1 = "</tr>\n" +
//            "<tr>\n" +
//            "<td>\n" +
//            Common.ITEM_PRICE+"\n" +
//            "</td>\n" +
//            "<td>\n"
//            +Common.ITEM_QUANTITY+"\n" +
//            "</td>\n" +
//            "<td>\n" +
//            Common.ITEM_NAME+"\n" +
//            "</td>\n" +
//            "</tr>\n";


    public static final String BOTTOM_SHEET =
            "<tr>\n" +
                    "<td>\n" +
                    Common.ITEM_DEL_VAL+"\n" +
                    "</td>\n" +
                    "<td>\n" +
                    "</td>\n" +
                    "<td>\n" +
                    "</td>\n" +
                    "<td>\n" +
                    Common.ITEM_DELIVERY+"\n" +
                    "</td>\n" +
                    "</tr>\n"+
                    "</table>"+
                    "<footer>\n" +
                    //  "<p>خدمة التوصيل 5 جنية</p>\n"+
                    "<h3 style=text-align:center;>"+Common.ITEM_TOTAL+"</h3>"+
                    "<p> يسعدنا دائمًا تلبية طلباتكم  </p>\n"+
                    "<p>000000</p>\n"+
                    "<p>00000</p>\n"+
                    "<p>0000</p>\n"+
                    "<p>المرتجعات بالبون خلال 14 يوم والفريشات 24 ساعة</p>\n"+
                    "</footer>" +
                    "</body>"+
                    "</html>";

}