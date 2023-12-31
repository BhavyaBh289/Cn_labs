package lab_1;
import java.awt.Color;
import java.awt.BasicStroke;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class sample extends ApplicationFrame {

    public sample( String applicationTitle, String chartTitle ) {
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle ,
                "Time" ,
                "Voltage" ,
                createDataset() ,
                PlotOrientation.VERTICAL ,
                true , true , false);

        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        final XYPlot plot = xylineChart.getXYPlot( );

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );
//        renderer.setSeriesPaint( 1 , Color.GREEN );
//        renderer.setSeriesPaint( 2 , Color.YELLOW );
        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
//        renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
//        renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
        plot.setRenderer( renderer );
        setContentPane( chartPanel );
    }

    private XYDataset createDataset( ) {
        final XYSeries firefox = new XYSeries( "101" );
        firefox.add( 0.0 , 0.0 );
        firefox.add( 0.0 , 1.0 );
        firefox.add( 1.0 , 1.0 );
        firefox.add( 1.0 , 0.0 );
        firefox.add( 2.0 , 0.0 );
        firefox.add( 2.0 , 1.0 );
        firefox.add( 3.0 , 1.0 );
        firefox.add( 3.0 , 0.0 );
//        firefox.add( 3.0 , 3.0 );

//        final XYSeries chrome = new XYSeries( "Chrome" );
//        chrome.add( 1.0 , 4.0 );
//        chrome.add( 2.0 , 5.0 );
//        chrome.add( 3.0 , 6.0 );
//
//        final XYSeries iexplorer = new XYSeries( "InternetExplorer" );
//        iexplorer.add( 3.0 , 4.0 );
//        iexplorer.add( 4.0 , 5.0 );
//        iexplorer.add( 5.0 , 4.0 );

        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries( firefox );
//        dataset.addSeries( chrome );
//        dataset.addSeries( iexplorer );
        return dataset;
    }

    public static void main( String[ ] args ) {
        sample chart = new sample("Signal",
                "sample Signal");
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}