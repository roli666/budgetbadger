package com.example.budgetbadger.model

class SearchResultBase(
    val results: List<SearchResult>,
    val page: Int,
    val total_results: Int,
    val total_pages: Int
)