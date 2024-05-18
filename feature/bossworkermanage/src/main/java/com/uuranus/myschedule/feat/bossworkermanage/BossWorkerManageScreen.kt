package com.uuranus.myschedule.feat.bossworkermanage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uuranus.designsystem.component.DeleteDialog
import com.uuranus.designsystem.component.LoadingScreen
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.component.MyScheduleOutlinedButton
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.WorkerInfo
import com.uuranus.myschedule.feature.bossmypage.R

val screenPadding = 16.dp

@Composable
fun BossWorkerManageScreen(
    viewModel: BossWorkerManageViewModel = hiltViewModel(),
) {

    val uiState by viewModel.bossWorkerManageUiState.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MyScheduleTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            MyScheduleAppBar(
                title = {
                    Text("직원 관리", style = MyScheduleTheme.typography.bold16)
                },
            )
            when (uiState) {
                is BossWorkerMangeUiState.Loading -> LoadingScreen()
                is BossWorkerMangeUiState.Success ->

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 21.dp)
                    ) {
                        val workers = (uiState as BossWorkerMangeUiState.Success).workers
                        items(workers.size) { index ->
                            WorkerInfoListItem(viewModel, workers[index])
                        }
                    }
            }

        }
    }
}

@Composable
fun WorkerInfoListItem(
    viewModel: BossWorkerManageViewModel,
    workerInfo: WorkerInfo,
) {

    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    var selectedWorker by remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = screenPadding, vertical = 6.dp)
            .border(
                1.dp, color = MyScheduleTheme.colors.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                workerInfo.workerName,
                style = MyScheduleTheme.typography.semiBold16
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row {

                Text(
                    "고용형태: ${workerInfo.workerType}",
                    style = MyScheduleTheme.typography.regular14
                )

                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(
                        id = com.uuranus.myschedule.core.designsystem.R.drawable.edit_icon
                    ),
                    contentDescription = "직원 고용형태 수정",
                    modifier = Modifier.clickable {
                        showEditDialog = true
                        selectedWorker = workerInfo.memeberId
                    }
                )

            }
        }
        Image(
            painter = painterResource(id = com.uuranus.myschedule.core.designsystem.R.drawable.delete_icon),
            contentDescription = "직원 삭제",
            modifier = Modifier.clickable {
                showDeleteDialog = true
                selectedWorker = workerInfo.memeberId
            }
        )

    }

    if (showEditDialog) {
        EditWorkerTypeDialog(
            onDismissDialog = {
                showEditDialog = false
            },
            onWorkerTypeSelected = {
                showEditDialog = false
                viewModel.editWorker(selectedWorker, it)
            }
        )
    }

    if (showDeleteDialog) {
        DeleteDialog(
            title = "직원 삭제",
            content = "해당 직원을 삭제하시겠습니까?",
            onDismissDialog = {
                showDeleteDialog = false
            }, onConfirmDialog = {
                showDeleteDialog = false
                viewModel.deleteWorker(selectedWorker)
            })
    }
}

@Composable
fun EditWorkerTypeDialog(
    onDismissDialog: () -> Unit,
    onWorkerTypeSelected: (String) -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissDialog,
        containerColor = MyScheduleTheme.colors.background,
        title = {
            Text(
                text = "고용형태 수정", style = MyScheduleTheme.typography.semiBold16,
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
            )
        }, confirmButton = {
            Column(modifier = Modifier.fillMaxWidth()) {
                MyScheduleOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    paddingValues = PaddingValues(13.dp),
                    buttonState = true,
                    content = {
                        Text(
                            "매니저", style = MyScheduleTheme.typography.semiBold16,
                            color = MyScheduleTheme.colors.textColor
                        )
                    }
                ) {
                    onWorkerTypeSelected("매니저")
                }
                Spacer(modifier = Modifier.height(4.dp))
                MyScheduleOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    paddingValues = PaddingValues(13.dp),
                    buttonState = true,
                    content = {
                        Text(
                            "직원", style = MyScheduleTheme.typography.semiBold16,
                            color = MyScheduleTheme.colors.textColor
                        )
                    }
                ) {
                    onWorkerTypeSelected("직원")
                }
                Spacer(modifier = Modifier.height(4.dp))
                MyScheduleOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    paddingValues = PaddingValues(13.dp),
                    buttonState = true,
                    content = {
                        Text(
                            "아르바이트", style = MyScheduleTheme.typography.semiBold16,
                            color = MyScheduleTheme.colors.textColor
                        )
                    }
                ) {
                    onWorkerTypeSelected("아르바이트")
                }
            }
        })
}
