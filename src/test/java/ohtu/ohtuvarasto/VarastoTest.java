package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenVarastonAlkuSaldoOikein() {
        Varasto v = new Varasto(10, 5);
        
        assertEquals(5, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenVarastonAlkuSaldoOikeinKunSaldoSuurempi() {
        Varasto v = new Varasto(10, 11);
        
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenVarastonAlkuSaldoOikeinVirheellisellaSyotteellaTilavuusJaSaldoParametreina() {
        Varasto v = new Varasto(10, -1);
        
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }
    

    
    @Test
    public void uudenVarastonTilavuusNollaVirheellisellaSyotteella() {
        Varasto v = new Varasto(-1);
        assertEquals(0, v.getTilavuus(),vertailuTarkkuus);
    }
    
    @Test
    public void uudenVarastonTilavuusNollaVirheellisellaSyotteellaTilavuusJaSaldoParametreina() {
        Varasto v = new Varasto(-1, 0);
        
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }
    
    
    @Test
    public void varastonSaldoEiMeneYliTilavuuden() {
        varasto.lisaaVarastoon(11);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoiOttaaSaldoaEnempaa() {
        varasto.lisaaVarastoon(2);
        
        varasto.otaVarastosta(3);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonSaldoEiMuutuKunLisataanVirheellisellaSyotteella() {
        
        varasto.lisaaVarastoon(-1);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonSaldoEiMuutuKunOtetaanVirheellisellaSyotteella() {
        varasto.otaVarastosta(-1);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonTulostusOikein() {
        assertTrue(varasto.toString().equals("saldo = 0.0, vielä tilaa 10.0"));
    }

}