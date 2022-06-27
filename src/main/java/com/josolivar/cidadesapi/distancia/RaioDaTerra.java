package com.josolivar.cidadesapi.distancia;

public enum RaioDaTerra {

    METROS("m", 6378168),
    QUILOMETROS("km", 6378.168f),
    MILHAS("mi", 3958.747716f);

    private final String unit;
    private final float value;

    RaioDaTerra(final String unit, final float value) {
        this.unit = unit;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
