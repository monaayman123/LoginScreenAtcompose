package com.example.loginscreenatcompose

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.loginscreenatcompose.ui.theme.LoginScreenAtcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreenAtcomposeTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
     LoginScreen()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
 @Composable
 fun LoginScreen(){
    val context = LocalContext.current
  var userName by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isclick by remember { mutableStateOf(false) }
    var isLogin by remember { mutableStateOf(false) }
    var showdialog by remember { mutableStateOf(false) }
    var isviewed by remember { mutableStateOf(false) }
    var iscontentupper by remember { mutableStateOf(false) }
    var iscontentdigit by remember { mutableStateOf(false) }
    var iscontentcha by remember { mutableStateOf(false) }
    var isActive by remember { mutableStateOf(false) }
    var isActive2 by remember { mutableStateOf(false) }
    var isActive3 by remember { mutableStateOf(false) }

    if(userName.isNotEmpty()&&email.isNotEmpty()&&pass.isNotEmpty()){
        isclick=true}


     Box (modifier = Modifier.fillMaxSize()){
       Surface (modifier = Modifier
           .fillMaxSize()
           .padding(24.dp),
      shape = RoundedCornerShape(16.dp), border = BorderStroke(4.dp, Color.Blue),
           shadowElevation = 6.dp
           ){
           ConstraintLayout (modifier = Modifier.fillMaxSize()){

               val textFieledEmail=createRef()
               OutlinedTextField(value = email,
                   onValueChange = {newValue->email=newValue}
                   , modifier = Modifier.constrainAs(textFieledEmail)
                   {
                       top.linkTo(parent.top,100  .dp)
                       start.linkTo(parent.start)
                       end.linkTo(parent.end)
                   }, shape = RoundedCornerShape(20.dp),colors  = TextFieldDefaults.outlinedTextFieldColors()
                   , label ={
                       Text(text = "Email", color = Color.Blue)
                   },
                   leadingIcon = {
                       Icon(imageVector = Icons.Filled.MailOutline, contentDescription ="",  tint = Color.Blue)
                   }

               )
               val textFieledOfUserName=createRef()

               OutlinedTextField(value = userName,
                   onValueChange = {newValue->userName=newValue}
                   , modifier = Modifier.constrainAs(textFieledOfUserName)
                   {

                       top.linkTo(textFieledEmail.bottom,16.dp)
                       start.linkTo(parent.start)
                       end.linkTo(parent.end)
                   }, shape = RoundedCornerShape(20.dp),colors  = TextFieldDefaults.outlinedTextFieldColors()
                   , label ={
                       Text(text = "userName", color = Color.Blue)
                   },
                   leadingIcon = {
                       Icon(imageVector = Icons.Filled.Person, contentDescription ="",  tint = Color.Blue)
                   }

               )
               val textFieledPass=createRef()
               val regex = Regex("[0-9]+")
               OutlinedTextField(value = pass,
                   onValueChange = {
                           newValue->pass=newValue

                       if (pass!=pass.lowercase()){iscontentupper=true}
                       if(iscontentupper)isActive=true
                       if (pass.length>=8){iscontentcha=true}
                       if(iscontentcha)isActive2=true
                       if(regex.containsMatchIn(pass)){iscontentdigit=true}
                       if(iscontentdigit)isActive3=true
                     
                                   }
    , visualTransformation =if(isviewed) VisualTransformation.None else PasswordVisualTransformation()
                   , modifier = Modifier.constrainAs(textFieledPass)
                   {
                       top.linkTo(textFieledOfUserName.bottom,16.dp)
                       start.linkTo(parent.start)
                       end.linkTo(parent.end)
                   }, shape = RoundedCornerShape(20.dp),colors  = TextFieldDefaults.outlinedTextFieldColors()
                   , label ={
                       Text(text = "password", color = Color.Blue)
                   },
                   leadingIcon = {
                       Icon(imageVector = Icons.Filled.Lock, contentDescription ="",  tint = Color.Blue)
                   }, trailingIcon = {
                       IconButton(onClick = { isviewed=true }) {
                           Icon(painter = painterResource(id = R.drawable.ic_show_password), contentDescription = "",
                               tint = Color.Blue)
                       }
                   }

               )
               val icon=createRef()
               Icon(imageVector = Icons.Filled.Check ,contentDescription ="",  tint = if (isActive) Color.Blue else Color.Gray,
                   modifier = Modifier
                       .size(30.dp)
                       .constrainAs(icon) {
                           top.linkTo(textFieledPass.bottom, 16.dp)
                           start.linkTo(parent.start, 40.dp)
                       }

               )
               val text=createRef()
               Text(text = "At Least 1 Upper_Case letter ", color = if(isActive) Color.Blue else Color.Gray, style = TextStyle(fontSize = 20.sp),
                   modifier = Modifier.constrainAs(text) {
                       top.linkTo(textFieledPass.bottom, 16.dp)
                       start.linkTo(icon.end,12.dp)
                   })
               val icon2=createRef()
               Icon(imageVector = Icons.Filled.Check ,contentDescription ="",  tint = if(isActive3) Color.Blue else Color.Gray,
                   modifier = Modifier
                       .size(30.dp)
                       .constrainAs(icon2) {
                           top.linkTo(icon.bottom, 16.dp)
                           start.linkTo(parent.start, 40.dp)
                       })
               val text2=createRef()
               Text(text = "At Least 1 digit  ", color = if(isActive3) Color.Blue else Color.Gray, style = TextStyle(fontSize = 20.sp),
                   modifier = Modifier.constrainAs(text2) {
                       top.linkTo(text.bottom, 16.dp)
                       start.linkTo(icon2.end,12.dp)
                   })
               val icon3=createRef()
               Icon(imageVector = Icons.Filled.Check ,contentDescription ="",  tint = if(isActive2) Color.Blue else Color.Gray,
                   modifier = Modifier
                       .size(30.dp)
                       .constrainAs(icon3) {
                           top.linkTo(icon2.bottom, 16.dp)
                           start.linkTo(parent.start, 40.dp)
                       })
               val text3=createRef()
               Text(text = "At Least 8 Charchters ", color =if(isActive2) Color.Blue else Color.Gray, style = TextStyle(fontSize = 20.sp),
                   modifier = Modifier.constrainAs(text3) {
                       top.linkTo(text2.bottom, 16.dp)
                       start.linkTo(icon3.end,12.dp)
                   })
               val button=createRef()
               Button(onClick =
               {
                   if(userName=="m"&&email=="mm"&&pass=="1A"){
                       isLogin=true
                       Toast.makeText(context,"Welcome for you",Toast.LENGTH_LONG).show()
                       showdialog=false
                   }else  {
                       Log.d("dd","er")
                       showdialog=true
                   }
               }
                  , enabled = isclick , modifier = Modifier.constrainAs(button){
                       top.linkTo(text3.bottom,24.dp)
                       start.linkTo(parent.start)
                       end.linkTo(parent.end)

                   }, colors = ButtonDefaults.buttonColors(Color.Blue)
               ) {
                   Text(text = "Login in ",)
               }
               var iconchecked=createRef()
               if(isLogin){
                   Icon(imageVector = Icons.Filled.CheckCircle, contentDescription ="" ,
                       tint=Color.Green  , modifier= Modifier
                           .size(50.dp)
                           .constrainAs(iconchecked) {
                               top.linkTo(button.bottom, 24.dp)
                               start.linkTo(parent.start)
                               end.linkTo(parent.end)
                           })
                   var textlogin=createRef()
                      Text(text = " Login Successful ", color = Color.Blue, style = TextStyle(fontSize = 32.sp)
                      , modifier = Modifier.constrainAs(textlogin){
                       top.linkTo(iconchecked.bottom,16.dp)
                           start.linkTo(parent.start)
                           end.linkTo(parent.end)
                       })


                  if(showdialog){

                      AlertDialog(
                          onDismissRequest = {showdialog=false  }
                          , confirmButton = {

                              Button(onClick = {  showdialog=false}) {
                                  Text(text = "ok")
                              }

                          }, title={
                              Text(text = "That is not correct!")
                          }, text = {
                              Text(text = "please write correct Email " +
                                      "userName and password")
                          }, dismissButton = {
                              Button(onClick = { showdialog=false}) {
                                  Text(text = "cancel")
                              }
                          }
                      )}
                  }
               }
           }
       }
     }



