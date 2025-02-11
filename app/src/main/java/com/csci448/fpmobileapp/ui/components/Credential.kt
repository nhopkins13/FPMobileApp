package com.csci448.fpmobileapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

/** Credential
 *  creates a TextField to enter username/email or password
 *
 *  pass a Boolean argument;
 *
 *  if true, field is a password;
 *      placeholder text is "password",
 *      entered text is visually transformed
 *
 *  if false, field is a username/email;
 *      placeholder text is username/email,
 *      entered text is not transformed
 *
 *  entered text is saved in the inputCred mutable
 *
 *  TODO:
 *      move placeholder text to strings.xml,
 *      pass entered information to/from ViewModel,
 *      implement confirmation via pressing "enter"
 *
 */
@Composable
fun Credential(isPwd: Boolean){
    val inputCred: MutableState<String> = remember {mutableStateOf("")}
    var placeHolder = "username or email"
    var thisTransform = VisualTransformation.None
    if(isPwd){
        placeHolder = "password"
        thisTransform = PasswordVisualTransformation()
    }
    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center){
        TextField(value = inputCred.value,
            onValueChange = fun(newVal: String){inputCred.value = newVal},
            modifier = Modifier,
            placeholder = {Text(placeHolder)},
            visualTransformation = thisTransform,
            singleLine = true
            )
    }
}

@Preview
@Composable
private fun PreveiwCredential(){
    Credential(false)
}
@Preview
@Composable
private fun PreveiwCredential2(){
    Credential(true)
}