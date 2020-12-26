package com.api;

import java.util.List;

public interface GitHubApi {
    List<String> getRepos(String user);
    String getURL();
    void setURL(String url);
}
