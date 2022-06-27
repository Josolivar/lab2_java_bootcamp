package com.josolivar.cidadesapi.distancia;

public class StringLocationUtils {

    public static Double[] transform(String geolocalizaao) {
        String result = geolocalizaao.replace("(", "").replace(")", "");
        String[] strings = result.trim().split(",");
        return new Double[] {Double.valueOf(strings[0]), Double.valueOf(strings[1])};
    }

}
