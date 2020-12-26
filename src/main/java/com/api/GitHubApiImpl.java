package com.api;

import com.parser.JSONParser;
import com.parser.JSONParserImpl;
import com.restreader.RestReader;
import com.restreader.RestReaderImpl;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class GitHubApiImpl implements GitHubApi {

    private String GIT_HUB_URL_PATH = "https://api.github.com/";

    private RestReader restReader  = new RestReaderImpl();;
    private JSONParser jsonParser  = new JSONParserImpl();

    public GitHubApiImpl(RestReader restReader, JSONParser jsonParser) {
        this.restReader = restReader;
        this.jsonParser = jsonParser;
    }

    private String getUrlUsersReport(String user) {
        return GIT_HUB_URL_PATH + "users/" + user + "/repos";
    }

    @Override
    public List<String> getRepos(String user) {
        Optional<String> jsonString = restReader.getJsonString(getUrlUsersReport(user));

        if (jsonString.isPresent())
            return jsonParser.parsReport(jsonString.get());
        else
            throw new RuntimeException("cannot get report");
    }

    @Override
    public String getURL() {
        return GIT_HUB_URL_PATH;
    }

    @Override
    public void setURL(String url) {
        GIT_HUB_URL_PATH = url;
    }
}
