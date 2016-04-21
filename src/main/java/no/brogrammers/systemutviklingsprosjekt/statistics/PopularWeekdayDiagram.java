package no.brogrammers.systemutviklingsprosjekt.statistics;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.StatisticsConnection;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ingunn on 21.04.2016.
 */
public class PopularWeekdayDiagram extends JFrame {
    StatisticsConnection statisticsConnection = new StatisticsConnection();

    public PopularWeekdayDiagram() {
        JFreeChart barChart = ChartFactory.createBarChart("Most Popular Weekdays", "Weekday", "Times Ordered", createDataset(), PlotOrientation.VERTICAL, false, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        setSize(800, 600);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setShadowVisible(false);
        renderer.setDrawBarOutline(true);
        renderer.setSeriesPaint(0, Color.GREEN);

        setVisible(true);
    }

    private CategoryDataset createDataset() {
        int[] deliveryDates = statisticsConnection.popularWeekdaysDeliveryDate();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String order = "Order";

        String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for(int i = 0; i < deliveryDates.length; i++) {
            dataset.addValue(deliveryDates[i], order, weekdays[i]);
        }

        return dataset;
    }
}
