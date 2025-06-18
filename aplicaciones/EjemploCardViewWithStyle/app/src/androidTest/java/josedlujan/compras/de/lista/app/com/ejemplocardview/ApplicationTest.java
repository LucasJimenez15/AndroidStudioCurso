package josedlujan.compras.de.lista.app.com.ejemplocardview;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Clase de prueba básica para verificar el contexto de la aplicación.
 * Esta clase reemplaza el uso obsoleto de ApplicationTestCase.
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest {

    @Test
    public void testUseAppContext() {
        // Obtenemos el contexto de la aplicación bajo prueba
        Context appContext = ApplicationProvider.getApplicationContext();

        // Verificamos que el nombre del paquete sea el esperado
        assertEquals("josedlujan.compras.de.lista.app.com.ejemplocardview", appContext.getPackageName());
    }
}
