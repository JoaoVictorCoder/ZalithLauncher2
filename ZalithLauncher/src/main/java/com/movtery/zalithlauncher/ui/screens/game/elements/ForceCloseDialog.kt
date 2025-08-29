package com.movtery.zalithlauncher.ui.screens.game.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.movtery.zalithlauncher.R
import com.movtery.zalithlauncher.bridge.ZLNativeInvoker
import com.movtery.zalithlauncher.ui.components.SimpleAlertDialog

sealed interface ForceCloseOperation {
    data object None : ForceCloseOperation
    /** 显示强制关闭对话框 */
    data object Show : ForceCloseOperation
}

@Composable
fun ForceCloseOperation(
    operation: ForceCloseOperation,
    onChange: (ForceCloseOperation) -> Unit,
    text: String
) {
    when (operation) {
        ForceCloseOperation.None -> {}
        ForceCloseOperation.Show -> {
            SimpleAlertDialog(
                title = stringResource(R.string.game_button_force_close),
                text = text,
                onConfirm = {
                    ZLNativeInvoker.jvmExit(0, false)
                },
                onDismiss = {
                    onChange(ForceCloseOperation.None)
                }
            )
        }
    }
}