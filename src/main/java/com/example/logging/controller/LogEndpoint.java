package com.example.logging.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogEndpoint {

    // -> private Logger logger =  LoggerFactory.getLogger(this.getClass());
    //    bu satırı her class içine tek tek yazmamak için @Slf4j annotation'ı kullanıyoruz

    @GetMapping
    public String getDetails(){
        log.info("getDetails method started");  //debug log daha doğru, hepsini görmek amaçlı info kullanıldı
        return internalLogDetail();
    }

    private String internalLogDetail(){
        try{
            log.debug("internalLogDetail method started");
            Thread.sleep(1000);
            return "API message";
        }catch (InterruptedException e){
            log.error("error : {}", e);
        }
        return "";
    }

    /*
        - loglama async olmalı. yapmazsa uygulama performansı loglama için kaybediliyor olacak. (büyük maaliyet)
            requesti bloklamadan arka planda çalışsın demek istiyoruz.
        - loglamada -> printStackTrace ve System.out.println() kullanılmamalı -> log formatını bozuyor, formata aykırı.
        - sensitive data olmamalıdır. -> müşteri bilgisi, kullanıcıId, password vs içermemeli (veri güvenliği ihlal edilmemeli)
        - Tüm loglar merkezi bir yerde ve belirli bir formatta toplanmalı(hepsi json, hepsi xml olmalı gibi)
                            -> takip açısından avantaj sağlar
        - level'lar dikkatli kullanılmalı.
        - farklı level'lar için farklı appendar(handler)lar kullanılabilir
                            -> tüm loglar merkezi yere toplandıktan sonra bellirli leveldaki loglar için işlem yapmak isteyebiliriz
                               bu durumda işimizi kolaylaştırır.
     */
}
