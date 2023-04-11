package com.example.phoneinfo


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneinfo.databinding.ActivityMainBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


const val GAME_LENGTH_MILLISECONDS = 3000L
const val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var interstitialAd: InterstitialAd? = null
    private var countdownTimer: CountDownTimer? = null
    private var gameIsInProgress = false
    private var adIsLoading: Boolean = false
    private var timerMilliseconds = 0L
    private var TAG = "MainActivity"

    private var mInterstitialAd: InterstitialAd? = null

    lateinit var mAdView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adRequest = AdRequest.Builder().build()


        // Log the Mobile Ads SDK version.
        Log.d(TAG, "Google Mobile Ads SDK Version: " + MobileAds.getVersion())

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this) {}

        // Set your test devices. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("ABCDEF012345"))
        // to get test ads on this device."
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder().setTestDeviceIds(listOf("ABCDEF012345")).build()
        )

        // Create the "retry" button, which triggers an interstitial between game plays.
        binding.spd.visibility = View.INVISIBLE
        binding.spd.setOnClickListener { showInterstitial() }

        // Kick off the first play of the "game."
        startGame()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError?.toString()?.let { Log.d(TAG, it) }
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        mAdView.loadAd(adRequest)


        loadDate()

        binding.upgrade.setOnClickListener{ upgrade() }

        binding.clickbtn.setOnClickListener{ bankCardMony() }

        binding.levelup.setOnClickListener{ levelup() }

        binding.settings.setOnClickListener{
            val i = Intent(this, SplashScreen::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }


    private fun bankCardMony(){
        val dd = binding.stud.text
        var ddd = Integer.parseInt(dd as String)

        val summAdd = binding.sum.text
        var sumInt = Integer.parseInt(summAdd as String)

        val subAdd = binding.ss.text
        var sumAdd = Integer.parseInt(subAdd as String)

        val moneyAdd = binding.money.text
        var moneyInt = Integer.parseInt(moneyAdd as String)

        ddd += sumInt
        moneyInt += (2 * sumInt)

        binding.stud.text = ddd.toString()
        val studnetsString = binding.stud.text.toString()

        binding.stud.text = studnetsString
        binding.money.text = moneyInt.toString()


        if (moneyInt > sumAdd){
            binding.levelup.visibility = View.VISIBLE
        } else {
            binding.levelup.visibility = View.INVISIBLE
        }
    }

    private fun levelup(){
        val moneyAdd = binding.money.text
        var moneyInt = Integer.parseInt(moneyAdd as String)

        val suAdd = binding.ss.text
        var ssAdd = Integer.parseInt(suAdd as String)


        val sumAdd = binding.sum.text
        var sumInt = Integer.parseInt(sumAdd as String)

        val dd = binding.stud.text
        var ddd = Integer.parseInt(dd as String)

        sumInt += 1
        var ssAddd = ssAdd + (ssAdd / 2)
        moneyInt -= ssAdd

        binding.sum.text = sumInt.toString()
        binding.stud.text = ddd.toString()
        binding.ss.text = ssAddd.toString()
        binding.money.text = moneyInt.toString()

        if(moneyInt < ssAdd){
            binding.levelup.visibility = View.INVISIBLE
        }
    }

    private fun upgrade(){
        val stud = binding.stud.text.toString()
        val moneys = binding.money.text.toString()
        val sums = binding.sum.text.toString()
        val ss = binding.ss.text.toString()


        val sharedPreferences = getSharedPreferences("Res", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("Students", stud)
            putString("Moneys", moneys)
            putString("Sums", sums)
            putString("SS", ss)
        }.apply()

        //Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()

        val i = Intent(this, LevelUpActivity::class.java)
        startActivity(i)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun loadDate(){
        val sharedPreferences = getSharedPreferences("Res", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("Students", "0")
        val moneyString = sharedPreferences.getString("Moneys", "0")
        val sumString = sharedPreferences.getString("Sums", "1")
        val ssString = sharedPreferences.getString("SS", "350")

        binding.stud.text = savedString
        binding.money.text = moneyString
        binding.sum.text = sumString
        binding.ss.text = ssString
    }

//Reklama



    private fun loadAd() {
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            AD_UNIT_ID,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    adError?.message?.let { Log.d(TAG, it) }
                    interstitialAd = null
                    adIsLoading = false
                    val error =
                        "domain: ${adError.domain}, code: ${adError.code}, " + "message: ${adError.message}"
                    //Toast.makeText( this@MainActivity, "onAdFailedToLoad() with error $error", Toast.LENGTH_SHORT ).show()
                }

                override fun onAdLoaded(ad: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    interstitialAd = ad
                    adIsLoading = false
                    //Toast.makeText(this@MainActivity, "onAdLoaded()", Toast.LENGTH_SHORT).show() //onAdLoaded()
                }
            }
        )
    }

    // Create the game timer, which counts down to the end of the level
    // and shows the "retry" button.
    private fun createTimer(milliseconds: Long) {
        countdownTimer?.cancel()

        countdownTimer =
            object : CountDownTimer(milliseconds, 50) {
                override fun onTick(millisUntilFinished: Long) {
                    timerMilliseconds = millisUntilFinished
                    binding.timer.text = "seconds remaining: ${ millisUntilFinished / 2500 + 1 }"
                }

                override fun onFinish() {
                    gameIsInProgress = false
                    binding.timer.text = "done!"
                    binding.spd.visibility = View.VISIBLE
                }
            }
    }

    // Show the ad if it's ready. Otherwise toast and restart the game.
    private fun showInterstitial() {


        if (interstitialAd != null) {
            interstitialAd?.fullScreenContentCallback =
                object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        Log.d(TAG, "Ad was dismissed.")
                        // Don't forget to set the ad reference to null so you
                        // don't show the ad a second time.
                        interstitialAd = null
                        loadAd()
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        Log.d(TAG, "Ad failed to show.")
                        // Don't forget to set the ad reference to null so you
                        // don't show the ad a second time.
                        interstitialAd = null
                    }

                    override fun onAdShowedFullScreenContent() {
                        Log.d(TAG, "Ad showed fullscreen content.")
                        // Called when ad is dismissed.
                        val moneyAdd = binding.money.text
                        var moneyInt = Integer.parseInt(moneyAdd as String)

                        var mon = moneyInt / 3
                        moneyInt += mon

                        binding.money.text = moneyInt.toString()
                    }
                }
            interstitialAd?.show(this)

        } else {
            //Toast.makeText(this, "Ad wasn't loaded.", Toast.LENGTH_SHORT).show()
            startGame()
        }
    }

    // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
    private fun startGame() {
        if (!adIsLoading && interstitialAd == null) {
            adIsLoading = true
            loadAd()
        }

        binding.spd.visibility = View.INVISIBLE
        resumeGame(GAME_LENGTH_MILLISECONDS)
    }

    private fun resumeGame(milliseconds: Long) {
        // Create a new timer for the correct length and start it.
        gameIsInProgress = true
        timerMilliseconds = milliseconds
        createTimer(milliseconds)
        countdownTimer?.start()
    }

    // Resume the game if it's in progress.
    public override fun onResume() {
        super.onResume()

        if (gameIsInProgress) {
            resumeGame(timerMilliseconds)
        }

    }

    // Cancel the timer if the game is paused.
    public override fun onPause() {
        countdownTimer?.cancel()
        super.onPause()
        //moneyup *= 2
        //binding.money.text = "${moneyup.toString()}$"

    }
}
