package com.companyname.automation.commontools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class PropertyFile {

    Properties prop;
    String sPropertiesFilePath;
    ArrayListFldValues alfvProperties;

    public PropertyFile(String sPropertiesFilePath) {
        this.sPropertiesFilePath = sPropertiesFilePath;

        prop = new Properties();

        File propfile = new File(sPropertiesFilePath);
        String saP[] = sPropertiesFilePath.split(".");
        if(propfile.exists()  && (saP[1].trim().compareToIgnoreCase("propertties")==0)) {
            try (InputStream input = new FileInputStream(sPropertiesFilePath)) {
                // load a properties file
                prop.load(input);
                 alfvProperties = GetAllProperties();
             } catch (IOException ex) {
                ex.printStackTrace();
            }

        }


    }

    public boolean SetProperty(String sKey, String sValue)
    {
        try {
            prop.setProperty(sKey, sValue);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public String GetProperty(String sKey)
    {
        try {
            return prop.getProperty(sKey);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public ArrayListFldValues GetAllProperties()
    {
        ArrayList<String> alKeys = new ArrayList();
        ArrayList<String> alValues = new ArrayList();

        prop.forEach((key, value) -> {
            alKeys.add(key.toString());
            alValues.add(value.toString());
        });
        return new ArrayListFldValues(alKeys,alValues);

    }
}
