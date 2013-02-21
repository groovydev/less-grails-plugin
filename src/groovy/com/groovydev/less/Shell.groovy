package com.groovydev.less

import groovy.util.logging.Log4j
import org.mozilla.javascript.Context

@Log4j
class Shell {

    public static void print(text) {
        log.debug text
    }

    public static String readFile(String filename) {
        log.debug "readFile: ${filename}"
        try {
            new File(filename).text
        } catch (Exception e) {
            e.printStackTrace()
            return null
        }
    }

    public static String writeFile(String filename, text) {
        new File(filename).text = text
    }

    public static String readUrl(String url) {
        log.debug "readUrl: ${url}"
        try {
            new URL(url).text
        } catch (Exception e) {
            return null
        }
    }

    public static String resolveUri(String path, org.mozilla.javascript.NativeArray paths) {
        log.debug "resolveUri: path=${path}"
        for (Object index : paths.getIds()) {
            def it = paths.get(index, null);
            def file = new File(it, path)
            log.trace "test exists: ${file}"
            if (file.exists()) {
                log.trace "found file: ${file}"
                return file.toURI().toString();
            }
        }

        return null
    }
}
