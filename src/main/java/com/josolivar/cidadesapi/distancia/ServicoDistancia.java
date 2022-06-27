package com.josolivar.cidadesapi.distancia;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;
import com.josolivar.cidadesapi.cidades.Cidade;
import com.josolivar.cidadesapi.cidades.RepositorioDeCidades;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Point;


@Service
public class ServicoDistancia {

    private final RepositorioDeCidades repositorioDeCidades;
    Logger log = LoggerFactory.getLogger(ServicoDistancia.class);

    public ServicoDistancia(final RepositorioDeCidades repositorioDeCidades) {
        this.repositorioDeCidades = repositorioDeCidades;
    }

    public double distanciaUsandoMath(final long cidadeId1, final long cidadeId2, final RaioDaTerra unit) {
        log.info("distanceUsingMath({}, {}, {})", cidadeId1, cidadeId2, unit);
        try {
            final List<Cidade> cidades = repositorioDeCidades.findAllById((Arrays.asList(cidadeId1, cidadeId2)));
            final Double[] localizacao1 = StringLocationUtils.transform(cidades.get(0).getGeolocalizacao());
            final Double[] localizacao2 = StringLocationUtils.transform(cidades.get(1).getGeolocalizacao());

            return doCalculation(localizacao1[0], localizacao1[1], localizacao2[0], localizacao2[1], unit);
        }
        catch (Exception e) {
            return -1.0;
        }
    }

    public double calculaDistanciaEmMilhas(final long cidadeId1, final long cidadeId2) {
        log.info("nativePostgresInMiles({}, {})", cidadeId1, cidadeId2);
        try {
            return repositorioDeCidades.distanceByPoints(cidadeId1, cidadeId2);
        }
        catch (Exception e) {
            return -1.0;
        }
    }


    public double distanciaUsandoPoints(final long cidadeId1, final long cidadeId2, final RaioDaTerra unit) {
        log.info("distanceUsingPoints({}, {}, {})", cidadeId1, cidadeId2, unit);
        final List<Cidade> cidades = repositorioDeCidades.findAllById((Arrays.asList(cidadeId1, cidadeId2)));
        try {
            Point p1 = cidades.get(0).getLocalizacao();
            Point p2 = cidades.get(1).getLocalizacao();

            return doCalculation(p1.getX(), p1.getY(), p2.getX(), p2.getY(), unit);
        }
        catch (Exception e) {
            return -1.0;
        }
    }

    public Double calculaDistanciaEmMetros(long cidadeId1, long cidadeId2) {
        log.info("distanceByCubeInMeters({}, {})", cidadeId1, cidadeId2);
        try {
            final List<Cidade> cidades = repositorioDeCidades.findAllById((Arrays.asList(cidadeId1, cidadeId2)));
            Point p1 = cidades.get(0).getLocalizacao();
            Point p2 = cidades.get(1).getLocalizacao();

            return repositorioDeCidades.distanceByCube(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
        catch (Exception e) {
            return -1.0;
        }
    }

    private double doCalculation(final double lat1, final double lon1, final double lat2,
                                 final double lng2, final RaioDaTerra raioDaTerra) {
        double lat = toRadians(lat2 - lat1);
        double lon = toRadians(lng2 - lon1);
        double a = sin(lat / 2) * sin(lat / 2) + cos(toRadians(lat1)) * cos(toRadians(lat2)) * sin(lon / 2) * sin(lon / 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));

        return raioDaTerra.getValue() * c;
    }
}