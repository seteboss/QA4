package com.restreader;

import java.util.Optional;

public interface RestReader {
    Optional<String> getJsonString(String urlPath);
}
