# InternetCheck

#Step 1
Add it in your root build.gradle at the end of repositories:
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

#Step 2
Add the dependency in applications build.gradle
dependencies {
	        implementation 'com.github.dattatraynande:InternetCheck:Tag'
	}

#Step 3
Implement your Activity with "Connectivity.ConnectivityListener" 

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
