package sg.edu.rp.c346.id20002694.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd;
    Button btnClear;
    Button btnDel;
    Spinner spinner;
    ListView lvTask;
    //clement kwa 20002694

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.etTask);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        btnDel = findViewById(R.id.btnDel);
        spinner = findViewById(R.id.spinner);
        lvTask = findViewById(R.id.lvTask);

        ArrayList <String> alTasks = new ArrayList<String>();

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);

        lvTask.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.add(etTask.getText().toString());
                Log.d("MainActivity","Added task, size of array "+alTasks.size());
                adapter.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int taskNum= alTasks.size();
                if(taskNum!=0){
                    int size= alTasks.size()-1;
                    for(int i=size; i>-1; i--){
                        alTasks.remove(i);
                    }
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "There are no tasks to be cleared", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delNum= Integer.parseInt(etTask.getText().toString());
                if(delNum>-1 && delNum<alTasks.size()){
                    alTasks.remove(delNum);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid index", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    btnAdd.setEnabled(true);
                    btnDel.setEnabled(false);
                    etTask.setHint("Enter a new task");
                }
                else if(position==1){
                    btnDel.setEnabled(true);
                    btnAdd.setEnabled(false);
                    etTask.setHint("Enter the index of the task to be removed");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}