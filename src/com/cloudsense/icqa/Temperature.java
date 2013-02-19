package com.cloudsense.icqa;

import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;



public class Temperature {
	private GraphicalView mChartView;
	
	 XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();

     XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

     XYSeries mCurrentSeries;

     XYSeriesRenderer mCurrentRenderer;

    public void initChart() {
        mCurrentSeries = new XYSeries("Sample Data");
        mDataset.addSeries(mCurrentSeries);
        mCurrentRenderer = new XYSeriesRenderer();
        mRenderer.addSeriesRenderer(mCurrentRenderer);
    }

    public void addSampleData() {
        mCurrentSeries.add(1, 2);
        mCurrentSeries.add(2, 3);
        mCurrentSeries.add(3, 2);
        mCurrentSeries.add(4, 5);
        mCurrentSeries.add(5, 4);
    }

	public GraphicalView getmChartView() {
		return mChartView;
	}

	public void setmChartView(GraphicalView mChartView) {
		this.mChartView = mChartView;
	}
    
    

		
}
