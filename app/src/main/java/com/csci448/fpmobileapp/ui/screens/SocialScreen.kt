package com.csci448.fpmobileapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.fpmobileapp.data.SaurusRepo
import com.csci448.fpmobileapp.ui.viewmodel.StudySaurusVM

/**
 * screen to find, view, and interact with friends
 *
 * TODO:
 *  implement search,
 *  keep track of users' friends,
 *  reorganize
 */
@Composable
fun SocialScreen(viewModel : StudySaurusVM){
    Column{
        //remove/change once page has content
        Text("SOCIAL SCREEN")
        Text("nothing here")
        Text("hit back button in studio")


        Row{
            //search box to add new friends by user
        }
        Row{
            //current friends in a list here
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun PreviewSocialScreen(){
    SocialScreen(viewModel = StudySaurusVM(SaurusRepo.mySaurus))
}