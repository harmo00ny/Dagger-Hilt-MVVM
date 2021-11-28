package com.marysugar.dagger_hilt_mvvm.repository

import com.marysugar.dagger_hilt_mvvm.model.Blog
import com.marysugar.dagger_hilt_mvvm.network.BlogApi
import com.marysugar.dagger_hilt_mvvm.network.BlogMapper
import com.marysugar.dagger_hilt_mvvm.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val blogApi: BlogApi,
    private val blogMapper: BlogMapper
) {
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkBlogs = blogApi.get()
            emit(DataState.Success(blogMapper.mapFromEntityList(networkBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}