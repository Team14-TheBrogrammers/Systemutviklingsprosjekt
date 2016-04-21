package no.brogrammers.systemutviklingsprosjekt.statistics;

import javax.swing.*;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.StatisticsConnection;
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.*;

import java.awt.*;

/**
 * Created by Ingunn on 20.04.2016.
 *
 */

public class CustomerTypeDiagram extends JFrame {
    StatisticsConnection statistics = new StatisticsConnection();

    public CustomerTypeDiagram() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setContentPane(mainPanel);
        setContentPane(createDemoPanel(createDataset()));
        setTitle("Diagram");
        setVisible(true);
        setSize(500, 500);
    }


    private PieDataset createDataset() {
        double privateCustomer = statistics.percentOfPrivateCustomers();
        DefaultPieDataset dataset = new DefaultPieDataset( );

        if(privateCustomer != -1) {
            double company = 100-privateCustomer;
            dataset.setValue("Private Customer", privateCustomer);
            dataset.setValue("Company", company);
        } else {
            dataset.setValue("Error", 100);
        }
        return dataset;
    }

    public JPanel createDemoPanel(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart("Customers", dataset, true, true, false);
        chart.getTitle().setFont(new Font("Arial", 0, 24));

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setShadowXOffset(0);
        plot.setShadowYOffset(0);
        plot.setLabelBackgroundPaint(Color.WHITE);

        ChartPanel chartPanel = new ChartPanel(chart);

        return new ChartPanel(chart);
    }
}