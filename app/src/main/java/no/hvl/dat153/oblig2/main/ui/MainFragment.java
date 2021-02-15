
// Denne klassen er brukt for testing, fragmentet kommer fra boken. Har ikke slettet den pga bruker den som hjelpemiddel avogtil, kopierer koide osv

//package no.hvl.dat153.oblig2.main.ui;
//
//
//import androidx.lifecycle.ViewModelProviders;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.Observer;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import no.hvl.dat153.oblig2.R;
//import no.hvl.dat153.oblig2.main.data.Student;
//
//import java.util.List;
//import java.util.Locale;
//
//public class MainFragment extends Fragment {
//
//    private StudentViewModel mViewModel;
//    private StudentListAdapter adapter;
//
//    private TextView StudentId;
//    private EditText StudentName;
//    private EditText StudentQuantity;
//
//    public static MainFragment newInstance() {
//        return new MainFragment();
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.main_fragment, container, false);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
//
////        StudentId = getView().findViewById(R.id.studentID);
////        StudentName = getView().findViewById(R.id.studentName);
////        StudentQuantity = getView().findViewById(R.id.productQuantity);
//
//        //mViewModel.deleteAllStudents();
//        //Student s = new Student("sander", BitmapFactory.decodeResource(this.getResources(), R.drawable.cat));
//        //mViewModel.insertStudent(s);
//
//
//        //listenerSetup();
//        observerSetup();
//        recyclerSetup();
//
//    }
//
////    private void listenerSetup() {
////
////        Button addButton = getView().findViewById(R.id.addingButton);
////        Button findButton = getView().findViewById(R.id.findButton);
////        Button deleteButton = getView().findViewById(R.id.deleteButton);
////
////        addButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////
////                String name = StudentName.getText().toString();
////                String quantity = StudentQuantity.getText().toString();
////
////                if (!name.equals("") && !quantity.equals("")) {
////                    Student Student = new Student(name,
////                            Integer.parseInt(quantity)); //ENDRE QUANTITY TIL IMAGE
////                    mViewModel.insertStudent(Student);
////                    clearFields();
////                } else {
////                    StudentId.setText("Incomplete information");
////                }
////            }
////        });
////
////        findButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                mViewModel.findStudent(StudentName.getText().toString());
////            }
////        });
////
////        deleteButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                mViewModel.deleteStudent(StudentName.getText().toString());
////                clearFields();
////            }
////        });
////    }
//
//
//    private void observerSetup() {
//
//        mViewModel.getAllStudents().observe(getViewLifecycleOwner(),
//                new Observer<List<Student>>() {
//                    @Override
//                    public void onChanged(@Nullable final List<Student> Students) {
//                        adapter.setStudentList(Students);
//                    }
//                });
//
//        mViewModel.getSearchResults().observe(getViewLifecycleOwner(),
//                new Observer<List<Student>>() {
//                    @Override
//                    public void onChanged(@Nullable final List<Student> Students) {
//
//                        if (Students.size() > 0) {
//                            StudentId.setText(String.format(Locale.US, "%d",
//                                    Students.get(0).getId()));
//                            StudentName.setText(Students.get(0).getName());
//
//                            StudentQuantity.setText(String.format(Locale.US, "%d",
//                                    Students.get(0).getImg()));
//
//                            //StudentQuantity.setText("zero");
//
//                        } else {
//                            StudentId.setText("No Match");
//                        }
//                    }
//                });
//    }
//
//    private void recyclerSetup() {
//
//        RecyclerView recyclerView;
//
//        adapter = new StudentListAdapter(R.layout.student_list_item);
//        recyclerView = getView().findViewById(R.id.student_recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);
//    }
//
//    private void clearFields() {
//        StudentId.setText("");
//        StudentName.setText("");
//        StudentQuantity.setText("");
//    }
//
//
//
//
//
//
//
//}