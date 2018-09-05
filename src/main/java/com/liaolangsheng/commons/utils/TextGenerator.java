package com.liaolangsheng.commons.utils;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class TextGenerator {
	
	public static String generate(String templatePath, Map<String, Object> replacements) {
		Properties p = new Properties();
		p.setProperty(VelocityEngine.INPUT_ENCODING, "UTF-8");
		p.setProperty(VelocityEngine.OUTPUT_ENCODING, "UTF-8");
		
		String templateDir = templatePath.substring(0, templatePath.lastIndexOf("/"));
		p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, templateDir);
		Velocity.init(p);

		String[] templatePaths = templatePath.split("/");
		String templateName = templatePaths[templatePaths.length - 1];

		Template template = Velocity.getTemplate(templateName);
		VelocityContext context = toVelocityContext(replacements);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		return writer.toString();
	}
	
	private static VelocityContext toVelocityContext(Map<String, Object> replacements){
		if(null == replacements){
			return null;
		}
		Set<String> keys = replacements.keySet();
		VelocityContext context = new VelocityContext();
		for(String key : keys){
			context.put(key, replacements.get(key));
		}
		return context;
	}
}
