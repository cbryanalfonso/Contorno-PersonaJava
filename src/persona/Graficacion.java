/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

/**
 *
 * @author audir
 */
public class Graficacion {

    SimpleRegression sr = new SimpleRegression();
    Plot2DPanel plot = new Plot2DPanel();
    JTextArea area1 = new JTextArea();

    // JTextArea resultados = new JTextArea();
    public void rectaXsobreY(double[] xx, double[] yy, int z) {
        double[] suma = new double[z];
        double[] sumay = new double[z];
        double mediax = 0, mediay = 0;
        for (int l = 0; l < z; l++) {
            System.out.println("x " + xx[l]);
            System.out.println("y " + yy[l]);
            //MEDIA ARITMETICA
            suma[l] = xx[l];
            sumay[l] = yy[l];
            mediax += suma[l];
            mediay += sumay[l];
        }
        mediay /= z;
        mediax /= z;
        double[] sumaXMEDIA = new double[z];
        double[] sumaYMEDIA = new double[z];
        double[] multMedia = new double[z];
        double[] sumaXMEDIACUADRADA = new double[z];
        double sumaMult = 0, sumacuadrada = 0;
        for (int j = 0; j < z; j++) {
            sumaXMEDIA[j] = xx[j] - mediax;// MULTIPLICACIÓN DE XI-XM
            sumaXMEDIACUADRADA[j] = Math.pow(sumaXMEDIA[j], 2); // CUADRADO DE XI-XM
            sumaYMEDIA[j] = yy[j] - mediay; // MLTIPLICACIONES YI - YM

            multMedia[j] = sumaXMEDIA[j] * sumaYMEDIA[j];// MULTIPLICANDO LAS DOS OPERACIONES ANTERIORES.
            sumaMult += multMedia[j]; // SUMATORIA TOTAL DE LA MULTIMPLICACIÓN (XI-MX)(YI-YM)
            sumacuadrada += sumaXMEDIACUADRADA[j];/// OBTENIENDO XI - MEDIA X AL CUADRADO

        }

        double varianza = (double) 1 / z * (sumacuadrada);
        double covarianza = (double) 1 / z * (sumaMult);
        /*
            OBTENER LA PENDIENTE y = mx+b
         */
        double pendiente = covarianza / varianza;
        double interseccion = mediay - (pendiente * mediax);
        area1.setBackground(Color.yellow);

        area1.append("\n\tMedia aritmetica de X " + mediax);
        area1.append("\n\tMedia aritemetica de y " + mediay);
        area1.append("\n\tSumatoria de (xi-xm)(yq-ym) " + sumaMult);
        area1.append("\n\tSumatoria de xi-xm2 " + sumacuadrada);
        area1.append("\n\tvarianza " + varianza);
        area1.append("\n\tcovarianza " + covarianza);
        area1.append("\n\tPendiente de la recta " + pendiente);
        area1.append("\n\tInterseccion " + interseccion);
        area1.append("\n\tEcuación de la recta y = " + pendiente + "x + " + interseccion);
        System.out.println("Media de x es igual " + mediax);
        System.out.println("Media aritemetica de y " + mediay);
        System.out.println("xi-xm)(yq-ym) " + sumaMult);
        System.out.println("xi-xm2 " + sumacuadrada);
        System.out.println("varianza " + varianza);
        System.out.println("covarianza " + covarianza);
        System.out.println("Pendiente " + pendiente);
        System.out.println("Interseccion " + interseccion);
        System.out.println("Ecuacion de la recta");
        System.out.println("y = " + pendiente + "x + " + interseccion);

    }

    public void Regresion(double[] x, double[] y, int z) {
        for (int i = 0; i < z; i++) {
            sr.addData(x[i], y[i]);
        }
        double[] yc = new double[y.length];

        for (int i = 0; i < z; i++) {
            yc[i] = sr.predict(x[i]); // REGRESA LA Y CALCULADA
        }

        plot.addLegend("South");
        plot.addScatterPlot("DATOS", x, y);
        plot.addLinePlot("Recta de regresión", x, yc);
        // plot.addLinePlot("Resultados de la regresión", x, yc); // yc son resultados de los datos calculados

        BaseLabel titulo = new BaseLabel("Grafica Diagrama", Color.red, 0.5, 1.1);

        plot.addPlotable(titulo);
        area1.append("\n\tDatos leidos  " + sr.getN());

        //area1.append("Media de X es igual a "+mediax);
        JFrame frame = new JFrame("Regresion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.add(plot, BorderLayout.CENTER);
        frame.add(area1, BorderLayout.SOUTH);

        frame.setVisible(true);

        // Graficacion.add(plot, BorderLayout.CENTER);
    }
}
