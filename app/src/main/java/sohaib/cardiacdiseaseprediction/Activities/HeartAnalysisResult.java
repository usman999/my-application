package sohaib.cardiacdiseaseprediction.Activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import sohaib.cardiacdiseaseprediction.R;

public class HeartAnalysisResult extends AppCompatActivity {

    private String Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_analysis_result);
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            AlertDialog.Builder alert = new AlertDialog.Builder(HeartAnalysisResult.this);
            final EditText edittext = new EditText(HeartAnalysisResult.this);
            alert.setMessage("Enter Your Name");
            alert.setView(edittext);
            alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    Name = edittext.getText().toString();
                    if (Name.isEmpty()){
                        Toast.makeText(HeartAnalysisResult.this,"Please Enter a Name",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(HeartAnalysisResult.this,"Result Saved",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            alert.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
