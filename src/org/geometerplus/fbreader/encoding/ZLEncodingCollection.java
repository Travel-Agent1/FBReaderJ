package org.geometerplus.fbreader.encoding;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.geometerplus.zlibrary.core.config.ZLConfigManager;
import org.geometerplus.zlibrary.core.library.ZLibrary;
import org.geometerplus.zlibrary.core.options.ZLBooleanOption;
import org.geometerplus.zlibrary.core.options.ZLOption;

public class ZLEncodingCollection {
	private static ZLEncodingCollection ourInstance;
	private	static ZLBooleanOption ourUseWindows1252HackOption;

	public static ZLEncodingCollection instance() {
		if (ourInstance == null) {
			ourInstance = new ZLEncodingCollection();
		}
		return ourInstance;
	}
	public	static String encodingDescriptionPath() {
		return ZLibrary.JAR_DATA_PREFIX + "zlibrary" + File.separator + "encodings";
	}
	
	public	static ZLBooleanOption useWindows1252HackOption() {
		if (ourUseWindows1252HackOption == null) {
			ourUseWindows1252HackOption =
				new ZLBooleanOption(ZLOption.CONFIG_CATEGORY, "Encoding", "UseWindows1252Hack", true);
		}
		return ourUseWindows1252HackOption;
	}
	
	public	static boolean useWindows1252Hack() {
		return ZLConfigManager.getInstance() != null/*.isInitialised()*/ && useWindows1252HackOption().getValue();
	}


	public ArrayList<ZLEncodingSet>  sets() {
		init();
		return mySets;
	}
	public	ZLEncodingConverterInfo info(String name) {
		init();
		String lowerCaseName = name.toLowerCase();
		if (useWindows1252Hack() && (lowerCaseName == "iso-8859-1")) {
			lowerCaseName = "windows-1252";
		}
		return (ZLEncodingConverterInfo)myInfosByName.get(lowerCaseName);
	}
	
	public	ZLEncodingConverterInfo info(int code) {
		String name = "" + code;
		return info(name);
	}
	
	public	ZLEncodingConverter defaultConverter() {
		return null;//DummyEncodingConverterProvider().createConverter();
	}
	public	void registerProvider(ZLEncodingConverterProvider provider) {
		myProviders.add(provider);
	}

	private void addInfo(ZLEncodingConverterInfo info) {
		
	}
	ArrayList/*<ZLEncodingConverterProvider>*/  providers() {
		return myProviders;
	}

	private final ArrayList/*<ZLEncodingSet>*/ mySets = new ArrayList();
	private	final HashMap/*<String,ZLEncodingConverterInfo>*/ myInfosByName = new HashMap();
	private	final ArrayList/*<ZLEncodingConverterProvider>*/  myProviders = new ArrayList();

	//private ZLEncodingCollection();
	private	void init() {
		if (mySets.isEmpty()) {
			String prefix = encodingDescriptionPath() + File.separator;
			//new ZLEncodingCollectionReader(this).readDocument(prefix + "Encodings.xml");
		}
	}
}