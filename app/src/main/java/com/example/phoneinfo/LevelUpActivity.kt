package com.example.phoneinfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneinfo.databinding.ActivityLevelupBinding

class LevelUpActivity: AppCompatActivity() {
    lateinit var binding: ActivityLevelupBinding
    var dip = 250_000
    var pp = 10
    var bookNum = 133700
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadDate()
        binding.btnUniver.setOnClickListener{
            btnUniver()
        }

        binding.btndiplom.setOnClickListener{
            btnDiplom()
        }

        binding.btnComp.setOnClickListener{
            btnComp()
        }

        binding.btnBook.setOnClickListener{
            btnBook()
        }

        binding.btnStipendia.setOnClickListener{
            btnStipendia()
        }

        binding.back.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

    }


//onCreate End
//Univesty Function


    private fun btnUniver(){
        val studString = binding.studnumber.text
        var studentInt = Integer.parseInt(studString as String)

        val moneyStr = binding.moneybalance.text
        var moneyInt = Integer.parseInt(moneyStr as String)

        val btnUnv = binding.tvuniver.text
        var intUniver = Integer.parseInt(btnUnv as String)
        val univerFloat: Any = intUniver.toFloat()
        univerFloat as Float

        val reitingContry = binding.reiting.text
        val reitingContryInt = Integer.parseInt(reitingContry as String)

        val reitingWorld = binding.reitingWorld.text
        val reitingworldInt = Integer.parseInt(reitingWorld as String)

        val procentString = binding.tvProDip.text
        val procentInt = Integer.parseInt(procentString as String)
        val procentFloat: Any = procentInt.toFloat()
        procentFloat as Float

        var reitingContrySum = 0
        var reitingWorldSum = 0
        val procentSum = procentFloat * univerFloat + 10
        val studentSum = studentInt * (procentSum / 100)
        val StudentIntSum:Int = studentSum.toInt()

        if (moneyInt >= dip){
            intUniver += 1
            moneyInt -= dip
            if (reitingContryInt > 1){
                reitingContrySum = reitingContryInt - 37
            } else if (reitingContryInt == 1){
                reitingContrySum = reitingContryInt - 0
            }


            if (reitingworldInt > 1){
                reitingWorldSum = reitingworldInt - 67
            } else if (reitingworldInt == 1){
                reitingWorldSum = reitingworldInt - 0
            }


        } else if (moneyInt < dip){
            intUniver += 0
            moneyInt -= 0

            reitingContrySum = reitingContryInt - 0

            reitingWorldSum = reitingworldInt - 0
        }

        //var limit = 75000
        //limit *= intUniver


        binding.reiting.text = reitingContrySum.toString()
        binding.reitingWorld.text = reitingWorldSum.toString()
        binding.tvuniver.text = intUniver.toString()
        //binding.limit.text = limit.toString()
        binding.moneybalance.text = moneyInt.toString()
        binding.tvProcent.text = procentSum.toString()
        binding.studed.text = StudentIntSum.toString()

        val sharedPreferences = getSharedPreferences("Res", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("University", binding.tvuniver.text.toString())
            putString("Moneys", binding.moneybalance.text.toString())
            //putString("Limits", binding.limit.text.toString())
            putString("ReitingContry", binding.reiting.text.toString())
            putString("ReitingWorld", binding.reitingWorld.text.toString())
            putString("StudProcents", binding.tvProcent.text.toString())
            putString("StudSums", binding.studed.text.toString())
        }.apply()

        //Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
    }


//Univesty Function End

////////////////////////////////////
//Diplom Function
    private fun btnDiplom(){
        val studNum = binding.studed.text
        val studInt = Integer.parseInt(studNum as String)

        val diplNum = binding.tvdiplom.text
        var diplInt = Integer.parseInt(diplNum as String)
        val diplomFloat: Any = diplInt.toFloat()
        diplomFloat as Float

        val moneyStr = binding.moneybalance.text
        var moneyInt = Integer.parseInt(moneyStr as String)

        val diplomSumFloat = (diplomFloat / studInt) * 100
        val diplomSumInt:Int = diplomSumFloat.toInt()
        if (studInt > diplInt) {
            val ten = 10 * 20
            if(ten <= moneyInt){
                diplInt += 10
                moneyInt -= ten
            } else if(moneyInt < ten && moneyInt > diplInt){
                diplInt += 1
                moneyInt -= 20
            }
        } else if (studInt == diplInt){
            diplInt += 0
            moneyInt -= 0
        }

        binding.moneybalance.text = moneyInt.toString()
        binding.tvdiplom.text = diplInt.toString()
        binding.tvProDip.text = diplomSumInt.toString()

    val sharedPreferences = getSharedPreferences("Res", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("Diploms", binding.tvdiplom.text.toString())
            putString("Moneys", binding.moneybalance.text.toString())
            putString("TvProDiplom", binding.tvProDip.text.toString())

        }.apply()

        //Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
    }

//Diplom Function End

////////////////////////////////////
//Stipendia Function

    private fun btnComp(){
        val compStr = binding.tvComp.text
        var compInt = Integer.parseInt(compStr as String)
        val compFloat: Any = compInt.toFloat()
        compFloat as Float

        val moneyStr = binding.moneybalance.text
        var moneyInt = Integer.parseInt(moneyStr as String)

        val univStr = binding.tvuniver.text
        val univInt = Integer.parseInt(univStr as String)

        val copm_univ = univInt * 53000

        val comp_prc = (compFloat / copm_univ) * 100
        val comp_prcInt:Int = comp_prc.toInt()

        if (copm_univ > compInt) {
            val ten = 10 * 1250
            if(ten <= moneyInt){
                compInt += 10
                moneyInt -= ten
                //proc = procFloat + (proc * (compInt / 100_000))
            } else if(moneyInt >= 1250 && ten > moneyInt){
                compInt += 1
                moneyInt -= 1250
                //proc = procInt + (proc * (compInt / 100_000))
            }
        } else if (copm_univ == compInt){
            compInt += 0
            moneyInt -= 0
        }

        binding.moneybalance.text = moneyInt.toString()
        binding.tvProComp.text = comp_prcInt.toString()
        binding.tvComp.text = compInt.toString()
        val sharedPreferences = getSharedPreferences("Res", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("Computers", binding.tvComp.text.toString())
            putString("Moneys", binding.moneybalance.text.toString())
            putString("CompPro", binding.tvProComp.text.toString())
        }.apply()
    }

//Stipendia Function End

///////////////////////////////////
//Book Function
    private fun btnBook(){

        val bookStr = binding.tvBook.text
        var bookInt = Integer.parseInt(bookStr as String)
        val tvunivFloat: Any = bookInt.toFloat()
        tvunivFloat as Float

        val moneyStr = binding.moneybalance.text
        var moneyInt = Integer.parseInt(moneyStr as String)

        val univStr = binding.tvuniver.text
        val univInt = Integer.parseInt(univStr as String)


        val bookSum = univInt * 80_000

        val tvunivFloatSum = (tvunivFloat / bookSum) * 100
        val tvunivFloatSumInt:Int = tvunivFloatSum.toInt()

        if (bookNum > bookInt) {
            val ten = 10 * 45
            if(ten <= moneyInt){
                bookInt += 10
                moneyInt -= ten
            } else if(moneyInt >= 45 && ten > moneyInt){
                bookInt += 1
                moneyInt -= 45
            }
        } else if (bookNum == bookInt){
            bookInt += 0
            moneyInt -= 0
        }

        binding.moneybalance.text = moneyInt.toString()
        binding.tvBook.text = bookInt.toString()
        binding.tvProBook.text = tvunivFloatSumInt.toString()

    val sharedPreferences = getSharedPreferences("Res", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("Books", binding.tvBook.text.toString())
            putString("Moneys", binding.moneybalance.text.toString())
            putString("BooksSum", binding.tvProBook.text.toString())
        }.apply()
    }

//Book Function End

////////////////////////////////////
//Stipendia Function

    private fun btnStipendia(){
        val studNum = binding.studed.text
        val studInt = Integer.parseInt(studNum as String)

        val stipendiaStr = binding.tvStipendia.text
        var stipendiaInt = Integer.parseInt(stipendiaStr as String)
        val stipProFloat: Any = stipendiaInt.toFloat()
        stipProFloat as Float

        val moneyStr = binding.moneybalance.text
        var moneyInt = Integer.parseInt(moneyStr as String)

        val stipProSum = (stipProFloat / studInt) * 100
        val stipProSumInt:Int = stipProSum.toInt()

        if (stipProSumInt <= 20){
            binding.agry.visibility = View.VISIBLE
            binding.iconConfused.visibility = View.INVISIBLE
            binding.iconHappySmall.visibility = View.INVISIBLE
            binding.iconHappy.visibility = View.INVISIBLE
        } else if (20 < stipProSumInt && stipProSumInt <= 45){
            binding.agry.visibility = View.INVISIBLE
            binding.iconConfused.visibility = View.VISIBLE
            binding.iconHappySmall.visibility = View.INVISIBLE
            binding.iconHappy.visibility = View.INVISIBLE
        } else if (45 < stipProSumInt && stipProSumInt <= 80){
            binding.agry.visibility = View.INVISIBLE
            binding.iconConfused.visibility = View.INVISIBLE
            binding.iconHappySmall.visibility = View.VISIBLE
            binding.iconHappy.visibility = View.INVISIBLE
        } else if (80 < stipProSumInt && stipProSumInt <= 100){
            binding.agry.visibility = View.INVISIBLE
            binding.iconConfused.visibility = View.INVISIBLE
            binding.iconHappySmall.visibility = View.INVISIBLE
            binding.iconHappy.visibility = View.VISIBLE
        }

        if (studInt > stipendiaInt) {
            val ten = 10 * 850
            if(ten <= moneyInt){
                stipendiaInt += 10
                moneyInt -= ten
            } else if(moneyInt in 851 until ten){
                stipendiaInt += 1
                moneyInt -= 850
            }
        } else if (studInt == stipendiaInt){
            stipendiaInt += 0
            moneyInt -= 0
        }
        binding.tvStipendia.text = stipendiaInt.toString()
        binding.moneybalance.text = moneyInt.toString()
        binding.tvProStip.text = stipProSumInt.toString()

        val sharedPreferences = getSharedPreferences("Res", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("Stipendia", binding.tvStipendia.text.toString())
            putString("Moneys", binding.tvStipendia.text.toString())
            putString("StipProSum", binding.tvProStip.text.toString())

        }.apply()

    }

//Stipendia Function End
////////////////////////////////////
    private fun loadDate(){
        val sharedPreferences = getSharedPreferences("Res", Context.MODE_PRIVATE)
        val universityString = sharedPreferences.getString("University", "1")
        val diplomsString = sharedPreferences.getString("Diploms", "0")
        val studentsString = sharedPreferences.getString("Students", "0")
        val computerString = sharedPreferences.getString("Computers", "0")
        val stipendiaString = sharedPreferences.getString("Stipendia", "0")
        val bookString = sharedPreferences.getString("Books", "0")
        val moneyString = sharedPreferences.getString("Moneys", "0")
        val limitString = sharedPreferences.getString("Limits", "75000")
        val procentString = sharedPreferences.getString("StudProcents", "10")
        val reitingContryString = sharedPreferences.getString("ReitingContry", "2485")
        val reitingWorldString = sharedPreferences.getString("ReitingWorld", "31827")
        val studentSumString = sharedPreferences.getString("StudSums", "0")
        val diplomSumString = sharedPreferences.getString("TvProDiplom", "0")
        val compSumString = sharedPreferences.getString("CompPro", "0")
        val BookSumString = sharedPreferences.getString("BooksSum", "0")
        val StipProString = sharedPreferences.getString("StipProSum", "0")


        binding.tvuniver.text = universityString
        binding.tvdiplom.text = diplomsString
        binding.tvComp.text = computerString
        binding.tvBook.text = bookString
        binding.tvStipendia.text = stipendiaString
        binding.studnumber.text = studentsString
        binding.limit.text = limitString
        binding.studed.text = studentsString
        binding.moneybalance.text = moneyString
        binding.tvProcent.text = procentString
        binding.reiting.text = reitingContryString
        binding.reitingWorld.text = reitingWorldString
        binding.studed.text = studentSumString
        binding.tvProDip.text = diplomSumString
        binding.tvProComp.text = compSumString
        binding.tvProBook.text = BookSumString
        binding.tvProStip.text = StipProString

    }
}
