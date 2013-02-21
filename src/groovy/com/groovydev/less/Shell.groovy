package com.groovydev.less

import groovy.util.logging.Log4j
import org.mozilla.javascript.Context
import org.mozilla.javascript.NativeArray

@Log4j
class Shell {

    static void print(text) {
        log.debug text
    }

    static String readFile(String filename) {
        log.debug "readFile: ${filename}"
        try {
            new File(filename).text
        } catch (Exception e) {
            e.printStackTrace()
            return null
        }
    }

    static String writeFile(String filename, text) {
        new File(filename).text = text
    }

    static String readUrl(String url) {
        log.debug "readUrl: ${url}"
        try {
            new URL(url).text
        } catch (Exception e) {
            return null
        }
    }

    static String resolveUri(String path, NativeArray paths) {
        log.debug "resolveUri: path=${path}"
        for (Object index : paths.getIds()) {
            def it = paths.get(index, null)
            def file = new File(it, path)
            log.trace "test exists: ${file}"
            if (file.exists()) {
                log.trace "found file: ${file}"
                return file.toURI().toString()
            }
        }

        return null
    }
}
