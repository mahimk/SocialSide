package com.example.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.adapter.CommentAdapter;
import com.example.myapplication.model.CommentDataModel;


import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class CommentActivity extends AppCompatActivity {

      private RecyclerView recyclerView;
      private Button btn_send;
      private EditText message;
      CommentAdapter mAdapter;
      Realm realm;

    String user_Name,profilepic;
    int id;

    ArrayList<CommentDataModel> arrayList_CommentList=new ArrayList<CommentDataModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        realm=Realm.getDefaultInstance();


        recyclerView=findViewById(R.id.recycler_view_comment);
        btn_send=findViewById(R.id.btn_send);
        message=findViewById(R.id.message);

        user_Name=getIntent().getExtras().getString("name");
        profilepic=getIntent().getExtras().getString("profilepic");
        id=getIntent().getExtras().getInt("id");

        mAdapter = new CommentAdapter(arrayList_CommentList, getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        viewData(realm);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment=message.getText().toString();
                
                if(!comment.isEmpty()){

                    String timestamp = convertEpochtime();

                    realm.beginTransaction();
                    CommentDataModel commentDataModel=realm.createObject(CommentDataModel.class);


                    commentDataModel.setId(id);
                    commentDataModel.setName(user_Name);
                    commentDataModel.setComment(comment);
                    commentDataModel.setProfilePic(profilepic);
                    commentDataModel.setTimpstamp(timestamp);
                    realm.commitTransaction();

                    arrayList_CommentList.add(commentDataModel);

                    mAdapter.notifyDataSetChanged();

                    message.setText("");

                    
                }else {

                    Toast.makeText(CommentActivity.this, "Please Enter Comment...!!", Toast.LENGTH_SHORT).show();
                    
                }

               
                
                
            }
        });


    }



     void viewData(Realm realm){

         RealmResults<CommentDataModel> object = realm.where(CommentDataModel.class)
                 .equalTo("id", id)
                 .findAll();
         arrayList_CommentList.addAll(object);
         mAdapter.notifyDataSetChanged();


     }
    




    public static String convertEpochtime() {
        Date date = new Date();
        String epoch = String.valueOf(date.getTime());
        System.out.println(epoch); // 1055545912454
        return epoch;


    }


}
