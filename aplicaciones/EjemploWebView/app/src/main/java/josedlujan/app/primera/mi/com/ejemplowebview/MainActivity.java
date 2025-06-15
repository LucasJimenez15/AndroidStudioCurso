package josedlujan.app.primera.mi.com.ejemplowebview;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wb = findViewById(R.id.webview); // No hace falta el cast con AndroidX

<<<<<<< HEAD
        // Definimos la URL que se cargará en el WebView.|
        // En este caso se trata de un archivo HTML local que está dentro de la carpeta `assets`.
        String url = "file:///android_asset/index.html";
        // También se podría usar una URL en línea, como una página web real:
        // String url = "https://www.escuela.it";
=======
        String url = "file:///android_asset/PremiumGymApp/web/index.xhtml";
        //String url = "https://www.escuela.it";
>>>>>>> 66a49e072d16da3167898f2a154109ac288bf2ef

        /*🔧 Métodos más importantes de WebSettings en WebView:
        setJavaScriptEnabled(true)	Habilita JavaScript en las páginas web (esencial para sitios modernos).
        setBuiltInZoomControls(true)	Activa los botones de zoom (acercar/alejar).
        setDisplayZoomControls(false)	Oculta los botones de zoom en pantalla (pero permite hacer zoom con gestos).
        setLoadWithOverviewMode(true)	Ajusta el contenido web al ancho de la pantalla.
        setUseWideViewPort(true)	Permite al WebView usar el viewport de la página como si fuera un navegador.
        setDomStorageEnabled(true)	Habilita almacenamiento tipo localStorage / sessionStorage (HTML5).
        setAllowFileAccess(true)	Permite acceso a archivos locales (por defecto está activado).
        setCacheMode(WebSettings.LOAD_NO_CACHE)	Desactiva la caché del navegador. Puedes cambiarlo según el comportamiento deseado.
        setSupportZoom(true)	Habilita o desactiva la función de zoom general.
        setTextZoom(100)	Define el nivel de zoom del texto (por defecto 100%).*/

        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setBuiltInZoomControls(true);


        /*Aqui estamos haciendo la carga de la url y tambien evitamos abrir enlaces fuera del WebView, es decir abrir solo una vez el navegador y no repetidas veces y que se haga dentro de la aplicacion*/
        wb.setWebViewClient(new WebViewClient() {
            //aqui setwebviewclient dice que se comportara como un cliente y creamos uno con el new wevviewclient
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //este metodo dice o significa que se deeberia sobreescribir la url que nosotros cargamos, Este código le dice al WebView que cargue cualquier enlace dentro de sí mismo en lugar de abrirlo en el navegador externo del celular.
                Uri uri = request.getUrl();
                String url = uri.toString();

                if (url.startsWith("http") || url.startsWith("https")) {
                    view.loadUrl(url);
                    return false; // WebView puede manejarlo
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    try {
                        view.getContext().startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        // Si no hay app que maneje la URL
                        Toast.makeText(view.getContext(), "No hay app para abrir este enlace", Toast.LENGTH_SHORT).show();
                    }
                    return true; // lo manejamos nosotros
                }
            }
        });
        wb.loadUrl(url);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); // Asegurate que existe
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Acciones del menú
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Opcional: manejar retroceso para navegar dentro del WebView
    @Override
    public void onBackPressed() {
        if (wb != null && wb.canGoBack()) {
            wb.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
<<<<<<< HEAD


/*🧠 ¿Por qué se usa WebView y por qué se configura así?
Antes, para mostrar una web, se necesitaba salir de la app y abrir un navegador externo, lo cual sacaba al usuario de la experiencia de la app.
El WebView permite mostrar contenido web dentro de la app, como si fuera una mini-página web embebida.
Al agregar un WebViewClient, evitamos que los enlaces se abran fuera de la app, manteniendo el usuario siempre dentro de nuestro entorno.
Activar JavaScript y controles de zoom es importante porque muchas páginas hoy no se ven correctamente sin estos ajustes.
WebView es un componente que permite mostrar páginas web dentro de tu app Android, como un navegador integrado.
Puede cargar motores de búsqueda (como Google), pero no es uno en sí mismo.*/
=======
>>>>>>> 66a49e072d16da3167898f2a154109ac288bf2ef
