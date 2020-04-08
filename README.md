# InternetCheck

Step 1:
Add below code in your root build.gradle:

allprojects {

		repositories {

			maven { url 'https://jitpack.io' }

		}

	}

Step 2:
Add the dependency in applications build.gradle

dependencies {

	        implementation 'com.github.dattatraynande:InternetCheck:1.0.0'

	}

Step 3:
Implement your Activity with "Connectivity.ConnectivityListener" 

public class MainActivity extends AppCompatActivity implements Connectivity.ConnectivityListener 


Add code in onCreate method of Activity

	Connectivity connectivity;
    int interval = 3;// Time interval in sec.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectivity = new Connectivity(this, interval, this);
        connectivity.startConnectivityCheck();
    }
