/*
 * Copyright (c)
 */

package io.examples.fakes.util;

import io.examples.fakes.CSVManagerIfc;
import io.github.hglabplh_tech.csvxml.Kvdef;
import io.github.hglabplh_tech.csvxml.Linekeyvals;
import io.github.hglabplh_tech.csvxml.ObjectFactory;
import io.github.hglabplh_tech.csvxml.Rowdev;
import jakarta.xml.bind.*;
import org.apache.commons.collections.KeyValue;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.examples.fakes.CSVManagerIfc.*;

public class XMLWriter {

    private final Map<String, String> keyValues;

    /*
    has never to be called
     */
    private XMLWriter() {
        this.keyValues = new HashMap<>();
    }
    public XMLWriter (Map<String, String> keyValues) {
        this.keyValues = keyValues;
    }


    public  Linekeyvals buildRoot() {
        ObjectFactory factory = new ObjectFactory();
        return factory.createLinekeyvals();
    }

    public  Linekeyvals buildXMLTree(Linekeyvals lineKV) {
        ObjectFactory factory = new ObjectFactory();
        Rowdev row = factory.createRowdev();
        lineKV.getLine().add(row);
        for (String key: keyValues.keySet()) {
            Kvdef keyValue = factory.createKvdef();
            Class<String> string = null;
            Arrays.asList(
                    new JAXBElement<>(new QName("key"), String.class, key),
                    new JAXBElement<>(new QName("value"), String.class, keyValues.get(key)))
                    .forEach(stringJAXBElement ->
                            keyValue.getKeyAndValue().add(stringJAXBElement));
            row.getKeyval().add(keyValue);
        }
        return lineKV;
    }

    public void marshallXML(Linekeyvals root, IOBlock ioBlock) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance( "hglabplh-tech.github.io/csvxml" );
        Marshaller marshaller = jc.createMarshaller();
        marshaller.marshal(root, ioBlock.getOutStream());
    }

    public JAXBElement<Linekeyvals> unmarshallXML(IOBlock ioBlock) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance( "hglabplh-tech.github.io/csvxml" );
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (JAXBElement<Linekeyvals>) unmarshaller.unmarshal(ioBlock.getInStream());
    }
}
