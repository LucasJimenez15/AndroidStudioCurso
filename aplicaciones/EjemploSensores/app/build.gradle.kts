plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.ejemplosensores"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ejemplosensores"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging {
        resources {
            // Con pickFirst indicamos que si aparece el mismo archivo en varias librerías,
            // tome el primero y no genere error por conflicto.
            resources.pickFirsts.add("kotlin/reflect/reflect.kotlin_builtins")
            resources.pickFirsts.add("kotlin/annotation/annotation.kotlin_builtins")
            resources.pickFirsts.add("kotlin/coroutines/coroutines.kotlin_builtins")
            resources.pickFirsts.add("kotlin/collections/collections.kotlin_builtins")
            resources.pickFirsts.add("META-INF/io.netty.versions.properties")
            resources.pickFirsts.add("kotlin/internal/internal.kotlin_builtins")
            resources.pickFirsts.add("kotlin/kotlin.kotlin_builtins")
            resources.pickFirsts.add("kotlin/ranges/ranges.kotlin_builtins")
            // Aquí excluimos (excludes) el archivo META-INF/INDEX.LIST que genera el warning
            excludes += "META-INF/INDEX.LIST"

            /*¿Por qué usar pickFirst? Porque varios módulos o librerías Kotlin que usas incluyen archivos .kotlin_builtins que describen ciertas características de las librerías compiladas.Cuando hay más de uno con el mismo nombre, Gradle no sabe cuál usar y da warning o error. pickFirst dice: "Si hay varios, usa el primero que encuentres y descarta el resto". Eso funciona porque esos archivos son idénticos o compatibles.*/
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.appcrawler.platform) {
        exclude(group = "com.google.code.findbugs", module = "jsr305")
        exclude(group = "com.google.auto.value", module = "auto-value")
    }
}

/*Explicación de los cambios:
excludes += "META-INF/INDEX.LIST" dentro de packaging.resources:
Le indica a Gradle que ignore ese archivo duplicado durante el empaquetado para evitar el warning y posibles conflictos.
Esto es mejor que excluir el archivo globalmente, porque solo afecta a este problema específico.

pickFirst("..."):
Ya tenías varios pickFirst para otros archivos duplicados comunes en librerías Kotlin y Netty. Eso significa que si estos archivos aparecen más de una vez en diferentes librerías, Gradle usará el primero y no dará error.
Esto evita conflictos en archivos de metadatos no críticos.*/