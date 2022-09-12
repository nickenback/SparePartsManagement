package jp.techacademy.kenji.sparepartsmanagement

data class ItemDBtype(
    var iteminputnumber : Long,
    var iteminputdate : String,
    var iteminputauther : String,
    var itemplace : String,
    var itemnumber : Long,
    var itemname : String,
    var itemspec : String,
    var itemmaker : String,
    var itemunitname : String,
    var itempricenew : Long,
    var itempricesec : Long,
    var itemqtynew : Long,
    var itemqtysec : Long,
    var itempurchasenumber : String,
    var itemsaler : String,
    var itemnote : String
)