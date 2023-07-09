package lab_1;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.util.Scanner;

public class encoding  extends ApplicationFrame {

    public encoding(int[] input) {
        super("Signal");
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "Signal" ,
                "Time" ,
                "Voltage" ,
                createDataset(input) ,
                PlotOrientation.VERTICAL ,
                true , true , false);

        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        final XYPlot plot = xylineChart.getXYPlot( );

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );
        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
        plot.setRenderer( renderer );
        setContentPane( chartPanel );
    }

    private XYDataset createDataset(int[] input ) {
        final XYSeries firefox = new XYSeries( "input" );
        int time = 0;
        firefox.add( 0.0 , 0.0 );

        for (int i :input){
            firefox.add( time , i );
            time++;
            firefox.add(time,i);
        }
//        firefox.add( 0.0 , 1.0 );
//        firefox.add( 1.0 , 1.0 );
//        firefox.add( 1.0 , 0.0 );
//        firefox.add( 2.0 , 0.0 );
//        firefox.add( 2.0 , 1.0 );
//        firefox.add( 3.0 , 1.0 );
//        firefox.add( 3.0 , 0.0 );
        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries( firefox );
        return dataset;
    }

    public static void main( String[ ] args ) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] inputt  = new int[len];
        int i = 0;
        while (i<len) {
            inputt[i]=sc.nextInt();
            i++;
        }
        encoding chart = new encoding(inputt);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}
