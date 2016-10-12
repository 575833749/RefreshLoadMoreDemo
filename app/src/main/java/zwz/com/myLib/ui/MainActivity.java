
package zwz.com.myLib.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import zwz.com.myLib.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnExpandListView;
    private Button btnRecyclerViewRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRecyclerViewRefresh= (Button) findViewById(R.id.btnRecyclerViewRefresh);
        btnExpandListView= (Button) findViewById(R.id.btnExpandListView);

        btnRecyclerViewRefresh.setOnClickListener(this);
        btnExpandListView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()) {
            case R.id.btnExpandListView:
                intent=new Intent(MainActivity.this,ExpandListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btnRecyclerViewRefresh:
                intent=new Intent(MainActivity.this,RecyclerViewActivity.class);
                startActivity(intent);

                break;

            default:
                break;
        }
    }
}
