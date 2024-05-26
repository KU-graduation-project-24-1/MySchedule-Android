package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.WorkerInfo
import com.uuranus.myschedule.core.network.model.DeleteWorkerBody
import com.uuranus.myschedule.core.network.model.PatchWorkerTypeBody
import com.uuranus.myschedule.core.network.model.mapper.toData
import com.uuranus.myschedule.core.network.service.MyScheduleService
import javax.inject.Inject

class WorkerDataSourceImpl @Inject constructor(
    private val service: MyScheduleService,
) : WorkerDataSource {

    override suspend fun getAllWorkers(accessToken: String, storeId: Int): List<WorkerInfo> {
        val response = service.getAllWorkers(
            "Bearer $accessToken",
            storeId
        )

        if (response.isSuccessful) {
            return response.body()?.result?.toData() ?: emptyList()
        } else {
            throw Exception(response.body()?.result?.toString())
        }
    }

    override suspend fun changeWorkerType(
        accessToken: String,
        storeId: Int,
        memberId: Int,
        workerType: String,
    ): Boolean {
        val response = service.patchWorkerType(
            "Bearer $accessToken",
            PatchWorkerTypeBody(
                storeId,
                memberId,
                workerType
            )
        )

        if (response.isSuccessful) {
            return true
        } else {
            throw Exception(response.body()?.result?.toString())
        }

    }

    override suspend fun deleteWorker(accessToken: String, storeId: Int, memberId: Int): Boolean {
        val response = service.deleteWorker(
            "Bearer $accessToken",
            DeleteWorkerBody(
                storeId,
                memberId
            )
        )

        if (response.isSuccessful) {
            return true
        } else {
            throw Exception(response.body()?.result?.toString())
        }
    }


}