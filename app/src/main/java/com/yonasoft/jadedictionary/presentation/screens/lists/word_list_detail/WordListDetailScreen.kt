package com.yonasoft.jadedictionary.presentation.screens.lists.word_list_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yonasoft.jadedictionary.presentation.components.word_row.WordRow
import com.yonasoft.jadedictionary.presentation.screens.lists.ListsViewModel


@Composable
fun WordListDetailScreen(
    navController: NavController,
    wordListId: Int, // Assume this is passed correctly through the composable parameters
    viewModel: ListsViewModel = hiltViewModel()
) {

    val words by viewModel.wordListWords.collectAsState()
    val isWordDialogOpen = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = wordListId) {
        viewModel.fetchWordListById(wordListId)
    }

    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = viewModel.editTitle.value,
            onValueChange = { viewModel.editTitle.value = it },
            label = { Text("Title") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = viewModel.editDescription.value,
            onValueChange = { viewModel.editDescription.value = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )
        Button(
            modifier = Modifier.padding(vertical = 8.dp),
            onClick = {
                viewModel.saveWordList()
                navController.navigateUp()
            },
        ) {
            Text("Save Changes")
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(
                words.size
            ) {
                val word = words[it]
                WordRow(
                    word = word,
                    onClick = {
                        isWordDialogOpen.value = true
                    },
                    isDialogOpen = isWordDialogOpen,
                    dropdownMenu = { menuExpanded ->
                        DropdownMenu(
                            expanded = menuExpanded.value,
                            onDismissRequest = { menuExpanded.value = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Remove") },
                                onClick = {
                                    // Action to add the word to a list
                                    menuExpanded.value = false
                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Filled.Delete,
                                        contentDescription = "Remove from from List"
                                    )
                                }
                            )
                        }
                    }
                )
                Divider(color = Color.Black)
            }
        }
    }
}


