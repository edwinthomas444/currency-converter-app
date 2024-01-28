package com.tutorial.unitconverter


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.tutorial.unitconverter.ui.theme.UnitConverterTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme{
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    // add a text element
                    ConvertorWindow()
                }
            }
        }
    }
}

@Composable
fun ConvertorWindow(){
    var inputText by remember { mutableStateOf("")}
    var outputText by remember { mutableDoubleStateOf(value=0.0) }

    // default values of text to be displayed in currency selectors
    var fromDisplayText by remember { mutableStateOf(value="From Currency") }
    var toDisplayText by remember { mutableStateOf(value="To Currency") }

    // stores whether the drop down much be expanded or not
    var fromExpand by remember { mutableStateOf(value=false) }
    var toExpand by remember { mutableStateOf(value=false) }

    // function to standardize currency
    fun standardizeFromCurrency():Double{
        var outText = when (fromDisplayText){
            "INR"-> (inputText.toDoubleOrNull() ?: 0.0)
            "CAD"-> (inputText.toDoubleOrNull() ?: 0.0)*62.5
            "AED"-> (inputText.toDoubleOrNull() ?: 0.0)*22.7272
            else -> 0.0
        }
        return outText
    }
    // function to convert currency
    fun convertToCurrency(standardized: Double):Double{
        var outText = when (toDisplayText){
            "INR"-> standardized
            "CAD"-> standardized*0.016
            "AED"-> standardized*0.044
            else -> 0.0
        }
        return outText
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text("Currency Converter",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputText, onValueChange = {
            inputText = it
            var standardized = standardizeFromCurrency()
            // dynamically change results
            outputText = convertToCurrency(standardized)
        })
        Spacer(modifier = Modifier.height(16.dp))

        Row{
            Box{
                Button(
                    onClick = {
                        fromExpand  = !fromExpand
                    }
                ){
                    Text(fromDisplayText)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "For From Drop down")
                }
                DropdownMenu(
                    expanded = fromExpand,
                    onDismissRequest = {
                        fromExpand=!fromExpand
                        // dont change the stored state
                    }) {
                    DropdownMenuItem(
                        text = { Text("INR") },
                        onClick = {
                            fromExpand = !fromExpand
                            fromDisplayText = "INR"
                            var standardized = standardizeFromCurrency()
                            // dynamically change results
                            outputText = convertToCurrency(standardized)
                        })
                    DropdownMenuItem(
                        text = { Text("CAD") },
                        onClick = {
                            fromExpand = !fromExpand
                            fromDisplayText = "CAD"
                            var standardized = standardizeFromCurrency()
                            // dynamically change results
                            outputText = convertToCurrency(standardized)
                        })
                    DropdownMenuItem(
                        text = { Text("AED") },
                        onClick = {
                            fromExpand = !fromExpand
                            fromDisplayText = "AED"
                            var standardized = standardizeFromCurrency()
                            // dynamically change results
                            outputText = convertToCurrency(standardized)
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(
                    onClick = {
                        toExpand  = !toExpand
                    }
                ){
                    Text(toDisplayText)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "For To Drop down")
                }

                DropdownMenu(
                    expanded = toExpand,
                    onDismissRequest = {
                        toExpand = !toExpand
                        // do not perform any conversion
                        // keep state of output text
                    }) {
                    DropdownMenuItem(
                        text = { Text("INR") },
                        onClick = {
                            toExpand = !toExpand
                            toDisplayText = "INR"
                            var standardized = standardizeFromCurrency()
                            // dynamically change results
                            outputText = convertToCurrency(standardized)
                        })
                    DropdownMenuItem(
                        text = { Text("CAD") },
                        onClick = {
                            toExpand = !toExpand
                            toDisplayText = "CAD"
                            var standardized = standardizeFromCurrency()
                            // dynamically change results
                            outputText = convertToCurrency(standardized)
                        })
                    DropdownMenuItem(
                        text = { Text("AED") },
                        onClick = {
                            toExpand = !toExpand
                            toDisplayText = "AED"
                            var standardized = standardizeFromCurrency()
                            // dynamically change results
                            outputText = convertToCurrency(standardized)
                        })
                }
            }
        }

        // result text
        Text("Result: ${outputText.toString()}",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme {
        ConvertorWindow()
    }
}