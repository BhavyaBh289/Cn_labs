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
        super.pack( );
        super.setVisible( true );

    }

    private XYDataset createDataset(int[] input ) {
        final XYSeries dataa = new XYSeries( "input" );
        int time = 0;
        dataa.add( 0.0 , 0.0 );

        for (int i :input){
            dataa.add( time , i );
            time++;
            dataa.add(time,i);
        }
        dataa.add(time,0);
        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries( dataa );
        return dataset;
    }

    public static void main( String[ ] args ) {
        Scanner sc = new Scanner(System.in);
        String lent = sc.next();
        String[] digits = String.valueOf(lent).split("");
        int len = digits.length;
        int[] input  = new int[len];
        for(int i=0;i<len;i++){
            input[i]=Integer.parseInt(digits[i]);
        }
        encoding chart = new encoding(input);
    }
}
