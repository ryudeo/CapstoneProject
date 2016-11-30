package ryudeo.capstoneproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ryudeo.capstoneproject.Database.DbAdapter;
import ryudeo.capstoneproject.R;

public class FabWaterActivity extends AppCompatActivity {

    private DbAdapter mDbAdapter = new DbAdapter(this); //데이터베이스
    private Button submitButton;
    private EditText waterEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_water);

        setUpLayout();
    }

    void setUpLayout() {

        submitButton = (Button)findViewById(R.id.submit_button);
        waterEditText = (EditText)findViewById(R.id.waterEditText);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantity = Integer.parseInt(waterEditText.getText().toString());
                mDbAdapter.open().insertData("Water", quantity); //Water type을 db에 insert
                mDbAdapter.close();
            }
        });
    }
}
