package no.brogrammers.systemutviklingsprosjekt.statistics;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.StatisticsConnection;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Ingunn on 21.04.2016.
 * Class for diagram of the top 10 most ordered recipes.
 */

public class MostPopularRecipesDiagram {
    StatisticsConnection statisticsConnection = new StatisticsConnection();


    /**
     * Method for making a dataset for most popular recipes
     * @return DefaultCategoryDataset dataset
     */
    private CategoryDataset createDataset() {
        String recipe = "Recipe";
        String[][] recipes = statisticsConnection.top10Recipes();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i < recipes.length; i++) {
            for (int j = 0; j < recipes[i].length; j++) {
                if(recipes[i][j] != null) {
                    String rec = recipes[i][1];
                    dataset.addValue((Double.parseDouble(rec)), recipe, recipes[i][0]);
                }
            }
        }
        return dataset;
    }

    public ChartPanel createChartPanel() {
        JFreeChart barChart = ChartFactory.createBarChart("Top 10 Most Ordered Recipes", "Recipe", "Times Ordered", createDataset(), PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setShadowVisible(false);
        renderer.setDrawBarOutline(true);

        barChart.setBackgroundImageAlpha(0.2f);
        barChart.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        return chartPanel;
    }
}