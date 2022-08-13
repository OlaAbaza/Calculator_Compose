package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.DarkGray
import com.example.calculator.ui.theme.LightGray
import com.example.calculator.ui.theme.MediumGray
import com.example.calculator.ui.theme.Orange
import androidx.compose.ui.unit.sp as sp1


@Composable
fun Calculator(
    status: CalculatorStatus,
    modifier: Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Text(
                text = status.number1 + (status.operation?.symbol ?: "") + status.number2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                fontWeight = FontWeight.Light,
                fontSize = 80.sp1,
                color = Color.White,
                maxLines = 2
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(2f)
                        .weight(2f),
                    onClick = {
                        onAction(CalculatorAction.Clear)
                    }
                )
                CalculatorButton(
                    symbol = "Del",
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Delete)
                    }
                )
                CalculatorButton(
                    symbol = "/",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                    }
                )
            }

            CalculatorRow(
                operationSymbol = "x",
                calculatorOperation = CalculatorOperation.Multiply,
                numberList = listOf(7, 8, 9),
                buttonSpacing = buttonSpacing,
                onAction = onAction
            )
            CalculatorRow(
                operationSymbol = "-",
                calculatorOperation = CalculatorOperation.Subtract,
                numberList = listOf(4, 5, 6),
                buttonSpacing = buttonSpacing,
                onAction = onAction
            )
            CalculatorRow(
                operationSymbol = "+",
                calculatorOperation = CalculatorOperation.Add,
                numberList = listOf(1, 2, 3),
                buttonSpacing = buttonSpacing,
                onAction = onAction
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "0",
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(2f)
                        .weight(2f),
                    onClick = {
                        onAction(CalculatorAction.Number(0))
                    }
                )
                CalculatorButton(
                    symbol = ".",
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Decimal)
                    }
                )
                CalculatorButton(
                    symbol = "=",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Calculate)
                    }
                )
            }
        }
    }

}

@Composable
fun CalculatorRow(
    operationSymbol: String,
    calculatorOperation: CalculatorOperation,
    numberList: List<Int>,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
    ) {
        for (number in numberList)
            CalculatorButton(
                symbol = number.toString(),
                modifier = Modifier
                    .background(MediumGray)
                    .aspectRatio(1f)
                    .weight(1f),
                onClick = {
                    onAction(CalculatorAction.Number(number))
                }
            )

        CalculatorButton(
            symbol = operationSymbol,
            modifier = Modifier
                .background(Orange)
                .aspectRatio(1f)
                .weight(1f),
            onClick = {
                onAction(CalculatorAction.Operation(calculatorOperation))
            }
        )
    }
}

@Preview
@Composable
fun AppPreview() {
    Calculator(
        buttonSpacing = 8.dp,
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGray)
            .padding(16.dp),
        onAction = {},
        status = CalculatorStatus()
    )
}