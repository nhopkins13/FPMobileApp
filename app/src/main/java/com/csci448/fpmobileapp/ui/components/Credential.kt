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
 *  pass an Integer argument;
 *
 *
 *  if 0, field is a username/email;
 *      placeholder text is username/email,
 *      entered text is not transformed
 *  if 1, field is a password;
 *       placeholder text is "password",
 *       entered text is visually transformed
 *   if 2, field is a password confirmation;
 *      placeholder text is "confirm password",
 *      entered text is visually transformed
 *
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
fun Credential(entryType: Int, textPlaceHold: String = "username or email"){
    val inputCred: MutableState<String> = remember {mutableStateOf("")}
    var thisTransform = VisualTransformation.None
    if(entryType > 0){
        thisTransform = PasswordVisualTransformation()
    }
    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center){
        TextField(value = inputCred.value,
            onValueChange = fun(newVal: String){inputCred.value = newVal},
            modifier = Modifier,
            placeholder = {Text(textPlaceHold)},
            visualTransformation = thisTransform,
            singleLine = true
            )
    }
}

@Preview
@Composable
private fun PreveiwCredential(){
    Credential(0)
}
@Preview
@Composable
private fun PreveiwCredential2(){
    Credential(0)
}