package ru.shishkin.docker;

import java.io.*;
import java.net.URL;
import java.util.stream.Collectors;

public class Utils {
    public static String readAll(final String url) {
        try {
            try (InputStream is = new URL(url).openConnection().getInputStream()) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                    return reader.lines().collect(Collectors.joining());
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
