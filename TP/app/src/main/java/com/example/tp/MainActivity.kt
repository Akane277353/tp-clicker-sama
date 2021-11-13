package com.example.tp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Geocoder
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.tp.Fragments.*
import com.example.tp.Model.PlayableChar
import com.example.tp.Model.Player
import com.example.tp.Outils.ObtPib
import com.example.tp.Outils.Timer
import com.example.tp.Outils.OnSwipeTouchListener
import com.example.tp.Stockage.CharStorage
import com.example.tp.Stockage.PlayerStorage
import com.google.android.gms.tasks.Task
import java.util.*
import kotlin.collections.ArrayList
import android.widget.Toast
import android.location.Address
import com.google.android.gms.location.*
import android.text.TextUtils
import android.util.Log
import java.io.IOException
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var layout: LinearLayout

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    companion object{
        var pays = "Inconnue"
    }

    val pib = ObtPib(this)
    var multi = 1

    var swipe = true

    var charList = arrayListOf<PlayableChar>()

    //Création du joueur
    var player = Player(
        1,"Gontran", 0, 1, 0, 0, 1, false
    )

    //Création des variables des personnages
    lateinit var char1 :PlayableChar
    lateinit var char2 :PlayableChar
    lateinit var char3 :PlayableChar
    lateinit var char4 :PlayableChar
    lateinit var char5 :PlayableChar

    //Création de la variable sensorManager

    lateinit var sensorManager :SensorManager

    private var updateFragment = UpdateFragment(this, charList)

    //Fonction qui update le nombre de gold
    fun updateGold(){
        player.gold = player.gold + 10 * player.multiplicateur + multi//Augmente le nombre de gold dans le joueur
        findViewById<TextView>(R.id.goldText).text = player.gold.toString() //Modifie l'affichage
        update() //Sauvegarde
    }

    fun updateXp(){
        player.xp = player.xp + 10 //Augmente le nombre d xp du joueur
        findViewById<TextView>(R.id.xpText).text = player.xp.toString() //Modifie l'affichage
        update() //Sauvegarde
    }

    fun updatenbChar(){
        player.nbPerso = player.nbPerso + 1 //Augmente le nombre d xp du joueur
        update() //Sauvegarde
    }

    //Fonction qui update les personnage dans la liste
    fun updateChar(newChar: PlayableChar, pos: Int){
        charList[pos] = newChar
    }

    //Fonction qui permet d'acheter
    fun achat(price: Int){
        if (player.gold >= price){
            player.gold = player.gold - price //Modifie nombre de gold
            findViewById<TextView>(R.id.goldText).text = player.gold.toString() //Modifie l'affichage
            update() //Sauvegarde
        }
    }

    //Fonction qui update le nombre de clique
    fun updateNbClique(){
        player.nbClique = player.nbClique + 1 //Modifie le nombre de clique
    }


    //Fonction utilisé dans le shop qui double le nombre de gold gagné pendant 10 minutes
    fun doubleOr(){
        if (player.gold >= 300){ //Teste si le joueur à assez de gold pour acheter le boost
            player.gold = player.gold - 300
            player.multiplicateur = player.multiplicateur + 2 //Modifie le multiplicateur de gold
            findViewById<TextView>(R.id.goldText).text = player.gold.toString()
            val liveData: MutableLiveData<String> = MutableLiveData()
            val customCountDownTimer = Timer(liveData, 2, player)
            customCountDownTimer.start(600)  //Démarre le compteur pour 10 minutes
            customCountDownTimer.mutableLiveData.observe(this, Observer { counterState ->
                counterState?.let {
                }
            })
        }
    }
    //Fonction utilisé dans le shop qui triple le nombre de gold gagné pendant 10 minutes
    fun tripleOr(){
        if (player.gold >= 1000){ //Teste si le joueur à assez de gold pour acheter le boost
            player.gold = player.gold - 1000
            player.multiplicateur = player.multiplicateur + 3 //Modifie le multiplicateur de gold
            findViewById<TextView>(R.id.goldText).text = player.gold.toString()
            val liveData: MutableLiveData<String> = MutableLiveData()
            val customCountDownTimer = Timer(liveData, 3, player)
            customCountDownTimer.start(300) //Démarre le compteur pour 10 minutes
            customCountDownTimer.mutableLiveData.observe(this, Observer { counterState ->
                counterState?.let {
                }
            })
        }
    }

    /*Fonction utilisé dans le shop qui permet d'avoir un clique automatique (le joueur n'as
    pas besoin d'appuyer sur le bouton pour gagner des gold)*/
    fun cliqueAuto(){
        if (player.gold >= 2000){
            val liveData: MutableLiveData<String> = MutableLiveData()
            val customCountDownTimer = Timer(liveData, 0, player)
            player.gold = player.gold - 2000
            customCountDownTimer.start(1800) //Epoch timestamp
            customCountDownTimer.mutableLiveData.observe(this, Observer { counterState ->
                counterState?.let {
                    updateGold() //Chaque seconde le nombre de gold s'update
                    updateNbClique()
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragmentContainer, HomeFragment(this))
        trans.addToBackStack(null)
        trans.commit()
        updateFragment.updateCurFrag("home")

        //PlayerStorage.get(applicationContext).clear()
        //Si c'est la première fois que le jeu est lancé il n'y a pas de joueur sauvegarder donc il est créer
        if(PlayerStorage.get(applicationContext).size() == 0) {
            PlayerStorage.get(applicationContext).insert(player)
        }
        //Sinon on importe le joueur existant
        else{
            player = PlayerStorage.get(applicationContext).findAll()[0]
        }
        /* Si c'est la première fois que le jeu est lancer on créer les personnages
            puis on les ajoutes à la sauvegarde
         */

        multi = pib.getPib(pays)
        Toast.makeText(this, multi.toString(), Toast.LENGTH_SHORT).show()

        if(CharStorage.get(applicationContext).size() != 5) {
            char1 = PlayableChar(
                1, "Giusepe", 1000, 100, 150,
                250, 0, 0, 0, false, false,10000
            )

            char2 =  PlayableChar(
                2, "Rigobert", 1750, 101, 150,
                150, 1, 1, 1, false, false,50000
            )

            char3 =  PlayableChar(
                3, "George Ducoup", 3000, 30, 40,
                50, 1, 1, 1, false, false,75000
            )

            char4 = PlayableChar(
                4,"Semi Chips", 450, 350, 450,
                400, 1, 1, 1, false, false,110000
            )

            char5 = PlayableChar(
                5,"404 Error", 1, 3333333, 66666,
                999, 66666, 66666, 666, false, false,999999999
            )

            CharStorage.get(applicationContext).insert(char1)
            CharStorage.get(applicationContext).insert(char2)
            CharStorage.get(applicationContext).insert(char3)
            CharStorage.get(applicationContext).insert(char4)
            CharStorage.get(applicationContext).insert(char5)
        }
        //Sinon on les importes
        else{
            char1 = CharStorage.get(applicationContext).findAll()[0]
            char2 = CharStorage.get(applicationContext).findAll()[1]
            char3 = CharStorage.get(applicationContext).findAll()[2]
            char4 = CharStorage.get(applicationContext).findAll()[3]
            char5 = CharStorage.get(applicationContext).findAll()[4]
        }

        //Ajout des personnages à la liste des personnages existant
        charList.add(char1)
        charList.add(char2)
        charList.add(char3)
        charList.add(char4)
        charList.add(char5)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        fetchLocation()

        //Modification de l'affichage
        findViewById<TextView>(R.id.goldText).text = player.gold.toString()
        findViewById<TextView>(R.id.xpText).text = player.xp.toString()
        findViewById<TextView>(R.id.goldText).text = player.gold.toString()

        layout = findViewById(R.id.main_activity)
        layout.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                updateFragment.switch(false)
            }
            override fun onSwipeRight() {
                super.onSwipeRight()
                updateFragment.switch(true)
            }
        })

        refreshFragment()
    }

    private fun fetchLocation() {
        val task:Task<Location> = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            //ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            checkForPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION, "activity", 101)
            //fetchLocation()

        }
        task.addOnSuccessListener {
            if(it != null){
                val geocoder = Geocoder(this, Locale.getDefault())

                var adresses: List<Address>? = null
                try {
                    adresses = geocoder.getFromLocation(
                        it.latitude,
                        it.longitude,
                        1
                    )
                } catch (ioException: IOException) {
                    Log.e("GPS", "erreur", ioException)
                } catch (illegalArgumentException: IllegalArgumentException) {
                    Log.e("GPS", "erreur", illegalArgumentException)
                }

                if (adresses == null || adresses.size == 0) {
                    Log.e("GPS", "erreur aucune adresse !")
                    Toast.makeText(this, "erreur aucune adresse", Toast.LENGTH_LONG).show()
                } else {
                    val adresse: Address = adresses[0]
                    val addressFragments = ArrayList<String?>()
                    for (i in 0..adresse.getMaxAddressLineIndex()) {
                        addressFragments.add(adresse.getAddressLine(i))
                    }
                    pays = adresse.countryName
                    Log.d("pays", pays)
                    multi = pib.getPib(pays)
                    Toast.makeText(this, multi.toString(), Toast.LENGTH_SHORT).show()

                    Log.d(
                        "GPS",
                        TextUtils.join(System.getProperty("line.separator"), addressFragments)
                    )
                }
            }
        }
    }


    //Test si la permission pour la fonctionnalité "activité physique" est accepté
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101 && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fetchLocation()
        }
    }

    //Test si la permission pour la fonctionnalité "activité physique" est accepté
    fun checkForPermissions(permission: String, name: String, requestCode: Int){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                ContextCompat.checkSelfPermission(applicationContext, permission) == PackageManager.PERMISSION_GRANTED -> {}

                //shouldShowRequestPermissionRationale(permission) -> showDialog(permission, name, requestCode)

                else -> ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            }
        }
    }

   /* fun showDialog(permission: String, name: String, requestCode: Int){
        val builder = AlertDialog.Builder(this)

        builder.apply {
            setMessage("Permission Activité physique")
            setTitle("Permission required")
            setPositiveButton("OK") {dialog, which ->
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
            }
        }
        val dialog = builder.create()
        dialog.show()
    }*/


    override fun onSensorChanged(Event: SensorEvent){
        if (player.detecteurPas == true){
            updateGold()

        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        if (player.detecteurPas == true){
            updateGold()
        }
    }
    //Met en pause le service de détection de pas
    fun unregisterSensor(){
        sensorManager.unregisterListener(this)
    }
    //Active le service de détection de pas
    fun registerSensor(){
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR),
            SensorManager.SENSOR_DELAY_FASTEST)
    }


    override fun onResume() {
        super.onResume()
        registerSensor()
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    //Fonction qui permet de sauvegarder l'état du joueur et l'état des personnages
     fun update() {
        PlayerStorage.get(applicationContext).update(1, player)
        CharStorage.get(applicationContext).update(1, char1)
        CharStorage.get(applicationContext).update(2, char2)
        CharStorage.get(applicationContext).update(3, char3)
        CharStorage.get(applicationContext).update(4, char4)
        CharStorage.get(applicationContext).update(5, char5)
    }



    fun refreshFragment(){
        updateFragment.goTeam(true)
        updateFragment.goSearch(true)
        updateFragment.goClicker(true)
        updateFragment.goProfil()
        updateFragment.goShop(true)
    }
}