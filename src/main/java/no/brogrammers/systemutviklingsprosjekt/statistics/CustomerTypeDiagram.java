package no.brogrammers.systemutviklingsprosjekt.statistics;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.StatisticsConnection;
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.*;
import java.awt.*;

/**
 * Created by Ingunn on 20.04.2016.
 * CustomerTypeDiagram class
 * Class for making a pie chart diagram from percentOfPrivateCustomers() method (in StatisticsConnection class)
 */

public class CustomerTypeDiagram {
    StatisticsConnection statistics = new StatisticsConnection();

    /**
     * The method creates a PieDataset. It gets percent of private customers from the percentOfPrivateCustomers() method.
     * @return PieDataset dataset
     */

    private PieDataset createDataset() {
        double privateCustomer = statistics.percentOfPrivateCustomers();
        DefaultPieDataset dataset = new DefaultPieDataset( );

        if(privateCustomer != -1) {
            double company = 100-privateCustomer;       //Percentage of companies: 100-percentage of private customers
            dataset.setValue("Private Customer", privateCustomer);
            dataset.setValue("Company", company);
        } else {
            dataset.setValue("Error", 100);
        }
        return dataset;
    }

    public ChartPanel createChartPanel() {
        JFreeChart chart = ChartFactory.createPieChart("Customers", createDataset(), true, true, false);
        chart.getTitle().setFont(new Font("Arial", 0, 24));
        Color color1 = new Color(138, 80, 185);         //Purple
        Color color2 = new Color(135, 203, 173);        //Seagreen

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setShadowXOffset(0);                       //Remove shadow
        plot.setShadowYOffset(0);                       //Remove shadow
        plot.setLabelBackgroundPaint(Color.WHITE);
        plot.setSectionPaint("Company", color1);
        plot.setSectionPaint("Private Customer", color2);

        ChartPanel chartPanel = new ChartPanel(chart);
        return new ChartPanel(chart);
    }
}