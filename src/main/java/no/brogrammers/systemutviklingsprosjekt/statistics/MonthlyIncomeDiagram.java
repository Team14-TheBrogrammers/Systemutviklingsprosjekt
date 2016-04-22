package no.brogrammers.systemutviklingsprosjekt.statistics;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.StatisticsConnection;

import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

/**
 * Created by Ingunn on 21.04.2016.
 */
public class MonthlyIncomeDiagram extends JFrame {
    StatisticsConnection statisticsConnection = new StatisticsConnection();

    public MonthlyIncomeDiagram() {
        setContentPane(createChartPanel());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000, 500);
    }


    public ChartPanel createChartPanel() {
        JFreeChart chart = ChartFactory.createLineChart("Monthly Income Last 12 Months", "Month", "Income", createDataset(), PlotOrientation.VERTICAL, true,true,false);
        //chart.setBackgroundPaint(Color.WHITE);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);

        return new ChartPanel(chart);
    }

    /**
     * The method
     * @return DefaultCategoryDataset dataset
     */

    private DefaultCategoryDataset createDataset() {
        double[] monthlyIncome = statisticsConnection.monthlyIncome();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String income = "Income";
        String[] months = {"Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
        int currentYear = Calendar.getInstance().get(Calendar.YEAR); //Starts with 1
        String curYear = (String.valueOf(currentYear)).substring(2, 4);
        int curYear2 = Integer.parseInt(curYear);
        System.out.println(curYear);
        int currentMonth = ((Calendar.getInstance().get(Calendar.MONTH)) + 1); //Add 1 because Calendar.MONTH starts with 0

        for (int i = 0; i < monthlyIncome.length; i++) {
            int nextMonth = (i + currentMonth);

            if(nextMonth < monthlyIncome.length) {
                dataset.addValue(monthlyIncome[nextMonth], income, months[nextMonth] + " -" + (curYear2-1));
            }
            if(nextMonth > monthlyIncome.length) {
                dataset.addValue(monthlyIncome[i-currentMonth-currentMonth-1], income, months[i-currentMonth-currentMonth-1] + " -" + curYear2);
            }
        }
        dataset.addValue(monthlyIncome[currentMonth-1], income, months[currentMonth-1] + " -" + curYear2);

        return dataset;
    }

}
