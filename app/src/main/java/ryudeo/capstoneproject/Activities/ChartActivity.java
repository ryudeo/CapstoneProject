package ryudeo.capstoneproject.Activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import ryudeo.capstoneproject.R;

public class ChartActivity extends AppCompatActivity {

    public enum ChartType {

        Water,
        Weight,
        Food,
        Exercise
    }

    public final static String EXTRA_CHART_TYPE = "extraChartType";
    public final static String[] months = new String[]{"10-3주","10-4주","11-1주","11-2주","11-3주","11-4주","12-1주"};

    public final static String[] days = new String[]{"일","월", "화", "수", "목", "금", "토"};
    private LineChartView chartTop;
    private ColumnChartView chartBottom;
    private LineChartData lineData;
    private ColumnChartData columnData;

    private ChartType mCharType;
    private TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_brief);

        mTitleTextView = (TextView)findViewById(R.id.textview_type);
        if (getIntent().hasExtra(EXTRA_CHART_TYPE)) {

            mCharType = (ChartType)getIntent().getSerializableExtra(EXTRA_CHART_TYPE);
            mTitleTextView.setText(getTitle(mCharType));
        }




        setUpGraph();

        generateInitialLineData();
        generateColumnData();

    }

    private void setUpGraph() {

        chartTop = (LineChartView) findViewById(R.id.chart_top);
        chartBottom = (ColumnChartView) findViewById(R.id.chart_bottom);
    }


    private void generateColumnData() {

        int numSubcolumns = 1;
        int numColumns = months.length;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5, getColor(mCharType)));
            }

            axisValues.add(new AxisValue(i).setLabel(months[i]));

            columns.add(new Column(values).setHasLabelsOnlyForSelected(true));
        }

        columnData = new ColumnChartData(columns);

        columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        columnData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(2));

        chartBottom.setColumnChartData(columnData);

        // Set value touch listener that will trigger changes for chartTop.
        chartBottom.setOnValueTouchListener(new ValueTouchListener());

        // Set selection mode to keep selected month column highlighted.
        chartBottom.setValueSelectionEnabled(true);

        chartBottom.setZoomType(ZoomType.HORIZONTAL);

        // chartBottom.setOnClickListener(new View.OnClickListener() {
        //
        // @Override
        // public void onClick(View v) {
        // SelectedValue sv = chartBottom.getSelectedValue();
        // if (!sv.isSet()) {
        // generateInitialLineData();
        // }
        //
        // }
        // });

    }

    /**
     * Generates initial data for line chart. At the begining all Y values are equals 0. That will change when user
     * will select value on column chart.
     */
    private void generateInitialLineData() {
        int numValues = 7;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int i = 0; i < numValues; ++i) {
            values.add(new PointValue(i, 0));
            axisValues.add(new AxisValue(i).setLabel(days[i]));
        }

        Line line = new Line(values);
        line.setColor(getColor(mCharType)).setCubic(true);

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        lineData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(3));

        chartTop.setLineChartData(lineData);

        // For build-up animation you have to disable viewport recalculation.
        chartTop.setViewportCalculationEnabled(false);

        // And set initial max viewport and current viewport- remember to set viewports after data.
        Viewport v = new Viewport(0, 110, 6, 0);
        chartTop.setMaximumViewport(v);
        chartTop.setCurrentViewport(v);

        chartTop.setZoomType(ZoomType.HORIZONTAL);
    }

    private void generateLineData(int color, float range) {
        // Cancel last animation if not finished.
        chartTop.cancelDataAnimation();

        // Modify data targets
        Line line = lineData.getLines().get(0);// For this example there is always only one line.
        line.setColor(color);
        for (PointValue value : line.getValues()) {
            // Change target only for Y value.
            value.setTarget(value.getX(), (float) Math.random() * range);
        }

        // Start new data animation with 300ms duration;
        chartTop.startDataAnimation(300);
    }

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            generateLineData(value.getColor(), 100);
        }

        @Override
        public void onValueDeselected() {

            generateLineData(getColor(mCharType), 0);

        }
    }


    private int getColor(ChartType type) {

        switch (type) {

            case Water:
                return getResources().getColor(R.color.colorEventWater);
            case Exercise:
                return getResources().getColor(R.color.colorEventExercise);
            case Food:
                return getResources().getColor(R.color.colorEventFood);
            case Weight:
                return getResources().getColor(R.color.colorPrimary);
            default:
                return getResources().getColor(R.color.colorPrimary);
        }
    }

    private String getTitle(ChartType type) {

        switch (type) {

            case Water:
                return "물 섭취량 그래프";
            case Exercise:
                return "운동량 그래프";
            case Food:
                return "섭취한 음식 변화량 그래프";
            case Weight:
                return "몸무게 변화량";
            default:
                return "몸무게 변화량";
        }
    }


}






