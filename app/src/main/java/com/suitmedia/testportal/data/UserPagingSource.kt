package com.suitmedia.testportal.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.suitmedia.testportal.data.remote.response.DataItem
import com.suitmedia.testportal.data.remote.retrofit.ApiService

class UserPagingSource(
    private val apiService: ApiService,
    private val page: Int
) : PagingSource<Int, DataItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val nextPage = params.key ?: page
            val response = apiService.getUsers(nextPage, params.loadSize)
            val maxpage = response.totalPages

            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPage == page) null else nextPage - 1,
                nextKey = if (nextPage == maxpage) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}