package models;

import java.util.Comparator;

public class ComparadorPalavraReservadas implements Comparator<PalavraReservadaModel> {

        @Override
        public int compare(PalavraReservadaModel o1, PalavraReservadaModel o2) {

            int l1 = o1.getValor().length();
                int l2 = o2.getValor().length();

                int lmin = Math.min(l1, l2);

                for (int i = 0; i < lmin; i++) {
                    int str1_ch = (int)o1.getValor().charAt(i);
                    int str2_ch = (int)o2.getValor().charAt(i);
        
                    if (str1_ch != str2_ch) {
                        return str1_ch - str2_ch;
                    }
                }

            // Edge case for strings like
            // String 1="Geeks" and String 2="Geeksforgeeks"
            if (l1 != l2) {
                return l1 - l2;
            }
    
            // If none of the above conditions is true,
            // it implies both the strings are equal
            else {
                return 0;
            }
        }
    }
