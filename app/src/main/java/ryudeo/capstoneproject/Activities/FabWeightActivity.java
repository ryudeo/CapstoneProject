package ryudeo.capstoneproject.Activities;

import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;

import ryudeo.capstoneproject.Database.Cols;
import ryudeo.capstoneproject.Database.DbAdapter;
import ryudeo.capstoneproject.R;

public class FabWeightActivity extends AppCompatActivity {

    private DbAdapter mDbAdapter;
    //private ArrayList<Cols> colsList;
    private int weight;

    private ViewGroup mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_weight);

        mContainer = (ViewGroup)findViewById(R.id.activity_fab_weight);
        mDbAdapter = new DbAdapter(this);

        final NumberPicker numberPicker = (NumberPicker) findViewById(R.id.weight_number_picker);

        Button button = (Button) findViewById(R.id.submit_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                weight = numberPicker.getValue(); //get weight value what user choose.
                mDbAdapter.open().insertData("Weight","", weight);
                mDbAdapter.close();
                Snackbar.make(mContainer, "몸무게가 입력되었습니다.", Snackbar.LENGTH_SHORT).show();
            }
        });


    }
}
