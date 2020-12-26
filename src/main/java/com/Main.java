package com;

import com.api.GitHubApi;
import com.api.GitHubApiImpl;

public class Main {

    public static void main (String[] args){
        GitHubApi api = new GitHubApiImpl();

        api.getRepos("burwestnik")
                .forEach(System.out::println);
    }

}
