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
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Ingunn on 21.04.2016.
 */

public class MostPopularRecipesDiagram extends JFrame {
    StatisticsConnection statisticsConnection = new StatisticsConnection();

    public MostPopularRecipesDiagram() {//(String applicationTitle) {
        //super( "Car Usage MostPopularRecipesDiagram" );
        JFreeChart barChart = ChartFactory.createBarChart("Top 10 Most Ordered Recipes", "Recipe", "Times Ordered", createDataset(), PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setShadowVisible(false);
        renderer.setDrawBarOutline(true);

        /*XYPlot plot2 = (XYPlot) barChart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot2.getRenderer();
        renderer.setShadowVisible(false);*/

        barChart.setBackgroundImageAlpha(0.2f);
        barChart.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);


        pack();
        setVisible(true);
    }

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


    //RefineryUtilities.centerFrameOnScreen( chart );
}
