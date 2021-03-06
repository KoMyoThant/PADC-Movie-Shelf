package com.ps.movieshelf.network.responses;

import com.google.gson.annotations.SerializedName;
import com.ps.movieshelf.data.vo.MovieVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 12/6/2017.
 */

public class GetPopularMovieResponse {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("apiVersion")
    private String apiVersion;
    @SerializedName("page")
    private int page;
    @SerializedName("popular-movies")
    private List<MovieVO> popularMovies;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public int getPage() {
        return page;
    }

    public List<MovieVO> getPopularMovies() {
        if (popularMovies == null) {
            popularMovies = new ArrayList<>();
        }
        return popularMovies;
    }
}
