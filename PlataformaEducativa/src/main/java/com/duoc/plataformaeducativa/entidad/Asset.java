package com.duoc.plataformaeducativa.entidad;

import java.net.URL;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Asset {

    String name;
    String key;
    URL url;

}
