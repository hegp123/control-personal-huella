package com.anjelin.util;

import java.util.EnumMap;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import java.util.EnumSet;
import java.util.Iterator;

public class Utilities {

    private static final EnumMap<DPFPFingerIndex, String> fingerNames;
    static {
    	fingerNames = new EnumMap<DPFPFingerIndex, String>(DPFPFingerIndex.class);
    	fingerNames.put(DPFPFingerIndex.LEFT_PINKY,  "MEÑIQUE IZQUIERDO");
    	fingerNames.put(DPFPFingerIndex.LEFT_RING,    "ANULAR IZQUIERDO");
    	fingerNames.put(DPFPFingerIndex.LEFT_MIDDLE,  "MEDIO IZQUIERDO");
    	fingerNames.put(DPFPFingerIndex.LEFT_INDEX,   "INDICE IZQUIERDO");
    	fingerNames.put(DPFPFingerIndex.LEFT_THUMB,   "PULGAR IZQUIERDO");
    	
    	fingerNames.put(DPFPFingerIndex.RIGHT_PINKY,  "MEÑIQUE DERECHO");
    	fingerNames.put(DPFPFingerIndex.RIGHT_RING,   "ANULAR DERECHO");
    	fingerNames.put(DPFPFingerIndex.RIGHT_MIDDLE, "MEDIO DERECHO");
    	fingerNames.put(DPFPFingerIndex.RIGHT_INDEX,  "INDICE DERECHO");
    	fingerNames.put(DPFPFingerIndex.RIGHT_THUMB,  "PULGAR DERECHO");
    }

    public static String fingerName(DPFPFingerIndex finger) {
    	return fingerNames.get(finger); 
    }
    public static String fingerprintName(DPFPFingerIndex finger) {
    	return "HUELLA DACTILAR "+fingerNames.get(finger); 
    }
    
    public static DPFPFingerIndex enumFromBit(long bit) {

        EnumSet<DPFPFingerIndex> fromMask = DPFPFingerIndex.fromMask(bit);
        DPFPFingerIndex fingerIndex = null;
        for (Iterator<DPFPFingerIndex> it = fromMask.iterator(); it.hasNext();) {
            fingerIndex = it.next();
        }
        
        return fingerIndex;

    }
    
}
