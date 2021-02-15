package no.hvl.dat153.oblig2.main.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import no.hvl.dat153.oblig2.R;
import no.hvl.dat153.oblig2.main.data.Student;
import no.hvl.dat153.oblig2.main.ui.StudentListAdapter;
import no.hvl.dat153.oblig2.main.ui.StudentViewModel;

public class DatabaseActivity extends AppCompatActivity {



//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonDelete;
    private EditText editTextDelete;

    private StudentViewModel mViewModel;
    private StudentListAdapter adapter;

    public static ArrayList<Student> pList;

    //Endre adapter og viewmodel her slik at recyclerview kan funke slik som den skal


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        mViewModel = new ViewModelProvider(this).get(StudentViewModel.class);


        observerSetup();
        recyclerSetup();


        buttonDelete = findViewById(R.id.button_delete);
        editTextDelete = findViewById(R.id.edittext_delete);






deleteItemOnclick();

    }


    public void deleteItemOnclick() {
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.deleteStudentId(Integer.parseInt(editTextDelete.getText().toString()));

            }
        });
    }

//    public void deleteItem(int pos) {
////pos-1 slik at posisjon 1 for bruker blir 1 for program, listen starter fra 1 osv...
//        Database.removePerson(pList.get(pos - 1)); //Her sletter vi personen fra databasen, er en bug hvor "Gray blir lagt til i recycler view, men ikke i databasen.... DETTE ER BUGGEN SOM STÅR OVER I LINJE 52
//        mAdapter.notifyItemRemoved(pos - 1);
//    }

    public void goAdd(View View) {
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }

    public void goMenu(View View) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    public void resetView(View View) {
        Intent i = new Intent(this, DatabaseActivity.class);
        startActivity(i);
    }



    private void observerSetup() {

        mViewModel.getAllStudents().observe(this,
                new Observer<List<Student>>() {
                    @Override
                    public void onChanged(@Nullable final List<Student> Students) {
                        pList = new ArrayList<Student>(Students);
                        adapter.setStudentList(Students);
                    }
                });


    }

    public void deleteItem(int id) {
    buttonDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mViewModel.deleteStudentId(id);

        }
    });
}


        private void recyclerSetup() {

        RecyclerView recyclerView;

        adapter = new StudentListAdapter(R.layout.student_list_item);
        recyclerView = findViewById(R.id.recyclerView);

//        mLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


//    public void buildRecyclerView() {
//        mRecyclerView = findViewById(R.id.recyclerView);
//        //mRecyclerView.setHasFixedSize(true);  //wont change in size
//        mLayoutManager = new LinearLayoutManager(this);
//
//        mAdapter = new AdapterDatabas(pList);
//
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
//    }


}