package x.miempresa.com.ejemploproyectoescuelait;

import androidx.appcompat.app.AppCompatActivity;  // ← actualizado
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TweetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creamos una lista de tweets
        Tweet[] tweets = new Tweet[]{
                new Tweet(R.drawable.ic_launcher, "El primer tweet"),
                new Tweet(R.drawable.ic_launcher, "El segundo tweet"),
                new Tweet(R.drawable.ic_launcher, "El tercero tweet"),
                new Tweet(R.drawable.ic_launcher, "El cuarto tweet"),
                new Tweet(R.drawable.ic_launcher, "El quinto tweet"),
                new Tweet(R.drawable.ic_launcher, "El sexto tweet"),
                new Tweet(R.drawable.ic_launcher, "El séptimo tweet"),
                new Tweet(R.drawable.ic_launcher, "El octavo tweet"),
                new Tweet(R.drawable.ic_launcher, "El noveno tweet"),
                new Tweet(R.drawable.ic_launcher, "El décimo tweet")
        };

        // Instanciamos el adaptador
        adapter = new TweetAdapter(this, R.layout.listiview_item_row, tweets);

        // Buscamos el ListView y le agregamos un header si hay
        listView = findViewById(R.id.listview);
        View header = getLayoutInflater().inflate(R.layout.list_header_row, null);
        listView.addHeaderView(header);

        // Asignamos el adaptador al ListView
        listView.setAdapter(adapter);

        // Manejo del clic en cada item del ListView
        listView.setOnItemClickListener((parent, view, position, id) -> {
            TextView texto = view.findViewById(R.id.textView);
            Toast.makeText(getApplicationContext(), texto.getText(), Toast.LENGTH_SHORT).show();
        });
    }
}
