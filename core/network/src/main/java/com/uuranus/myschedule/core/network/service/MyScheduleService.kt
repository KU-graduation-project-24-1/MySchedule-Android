package com.uuranus.myschedule.core.network.service

import com.uuranus.myschedule.core.network.model.ApiResponse
import com.uuranus.myschedule.core.network.model.DeleteFixedScheduleBody
import com.uuranus.myschedule.core.network.model.DeleteOperationInfoBody
import com.uuranus.myschedule.core.network.model.DeletePossibleTimeBody
import com.uuranus.myschedule.core.network.model.DeleteWorkerBody
import com.uuranus.myschedule.core.network.model.GetAllWorkersResult
import com.uuranus.myschedule.core.network.model.GetFixedScheduleResponse
import com.uuranus.myschedule.core.network.model.GetMonthlyPossibleTimesResult
import com.uuranus.myschedule.core.network.model.GetMonthlyScheduleResult
import com.uuranus.myschedule.core.network.model.GetOperationInfoResult
import com.uuranus.myschedule.core.network.model.LoginRequest
import com.uuranus.myschedule.core.network.model.LoginResponse
import com.uuranus.myschedule.core.network.model.PatchFixedScheduleResponse
import com.uuranus.myschedule.core.network.model.PatchScheduleBody
import com.uuranus.myschedule.core.network.model.PatchScheduleCover
import com.uuranus.myschedule.core.network.model.PatchScheduleResult
import com.uuranus.myschedule.core.network.model.PatchWorkerNumBody
import com.uuranus.myschedule.core.network.model.PatchWorkerTypeBody
import com.uuranus.myschedule.core.network.model.PathFixedScheduleBody
import com.uuranus.myschedule.core.network.model.PostOperationInfoBody
import com.uuranus.myschedule.core.network.model.PostOperationInfoResponse
import com.uuranus.myschedule.core.network.model.PostPossibleTime
import com.uuranus.myschedule.core.network.model.PostPossibleTimeBody
import com.uuranus.myschedule.core.network.model.PostScheduleBody
import com.uuranus.myschedule.core.network.model.PostScheduleResult
import com.uuranus.myschedule.core.network.model.SignUpRequest
import com.uuranus.myschedule.core.network.model.SignUpResponse
import com.uuranus.myschedule.core.network.model.StoreResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyScheduleService {

    @POST("auth/login")
    suspend fun serviceLogin(
        @Body body: LoginRequest,
    ): LoginResponse

    @POST("auth/name")
    suspend fun signUp(
        @Header("Authorization") authorization: String,
        @Body body: SignUpRequest,
    ): SignUpResponse

    @GET("member/store")
    suspend fun getStoreList(
        @Header("Authorization") authorization: String,
    ): StoreResponse
    /*
        로그인 api 자리
     */

    @GET("/store/{storeId}/schedule/{dateYM}")
    suspend fun getMonthlySchedules(
        @Header("Authorization") authorization: String,
        @Path("storeId") storeId: Int,
        @Path("dateYM") dateYM: String,
    ): Response<ApiResponse<GetMonthlyScheduleResult>>

    @GET("/store/{storeId}/available-schedule/{dateYM}")
    suspend fun getMonthlyPossibleTimes(
        @Header("Authorization") authorization: String,
        @Path("storeId") storeId: Int,
        @Path("dateYM") dateYM: String,
    ): Response<ApiResponse<GetMonthlyPossibleTimesResult>>

    @POST("/store/schedule/available")
    suspend fun postPossibleTime(
        @Header("Authorization") authorization: String,
        @Body body: PostPossibleTimeBody,
    ): Response<ApiResponse<PostPossibleTime>>

    @HTTP(method = "DELETE", path = "/store/schedule/available", hasBody = true)
    suspend fun deletePossibleTime(
        @Header("Authorization") authorization: String,
        @Body body: DeletePossibleTimeBody,
    ): Response<ApiResponse<String>>

    @POST("/store/schedule/{scheduleId}/cover")
    suspend fun postScheduleCover(
        @Header("Authorization") authorization: String,
        @Path("scheduleId") scheduleId: Int,
    ): Response<ApiResponse<String>>

    @PATCH("/store/schedule/{scheduleId}/cover")
    suspend fun patchScheduleCover(
        @Header("Authorization") authorization: String,
        @Path("scheduleId") scheduleId: Int,
    ): Response<ApiResponse<PatchScheduleCover>>

    //고용인 한정 기능
    @GET("/executive/{storeId}/employee")
    suspend fun getAllWorkers(
        @Header("Authorization") authorization: String,
        @Path("storeId") storeId: Int,
    ): Response<ApiResponse<GetAllWorkersResult>>

    @PATCH("/executive/employee/grade")
    suspend fun patchWorkerType(
        @Header("Authorization") authorization: String,
        @Body body: PatchWorkerTypeBody,
    ): Response<ApiResponse<String>>

    @HTTP(method = "DELETE", path = "/executive/employee", hasBody = true)
    suspend fun deleteWorker(
        @Header("Authorization") authorization: String,
        @Body body: DeleteWorkerBody,
    ): Response<ApiResponse<String>>

    @DELETE("/executive/store/{storeId}")
    suspend fun deleteStore(
        @Header("Authorization") authorization: String,
        @Path("storeId") storeId: Int,
    ): Response<ApiResponse<String>>

    @POST("/executive/schedule")
    suspend fun postSchedule(
        @Header("Authorization") authorization: String,
        @Body body: PostScheduleBody,
    ): Response<ApiResponse<PostScheduleResult>>

    @PATCH("/executive/schedule")
    suspend fun patchSchedule(
        @Header("Authorization") authorization: String,
        @Body body: PatchScheduleBody,
    ): Response<ApiResponse<PatchScheduleResult>>

    @DELETE("/executive/{storeId}/schedule/{scheduleId}")
    suspend fun deleteSchedule(
        @Header("Authorization") authorization: String,
        @Path("storeId") storeId: Int,
        @Path("scheduleId") scheduleId: Int,
    ): Response<ApiResponse<String>>

    @GET("/store/{storeId}/operation-info")
    suspend fun getOperationInfo(
        @Header("Authorization") authorization: String,
        @Path("storeId") storeId: Int,
        @Query("dayOfWeek") dayOfWeek: String,
    ): Response<ApiResponse<List<GetOperationInfoResult>>>

    @POST("/store/schedule/{storeId}/operation-info")
    suspend fun postOperationInfo(
        @Header("Authorization") authorization: String,
        @Path("storeId") storeId: Int,
        @Body body: PostOperationInfoBody,
    ): Response<ApiResponse<PostOperationInfoResponse>>

    @HTTP(method = "DELETE", path = "/store/schedule/{storeId}/operation-info", hasBody = true)
    suspend fun deleteOperationInfo(
        @Header("Authorization") authorization: String,
        @Path("storeId") storeId: Int,
        @Body body: DeleteOperationInfoBody,
    ): Response<ApiResponse<String>>

    @PATCH("/store/schedule/operation-info/required-employees")
    suspend fun patchWorkerNum(
        @Header("Authorization") authorization: String,
        @Body body: PatchWorkerNumBody,
    ): Response<ApiResponse<Any>>

    //마이페이지
    @GET("/store/{storeId}/fixed-schedule")
    suspend fun getFixedSchedule(
        @Header("Authorization") authorization: String,
        @Path("storeId") storeId: Int,
    ): Response<ApiResponse<GetFixedScheduleResponse>>

    @PATCH("/store/schedule/{storeId}/fixed-schedule/add")
    suspend fun patchFixedSchedule(
        @Header("Authorization") authorization: String,
        @Path("storeId") storeId: Int,
        @Body body: PathFixedScheduleBody,
    ): Response<ApiResponse<PatchFixedScheduleResponse>>

    @HTTP(
        method = "DELETE",
        path = "/store/schedule/{storeId}/fixed-schedule/delete",
        hasBody = true
    )
    suspend fun deleteFixedSchedule(
        @Header("Authorization") authorization: String,
        @Path("storeId") storeId: Int,
        @Body body: DeleteFixedScheduleBody,
    ): Response<ApiResponse<String>>

}
