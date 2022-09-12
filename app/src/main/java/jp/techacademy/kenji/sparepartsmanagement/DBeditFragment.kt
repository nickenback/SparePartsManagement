package jp.techacademy.kenji.sparepartsmanagement

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_dbedit.*
import kotlinx.android.synthetic.main.fragment_test.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class DBeditFragment: Fragment(), View.OnClickListener {
    companion object {
        private val DRIVER = "oracle.jdbc.driver.OracleDriver"
        private val URL = "jdbc:oracle:thin:@10.128.57.101:1521:XEPDB1"
        private val USERNAME = "TATSUTA"
        private val PASSWORD = "tatsuta"
    }
    private lateinit var con: Connection
    private lateinit var statement: Statement
    private lateinit var stringbuffer: StringBuffer
//    private lateinit var resultset: ResultSet
    private var isConnected: Boolean? = null
    private var command: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dbedit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ConUpdate()

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build()
        )

//        fabAdddataSend.setOnClickListener(this)
        fabDeleteSend.setOnClickListener(this)
        fabUpdate.setOnClickListener(this)

//        if (isConnected == false || isConnected == null) {
            try {
                textViewException.text = null

                Class.forName(DBeditFragment.DRIVER)
                con = DriverManager.getConnection(
                    DBeditFragment.URL,
                    DBeditFragment.USERNAME,
                    DBeditFragment.PASSWORD
                )

                isConnected = true
                ConUpdate()
            } catch (e: Exception) {
                textViewException.setText(e.toString())
            }
//        }

//        swipeRefreshLayout.setOnRefreshListener {
//            updatedata()
//        }

        view.setOnTouchListener({view, event ->
            if(event.actionMasked == MotionEvent.ACTION_DOWN){
                view.requestFocus()
            }
            view?.onTouchEvent(event)?: true
        })

        editTextItemInputNumber.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        editTextAuthName.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        editTextPlace.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        editTextItemCode.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        editTextItemName.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        editTextItemSpec.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        editTextItemMaker.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        editTextItemUnitName.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        editTextItemPriceNew.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        editTextItemPriceSec.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        editTextItemQtyNew.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        editTextItemQtySec.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        editTextItemPurchaseNum.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        editTextItemSaler.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        editTextItemNote.setOnFocusChangeListener{ view, hasFocus ->
            if(!hasFocus){
                val im = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }

    override fun onClick(v: View?) {
//        if (v === fabAdddataSend) {
//            if (isConnected == false || isConnected == null) {
//                Snackbar.make(
//                    v,
//                    getString(R.string.No_connection),
//                    Snackbar.LENGTH_INDEFINITE
//                )
//                    .setAction("close",{
//                    })
//                    .show()
//            } else {
//                try {
//                    command =
//                        "INSERT INTO ITEMLIST(" +
//                                "ITEMINPUTNUMBER," +
//                                "ITEMINPUTDATE," +
//                                "ITEMINPUTAUTHER," +
//                                "ITEMPLACE," +
//                                "ITEMCODE," +
//                                "ITEMNAME," +
//                                "ITEMSPEC," +
//                                "ITEMMAKER," +
//                                "ITEMUNITNAME," +
//                                "ITEMPRICENEW," +
//                                "ITEMPRICESEC," +
//                                "ITEMQTYNEW," +
//                                "ITEMQTYSEC," +
//                                "ITEMPURCHASENUMBER," +
//                                "ITEMSALER," +
//                                "ITEMNOTE" +
//                                ")VALUES(" +
//                                editTextItemInputNumber.text + "," +
//                                "CURRENT_TIMESTAMP,'" +
//                                editTextAuthName.text + "','" +
//                                editTextPlace.text + "','" +
//                                editTextItemCode.text + "','" +
//                                editTextItemName.text + "','" +
//                                editTextItemSpec.text + "','" +
//                                editTextItemMaker.text + "','" +
//                                editTextItemUnitName.text + "'," +
//                                editTextItemPriceNew.text + "," +
//                                editTextItemPriceSec.text + "," +
//                                editTextItemQtyNew.text + "," +
//                                editTextItemQtySec.text + ",'" +
//                                editTextItemPurchaseNum.text + "','" +
//                                editTextItemSaler.text + "','" +
//                                editTextItemNote.text + "')"
//                    statement = con.createStatement()
//                    stringbuffer = StringBuffer()
////                    resultset = statement.executeQuery(command)
//                    statement.executeUpdate(command)
//
////                    while (resultset.next()) {
////                        stringbuffer.append(resultset.getString(1) + "\n")
////                    }
////
////                    Snackbar.make(
////                        v,
////                        stringbuffer.toString(),
////                        Snackbar.LENGTH_INDEFINITE
////                    )
////                        .setAction("close",{
////                        })
////                        .show()
////
////                    resultset.close()
//
//                    statement.close()
////                    con.close()
////                    isConnected = false
//                    ConUpdate()
//                } catch (e: Exception) {
//                    SnackbarMessage(v,e)
//                }
//            }
//        }
        if (v === fabDeleteSend) {
            if (isConnected == false || isConnected == null) {
                Snackbar.make(
                    v,
                    getString(R.string.No_connection),
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction("close", {
                    })
                    .show()
            } else {
                try {
                    command =
                        "DELETE FROM ITEMLIST WHERE ITEMINPUTNUMBER ='" +
                                editTextItemInputNumber.text +
                                "'"
                    statement = con.createStatement()
                    stringbuffer = StringBuffer()
//                    resultset = statement.executeQuery(command)
                    statement.executeUpdate(command)

                    statement.close()
//                    con.close()
//                    isConnected = false
                    ConUpdate()
                }catch (e: Exception){
                   SnackbarMessage(v,e)
                }
            }
        }
        if (v === fabUpdate) {
            if (isConnected == false || isConnected == null) {
                Snackbar.make(
                    v,
                    getString(R.string.No_connection),
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction("close", {
                    })
                    .show()
            } else {

//                try{
//                    command =
//                        "CREATE TABLE ITEMLISTNEW AS SELECT * FROM ITEMLIST"
//                    statement = con.createStatement()
//                    stringbuffer = StringBuffer()
//                    statement.executeUpdate(command)
//
//                    statement.close()
//
//                }catch (e: Exception){
//                    SnackbarMessage(v,e)
//                }

                try {
                    command =
                        "INSERT INTO ITEMLISTNEW(" +
                                "ITEMINPUTNUMBER," +
                                "ITEMINPUTDATE," +
                                "ITEMINPUTAUTHER," +
                                "ITEMPLACE," +
                                "ITEMCODE," +
                                "ITEMNAME," +
                                "ITEMSPEC," +
                                "ITEMMAKER," +
                                "ITEMUNITNAME," +
                                "ITEMPRICENEW," +
                                "ITEMPRICESEC," +
                                "ITEMQTYNEW," +
                                "ITEMQTYSEC," +
                                "ITEMPURCHASENUMBER," +
                                "ITEMSALER," +
                                "ITEMNOTE" +
                                ")VALUES(" +
                                editTextItemInputNumber.text + "," +
                                "CURRENT_TIMESTAMP,'" +
                                editTextAuthName.text + "','" +
                                editTextPlace.text + "','" +
                                editTextItemCode.text + "','" +
                                editTextItemName.text + "','" +
                                editTextItemSpec.text + "','" +
                                editTextItemMaker.text + "','" +
                                editTextItemUnitName.text + "'," +
                                editTextItemPriceNew.text + "," +
                                editTextItemPriceSec.text + "," +
                                editTextItemQtyNew.text + "," +
                                editTextItemQtySec.text + ",'" +
                                editTextItemPurchaseNum.text + "','" +
                                editTextItemSaler.text + "','" +
                                editTextItemNote.text + "')"

                    statement = con.createStatement()
                    stringbuffer = StringBuffer()
//                    resultset = statement.executeQuery(command)
                    statement.executeUpdate(command)

                    statement.close()
//                    con.close()
//                    isConnected = false
                    ConUpdate()
                }catch (e: Exception){
                    SnackbarMessage(v,e)
                }


                try{
                    command=
                        "MERGE INTO ITEMLIST USING ITEMLISTNEW ON (ITEMLIST.ITEMINPUTNUMBER = ITEMLISTNEW.ITEMINPUTNUMBER)" +
                                "WHEN MATCHED THEN UPDATE SET " +
//                                "ITEMINPUTNUBER = ITEMLISTNEW.ITEMINPUTNUMBER," +
                                "ITEMINPUTDATE = ITEMLISTNEW.ITEMINPUTDATE," +
                                "ITEMINPUTAUTHER = ITEMLISTNEW.ITEMINPUTAUTHER," +
                                "ITEMPLACE = ITEMLISTNEW.ITEMPLACE," +
                                "ITEMCODE = ITEMLISTNEW.ITEMCODE," +
                                "ITEMNAME = ITEMLISTNEW.ITEMNAME," +
                                "ITEMSPEC = ITEMLISTNEW.ITEMSPEC," +
                                "ITEMMAKER = ITEMLISTNEW.ITEMMAKER," +
                                "ITEMUNITNAME = ITEMLISTNEW.ITEMUNITNAME," +
                                "ITEMPRICENEW = ITEMLISTNEW.ITEMPRICENEW," +
                                "ITEMPRICESEC = ITEMLISTNEW.ITEMPRICESEC," +
                                "ITEMQTYNEW = ITEMLISTNEW.ITEMQTYNEW," +
                                "ITEMQTYSEC = ITEMLISTNEW.ITEMQTYSEC," +
                                "ITEMPURCHASENUMBER = ITEMLISTNEW.ITEMPURCHASENUMBER," +
                                "ITEMSALER = ITEMLISTNEW.ITEMSALER," +
                                "ITEMNOTE = ITEMLISTNEW.ITEMNOTE " +
                                "WHEN NOT MATCHED THEN INSERT(" +
                                "ITEMINPUTNUMBER," +
                                "ITEMINPUTDATE," +
                                "ITEMINPUTAUTHER," +
                                "ITEMPLACE," +
                                "ITEMCODE," +
                                "ITEMNAME," +
                                "ITEMSPEC," +
                                "ITEMMAKER," +
                                "ITEMUNITNAME," +
                                "ITEMPRICENEW," +
                                "ITEMPRICESEC," +
                                "ITEMQTYNEW," +
                                "ITEMQTYSEC," +
                                "ITEMPURCHASENUMBER," +
                                "ITEMSALER," +
                                "ITEMNOTE" +
                                ")VALUES(" +
                                "ITEMLISTNEW.ITEMINPUTNUMBER," +
                                "ITEMLISTNEW.ITEMINPUTDATE," +
                                "ITEMLISTNEW.ITEMINPUTAUTHER," +
                                "ITEMLISTNEW.ITEMPLACE," +
                                "ITEMLISTNEW.ITEMCODE," +
                                "ITEMLISTNEW.ITEMNAME," +
                                "ITEMLISTNEW.ITEMSPEC," +
                                "ITEMLISTNEW.ITEMMAKER," +
                                "ITEMLISTNEW.ITEMUNITNAME," +
                                "ITEMLISTNEW.ITEMPRICENEW," +
                                "ITEMLISTNEW.ITEMPRICESEC," +
                                "ITEMLISTNEW.ITEMQTYNEW," +
                                "ITEMLISTNEW.ITEMQTYSEC," +
                                "ITEMLISTNEW.ITEMPURCHASENUMBER," +
                                "ITEMLISTNEW.ITEMSALER," +
                                "ITEMLISTNEW.ITEMNOTE" +
                                ")"
                    statement = con.createStatement()
                    stringbuffer = StringBuffer()
                    statement.executeUpdate(command)

                    statement.close()

                }catch (e: Exception){
                    SnackbarMessage(v,e)
                }


                try{
                    command =
                        "DELETE FROM ITEMLISTNEW"
                    statement = con.createStatement()
                    stringbuffer = StringBuffer()
                    statement.executeUpdate(command)

                    statement.close()

                }catch (e: Exception){
                    SnackbarMessage(v,e)
                }


            }
        }



    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        con.close()
        isConnected = false
        ConUpdate()

    }

    private fun ConUpdate(){
            favoriteImageView.setImageResource(if (isConnected == true) R.drawable.ic_connect else R.drawable.ic_disconnect)
    }

    private fun SnackbarMessage(v: View, e:java.lang.Exception){
        Snackbar.make(
            v,
            e.toString(),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction("close",{
            })
            .show()
    }


//    private fun updatedata(){
//        swipeRefreshLayout.isRefreshing = false
//    }
}
