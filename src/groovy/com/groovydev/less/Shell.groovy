package com.groovydev.less

import org.mozilla.javascript.Context

class Shell {

    public static void print(text) {
//        println text
    }

    public static String readFile(String filename) {
//        println "readFile: ${filename}"
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
//        println "readUrl: ${url}"
        try {
            new URL(url).text
        } catch (Exception e) {
            return null
        }
    }

    public static String resolveUri(String path, org.mozilla.javascript.NativeArray paths) {
//        println "resolveUri: path=${path}"
        for (Object index : paths.getIds()) {
            def it = paths.get(index, null);
            def file = new File(it, path)
//            println "test exists: ${file}"
            if (file.exists()) {
//                println "found file: ${file}"
                return file.toURI().toString();
            }
        }

        return null
    }
}
