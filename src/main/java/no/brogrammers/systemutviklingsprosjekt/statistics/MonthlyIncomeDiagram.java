package no.brogrammers.systemutviklingsprosjekt.statistics;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.StatisticsConnection;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.util.Calendar;

/**
 * Created by Ingunn on 21.04.2016.
 * MonthlyIncomeDiagram class
 * Class for making a line chart diagram from monthlyIncome() method (in StatisticsConnection class)
 */

public class MonthlyIncomeDiagram {
    StatisticsConnection statisticsConnection = new StatisticsConnection();

    /**
     * The method creates a DefaultCategoryDataset. It gets information from a double array from the monthlyIncome() method.
     * @return DefaultCategoryDataset dataset
     */

    private DefaultCategoryDataset createDataset() {
        double[] monthlyIncome = statisticsConnection.monthlyIncome();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String income = "Income";
        String[] months = {"Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);    //Starts with 1
        String curYear = (String.valueOf(currentYear)).substring(2, 4); //Get the two last numbers of the year
        int curYear2 = Integer.parseInt(curYear);                       //Parse curYear to Integer

        int currentMonth = ((Calendar.getInstance().get(Calendar.MONTH)) + 1); //Add 1 because Calendar.MONTH starts with 0

        //Puts the months in right order: 12 months ago -> this month
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

    public ChartPanel createChartPanel() {
        JFreeChart chart = ChartFactory.createLineChart("Monthly Income Last 12 Months", "Month", "Income", createDataset(), PlotOrientation.VERTICAL, true,true,false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);

        return new ChartPanel(chart);
    }

}
