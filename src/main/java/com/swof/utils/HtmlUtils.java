package com.swof.utils;

import com.swof.model.Day;
import com.swof.model.Engineer;
import com.swof.model.Shift;

import java.util.ArrayList;
import java.util.List;

public class HtmlUtils {
    public static String getHtml(ArrayList<Engineer> engineers, ArrayList<Day> days) {
        String html = "<html><head>"
                + "<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>"
                + "<title>Support Wheel of Fate</title>"
                + "</head>"
                + "<body>"
                + "<center>"
                + "<h2><strong>Support Wheel of Fate</strong></h2>"
                + "<table style=\"width:70%\">"
                + "<tr>"
                + "<td align=\"top\" valign=\"top\">"
                + "<table border=\"2\" style=\"width:95%\">"
                + "<tr align='center'><th colspan='4' >Schedule</th></tr>"
                + "<tr>"
                + "<th>" + "Date" + "</th>"
                + "<th>" + "Day" + "</th>"
                + "<th>" + "Shift" + "</th>"
                + "<th>" + "Engineer" + "</th>"
                + "</tr>";

        for (Day day : days) {
            int shiftNumber = 0;
            List<Shift> shifts = day.getShifts();
            String row = "<tr>"
                    + "<td rowspan=\"2\" align=\"center\">" + day.getDate() + "</td>"
                    + "<td rowspan=\"2\" align=\"center\">" + day.getName() + "</td>";
            for (Shift shift : shifts) {
                if (shiftNumber == 0) {
                    row = row
                            + "<td align=\"center\">" + ++shiftNumber + "</td>"
                            + "<td align=\"center\">" + shift.getEngineer().getName() + "</td>"
                            + "</tr>";
                } else {
                    row = row
                            + "<tr>"
                            + "<td align=\"center\">" + ++shiftNumber + "</td>"
                            + "<td align=\"center\">" + shift.getEngineer().getName() + "</td>"
                            + "</tr>";
                }

            }
            html = html + row;
        }

        html = html
                + "</table>"
                + "</td>"
                + "<td align=\"top\" valign=\"top\">"
                + "<table border=\"1\" style=\"width:95%\">"
                + "<tr>"
                + "<th>" + " List of Engineers" + "</th>"
                + "</tr>";

        for (Engineer engineer : engineers) {
            html = html
                    + "<tr>"
                    + "<td align=\"center\">" + engineer.getName() + "</td>"
                    + "</tr>";
        }

        html = html
                + "</table>"
                + "</td>"
                + "</tr>"
                + "</table>"
                + "</center>"
                + "</body></html>";

        return html;
    }

    public static String getErrorHtml() {
        String html = "<html><head>"
                + "<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>"
                + "<title>Support Wheel of Fate</title>"
                + "</head>"
                + "<body>"
                + "<center>"
                + "<h2><strong>Support Wheel of Fate</strong></h2>"
                + "<h4><strong>No path mapping found !</strong></h4>"
                + "</center>"
                + "</body></html>";

        return html;
    }
}
