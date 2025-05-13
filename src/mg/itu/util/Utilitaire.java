/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Eric Sandratriniavo
 */
public class Utilitaire {
    public static String formaterAr(double montant) {
        try {
            if (montant == 0) {
                return "0";
            }
            NumberFormat nf = NumberFormat.getInstance(Locale.FRENCH);
            // nf = new DecimalFormat("### ###,##");
            // nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String s = nf.format(montant);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
