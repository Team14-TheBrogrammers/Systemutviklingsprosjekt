package no.brogrammers.systemutviklingsprosjekt.statistics;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.StatisticsConnection;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;

/**
 * Created by Ingunn on 21.04.2016.
 */
public class MonthlyIncomeDiagram extends JFrame {
    StatisticsConnection statisticsConnection = new StatisticsConnection();

    public MonthlyIncomeDiagram() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JFreeChart lineChart = ChartFactory.createLineChart("Monthly Income Last 12 Months", "Month", "Income", createDataset(), PlotOrientation.VERTICAL, true,true,false);
        ChartPanel chartPanel = new ChartPanel( lineChart );
        setContentPane(chartPanel);
        //setTitle("Diagram");
        setVisible(true);
        setSize(500, 500);
    }

    private DefaultCategoryDataset createDataset() {
        double[] monthlyIncome = statisticsConnection.monthlyIncome();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        String income = "Income";

        dataset.addValue(monthlyIncome[0], income, "January");
        dataset.addValue(monthlyIncome[1], income, "February");
        dataset.addValue(monthlyIncome[2], income, "March");
        dataset.addValue(monthlyIncome[3], income, "April");
        dataset.addValue(monthlyIncome[4], income, "May");
        dataset.addValue(monthlyIncome[5], income, "June");
        dataset.addValue(monthlyIncome[6], income, "July");
        dataset.addValue(monthlyIncome[7], income, "August");
        dataset.addValue(monthlyIncome[8], income, "September");
        dataset.addValue(monthlyIncome[9], income, "October");
        dataset.addValue(monthlyIncome[10], income, "November");
        dataset.addValue(monthlyIncome[11], income, "December");

        return dataset;
    }

}
