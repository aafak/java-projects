// Copyright 2012 Citrix Systems, Inc. Licensed under the
// Apache License, Version 2.0 (the "License"); you may not use this
// file except in compliance with the License.  Citrix Systems, Inc.
// reserves all rights not expressly granted by the License.
// You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// 
// Automatically generated by addcopyright.py at 04/03/2012
package com.cloud.utils.qal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class PropertiesUtil {
    /**
     * Searches the class path and local paths to find the config file.
     * @param path path to find.  if it starts with / then it's absolute path.
     * @return File or null if not found at all.
     */
	
    public static File findConfigFile(String path) {
        ClassLoader cl = PropertiesUtil.class.getClassLoader();
        URL url = cl.getResource(path);
        if (url != null) {
            return new File(url.getFile());
        }
        
        url =  ClassLoader.getSystemResource(path);
        if (url != null) {
            return new File(url.getFile());
        }
        
        File file = new File(path);
        if (file.exists()) {
            return file;
        }
        
        String newPath = "conf" + (path.startsWith(File.separator) ? "" : "/") + path;
        url = ClassLoader.getSystemResource(newPath);
        if (url != null) {
            return new File(url.getFile());
        }
        
        url = cl.getResource(newPath);
        if (url != null) {
            return new File(url.getFile());
        }
        
        newPath = "conf" + (path.startsWith(File.separator) ? "" : File.separator) + path;
        file = new File(newPath);
        if (file.exists()) {
            return file;
        }
        
        newPath = System.getenv("CATALINA_HOME");
        if (newPath == null) {
        	newPath = System.getenv("CATALINA_BASE");
        }
        
        if (newPath == null) {
        	newPath = System.getProperty("catalina.home");
        }
        
        if (newPath == null) {
            return null;
        }
        
        file = new File(newPath + File.separator + "conf" + File.separator + path);
        if (file.exists()) {
            return file;
        }
        
        return null;
    }
    
    public static Map<String, Object> toMap(Properties props) {
        Set<String> names = props.stringPropertyNames();
        HashMap<String, Object> map = new HashMap<String, Object>(names.size());
        for (String name : names) {
            map.put(name, props.getProperty(name));
        }
        
        return map;
    }
    
    /*
     * Returns an InputStream for the given resource 
     * This is needed to read the files within a jar in classpath.
     */
    public static InputStream openStreamFromURL(String path){
        ClassLoader cl = PropertiesUtil.class.getClassLoader();
        URL url = cl.getResource(path);
        if (url != null) {
             try{
                 InputStream stream = url.openStream();
                 return stream;
            } catch (IOException ioex) {
                return null;
            }
        }
        return null;
    }
}
